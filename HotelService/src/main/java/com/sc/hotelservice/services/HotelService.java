package com.sc.hotelservice.services;

import com.sc.hotelservice.entities.Hotel;
import com.sc.hotelservice.entities.Rating;
import com.sc.hotelservice.exceptions.ResourceNotFoundException;
import com.sc.hotelservice.external.services.IRatingService;
import com.sc.hotelservice.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelService implements IHotelService {

    private final HotelRepository hotelRepository;
    private final IRatingService ratingService;

    @Autowired
    public HotelService(HotelRepository hotelRepository, IRatingService ratingService) {
        this.hotelRepository = hotelRepository;
        this.ratingService = ratingService;
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        String randomHotelId = UUID.randomUUID().toString();
        hotel.setHotelId(randomHotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        List<Hotel> hotelList = hotelRepository.findAll();

        return hotelList.stream().peek(hotel -> {
            Hotel hotelObj = hotelRepository.findById(hotel.getHotelId())
                .orElseThrow(() -> new ResourceNotFoundException("Hotel id [" + hotel.getHotelId() + "] invalid."));
            List<Rating> ratings = ratingService.getAllRatings(hotelObj.getHotelId());

            hotelObj.setRatings(ratings);
        }).toList();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        Hotel hotelObj = hotelRepository.findById(hotelId)
            .orElseThrow(() -> new ResourceNotFoundException("Hotel id [" + hotelId + "] invalid."));
        List<Rating> ratings = ratingService.getAllRatings(hotelObj.getHotelId());

        hotelObj.setRatings(ratings);
        return hotelObj;
    }
}

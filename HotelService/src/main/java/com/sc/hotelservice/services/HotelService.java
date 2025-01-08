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
    public List<Hotel> getAllHotels(boolean includeUsers) {
        final List<Hotel> hotelList = hotelRepository.findAll();

        for (Hotel hotel : hotelList) {
            List<Rating> ratingList = getRatings(hotel.getHotelId(), includeUsers);
            hotel.setRatings(ratingList);
        }
        return hotelList;
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        Hotel hotelObj = hotelRepository.findById(hotelId)
            .orElseThrow(() -> new ResourceNotFoundException("Hotel id [" + hotelId + "] invalid."));
        List<Rating> ratings = ratingService.getRatingsByHotelId(hotelObj.getHotelId());

        hotelObj.setRatings(ratings);
        return hotelObj;
    }
}

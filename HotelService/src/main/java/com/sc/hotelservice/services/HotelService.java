package com.sc.hotelservice.services;

import com.sc.hotelservice.entities.Hotel;
import com.sc.hotelservice.entities.MUser;
import com.sc.hotelservice.entities.Rating;
import com.sc.hotelservice.exceptions.ResourceNotFoundException;
import com.sc.hotelservice.external.services.IRatingService;
import com.sc.hotelservice.external.services.IUserService;
import com.sc.hotelservice.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelService implements IHotelService {

    private final HotelRepository hotelRepository;
    private final IRatingService ratingService;
    private final IUserService userService;

    @Autowired
    public HotelService(
        HotelRepository hotelRepository, IRatingService ratingService, IUserService userService) {
        this.hotelRepository = hotelRepository;
        this.ratingService = ratingService;
        this.userService = userService;
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
        List<Rating> ratings = getRatings(hotelObj.getHotelId());

        hotelObj.setRatings(ratings);
        return hotelObj;
    }

    private List<Rating> getRatings(String hotelId) {
        final List<Rating> ratingList = ratingService.getRatingsByHotelId(hotelId);

        for (Rating rating : ratingList) {
            MUser userobj = userService.getUserById(rating.getUserId(), false);
            rating.setUser(userobj);
        }
        return ratingList;
    }

    private List<Rating> getRatings(String hotelId, boolean includeUsers) {
        final List<Rating> ratingList = ratingService.getRatingsByHotelId(hotelId);

        if (includeUsers) {
            for (Rating rating : ratingList) {
                MUser userobj = userService.getUserById(rating.getUserId(), false);
                rating.setUser(userobj);
            }
        }
        return ratingList;
    }
}

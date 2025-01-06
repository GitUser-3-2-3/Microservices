package com.sc.ratingservice.services;

import com.sc.ratingservice.entities.Rating;

import java.util.List;

public interface IRatingService {

    Rating createRating(Rating hotel);

    List<Rating> getAllRatings();

    List<Rating> getRatingsByUserId(String userId);

    List<Rating> getRatingsByHotelId(String hotelId);
}

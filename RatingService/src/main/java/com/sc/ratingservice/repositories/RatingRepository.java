package com.sc.ratingservice.repositories;

import com.sc.ratingservice.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {

    List<Rating> findRatingsByHotelId(String hotelId);

    List<Rating> findRatingsByUserId(String userId);
}

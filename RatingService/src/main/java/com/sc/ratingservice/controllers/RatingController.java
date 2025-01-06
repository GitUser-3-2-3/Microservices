package com.sc.ratingservice.controllers;

import com.sc.ratingservice.entities.Rating;
import com.sc.ratingservice.services.IRatingService;
import com.sc.ratingservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final IRatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        Rating mUser = ratingService.createRating(rating);
        return ResponseEntity.status(CREATED).body(mUser);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> userList = ratingService.getAllRatings();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        List<Rating> ratingsByUserId = ratingService.getRatingsByUserId(userId);
        return ResponseEntity.ok(ratingsByUserId);
    }

    @GetMapping("hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
        List<Rating> ratingsByHotelId = ratingService.getRatingsByHotelId(hotelId);
        return ResponseEntity.ok(ratingsByHotelId);
    }
}

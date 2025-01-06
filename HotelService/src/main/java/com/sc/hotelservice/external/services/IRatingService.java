package com.sc.hotelservice.external.services;

import com.sc.hotelservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface IRatingService {

    @GetMapping("/ratings/hotels/{hotelId}")
    List<Rating> getAllRatings(@PathVariable String hotelId);
}

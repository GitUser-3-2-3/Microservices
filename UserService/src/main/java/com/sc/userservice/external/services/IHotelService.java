package com.sc.userservice.external.services;

import com.sc.userservice.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface IHotelService {

    @GetMapping("/hotels/{hotelId}")
    Hotel getHotelById(@PathVariable String hotelId);
}

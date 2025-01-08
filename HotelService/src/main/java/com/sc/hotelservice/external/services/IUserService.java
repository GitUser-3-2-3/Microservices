package com.sc.hotelservice.external.services;

import com.sc.hotelservice.entities.MUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface IUserService {

    @GetMapping("/users/{userId}")
    MUser getUserById(@PathVariable String userId, boolean includeHotel);
}

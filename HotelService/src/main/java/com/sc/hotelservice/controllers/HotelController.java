package com.sc.hotelservice.controllers;

import com.sc.hotelservice.entities.Hotel;
import com.sc.hotelservice.services.HotelService;
import com.sc.hotelservice.services.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final IHotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        Hotel mUser = hotelService.createHotel(hotel);
        return ResponseEntity.status(CREATED).body(mUser);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId) {
        Hotel user = hotelService.getHotel(hotelId);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> userList = hotelService.getAllHotels();
        return ResponseEntity.ok(userList);
    }
}

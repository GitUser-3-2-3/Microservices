package com.sc.hotelservice.services;

import com.sc.hotelservice.entities.Hotel;

import java.util.List;

public interface IHotelService {

    Hotel createHotel(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getHotel(String hotelId);
}

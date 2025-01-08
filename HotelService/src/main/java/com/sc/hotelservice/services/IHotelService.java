package com.sc.hotelservice.services;

import com.sc.hotelservice.entities.Hotel;

import java.util.List;

public interface IHotelService {

    Hotel createHotel(Hotel hotel);

    List<Hotel> getAllHotels(boolean includeUsers);

    Hotel getHotelById(String hotelId);
}

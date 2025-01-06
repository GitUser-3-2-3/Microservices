package com.sc.userservice.services;

import com.sc.userservice.entities.Hotel;
import com.sc.userservice.entities.MUser;
import com.sc.userservice.entities.Rating;
import com.sc.userservice.exceptions.ResourceNotFoundException;
import com.sc.userservice.repositories.MUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class MUserService implements IMUserService {

    private final Logger logger = LoggerFactory.getLogger(MUserService.class);

    private final MUserRepository userRepository;
    private final RestTemplate restTemplate;
    private final String ratingServiceUrl;
    private final String hotelServiceUrl;

    @Autowired
    public MUserService(MUserRepository userRepository, RestTemplate restTemplate,
                        @Value("${ratings.service.url}") String ratingServiceUrl,
                        @Value("${hotel.service.url}") String hotelServiceUrl) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.ratingServiceUrl = ratingServiceUrl;
        this.hotelServiceUrl = hotelServiceUrl;
    }

    @Override
    public MUser saveUser(MUser user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<MUser> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public MUser getUserById(String userId) {
        MUser userObj = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User id " + userId + " invalid."));
        List<Rating> response = getRatings(userObj);

        userObj.setRatings(response);
        return userObj;
    }

    private List<Rating> getRatings(MUser userObject) {
        Rating[] ratings = restTemplate
            .getForObject(ratingServiceUrl + userObject.getUserId(), Rating[].class);
        assert ratings != null;

        List<Rating> ratingList = Arrays.stream(ratings).toList();

        return ratingList.stream().peek(rating -> {
            Hotel hotel = restTemplate.getForObject(hotelServiceUrl + rating.getHotelId(), Hotel.class);
            rating.setHotel(hotel);
            logger.info("Hotel set for rating::{}", rating);
        }).toList();
    }
}

package com.sc.userservice.services;

import com.sc.userservice.entities.Hotel;
import com.sc.userservice.entities.MUser;
import com.sc.userservice.entities.Rating;
import com.sc.userservice.exceptions.ResourceNotFoundException;
import com.sc.userservice.external.services.IHotelService;
import com.sc.userservice.external.services.IRatingService;
import com.sc.userservice.repositories.MUserRepository;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class MUserService implements IMUserService {

    private final MUserRepository userRepository;
    private final IRatingService ratingService;
    private final IHotelService hotelService;

    @Autowired
    public MUserService(
        MUserRepository userRepository, IRatingService ratingService, IHotelService hotelService) {
        this.userRepository = userRepository;
        this.ratingService = ratingService;
        this.hotelService = hotelService;
    }

    @Override
    public MUser saveUser(MUser user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    int count = 0;

    /*
    @Retry(name = "getAllUser", fallbackMethod = "getAllUserFallback")
    @CircuitBreaker(name = "getAllUser", fallbackMethod = "getAllUserFallback")
     */
    @Override
    @RateLimiter(name = "getAllUser", fallbackMethod = "getAllUserFallback")
    public List<MUser> getAllUser() {
        final List<MUser> userList = userRepository.findAll();

        log.error("RATE LIMITER PASSED::{}", ++count);

        for (MUser userobj : userList) {
            List<Rating> ratingList = getRatings(userobj.getUserId());
            userobj.setRatings(ratingList);
        }
        /*
        return userList.stream().peek(user -> {
            MUser userObj = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserId() + " invalid."));
            List<Rating> response = getRatings(userObj);

            userObj.setRatings(response);
        }).toList();
         */
        return userList;
    }

    /*
    @Retry(name = "getAllUser", fallbackMethod = "getAllUserFallback")
    @CircuitBreaker(name = "getAllUser", fallbackMethod = "getAllUserFallback")
     */
    @Override
    @RateLimiter(name = "getUserById", fallbackMethod = "getUserByIdFallback")
    public MUser getUserById(String userId, boolean includeHotel) {
        MUser userObj = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User id " + userId + " invalid."));

        List<Rating> response = getRatings(userObj.getUserId(), includeHotel);
        userObj.setRatings(response);
        return userObj;
    }

    private List<Rating> getRatings(final String userId) {
        final List<Rating> ratingList = ratingService.getRatingsByUserId(userId);

        for (Rating rating : ratingList) {
            Hotel hotel = hotelService.getHotelById(rating.getHotelId());
            rating.setHotel(hotel);
        }
        return ratingList;
    }

    private List<Rating> getRatings(final String userId, boolean includeHotel) {
        final List<Rating> ratingList = ratingService.getRatingsByUserId(userId);

        if (includeHotel) {
            for (Rating rating : ratingList) {
                Hotel hotel = hotelService.getHotelById(rating.getHotelId());
                rating.setHotel(hotel);
            }
        }
        /*
        Rating[] ratings = restTemplate
            .getForObject(ratingServiceUrl + userObject.getUserId(), Rating[].class);
        assert ratings != null;

        List<Rating> ratingList = Arrays.stream(ratings).toList();

        return ratingList.stream().peek(rating -> {
            Hotel hotel = restTemplate.getForObject(hotelServiceUrl + rating.getHotelId(), Hotel.class);
            rating.setHotel(hotel);
            logger.info("Hotel set for rating::\n{}", rating);
        }).toList();
         */
        return ratingList;
    }

    // Fallback method for circuit breaker

    @SuppressWarnings("unused")
    public List<MUser> getAllUserFallback(Throwable rtExp) {
        log.warn("Executed::getAllUserFallback, Service Down::{}", String.valueOf(rtExp));
        return List.of();
    }

    @SuppressWarnings("unused")
    public MUser getUserByIdFallback(Throwable rtExp) {
        log.warn("Executed::getUserByIdFallback, Service Down::{}", String.valueOf(rtExp));
        return new MUser();
    }
}

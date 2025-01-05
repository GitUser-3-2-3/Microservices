package com.sc.userservice.services;

import com.sc.userservice.entities.MUser;
import com.sc.userservice.exceptions.ResourceNotFoundException;
import com.sc.userservice.repositories.MUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MUserService implements IMUserService {

    private final MUserRepository userRepository;

    @Autowired
    public MUserService(MUserRepository userRepository) {
        this.userRepository = userRepository;
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
    public MUser getUser(String userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User with given id::" + userId + " doesn't exist."));
    }
}

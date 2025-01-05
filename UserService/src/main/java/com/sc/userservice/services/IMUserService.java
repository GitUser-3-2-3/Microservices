package com.sc.userservice.services;

import com.sc.userservice.entities.MUser;

import java.util.List;

public interface IMUserService {

    MUser saveUser(MUser user);

    List<MUser> getAllUser();

    MUser getUser(String userId);
}

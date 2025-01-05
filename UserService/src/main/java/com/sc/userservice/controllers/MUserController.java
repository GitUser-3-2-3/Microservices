package com.sc.userservice.controllers;

import com.sc.userservice.entities.MUser;
import com.sc.userservice.services.MUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/users")
public class MUserController {

    private final MUserService userService;

    @Autowired
    public MUserController(MUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<MUser> createUser(@RequestBody MUser user) {
        MUser mUser = userService.saveUser(user);
        return ResponseEntity.status(CREATED).body(mUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<MUser> getUser(@PathVariable String userId) {
        MUser user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<MUser>> getAllUsers() {
        List<MUser> userList = userService.getAllUser();
        return ResponseEntity.ok(userList);
    }
}

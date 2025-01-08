package com.sc.userservice.controllers;

import com.sc.userservice.entities.MUser;
import com.sc.userservice.services.IMUserService;
import com.sc.userservice.services.MUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/users")
public class MUserController {

    private final IMUserService userService;

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
    public ResponseEntity<MUser> getUser(
        @PathVariable String userId, @RequestParam(defaultValue = "false") boolean includeHotel) {
        MUser user = userService.getUserById(userId, includeHotel);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<MUser>> getAllUsers(
        @RequestParam(defaultValue = "false") boolean includeHotel) {
        List<MUser> userList = userService.getAllUser(includeHotel);
        return ResponseEntity.ok(userList);
    }
}

package com.serialization.controller;

import com.serialization.model.User;
import com.serialization.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService service;

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> findUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(new Long(1),"juan puerto 8081"));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{name}")
    public User findByName(@RequestParam String name) {
        return service.findByName(name);
    }

    @PutMapping("/user/{id}")
    public User updateUserById(@PathVariable Long id, @RequestBody  User user) {
        return service.updateUserById(user, id);
    }

}

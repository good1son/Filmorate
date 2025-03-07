package com.example.filmorate.controller;

import com.example.filmorate.storage.model.User;
import com.example.filmorate.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable int id) {
        return userService.findUserById(id);
    }

    @GetMapping
    public Collection<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/add")
    public void addUser(@Valid @RequestBody User user) {
        userService.addUser(user);
    }

    @PatchMapping("update/{id}")
    public void updateUser(@PathVariable int id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @DeleteMapping("delete/{id}")
    public void deleteFilm(@PathVariable int id) {
        userService.deleteUserById(id);
    }
}

package com.example.filmorate.controller;

import com.example.filmorate.service.SubsService;
import com.example.filmorate.storage.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SubsController {
    private final SubsService subsService;

    @GetMapping("/{id}/subscribers")
    public Collection<User> getSubscribers(@PathVariable int id) {
        return subsService.getSubscribers(id);
    }

    @GetMapping("/{id}/subscribers/common/{otherId}")
    public Collection<User> commonSubscribers(@PathVariable int id, @PathVariable int otherId) {
        return subsService.commonSubscribers(id, otherId);
    }

    @PutMapping("/{id}/subscribe/{subsId}")
    public void addSubscribe(@PathVariable int id, @PathVariable int subsId) {
        subsService.addSubscribe(id, subsId);
    }

    @DeleteMapping("/{id}/subscribe/{subsId}")
    public void deleteSubscribe(@PathVariable int id, @PathVariable int subsId) {
        subsService.deleteSubscribe(id, subsId);
    }
}

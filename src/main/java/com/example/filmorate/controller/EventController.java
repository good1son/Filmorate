package com.example.filmorate.controller;

import com.example.filmorate.service.EventService;
import com.example.filmorate.storage.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EventController {
    private final EventService eventService;

    @GetMapping("/feed")
    public Collection<Event> getEventsFeed() {
        return eventService.getEventsFeed();
    }

    @GetMapping("/{id}/feed")
    public Collection<Event> getUserEvents(@PathVariable int id) {
        return eventService.getUserEvents(id);
    }

    @GetMapping("/users/{id}/feed")
    public Collection<Event> getUserFeed(@PathVariable int id) {
        return eventService.getUserFeed(id);
    }
}

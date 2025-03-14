package com.example.filmorate.service;

import com.example.filmorate.storage.dao.impl.EventDbStorage;
import com.example.filmorate.storage.model.Event;
import com.example.filmorate.storage.model.type.ACTION;
import com.example.filmorate.storage.model.type.TARGET;
import com.example.filmorate.storage.util.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EventService {
    private final EventDbStorage eventDbStorage;

    public void createEvent(Integer userId, ACTION actionType, Integer targetId, TARGET targetType) {
        eventDbStorage.createEvent(userId, actionType, targetId, targetType);
    }

    public Collection<Event> getEventsFeed() {
        return eventDbStorage.getEventsFeed();
    }

    public Collection<Event> getUserEvents(Integer userId) {
        return eventDbStorage.getUserEvents(userId);
    }
}

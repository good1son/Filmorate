package com.example.filmorate.storage.dao;

import com.example.filmorate.storage.model.Event;
import com.example.filmorate.storage.model.type.ACTION;
import com.example.filmorate.storage.model.type.TARGET;

import java.util.Collection;

public interface EventStorage {
    public void createEvent(Integer userId, ACTION actionType, Integer targetId,TARGET targetType);
    public Collection<Event> getEventsFeed();
    public Collection<Event> getUserEvents(Integer id);
    public Collection<Event> getUsersEvents(Collection<Integer> usersId);
}

package com.example.filmorate.service;

import com.example.filmorate.storage.dao.impl.EventDbStorage;
import com.example.filmorate.storage.dao.impl.FriendshipDbStorage;
import com.example.filmorate.storage.model.Event;
import com.example.filmorate.storage.model.type.ACTION;
import com.example.filmorate.storage.model.type.TARGET;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EventService {
    private final EventDbStorage eventDbStorage;
    private final FriendshipDbStorage friendshipDbStorage;

    public void createEvent(Integer userId, ACTION actionType, Integer targetId, TARGET targetType) {
        eventDbStorage.createEvent(userId, actionType, targetId, targetType);
    }

    public Collection<Event> getEventsFeed() {
        return eventDbStorage.getEventsFeed();
    }

    public Collection<Event> getUserEvents(Integer id) {
        return eventDbStorage.getUserEvents(id);
    }

    public Collection<Event> getUserFeed(Integer id) {
        List<Integer> friendsIdList = friendshipDbStorage.getFriendsIdList(id);
        return eventDbStorage.getUsersEvents(friendsIdList);
    }
}

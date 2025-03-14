package com.example.filmorate.storage.dao.impl;

import com.example.filmorate.storage.dao.EventStorage;
import com.example.filmorate.storage.model.Event;
import com.example.filmorate.storage.model.type.ACTION;
import com.example.filmorate.storage.model.type.TARGET;
import com.example.filmorate.storage.util.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EventDbStorage implements EventStorage {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void createEvent(Integer userId, ACTION actionType, Integer targetId, TARGET targetType) {
        String sql = "INSERT INTO events (user_id, action, target_id, target_type) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, userId, actionType.name(), targetId, targetType.name());
    }

    @Override
    public Collection<Event> getEventsFeed() {
        String sql = "SELECT * FROM events ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, new EventMapper());
    }

    @Override
    public Collection<Event> getUserEvents(Integer userId) {
        String sql = "SELECT * FROM events WHERE user_id = ? ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, new EventMapper(), userId);
    }
}

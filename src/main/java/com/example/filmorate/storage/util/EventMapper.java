package com.example.filmorate.storage.util;

import com.example.filmorate.storage.model.Event;
import com.example.filmorate.storage.model.type.ACTION;
import com.example.filmorate.storage.model.type.TARGET;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper implements RowMapper<Event> {
    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        Event event = new Event();
        event.setId(rs.getInt("id"));
        event.setUserId(rs.getInt("user_id"));
        event.setActionType(ACTION.valueOf(rs.getString("action")));
        event.setTargetId(rs.getInt("target_id"));
        event.setTargetType(TARGET.valueOf(rs.getString("target_type")));
        event.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return event;
    }
}

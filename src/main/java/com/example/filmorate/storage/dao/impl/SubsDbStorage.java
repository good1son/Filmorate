package com.example.filmorate.storage.dao.impl;

import com.example.filmorate.storage.dao.SubsStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SubsDbStorage implements SubsStorage {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addSubscribe(int id, int subsId) {
        String sql = "INSERT INTO subs (user_id, subscriber_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, id, subsId);
    }

    @Override
    public Collection<Integer> getSubscribers(int id) {
        String sql = "SELECT subscriber_id FROM subs WHERE user_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("subscriber_id"), id);
    }

    @Override
    public Collection<Integer> commonSubscribers(int id, int otherId) {
        String sql = "SELECT s1.subscriber_id FROM subs s1 " +
                "INNER JOIN subs s2 ON s1.subscriber_id = s2.subscriber_id " +
                "WHERE s1.user_id = ? AND s2.user_id = ?";
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> rs.getInt("subscriber_id"), id, otherId);
    }

    @Override
    public void deleteSubscribe(int id, int subsId) {
        String sql = "DELETE FROM subs WHERE user_id = ? AND subscriber_id = ?";
        jdbcTemplate.update(sql, id, subsId);
    }
}

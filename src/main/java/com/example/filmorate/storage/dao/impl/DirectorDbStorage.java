package com.example.filmorate.storage.dao.impl;

import com.example.filmorate.storage.dao.DirectorStorage;
import com.example.filmorate.storage.model.Director;
import com.example.filmorate.storage.util.DirectorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DirectorDbStorage implements DirectorStorage {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Director> getDirector(Integer id) {
        String sql = "SELECT name FROM directors WHERE id = ?";
        try {
            Director director = jdbcTemplate.queryForObject(sql, new DirectorMapper(), id);
            return Optional.ofNullable(director);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Collection<Director> getAllDirectors() {
        String sql = "SELECT * FROM directors";
        return jdbcTemplate.query(sql, new DirectorMapper());
    }
}

package com.example.filmorate.storage.dao.impl;

import com.example.filmorate.storage.dao.MpaStorage;
import com.example.filmorate.storage.model.type.MPA;
import com.example.filmorate.storage.util.MpaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MpaDbStorage implements MpaStorage {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<MPA> getMpa(int id) {
        String sql = "SELECT * FROM mpa WHERE id = ?";
        try {
            MPA mpa = jdbcTemplate.queryForObject(sql, new MpaMapper(), id);
            return Optional.ofNullable(mpa);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Integer getMpaId(MPA mpa) {
        String sql = "SELECT id FROM mpa WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> rs.getInt("id"), mpa.toString());
    }

    @Override
    public Collection<MPA> getAllMpa() {
        String sql = "SELECT * FROM mpa";
        return jdbcTemplate.query(sql, new MpaMapper());
    }
}

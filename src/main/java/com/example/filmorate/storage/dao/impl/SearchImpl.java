package com.example.filmorate.storage.dao.impl;

import com.example.filmorate.storage.dao.SearchFilm;
import com.example.filmorate.storage.model.type.GENRE;
import com.example.filmorate.storage.model.type.MPA;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.util.Collection;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SearchImpl implements SearchFilm {
    private final JdbcTemplate jdbcTemplate;
    private final FilmDbStorage filmDbStorage;
    private final GenreDbStorage genreDbStorage;

    @Override
    public Collection<Integer> searchFilms(String name, GENRE genre, String year, MPA mpa) {
        String sql = "SELECT id FROM films " +
                "WHERE (name IS NULL OR LOWER(name) = ?)";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("id"), name.toLowerCase());
    }
}

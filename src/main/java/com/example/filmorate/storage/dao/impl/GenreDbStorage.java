package com.example.filmorate.storage.dao.impl;

import com.example.filmorate.storage.dao.GenreStorage;
import com.example.filmorate.storage.model.type.GENRE;
import com.example.filmorate.storage.util.GenreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class GenreDbStorage implements GenreStorage {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<GENRE> getGenre(int id) {
        String sql = "SELECT genre FROM genre_list WHERE id = ?";
        try {
            GENRE genre = jdbcTemplate.queryForObject(sql, new GenreMapper(), id);
            return Optional.ofNullable(genre);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Collection<GENRE> getGenres() {
        String sql = "SELECT * FROM genre_list";
        return jdbcTemplate.query(sql, new GenreMapper());
    }

    @Override
    public Collection<GENRE> getFilmGenres(int id) {
        String sql = "SELECT gl.genre FROM genre_list gl JOIN genres g ON gl.id = g.genre_id " +
                "JOIN films f ON g.film_id = f.id WHERE f.id =?";
//        String sql = "SELECT genre FROM genre_list WHERE id IN " +
//                "(SELECT genre_id FROM genres WHERE film_id = ?";
        return jdbcTemplate.query(sql, new GenreMapper(), id);
    }

    @Override
    public Integer getGenreId(GENRE genre) {
        String sql = "SELECT id FROM genre_list WHERE genre = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> rs.getInt("id"), genre.toString());
    }

    @Override
    public void addGenre(int id, Collection<Integer> genreId) {
        for (Integer genre: genreId) {
            String sql = "INSERT INTO genres (film_id, genre_id) VALUES (?, ?)";
            jdbcTemplate.update(sql, id, genre);
        }
    }
}

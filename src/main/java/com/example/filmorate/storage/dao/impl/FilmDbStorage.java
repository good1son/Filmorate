package com.example.filmorate.storage.dao.impl;

import com.example.filmorate.storage.dao.FilmStorage;
import com.example.filmorate.storage.model.Film;
import com.example.filmorate.storage.util.FilmMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.Optional;


@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FilmDbStorage implements FilmStorage {
    private static final Logger log = LoggerFactory.getLogger(FilmDbStorage.class);
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {

    }

    @Override
    public void add(Film film) {
        String sql = "INSERT INTO films (name, description, release_date, duration, mpa_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                film.getName(), film.getDescription(), film.getReleaseDate(),
                film.getDuration(), film.getMpaId());
    }

    @Override
    public Optional<Film> get(int id) {
        String sql = "SELECT * FROM films WHERE id = ?";
        try {
            Film film = jdbcTemplate.queryForObject(sql, new FilmMapper(), id);
            return Optional.ofNullable(film);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Integer> getFilmIdByName(String name) {
        String sql = "SELECT id FROM films WHERE name =?";
        try {
            Integer id = jdbcTemplate.queryForObject(sql,
                    (rs, rowNum) -> rs.getInt("id"),
                    name);
            return Optional.ofNullable(id);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Collection<Film> getFilms() {
        String sql = "SELECT * FROM films";
        return jdbcTemplate.query(sql, new FilmMapper());
    }

    @Override
    public void putLike(int id) {
        String sql = "UPDATE films SET likes = likes + 1 WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void removeLike(int id) {
        String sql = "UPDATE films SET likes = likes - 1 WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Collection<Integer> getPopular(int count) {
        String sql = "SELECT id FROM films ORDER BY likes DESC LIMIT(?)";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("id"), count);
    }

    @Override
    public void update(Film film) {
        String sql = "UPDATE films SET name = ?, description = ?, release_date = ?, duration = ?, " +
                "mpa_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, film.getName(), film.getDescription(), film.getReleaseDate(), film.getDuration(),
                film.getMpaId(), film.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM films WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

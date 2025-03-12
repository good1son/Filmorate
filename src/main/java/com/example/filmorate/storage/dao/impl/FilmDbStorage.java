package com.example.filmorate.storage.dao.impl;

import com.example.filmorate.exception.AlreadyExistsException;
import com.example.filmorate.storage.dao.FilmStorage;
import com.example.filmorate.storage.model.Film;
import com.example.filmorate.storage.util.FilmMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
    public void rateFilm(int filmId, int userId, int rating) {
        try {
            String sql = "INSERT INTO film_ratings (film_id, user_id, rating) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, filmId, userId, rating);
        } catch (DuplicateKeyException e) {
            throw new AlreadyExistsException("Вы уже оставляли оценку данному фильму");
        }
        updateFilmRating(filmId);
    }

    @Override
    public void reRateFilm(int filmId, int userId, int rating) {
        String sql = "UPDATE film_ratings SET rating = ? WHERE film_id = ? AND user_id = ?";
        jdbcTemplate.update(sql, rating, filmId, userId);
        updateFilmRating(filmId);
    }

    @Override
    public void deleteRate(int filmId, int userId) {
        String sql = "DELETE FROM film_ratings WHERE film_id = ? AND user_id = ?";
        jdbcTemplate.update(sql, filmId, userId);
        updateFilmRating(filmId);
    }

    @Override
    public Collection<Film> searchFilms(Float minRating, Float maxRating, Integer yearA, Integer yearB,
                                        List<Integer> genresId, List<Integer> mpaId, Integer count,
                                        String order, String sort) {
        String sql = "SELECT * FROM films WHERE id IS NOT NULL" +
                createSearchingSql(minRating, maxRating, yearA, yearB, genresId, mpaId, count, order, sort);
        Object[] params = getSqlParam(minRating, maxRating, yearA, yearB, genresId, mpaId, count);
        return jdbcTemplate.query(sql, new FilmMapper(), params);
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

    private void updateFilmRating(int filmId) {
        String sql = "UPDATE films SET rating = " +
                "(SELECT AVG(rating) FROM film_ratings WHERE film_id = ?) " +
                "WHERE id = ?";
        jdbcTemplate.update(sql, filmId, filmId);
    }

    private String createSearchingSql(Float minRating, Float maxRating, Integer yearA, Integer yearB,
                                      List<Integer> genresId, List<Integer> mpaId, Integer count, String order,
                                      String sort) {
        StringBuilder sql = new StringBuilder();
        addSearchRating(minRating,maxRating, sql);
        addSearchYear(yearA, yearB, sql);
        if (!genresId.isEmpty())
            addSearchGenre(genresId, sql);
        if (!mpaId.isEmpty())
            addSearchMpa(mpaId, sql);
        addLimitOrder(count, order, sort, sql);
        return sql.toString();
    }

    private Object[] getSqlParam(Float minRating, Float maxRating, Integer yearA, Integer yearB,
                                          List<Integer> genresId, List<Integer> mpaId, Integer count) {
        List<Object> params = new ArrayList<>();
        if (minRating != null)
            params.add(minRating);
        if (maxRating != null)
            params.add(maxRating);
        if (yearA != null)
            params.add(yearA);
        if (yearB != null)
            params.add(yearB);
        if (!genresId.isEmpty())
            params.addAll(genresId);
        if (!mpaId.isEmpty())
            params.addAll(mpaId);
        if (count != null)
            params.add(count);
        return params.toArray();
    }

    private void addSearchRating(Float minRating, Float maxRating, StringBuilder sql) {
        if (minRating != null)
            sql.append(" AND rating >= ?");
        if (maxRating != null)
            sql.append(" AND rating <= ?");

    }

    private void addSearchYear(Integer yearA, Integer yearB, StringBuilder sql) {
        if (yearA != null)
            sql.append(" AND YEAR(release_date) >= ?");
        if (yearB != null)
            sql.append(" AND YEAR(release_date) <= ?");
    }

    private void addSearchGenre(List<Integer> genresId, StringBuilder sql) {
        sql.append(" AND id IN (SELECT film_id FROM genres WHERE (genre_id = ?");
        sql.append(" OR genre_id = ?".repeat(Math.max(0, genresId.size() - 1)));
        sql.append("))");
    }

    private void addSearchMpa(List<Integer> mpaId, StringBuilder sql) {
        sql.append(" AND (mpa_id = ?");
        sql.append(" OR mpa_id = ?".repeat(Math.max(0, mpaId.size() - 1)));
        sql.append(")");
    }

    private void addLimitOrder(Integer count, String order, String sort, StringBuilder sql) {
        sql.append(String.format(" ORDER BY %s %s", order, sort));
        if (count != null)
            sql.append(" LIMIT ?");
    }
}

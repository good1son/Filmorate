package com.example.filmorate.service;

import com.example.filmorate.exception.NotFoundException;
import com.example.filmorate.storage.model.Film;
import com.example.filmorate.storage.dao.impl.FilmDbStorage;
import com.example.filmorate.storage.model.type.GENRE;
import com.example.filmorate.storage.model.type.MPA;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FilmService {
    private final FilmDbStorage filmDbStorage;
    private final GenreService genreService;
    private final MpaService mpaService;

    public void addFilm(Film film) {
        filmDbStorage.add(film);
        int id = filmDbStorage.getFilmIdByName(film.getName()).get();
        genreService.addGenre(id, film.getGenres());
    }

    public Film findFilmById(int id) {
        if (!filmDbStorage.filmExists(id))
            throw new NotFoundException("Фильм с указанным ID не найден");
        Film film = filmDbStorage.get(id);
        Set<GENRE> genres = new HashSet<>(genreService.getFilmGenres(id));
        film.setGenres(genres);
        return film;
    }

    private Optional<Integer> findFilmByName(String name) {
        return filmDbStorage.getFilmIdByName(name);
    }

    public Collection<Film> getFilms() {
        Collection<Film> films = filmDbStorage.getFilms();
        if (films.isEmpty())
            throw new NotFoundException("Фильмы не найдены");
        return films;
    }

    public Collection<Film> getFilms(Collection<Integer> idList) {
        return idList.stream()
                .map(this::findFilmById)
                .toList();
    }

    public void updateFilm(int id, Film film) {
        Film updatedFilm = findFilmById(id);
        if (film.getName() != null)
            updatedFilm.setName(film.getName());
        if (film.getDescription() != null)
            updatedFilm.setDescription(film.getDescription());
        if (film.getReleaseDate() != null)
            updatedFilm.setReleaseDate(film.getReleaseDate());
//        if (film.getGenreId() != 0)
//            updatedFilm.setGenreId(film.getGenreId());
        if (film.getDuration() != 0)
            updatedFilm.setDuration(film.getDuration());
        if (film.getMpaId() != 0)
            updatedFilm.setMpaId(film.getMpaId());
        filmDbStorage.update(updatedFilm);
    }

    public void deleteFilmById(int id) {
        filmDbStorage.delete(id);
    }

    public Collection<Film> getPopular(Float minRating, Float maxRating, Integer yearA, Integer yearB,
                                       List<String> directors, List<String> genre, List<String> mpa,
                                       Integer count, String sort) {
        return searchFilms(minRating, maxRating, yearA, yearB, directors, genre, mpa, count, "rating", sort);
    }

    public Collection<Film> searchFilms(Float minRating, Float maxRating, Integer yearA, Integer yearB,
                                        List<String> directors, List<String> genre, List<String> mpa,
                                        Integer count, String order, String sort) {
        List<Integer> mpaId = getMpaIdList(mpa);
        List<Integer> genresId = getGenreIdList(genre);

        Collection<Film> findFilms = filmDbStorage.searchFilms(minRating, maxRating, yearA, yearB, directors,
                genresId, mpaId, count, order, sort);

        for (Film film : findFilms)
            film.setGenres(new HashSet<>(genreService.getFilmGenres(film.getId())));
        return findFilms;
    }

    private List<Integer> getMpaIdList(List<String> mpa) {
        List<Integer> mpaId = new ArrayList<>();
        if (mpa != null && !mpa.isEmpty())
            return mpa.stream()
                    .map(x -> MPA.valueOf(x.toUpperCase()))
                    .map(mpaService::getMpaId)
                    .toList();
        return mpaId;
    }

    private List<Integer> getGenreIdList(List<String> genre) {
        List<Integer> genresId = new ArrayList<>();
        if (genre != null && !genre.isEmpty())
            genresId = genre.stream()
                    .map(x -> GENRE.valueOf(x.toUpperCase()))
                    .map(genreService::getGenreId)
                    .toList();
        return genresId;
    }
}

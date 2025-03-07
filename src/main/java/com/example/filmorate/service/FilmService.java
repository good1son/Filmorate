package com.example.filmorate.service;

import com.example.filmorate.exception.NotFoundException;
import com.example.filmorate.storage.model.Film;
import com.example.filmorate.storage.dao.impl.FilmDbStorage;
import com.example.filmorate.storage.model.type.GENRE;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FilmService {
    private final FilmDbStorage filmDbStorage;
    private final GenreService genreService;

    public void addFilm(Film film) {
        filmDbStorage.add(film);
        int id = filmDbStorage.getFilmIdByName(film.getName()).get();
        genreService.addGenre(id, film.getGenres());
    }

    public Optional<Film> findFilmById(int id) {
        Optional<Film> film = filmDbStorage.get(id);
        if (film.isPresent()) {
            Set<GENRE> genres = new HashSet<>(genreService.getFilmGenres(id));
            film.get().setGenres(genres);
        }
        else throw  new NotFoundException("Фильм не найден");
        return film;
//        return Optional.of(filmDbStorage.get(id)
//                .orElseThrow(() -> new NotFoundException("Фильм не найден")));
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
                .flatMap(Optional::stream)
                .toList();
    }

    public void updateFilm(int id, Film film) {
        Film updatedFilm = findFilmById(id).get();
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

    public void putLike(int id) {
        filmDbStorage.putLike(id);
    }

    public void removeLike(int id) {
        filmDbStorage.removeLike(id);
    }

    public Collection<Film> getPopular(int count) {
        Collection<Integer> popularFilmsIdList = filmDbStorage.getPopular(count);
// ДОБАВИТЬ ДРУЖБУ, ЧАТ И АЗЕР ФИЧЕРС
        return popularFilmsIdList.stream()
                .map(this::findFilmById)
                .flatMap(Optional::stream)
                .toList();
    }

    /*
    public Film like(long id, long userId) {
        get(id).getLikes().add(userId);
        return get(id);
    }

    public Film dislike(long id, long userId) {
        get(id).getLikes().remove(userId);
        return get(id);
    }

    public List<Film> getPopular(int count) {
        if (count - 1 > filmStorage.get().size())
            count = filmStorage.get().size();

        return filmStorage.get().values().stream()
                .sorted(new LikeComparator())
                .limit(count)
                .toList();
    }

    public Film update(long id, Film film) {
        if (!filmStorage.get().containsKey(id))
            throw new FilmNotFound(String.format("Фильм с указанным ID: %d - не найден!", id));
        film.setId(id);
        return filmStorage.update(id, film);
    }

    public List<Film> show() {
        return filmStorage.get().values().stream().toList();
    }*/

}

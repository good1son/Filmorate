package com.example.filmorate.controller;

import com.example.filmorate.service.RateService;
import com.example.filmorate.storage.model.Film;
import com.example.filmorate.service.FilmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FilmController {
    private final FilmService filmService;
    private final RateService rateService;

    @GetMapping("/{id}")
    public Film getFilm(@PathVariable int id) {
        return filmService.findFilmById(id);
    }

    @GetMapping
    public Collection<Film> getFilms() {
        return filmService.getFilms();
    }

    @GetMapping("/popular")
    public Collection<Film> getPopular(@RequestParam(required = false) Float minRating,
                                       @RequestParam(required = false) Float maxRating,
                                       @RequestParam(required = false) Integer yearA,
                                       @RequestParam(required = false) Integer yearB,
                                       @RequestParam(required = false) List<String> directors,
                                       @RequestParam(required = false) List<String> genre,
                                       @RequestParam(required = false) List<String> mpa,
                                       @RequestParam(required = false) Integer count,
                                       @RequestParam(defaultValue = "desc") String sort) {
        return filmService.getPopular(minRating, maxRating, yearA, yearB, directors, genre, mpa, count, sort);
    }


    @GetMapping("/search")
    public Collection<Film> searchFilms(@RequestParam(required = false) Float minRating,
                                        @RequestParam(required = false) Float maxRating,
                                        @RequestParam(required = false) Integer yearA,
                                        @RequestParam(required = false) Integer yearB,
                                        @RequestParam(required = false) List<String> directors,
                                        @RequestParam(required = false) List<String> genre,
                                        @RequestParam(required = false) List<String> mpa,
                                        @RequestParam(required = false) Integer count,
                                        @RequestParam(defaultValue = "name") String order,
                                        @RequestParam(defaultValue = "asc") String sort) {
        return filmService.searchFilms(minRating, maxRating, yearA, yearB, directors, genre, mpa, count, order, sort);
    }

    @GetMapping("/{id}/subscribers/common/{otherId}")

    @PostMapping("/add")
    public void addFilm(@Valid @RequestBody Film film) {
        filmService.addFilm(film);
    }

    @PutMapping("{filmId}/rate/{userId}")
    public void rateFIlm(@PathVariable Integer filmId, @PathVariable Integer userId,
                         @RequestBody Map<String, Object> request) {
        rateService.rateFilm(filmId, userId, (Integer) request.get("rating"));
    }

    @PatchMapping("update/{id}")
    public void updateFilm(@PathVariable int id, @RequestBody Film film) {
        filmService.updateFilm(id, film);
    }

    @PatchMapping("{filmId}/rate/{userId}")
    public void reRateFIlm(@PathVariable Integer filmId, @PathVariable Integer userId,
                           @RequestBody Map<String, Object> request) {
        rateService.reRateFilm(filmId, userId, (Integer) request.get("rating"));
    }

    @DeleteMapping("delete/{id}")
    public void deleteFilm(@PathVariable int id) {
        filmService.deleteFilmById(id);
    }

    @DeleteMapping("{filmId}/rate/{userId}")
    public void deleteRate(@PathVariable Integer filmId, @PathVariable Integer userId) {
        rateService.deleteRate(filmId, userId);
    }
}

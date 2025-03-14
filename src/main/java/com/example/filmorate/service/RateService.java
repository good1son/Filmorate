package com.example.filmorate.service;

import com.example.filmorate.exception.AlreadyExistsException;
import com.example.filmorate.exception.NotFoundException;
import com.example.filmorate.storage.dao.impl.RateDbStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RateService {
    private final RateDbStorage rateDbStorage;

    public void rateFilm(int filmId, int userId, int rating) {
        if (rateDbStorage.rateExists(filmId, userId))
            throw new AlreadyExistsException("Вы уже оставляли оценку данному фильму");
        rateDbStorage.rateFilm(filmId, userId, rating);
    }

    public void reRateFilm(int filmId, int userId, int rating) {
        if (!rateDbStorage.rateExists(filmId, userId))
            throw new NotFoundException("Оценка на данный фильм не найдена");
        rateDbStorage.reRateFilm(filmId, userId, rating);
    }

    public void deleteRate(int filmId, int userId) {
        if (!rateDbStorage.rateExists(filmId, userId))
            throw new NotFoundException("Удаление невозможно, т.к. оценка на данный фильм не найдена");
        rateDbStorage.deleteRate(filmId, userId);
    }
}

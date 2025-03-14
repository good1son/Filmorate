package com.example.filmorate.storage.dao;

import com.example.filmorate.storage.model.Event;

import java.util.Collection;

public interface RateStorage {
    public void rateFilm(int filmId, int userId, int rating);
    public void reRateFilm(int filmId, int userId, int rating);
    public void deleteRate(int filmId, int userId);
}

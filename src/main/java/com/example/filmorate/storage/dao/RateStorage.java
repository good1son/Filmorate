package com.example.filmorate.storage.dao;


public interface RateStorage {
    public void rateFilm(int filmId, int userId, int rating);
    public void reRateFilm(int filmId, int userId, int rating);
    public void deleteRate(int filmId, int userId);
}

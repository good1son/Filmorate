package com.example.filmorate.storage.dao;

import com.example.filmorate.storage.model.Review;

import java.util.Collection;

public interface ReviewStorage {
    public Collection<Review> getFilmReviews(int id);
    public Collection<Review> getUserReviews(int id);
    public void addReview(int filmId, int userId, String text);
    public void editReview(int filmId, int userId, String text);
    public void deleteReview(int filmId, int userId);
}

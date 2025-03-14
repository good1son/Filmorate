package com.example.filmorate.storage.dao;

import com.example.filmorate.storage.model.Review;

import java.util.Collection;

public interface ReviewStorage {
    public Collection<Review> getAllReviews();
    public Collection<Review> getFilmReviews(int id);
    public Collection<Review> getUserReviews(int id);

    public void addReview(int filmId, int userId, String text, Boolean isPositive);
    public void editReview(int filmId, int userId, String text, Boolean isPositive);
    public void deleteReview(int filmId, int userId);
}

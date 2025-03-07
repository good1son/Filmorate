package com.example.filmorate.service;

import com.example.filmorate.storage.dao.impl.ReviewDbStorage;
import com.example.filmorate.storage.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReviewService {
    private final ReviewDbStorage reviewDbStorage;

    public Collection<Review> getFilmReviews(int id) {
        return reviewDbStorage.getFilmReviews(id);
    }

    public Collection<Review> getUserReviews(int id) {
        return reviewDbStorage.getUserReviews(id);
    }

    public void addReview(int filmId, int userId, String text) {
        reviewDbStorage.addReview(filmId, userId, text);
    }

    public void editReview(int filmId, int userId, String text) {
        reviewDbStorage.editReview(filmId, userId, text);
    }

    public void deleteReview(int filmId, int userId) {
        reviewDbStorage.deleteReview(filmId, userId);
    }
}

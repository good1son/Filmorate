package com.example.filmorate.service;

import com.example.filmorate.storage.dao.impl.ReviewDbStorage;
import com.example.filmorate.storage.model.ReviewFeed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReviewService {
    private final ReviewDbStorage reviewDbStorage;

    public Collection<ReviewFeed> getFilmReviews(int id) {
        return reviewDbStorage.getFilmReviews(id);
    }

    public Collection<ReviewFeed> getUserReviews(int id) {
        return reviewDbStorage.getUserReviews(id);
    }

    public void addReview(int filmId, int userId, String text, Boolean isPositive) {
        reviewDbStorage.addReview(filmId, userId, text, isPositive);
    }

    public void addReviewFeed(int reviewId, int userId, Boolean isHelpful) {
        reviewDbStorage.addReviewFeed(reviewId, userId, isHelpful);
    }

    public void editReview(int filmId, int userId, String text, Boolean isPositive) {
        reviewDbStorage.editReview(filmId, userId, text, isPositive);
    }

    public void editReviewFeed(int reviewId, int userId, Boolean isHelpful) {
        reviewDbStorage.editReviewFeed(reviewId, userId, isHelpful);
    }

    public void deleteReview(int filmId, int userId) {
        reviewDbStorage.deleteReview(filmId, userId);
    }

    public void deleteReviewFeed(int reviewId, int userId) {
        reviewDbStorage.deleteReviewFeed(reviewId, userId);
    }
}

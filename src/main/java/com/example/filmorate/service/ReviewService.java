package com.example.filmorate.service;

import com.example.filmorate.exception.AlreadyExistsException;
import com.example.filmorate.exception.NotFoundException;
import com.example.filmorate.storage.dao.impl.ReviewDbStorage;
import com.example.filmorate.storage.model.Review;
import com.example.filmorate.storage.model.ReviewFeed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReviewService {
    private final ReviewDbStorage reviewDbStorage;

    public Collection<Review> getAllReviews() {
        return reviewDbStorage.getAllReviews();
    }

    public Collection<Review> getFilmReviews(int id) {
        return reviewDbStorage.getFilmReviews(id);
    }

    public Collection<Review> getUserReviews(int id) {
        return reviewDbStorage.getUserReviews(id);
    }

    public void addReview(int filmId, int userId, String text, Boolean isPositive) {
        if (reviewDbStorage.reviewExists(filmId, userId))
            throw new AlreadyExistsException("Отзыв на данный фильм уже существует");
        reviewDbStorage.addReview(filmId, userId, text, isPositive);
    }

    public void editReview(int filmId, int userId, String text, Boolean isPositive) {
        if (!reviewDbStorage.reviewExists(filmId, userId))
            throw new NotFoundException("Отзыв на данный фильм не найден");
        reviewDbStorage.editReview(filmId, userId, text, isPositive);
    }

    public void deleteReview(int filmId, int userId) {
        if (!reviewDbStorage.reviewExists(filmId, userId))
            throw new NotFoundException("Удаление невозможно, т.к. отзыв на данный фильм не найден");
        reviewDbStorage.deleteReview(filmId, userId);
    }
}

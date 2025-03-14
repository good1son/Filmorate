package com.example.filmorate.service;

import com.example.filmorate.exception.AlreadyExistsException;
import com.example.filmorate.exception.NotFoundException;
import com.example.filmorate.storage.dao.impl.ReviewFeedDbStorage;
import com.example.filmorate.storage.model.ReviewFeed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReviewFeedService {
    private final ReviewFeedDbStorage reviewFeedDbStorage;

    public Collection<ReviewFeed> getAllReviewFeed() {
        return reviewFeedDbStorage.getAllReviewFeed();
    }

    public Collection<ReviewFeed> getUserReviewFeed(int id) {
        return reviewFeedDbStorage.getUserReviewFeed(id);
    }

    public void addReviewFeed(int reviewId, int userId, Boolean isHelpful) {
        if (reviewFeedDbStorage.reviewFeedExists(reviewId, userId))
            throw new AlreadyExistsException("Отзыв на оценку фильма уже существует");
        reviewFeedDbStorage.addReviewFeed(reviewId, userId, isHelpful);
    }

    public void editReviewFeed(int reviewId, int userId, Boolean isHelpful) {
        if (!reviewFeedDbStorage.reviewFeedExists(reviewId, userId))
            throw new NotFoundException("Отзыв на оценку данного фильма не найден");
        reviewFeedDbStorage.editReviewFeed(reviewId, userId, isHelpful);
    }

    public void deleteReviewFeed(int reviewId, int userId) {
        if (!reviewFeedDbStorage.reviewFeedExists(reviewId, userId))
            throw new NotFoundException("Удаление невозможно, т.к. отзыв на оценку данного фильма не найден");
        reviewFeedDbStorage.deleteReviewFeed(reviewId, userId);
    }
}

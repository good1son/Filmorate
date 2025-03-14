package com.example.filmorate.storage.dao;

import com.example.filmorate.storage.model.ReviewFeed;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

public interface ReviewFeedStorage {
    public Collection<ReviewFeed> getAllReviewFeed();
    public Collection<ReviewFeed> getUserReviewFeed(int id);

    public void addReviewFeed(int reviewId, int userId, Boolean isHelpful);
    public void editReviewFeed(int reviewId, int userId, Boolean isHelpful);
    public void deleteReviewFeed(int reviewId, int userId);
}

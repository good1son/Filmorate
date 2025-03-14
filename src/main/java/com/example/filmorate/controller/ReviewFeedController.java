package com.example.filmorate.controller;

import com.example.filmorate.service.ReviewFeedService;
import com.example.filmorate.service.ReviewService;
import com.example.filmorate.storage.model.Review;
import com.example.filmorate.storage.model.ReviewFeed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReviewFeedController {
    private final ReviewService reviewService;
    private final ReviewFeedService reviewFeedService;

    @GetMapping()
    public Collection<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/feedbacks")
    public Collection<ReviewFeed> getAllReviewFeed() {
        return reviewFeedService.getAllReviewFeed();
    }

    @GetMapping("/films/{id}")
    public Collection<Review> getFilmReviews(@PathVariable int id) {
        return reviewService.getFilmReviews(id);
    }

    @GetMapping("/users/{id}")
    public Collection<Review> getUserReviews(@PathVariable int id) {
        return reviewService.getUserReviews(id);
    }

    @GetMapping("/feedbacks/users/{id}")
    public Collection<ReviewFeed> getUserReviewFeed(@PathVariable int id) {
        return reviewFeedService.getUserReviewFeed(id);
    }

    @PostMapping("/{filmId}/review/{userId}")
    public void addReview(@PathVariable int filmId, @PathVariable int userId, @RequestBody Map<String, Object> request) {
        reviewService.addReview(filmId, userId, (String) request.get("text"), (Boolean) request.get("isPositive"));
    }

    @PostMapping("{reviewId}/feedback/{userId}")
    public void addReviewFeed(@PathVariable int reviewId, @PathVariable int userId, @RequestBody Map<String, Object> request) {
        reviewFeedService.addReviewFeed(reviewId, userId, (Boolean) request.get("isHelpful"));
    }

    @PatchMapping("/{filmId}/review/{userId}")
    public void editReview(@PathVariable int filmId, @PathVariable int userId, @RequestBody Map<String, Object> request) {
        reviewService.editReview(filmId, userId, (String) request.get("text"), (Boolean) request.get("isPositive"));
    }

    @PatchMapping("{reviewId}/feedback/{userId}")
    public void editReviewFeed(@PathVariable int reviewId, @PathVariable int userId, @RequestBody Map<String, Object> request) {
        reviewFeedService.editReviewFeed(reviewId, userId, (Boolean) request.get("isHelpful"));
    }

    @DeleteMapping("/{filmId}/review/{userId}")
    public void deleteReview(@PathVariable int filmId, @PathVariable int userId) {
        reviewService.deleteReview(filmId, userId);
    }

    @DeleteMapping("{reviewId}/feedback/{userId}")
    public void deleteReviewFeed(@PathVariable int reviewId, @PathVariable int userId) {
        reviewFeedService.deleteReviewFeed(reviewId, userId);
    }
}

package com.example.filmorate.controller;

import com.example.filmorate.service.ReviewService;
import com.example.filmorate.storage.model.ReviewFeed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/films/{id}/reviews")
    public Collection<ReviewFeed> getFilmReviews(@PathVariable int id) {
        return reviewService.getFilmReviews(id);
    }

    @GetMapping("/users/{id}/reviews")
    public Collection<ReviewFeed> getUserReviews(@PathVariable int id) {
        return reviewService.getUserReviews(id);
    }

    @PostMapping("/{filmId}/review/{userId}")
    public void addReview(@PathVariable int filmId, @PathVariable int userId, @RequestBody Map<String, Object> request) {
        reviewService.addReview(filmId, userId, (String) request.get("text"), (Boolean) request.get("isPositive"));
    }

    @PostMapping("{reviewId}/feedback/{userId}")
    public void addReviewFeed(@PathVariable int reviewId, @PathVariable int userId, @RequestBody Map<String, Object> request) {
        reviewService.addReviewFeed(reviewId, userId, (Boolean) request.get("isHelpful"));
    }

    @PutMapping("/{filmId}/review/{userId}")
    public void editReview(@PathVariable int filmId, @PathVariable int userId, @RequestBody Map<String, Object> request) {
        reviewService.editReview(filmId, userId, (String) request.get("text"), (Boolean) request.get("isPositive"));
    }

    @PutMapping("{reviewId}/feedback/{userId}")
    public void editReviewFeed(@PathVariable int reviewId, @PathVariable int userId, @RequestBody Map<String, Object> request) {
        reviewService.editReviewFeed(reviewId, userId, (Boolean) request.get("isHelpful"));
    }

    @DeleteMapping("/{filmId}/review/{userId}")
    public void deleteReview(@PathVariable int filmId, @PathVariable int userId) {
        reviewService.deleteReview(filmId, userId);
    }

    @DeleteMapping("{reviewId}/feedback/{userId}")
    public void deleteReviewFeed(@PathVariable int reviewId, @PathVariable int userId) {
        reviewService.deleteReviewFeed(reviewId, userId);
    }
}

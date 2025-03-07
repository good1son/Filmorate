package com.example.filmorate.controller;

import com.example.filmorate.service.ReviewService;
import com.example.filmorate.storage.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/films/{id}/reviews")
    public Collection<Review> getFilmReviews(@PathVariable int id) {
        return reviewService.getFilmReviews(id);
    }

    @GetMapping("/users/{id}/reviews")
    public Collection<Review> getUserReviews(@PathVariable int id) {
        return reviewService.getUserReviews(id);
    }

    @PutMapping("films/{filmId}/review/{userId}")
    public void addReview(@PathVariable int filmId, @PathVariable int userId, @RequestBody String text) {
        reviewService.addReview(filmId, userId, text);
    }

    @PatchMapping("films/{filmId}/review/{userId}")
    public void editReview(@PathVariable int filmId, @PathVariable int userId, @RequestBody String text) {
        reviewService.editReview(filmId, userId, text);
    }

    @DeleteMapping("films/{filmId}/review/{userId}")
    public void removeLike(@PathVariable int filmId, @PathVariable int userId) {
        reviewService.deleteReview(filmId, userId);
    }
}

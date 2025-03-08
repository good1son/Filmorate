package com.example.filmorate.storage.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReviewFeed {
    private Integer id;
    private Integer reviewId;
    private Integer userId;
    private Boolean isHelpful;
    private LocalDateTime createdAt;

    public ReviewFeed(Integer id, Integer review) {
        this.id = id;
        this.reviewId = review;
    }
}

package com.example.filmorate.storage.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Review {
    private Integer id;
    private Integer filmId;
    private Integer userId;
    private String review;
    private Boolean isPositive;
    private Integer rating;
    private LocalDateTime createdAt;
    //private List<ReviewFeed> feedbacks;
}

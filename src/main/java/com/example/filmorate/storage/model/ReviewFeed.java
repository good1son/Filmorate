package com.example.filmorate.storage.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewFeed {
    private Integer id;
    private Integer reviewId;
    private Integer userId;
    private Boolean isHelpful;
    private LocalDateTime createdAt;
}

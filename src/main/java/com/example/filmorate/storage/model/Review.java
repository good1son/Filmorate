package com.example.filmorate.storage.model;

import java.util.List;

public class Review {
    private Integer id;
    private Integer filmId;
    private Integer userId;
    private String review;
    private Boolean isPositive;
    private Integer rating;
    private List<ReviewFeed> feedbacks;


}

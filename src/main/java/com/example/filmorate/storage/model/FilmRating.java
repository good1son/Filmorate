package com.example.filmorate.storage.model;

import lombok.Data;

@Data
public class FilmRating {
    private Integer filmId;
    private Integer userId;
    private Integer rating;
}

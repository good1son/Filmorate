package com.example.filmorate.storage.util;

import com.example.filmorate.storage.model.ReviewFeed;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements RowMapper<ReviewFeed> {
    @Override
    public ReviewFeed mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ReviewFeed(rs.getInt("id"), rs.getInt("review"));
    }
}
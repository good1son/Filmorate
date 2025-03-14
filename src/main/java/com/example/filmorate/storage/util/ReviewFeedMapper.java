package com.example.filmorate.storage.util;

import com.example.filmorate.storage.model.ReviewFeed;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewFeedMapper implements RowMapper<ReviewFeed> {
    @Override
    public ReviewFeed mapRow(ResultSet rs, int rowNum) throws SQLException {
        ReviewFeed reviewFeed = new ReviewFeed();
        reviewFeed.setId(rs.getInt("id"));
        reviewFeed.setReviewId(rs.getInt("review_id"));
        reviewFeed.setUserId(rs.getInt("user_id"));
        reviewFeed.setIsHelpful(rs.getBoolean("is_helpful"));
        reviewFeed.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return reviewFeed;
    }
}
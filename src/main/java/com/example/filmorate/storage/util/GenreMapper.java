package com.example.filmorate.storage.util;

import com.example.filmorate.storage.model.type.GENRE;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<GENRE> {
    @Override
    public GENRE mapRow(ResultSet rs, int rowNum) throws SQLException {
        return GENRE.valueOf(rs.getString("genre"));
    }
}

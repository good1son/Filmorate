package com.example.filmorate.storage.util;

import com.example.filmorate.storage.model.type.MPA;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MpaMapper implements RowMapper<MPA> {
    @Override
    public MPA mapRow(ResultSet rs, int rowNum) throws SQLException {
        return MPA.valueOf(rs.getString("name"));
    }
}

package com.hdsoft.mapper;


import com.hdsoft.model.Color;
import com.hdsoft.model.Gradient;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ColorMapper implements RowMapper<Color> {
    @Override
    public Color mapRow(ResultSet rs, int i) throws SQLException {
        return new Color(rs.getInt(1),rs.getString(2));
    }
}

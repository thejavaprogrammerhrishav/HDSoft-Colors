package com.hdsoft.mapper;


import com.hdsoft.model.Gradient;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class GradientMapper implements RowMapper<Gradient> {
    @Override
    public Gradient mapRow(ResultSet rs, int i) throws SQLException {
        return new Gradient(rs.getInt(1),rs.getString(2));
    }
}

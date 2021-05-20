package com.hdsoft.dao;

import com.hdsoft.mapper.ColorMapper;
import com.hdsoft.mapper.GradientMapper;
import com.hdsoft.model.Color;
import com.hdsoft.model.Gradient;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseServer {
    private final String insertGradient="INSERT INTO gradient(code) values(?)";
    private final String insertColor="INSERT INTO colors(color) values(?)";

    private final String updateGradient="UPDATE gradient SET code=? WHERE id=?";
    private final String updateColor="UPDATE colors SET color=? WHERE id=?";

    private final String deleteGradient="DELETE FROM gradient WHERE id=?";
    private final String deleteColor="DELETE FROM colors WHERE id=?";

    private final String selectGradient="SELECT * FROM gradient";
    private final String selectColor="SELECT * FROM colors";

    private final JdbcTemplate template;

    public DatabaseServer(JdbcTemplate template) {
        this.template = template;
    }

    @Transactional
    public boolean saveGradient(Gradient gradient){
        int update = template.update(insertGradient, gradient.getCode());
        return update>0;
    }

    @Transactional
    public boolean saveColor(Color color){
        int update = template.update(insertColor, color.getColor());
        return update>0;
    }

    @Transactional
    public boolean updateGradient(Gradient gradient){
        int update = template.update(updateGradient, gradient.getCode(),gradient.getId());
        return update>0;
    }

    @Transactional
    public boolean updateColor(Color color){
        int update = template.update(updateColor, color.getColor(),color.getId());
        return update>0;
    }


    @Transactional
    public boolean deleteGradient(Gradient gradient){
        int update = template.update(deleteGradient, gradient.getId());
        return update>0;
    }

    @Transactional
    public boolean deleteColor(Color color){
        int update = template.update(deleteColor, color.getId());
        return update>0;
    }


    @Transactional
    public List<Gradient> selectGradient(){
        List<Gradient> query = template.query(selectGradient, new GradientMapper());
        return query;
    }

    @Transactional
    public List<Color> selectColor(){
        List<Color> query = template.query(selectColor, new ColorMapper());
        return query;
    }
}

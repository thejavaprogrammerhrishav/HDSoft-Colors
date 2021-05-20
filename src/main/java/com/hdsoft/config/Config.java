package com.hdsoft.config;

import com.hdsoft.App;
import com.hdsoft.model.Color;
import com.hdsoft.model.Gradient;
import com.hdsoft.service.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(basePackages = "com.hdsoft")
public class Config {
    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.sqlite.JDBC");
        ds.setUrl("jdbc:sqlite:./database.db");
        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate template = new JdbcTemplate(dataSource);
        return template;
    }

    @PostConstruct
    public void work() {
        JdbcTemplate template = jdbcTemplate(dataSource());
        String gradient = "CREATE TABLE IF NOT EXISTS gradient (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,code TEXT NOT NULL UNIQUE);";
        String color = "CREATE TABLE IF NOT EXISTS colors (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,color TEXT NOT NULL UNIQUE);";

        template.update(gradient);
        template.update(color);

        try {
            Integer grads = template.queryForObject("select count(*) from gradient", Integer.class);
            if (grads == 0)
                grad(template);
            Integer colorc = template.queryForObject("select count(*) from colors", Integer.class);
            if (colorc == 0)
                color(template);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void grad(JdbcTemplate template) throws IOException {
        InputStream in = Config.class.getResourceAsStream("/com/hdsoft/commons/grad2.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        List<String> grads = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            grads.add(line);
        }

        in = Config.class.getResourceAsStream("/com/hdsoft/commons/grad2.txt");
        br = new BufferedReader(new InputStreamReader(in));

        while ((line = br.readLine()) != null) {
            grads.add(line);
        }

        grads.forEach(s -> {
            try {
                int update = template.update("INSERT INTO gradient(code) VALUES(?)", s);
                System.out.println("Saved Gradient: " + (update > 0));
            }catch (Exception e){
                System.out.println("Saved Gradient: false");
            }
        });
    }

    public void color(JdbcTemplate template) throws IOException {
        InputStream in = Config.class.getResourceAsStream("/com/hdsoft/commons/colors1.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        List<String> colors = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            colors.add(line);
        }

        in = Config.class.getResourceAsStream("/com/hdsoft/commons/colors2.txt");
        br = new BufferedReader(new InputStreamReader(in));

        while ((line = br.readLine()) != null) {
            colors.add(line);
        }

        in = Config.class.getResourceAsStream("/com/hdsoft/commons/colors3.txt");
        br = new BufferedReader(new InputStreamReader(in));

        while ((line = br.readLine()) != null) {
            colors.add(line);
        }

        colors.forEach(s -> {
            try {
                int update = template.update("INSERT INTO colors(color) VALUES(?)", s);
                System.out.println("Saved Color: " + (update > 0));
            }catch (Exception e){
                System.out.println("Saved Color: false");
            }
        });
    }
}

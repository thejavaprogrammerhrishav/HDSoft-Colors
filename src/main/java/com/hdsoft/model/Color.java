package com.hdsoft.model;

public class Color {
    private int id;
    private String color;

    public Color(String color) {
        this.color = color;
    }

    public Color(int id, String color) {
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Gradient{" +
                "id=" + id +
                ", color='" + color + '\'' +
                '}';
    }
}

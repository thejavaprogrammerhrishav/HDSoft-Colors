package com.hdsoft.model;

public class Gradient {
    private int id;
    private String code;

    public Gradient(String code) {
        this.code = code;
    }

    public Gradient(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Gradient{" +
                "id=" + id +
                ", code='" + code + '\'' +
                '}';
    }
}

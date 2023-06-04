package com.example.lab1oop;

import com.google.gson.Gson;

public class Car {
    private String name;
    private String brand;
    private String color;
    private Integer year;
    private Integer mileage;

    public Car(String name, String brand, String color, Integer year, Integer mileage) {
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.year=year;
        this.mileage=mileage;
    }

    // Пример конвертации в JSON
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    // Пример создания объекта из JSON
    public static Car fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Car.class);
    }
}
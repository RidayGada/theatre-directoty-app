package com.theatre_directory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "theatre")
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private int screens;

    public Theatre() {
        super();
    }

    public Theatre(Long id, String name, String city, int screens) {
        super();
        this.id = id;
        this.name = name;
        this.city = city;
        this.screens = screens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getScreens() {
        return screens;
    }

    public void setScreens(int screens) {
        this.screens = screens;
    }

    @Override
    public String toString() {
        return "Theatre [id=" + id +
                ", name=" + name +
                ", city=" + city +
                ", screens=" + screens + "]";
    }
}
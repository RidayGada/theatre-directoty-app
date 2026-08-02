package com.theatre_directory.dto.response;

public class TheatreResponse {

    private Long id;

    private String name;

    private String city;

    private int screens;

    public TheatreResponse() {
        super();
    }

    public TheatreResponse(Long id, String name, String city, int screens) {
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
}
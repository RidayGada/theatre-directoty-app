package com.theatre_directory.dto.request;

public class TheatreRequest {

    private String name;

    private String city;

    private int screens;

    public TheatreRequest() {
        super();
    }

    public TheatreRequest(String name, String city, int screens) {
        super();
        this.name = name;
        this.city = city;
        this.screens = screens;
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
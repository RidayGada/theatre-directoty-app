package com.theatre_directory.dto.response;

public class TheatreMovieResponse {

    private Long id;
    private Long theatreId;
    private Long movieId;

    public TheatreMovieResponse() {
    }

    public TheatreMovieResponse(Long id, Long theatreId, Long movieId) {
        this.id = id;
        this.theatreId = theatreId;
        this.movieId = movieId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Long theatreId) {
        this.theatreId = theatreId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
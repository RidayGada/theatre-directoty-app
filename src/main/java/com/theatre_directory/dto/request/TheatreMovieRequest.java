package com.theatre_directory.dto.request;

public class TheatreMovieRequest {

    private Long theatreId;
    private Long movieId;

    public TheatreMovieRequest() {
    }

    public TheatreMovieRequest(Long theatreId, Long movieId) {
        this.theatreId = theatreId;
        this.movieId = movieId;
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
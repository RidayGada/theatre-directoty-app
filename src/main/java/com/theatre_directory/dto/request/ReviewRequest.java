package com.theatre_directory.dto.request;

public class ReviewRequest {

    private int rating;

    private String review;

    private Long theatreId;

    public ReviewRequest() {
        super();
    }

    public ReviewRequest(int rating, String review, Long theatreId) {
        super();
        this.rating = rating;
        this.review = review;
        this.theatreId = theatreId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Long getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Long theatreId) {
        this.theatreId = theatreId;
    }
}
package com.theatre_directory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "theatre_review")
public class TheatreReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int rating;

    private String review;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "theatre_id", nullable = false)
    private Long theatreId;

    public TheatreReview() {
        super();
    }

    public TheatreReview(Long id, int rating, String review, Long userId, Long theatreId) {
        super();
        this.id = id;
        this.rating = rating;
        this.review = review;
        this.userId = userId;
        this.theatreId = theatreId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Long theatreId) {
        this.theatreId = theatreId;
    }

    @Override
    public String toString() {
        return "TheatreReview [id=" + id +
                ", rating=" + rating +
                ", review=" + review +
                ", userId=" + userId +
                ", theatreId=" + theatreId + "]";
    }
}
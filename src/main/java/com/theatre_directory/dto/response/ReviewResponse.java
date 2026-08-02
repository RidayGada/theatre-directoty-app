package com.theatre_directory.dto.response;

public class ReviewResponse {

    private Long id;

    private int rating;

    private String review;

    private Long userId;
    
    private Long theatreId;

    public ReviewResponse() {
        super();
    }


    public ReviewResponse(Long id, int rating, String review, Long userId, Long theatreId) {
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

    
}
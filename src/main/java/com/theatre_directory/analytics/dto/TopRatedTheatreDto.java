package com.theatre_directory.analytics.dto;

public class TopRatedTheatreDto {

	private String theatreName;
	private Double averageRating;
	private Long reviewCount;
	
	public TopRatedTheatreDto() {
		
	}

	public TopRatedTheatreDto(String theatreName, Double averageRating, Long reviewCount) {
		super();
		this.theatreName = theatreName;
		this.averageRating = averageRating;
		this.reviewCount = reviewCount;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	public Long getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(Long reviewCount) {
		this.reviewCount = reviewCount;
	}
	
	
}

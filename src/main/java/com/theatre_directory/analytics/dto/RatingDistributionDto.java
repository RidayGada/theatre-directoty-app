package com.theatre_directory.analytics.dto;

public class RatingDistributionDto {

	private Integer rating;
	private Long count;
	
	public RatingDistributionDto() {
		
	}
	
	public RatingDistributionDto(Integer rating, Long count) {
		super();
		this.rating = rating;
		this.count = count;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	
}

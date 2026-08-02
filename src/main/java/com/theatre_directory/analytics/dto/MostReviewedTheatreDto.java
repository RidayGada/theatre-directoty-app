package com.theatre_directory.analytics.dto;

public class MostReviewedTheatreDto {

	private String theatreName;
	private Long reviewCount;
	
	public MostReviewedTheatreDto() {
		
	}

	public MostReviewedTheatreDto(String theatreName, Long reviewCount) {
		super();
		this.theatreName = theatreName;
		this.reviewCount = reviewCount;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public Long getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(Long reviewCount) {
		this.reviewCount = reviewCount;
	}
	
	
}

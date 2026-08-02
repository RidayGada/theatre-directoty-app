package com.theatre_directory.analytics.dto;

public class CityTheatreCountDto {

	private String city;
	private Long theatreCount;
	
	public CityTheatreCountDto() {
		
	}

	public CityTheatreCountDto(String city, Long theatreCount) {
		super();
		this.city = city;
		this.theatreCount = theatreCount;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getTheatreCount() {
		return theatreCount;
	}

	public void setTheatreCount(Long theatreCount) {
		this.theatreCount = theatreCount;
	}
	
	
}

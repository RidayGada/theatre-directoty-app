package com.theatre_directory.analytics.dto;

public class PopularMovieDto {

	private String movieName;
	private Long theatreCount;
	
	public PopularMovieDto() {
		
	}

	public PopularMovieDto(String movieName, Long theatreCount) {
		super();
		this.movieName = movieName;
		this.theatreCount = theatreCount;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Long getTheatreCount() {
		return theatreCount;
	}

	public void setTheatreCount(Long theatreCount) {
		this.theatreCount = theatreCount;
	}
	
	
}

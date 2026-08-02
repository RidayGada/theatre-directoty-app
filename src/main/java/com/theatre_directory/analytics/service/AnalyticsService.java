package com.theatre_directory.analytics.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.theatre_directory.analytics.dto.CityTheatreCountDto;
import com.theatre_directory.analytics.dto.MostReviewedTheatreDto;
import com.theatre_directory.analytics.dto.PopularMovieDto;
import com.theatre_directory.analytics.dto.RatingDistributionDto;
import com.theatre_directory.analytics.dto.TopRatedTheatreDto;
import com.theatre_directory.analytics.repository.AnalyticsRepository;

@Service
public class AnalyticsService {

	
	private final AnalyticsRepository analyticsRepository;

	public AnalyticsService(AnalyticsRepository analyticsRepository) {
	    this.analyticsRepository = analyticsRepository;
	}
	
    public List<TopRatedTheatreDto> getTopRatedTheatres() {
        return analyticsRepository.getTopRatedTheatres();
    }

    public List<MostReviewedTheatreDto> getMostReviewedTheatres() {
        return analyticsRepository.getMostReviewedTheatres();
    }

    public List<CityTheatreCountDto> getCityWiseTheatreCount() {
        return analyticsRepository.getCityWiseTheatreCount();
    }

    public List<PopularMovieDto> getMoviesRunningInMostTheatres() {
        return analyticsRepository.getMoviesRunningInMostTheatres();
    }

    public List<RatingDistributionDto> getRatingDistribution() {
        return analyticsRepository.getRatingDistribution();
    }
}

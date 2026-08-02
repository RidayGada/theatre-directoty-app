package com.theatre_directory.analytics.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theatre_directory.analytics.dto.CityTheatreCountDto;
import com.theatre_directory.analytics.dto.MostReviewedTheatreDto;
import com.theatre_directory.analytics.dto.PopularMovieDto;
import com.theatre_directory.analytics.dto.RatingDistributionDto;
import com.theatre_directory.analytics.dto.TopRatedTheatreDto;
import com.theatre_directory.analytics.service.AnalyticsService;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

	private final AnalyticsService analyticsService;

	public AnalyticsController(AnalyticsService analyticsService) {
	    this.analyticsService = analyticsService;
	}
	
	@GetMapping("/hello")
	public String analyticsHello() {
		return "hello from analytics";
	}
	
    @GetMapping("/top-rated-theatres")
    public List<TopRatedTheatreDto> getTopRatedTheatres() {
        return analyticsService.getTopRatedTheatres();
    }

    @GetMapping("/most-reviewed-theatres")
    public List<MostReviewedTheatreDto> getMostReviewedTheatres() {
        return analyticsService.getMostReviewedTheatres();
    }

    @GetMapping("/city-theatre-count")
    public List<CityTheatreCountDto> getCityWiseTheatreCount() {
        return analyticsService.getCityWiseTheatreCount();
    }

    @GetMapping("/popular-movies")
    public List<PopularMovieDto> getMoviesRunningInMostTheatres() {
        return analyticsService.getMoviesRunningInMostTheatres();
    }

    @GetMapping("/rating-distribution")
    public List<RatingDistributionDto> getRatingDistribution() {
        return analyticsService.getRatingDistribution();
    }
}

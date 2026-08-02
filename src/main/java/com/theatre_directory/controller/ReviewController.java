package com.theatre_directory.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theatre_directory.dto.request.ReviewRequest;
import com.theatre_directory.dto.response.ReviewResponse;
import com.theatre_directory.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

	@PostMapping
	public ReviewResponse createReview(@RequestBody ReviewRequest request) {
	    return reviewService.createReview(request);
	}
	
	@PutMapping("/{id}")
	public ReviewResponse updateReview(
	        @PathVariable Long id,
	        @RequestBody ReviewRequest request) {

	    return reviewService.updateReview(id, request);
	}
	
	@DeleteMapping("/{id}")
	public void deleteReview(@PathVariable Long id) {

	    reviewService.deleteReview(id);
	}
	
	@GetMapping("/theatre/{id}")
	public List<ReviewResponse> getReviewsByTheatre(@PathVariable Long id) {

	    return reviewService.getReviewsByTheatre(id);
	}
}

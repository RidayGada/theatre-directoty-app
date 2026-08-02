package com.theatre_directory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.theatre_directory.dto.request.ReviewRequest;
import com.theatre_directory.dto.response.ReviewResponse;
import com.theatre_directory.entity.TheatreReview;
import com.theatre_directory.exception.ForbiddenOperationException;
import com.theatre_directory.exception.ReviewNotFoundException;
import com.theatre_directory.exception.TheatreNotFoundException;
import com.theatre_directory.repository.TheatreRepository;
import com.theatre_directory.repository.TheatreReviewRepository;
import com.theatre_directory.security.AuthorizationService;
import com.theatre_directory.security.UserContext;

@Service
public class ReviewService {

	private final TheatreReviewRepository theatreReviewRepository;
	
	private final UserContext userContext;
	
	private final TheatreRepository theatreRepository;
	
	private final AuthorizationService authorizationService;

	
	
	public ReviewService(TheatreReviewRepository theatreReviewRepository, UserContext userContext,
			TheatreRepository theatreRepository, AuthorizationService authorizationService) {
		super();
		this.theatreReviewRepository = theatreReviewRepository;
		this.userContext = userContext;
		this.theatreRepository = theatreRepository;
		this.authorizationService = authorizationService;
	}

	// create 
	public ReviewResponse createReview(ReviewRequest request) {
		
		authorizationService.requireUser();
		
		theatreRepository.findById(request.getTheatreId())
		        .orElseThrow(() -> new TheatreNotFoundException("Theatre not found."));
		
		Long currentUserId = userContext.getCurrentUserId();
		
		Optional<TheatreReview> existingReview =
		        theatreReviewRepository.findByUserIdAndTheatreId(
		                currentUserId,
		                request.getTheatreId()
		        );

		if (existingReview.isPresent()) {
			throw new ForbiddenOperationException("You have already reviewed this theatre.");
		}
		
		TheatreReview review = new TheatreReview();

		review.setRating(request.getRating());
		review.setReview(request.getReview());
		review.setUserId(currentUserId);
		review.setTheatreId(request.getTheatreId());
		
		review = theatreReviewRepository.save(review);
		
		ReviewResponse response = new ReviewResponse();

		response.setId(review.getId());
		response.setRating(review.getRating());
		response.setReview(review.getReview());
		response.setUserId(review.getUserId());
		response.setTheatreId(review.getTheatreId());
		
		return response;
	}
	
	// this is helper function for update and delete methods
	private boolean isReviewOwner(TheatreReview review) {
	    return review.getUserId().equals(userContext.getCurrentUserId());
	}
	
	// update
	public ReviewResponse updateReview(Long id, ReviewRequest request) {

		authorizationService.requireUser();
		
	    TheatreReview review = theatreReviewRepository.findById(id)
	            .orElseThrow(() -> new ReviewNotFoundException("Review not found."));

	    if (!isReviewOwner(review)) {
	    		throw new ForbiddenOperationException("You can only update your own review.");
	    }

	    review.setRating(request.getRating());
	    review.setReview(request.getReview());

	    review = theatreReviewRepository.save(review);

	    ReviewResponse response = new ReviewResponse();

	    response.setId(review.getId());
	    response.setRating(review.getRating());
	    response.setReview(review.getReview());
	    response.setUserId(review.getUserId());
	    response.setTheatreId(review.getTheatreId());

	    return response;
	}
	
	public void deleteReview(Long id) {

		authorizationService.requireUser();
		
	    TheatreReview review = theatreReviewRepository.findById(id)
	            .orElseThrow(() -> new ReviewNotFoundException("Review not found."));

	    if (!isReviewOwner(review)) {
	    		throw new ForbiddenOperationException("You can only delete your own review.");
	    }

	    theatreReviewRepository.delete(review);
	}
	
	public List<ReviewResponse> getReviewsByTheatre(Long theatreId) {

	    theatreRepository.findById(theatreId)
	            .orElseThrow(() -> new TheatreNotFoundException("Theatre not found."));

	    List<TheatreReview> reviews =
	            theatreReviewRepository.findByTheatreId(theatreId);

	    List<ReviewResponse> response = new ArrayList<>();

	    for (TheatreReview review : reviews) {

	        ReviewResponse reviewResponse = new ReviewResponse();

	        reviewResponse.setId(review.getId());
	        reviewResponse.setRating(review.getRating());
	        reviewResponse.setReview(review.getReview());
	        reviewResponse.setUserId(review.getUserId());
	        reviewResponse.setTheatreId(review.getTheatreId());

	        response.add(reviewResponse);
	    }

	    return response;
	}
}

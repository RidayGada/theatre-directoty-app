package com.theatre_directory.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.theatre_directory.dto.request.TheatreRequest;
import com.theatre_directory.dto.response.TheatreResponse;
import com.theatre_directory.entity.Theatre;
import com.theatre_directory.exception.ForbiddenOperationException;
import com.theatre_directory.exception.TheatreNotFoundException;
import com.theatre_directory.repository.TheatreMovieRepository;
import com.theatre_directory.repository.TheatreRepository;
import com.theatre_directory.repository.TheatreReviewRepository;
import com.theatre_directory.security.AuthorizationService;


@Service
public class TheatreService {

	
	private final TheatreRepository theatreRepository;
	
	private final TheatreReviewRepository theatreReviewRepository;
	
	private final TheatreMovieRepository theatreMovieRepository;
//	private final UserContext userContext;

	private final AuthorizationService authorizationService;
	
	
	


	public TheatreService(TheatreRepository theatreRepository, TheatreReviewRepository theatreReviewRepository,
		TheatreMovieRepository theatreMovieRepository, AuthorizationService authorizationService) {
	super();
	this.theatreRepository = theatreRepository;
	this.theatreReviewRepository = theatreReviewRepository;
	this.theatreMovieRepository = theatreMovieRepository;
	this.authorizationService = authorizationService;
}


	public TheatreResponse createTheatre(TheatreRequest request) {
		
		
		authorizationService.requireManager();
		
		Theatre theatre = new Theatre();
		
		theatre.setName(request.getName());
		theatre.setScreens(request.getScreens());
		theatre.setCity(request.getCity());
		
		theatreRepository.save(theatre);
		
		TheatreResponse theatreResponse = new TheatreResponse();
		
		theatreResponse.setId(theatre.getId());
		theatreResponse.setCity(theatre.getCity());
		theatreResponse.setName(theatre.getName());
		theatreResponse.setScreens(theatre.getScreens());
		
		return theatreResponse;
	}
	
	
	public List<TheatreResponse> getAllTheatres(){
		
		List<Theatre> theatres = theatreRepository.findAll();
		
		List<TheatreResponse> response = new ArrayList<>();
		
	    for (Theatre theatre : theatres) {

	        TheatreResponse theatreResponse = new TheatreResponse();

	        theatreResponse.setId(theatre.getId());
	        theatreResponse.setName(theatre.getName());
	        theatreResponse.setCity(theatre.getCity());
	        theatreResponse.setScreens(theatre.getScreens());

	        response.add(theatreResponse);
	    }

	    return response;
	}
	
	public TheatreResponse updateTheatre(Long id, TheatreRequest request) {
		
		authorizationService.requireManager();
		
		
		Theatre theatre = theatreRepository.findById(id)
		        .orElseThrow(() -> new TheatreNotFoundException("Theatre not found."));
		
		
		theatre.setName(request.getName());
		theatre.setCity(request.getCity());
		theatre.setScreens(request.getScreens());
		
		
		theatre = theatreRepository.save(theatre);
		
		
		TheatreResponse response = new TheatreResponse();

		response.setId(theatre.getId());
		response.setName(theatre.getName());
		response.setCity(theatre.getCity());
		response.setScreens(theatre.getScreens());
		
		return response;
	}
	
	
	
	public List<TheatreResponse> filterTheatres(int minScreens, int maxScreens) {

	    List<Theatre> theatres =
	            theatreRepository.findByScreensBetween(minScreens, maxScreens);

	    List<TheatreResponse> response = new ArrayList<>();

	    for (Theatre theatre : theatres) {

	        TheatreResponse theatreResponse = new TheatreResponse();

	        theatreResponse.setId(theatre.getId());
	        theatreResponse.setName(theatre.getName());
	        theatreResponse.setCity(theatre.getCity());
	        theatreResponse.setScreens(theatre.getScreens());

	        response.add(theatreResponse);
	    }

	    return response;
	}
	
	
	
	public void deleteTheatre(Long id) {

		authorizationService.requireManager();

	    Theatre theatre = theatreRepository.findById(id)
	            .orElseThrow(() -> new TheatreNotFoundException("Theatre not found."));

	    if (theatreMovieRepository.existsByTheatreId(id)) {
	        throw new ForbiddenOperationException(
	                "Theatre has movies assigned and cannot be deleted.");
	    }
	    
	    long reviewCount = theatreReviewRepository.countByTheatreId(id);

	    if (reviewCount > 0) {
	    	throw new ForbiddenOperationException("Cannot delete theatre with existing reviews.");
	    }

	    theatreRepository.delete(theatre);
	}
	
	
	
}

package com.theatre_directory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.theatre_directory.dto.request.TheatreMovieRequest;
import com.theatre_directory.dto.response.MovieResponse;
import com.theatre_directory.dto.response.TheatreResponse;
import com.theatre_directory.entity.Movie;
import com.theatre_directory.entity.Theatre;
import com.theatre_directory.entity.TheatreMovie;
import com.theatre_directory.exception.MovieAlreadyAssignedException;
import com.theatre_directory.exception.MovieAssignmentNotFoundException;
import com.theatre_directory.exception.MovieNotFoundException;
import com.theatre_directory.exception.TheatreNotFoundException;
import com.theatre_directory.repository.MovieRepository;
import com.theatre_directory.repository.TheatreMovieRepository;
import com.theatre_directory.repository.TheatreRepository;
import com.theatre_directory.security.AuthorizationService;

@Service
public class TheatreMovieService {

	
	private final TheatreRepository theatreRepository;
	
	private final MovieRepository movieRepository;
	
	private final AuthorizationService authorizationService;
	
	private final TheatreMovieRepository theatreMovieRepository;

	public TheatreMovieService(TheatreRepository theatreRepository, MovieRepository movieRepository,
			AuthorizationService authorizationService, TheatreMovieRepository theatreMovieRepository) {
		super();
		this.theatreRepository = theatreRepository;
		this.movieRepository = movieRepository;
		this.authorizationService = authorizationService;
		this.theatreMovieRepository = theatreMovieRepository;
	}
	
	public void assignMovieToTheatre(TheatreMovieRequest request) {

	    authorizationService.requireManager();

	    Theatre theatre = theatreRepository.findById(request.getTheatreId())
	            .orElseThrow(() ->
	                    new TheatreNotFoundException("Theatre not found."));

	    Movie movie = movieRepository.findById(request.getMovieId())
	            .orElseThrow(() ->
	                    new MovieNotFoundException("Movie not found."));

	    if (theatreMovieRepository.existsByTheatreIdAndMovieId(
	            theatre.getId(),
	            movie.getId())) {

	    		throw new MovieAlreadyAssignedException("Movie is already assigned to this theatre.");
	    }

	    TheatreMovie theatreMovie = new TheatreMovie();

	    theatreMovie.setTheatreId(theatre.getId());
	    theatreMovie.setMovieId(movie.getId());

	    theatreMovieRepository.save(theatreMovie);
	}
	
	public void removeMovieFromTheatre(Long theatreId, Long movieId) {

	    authorizationService.requireManager();

	    TheatreMovie theatreMovie = theatreMovieRepository
	            .findByTheatreIdAndMovieId(theatreId, movieId)
	            .orElseThrow(() ->
	            		new MovieAssignmentNotFoundException("Movie is not assigned to this theatre."));

	    theatreMovieRepository.delete(theatreMovie);
	}
	
	public List<MovieResponse> getMoviesByTheatre(Long theatreId) {

	    theatreRepository.findById(theatreId)
	            .orElseThrow(() ->
	                    new TheatreNotFoundException("Theatre not found."));

	    return theatreMovieRepository.findByTheatreId(theatreId)
	            .stream()
	            .map(mapping -> movieRepository.findById(mapping.getMovieId())
	                    .orElseThrow(() ->
	                            new MovieNotFoundException("Movie not found.")))
	            .map(movie -> new MovieResponse(
	                    movie.getId(),
	                    movie.getTitle(),
	                    movie.getGenre()))
	            .collect(Collectors.toList());
	}
	
	public List<TheatreResponse> getTheatresByMovie(Long movieId) {

	    movieRepository.findById(movieId)
	            .orElseThrow(() ->
	                    new MovieNotFoundException("Movie not found."));

	    List<TheatreMovie> mappings = theatreMovieRepository.findByMovieId(movieId);

	    List<TheatreResponse> theatres = new ArrayList<>();

	    for (TheatreMovie mapping : mappings) {

	        Theatre theatre = theatreRepository.findById(mapping.getTheatreId())
	                .orElseThrow(() ->
	                        new TheatreNotFoundException("Theatre not found."));

	        theatres.add(new TheatreResponse(
	                theatre.getId(),
	                theatre.getName(),
	                theatre.getCity(),
	                theatre.getScreens()
	        ));
	    }

	    return theatres;
	}
}

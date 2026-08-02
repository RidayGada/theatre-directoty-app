package com.theatre_directory.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.theatre_directory.dto.request.MovieRequest;
import com.theatre_directory.dto.response.MovieResponse;
import com.theatre_directory.entity.Movie;
import com.theatre_directory.exception.ForbiddenOperationException;
import com.theatre_directory.exception.MovieAlreadyExistsException;
import com.theatre_directory.exception.MovieNotFoundException;
import com.theatre_directory.repository.MovieRepository;
import com.theatre_directory.repository.TheatreMovieRepository;
import com.theatre_directory.security.AuthorizationService;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final AuthorizationService authorizationService;
    private final TheatreMovieRepository theatreMovieRepository;
    
    public MovieService(MovieRepository movieRepository, AuthorizationService authorizationService,
			TheatreMovieRepository theatreMovieRepository) {
		super();
		this.movieRepository = movieRepository;
		this.authorizationService = authorizationService;
		this.theatreMovieRepository = theatreMovieRepository;
	}

	public MovieResponse createMovie(MovieRequest request) {

        authorizationService.requireManager();

        if (movieRepository.existsByTitleIgnoreCase(request.getTitle())) {
        	throw new MovieAlreadyExistsException("Movie already exists.");
        }

        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setGenre(request.getGenre());

        movie = movieRepository.save(movie);

        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getGenre()
        );
    }
    
    public List<MovieResponse> getAllMovies() {

        return movieRepository.findAll()
                .stream()
                .map(movie -> new MovieResponse(
                        movie.getId(),
                        movie.getTitle(),
                        movie.getGenre()
                ))
                .collect(Collectors.toList());
    }
    
    public MovieResponse getMovieById(Long id) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() ->
                        new MovieNotFoundException("Movie not found."));

        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getGenre()
        );
    }
    
    public MovieResponse updateMovie(Long id, MovieRequest request) {

        authorizationService.requireManager();

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() ->
                        new MovieNotFoundException("Movie not found."));

        if (!movie.getTitle().equalsIgnoreCase(request.getTitle())
                && movieRepository.existsByTitleIgnoreCase(request.getTitle())) {

            throw new MovieAlreadyExistsException(
                    "Movie with this title already exists.");
        }
        
        movie.setTitle(request.getTitle());
        movie.setGenre(request.getGenre());

        movie = movieRepository.save(movie);

        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getGenre()
        );
    }
    
    public void deleteMovie(Long id) {

        authorizationService.requireManager();

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() ->
                        new MovieNotFoundException("Movie not found."));

        if (theatreMovieRepository.existsByMovieId(id)) {
            throw new ForbiddenOperationException(
                    "Movie is assigned to one or more theatres and cannot be deleted.");
        }

        movieRepository.delete(movie);
    }
    
    
}

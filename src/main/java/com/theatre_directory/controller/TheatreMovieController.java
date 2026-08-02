package com.theatre_directory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.theatre_directory.dto.request.TheatreMovieRequest;
import com.theatre_directory.dto.response.MovieResponse;
import com.theatre_directory.dto.response.TheatreResponse;
import com.theatre_directory.service.TheatreMovieService;

@RestController
@RequestMapping("/theatre-movies")
public class TheatreMovieController {

    private final TheatreMovieService theatreMovieService;

    public TheatreMovieController(TheatreMovieService theatreMovieService) {
        this.theatreMovieService = theatreMovieService;
    }

    @PostMapping
    public ResponseEntity<String> assignMovieToTheatre(
            @RequestBody TheatreMovieRequest request) {

        theatreMovieService.assignMovieToTheatre(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Movie assigned to theatre successfully.");
    }

    @DeleteMapping
    public ResponseEntity<Void> removeMovieFromTheatre(
            @RequestParam Long theatreId,
            @RequestParam Long movieId) {

        theatreMovieService.removeMovieFromTheatre(theatreId, movieId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/theatre/{theatreId}")
    public ResponseEntity<List<MovieResponse>> getMoviesByTheatre(
            @PathVariable Long theatreId) {

        return ResponseEntity.ok(
                theatreMovieService.getMoviesByTheatre(theatreId));
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<TheatreResponse>> getTheatresByMovie(
            @PathVariable Long movieId) {

        return ResponseEntity.ok(
                theatreMovieService.getTheatresByMovie(movieId));
    }
}
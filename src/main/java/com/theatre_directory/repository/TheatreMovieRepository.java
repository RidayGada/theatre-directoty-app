package com.theatre_directory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theatre_directory.entity.TheatreMovie;

@Repository
public interface TheatreMovieRepository extends JpaRepository<TheatreMovie, Long> {

    List<TheatreMovie> findByTheatreId(Long theatreId);

    List<TheatreMovie> findByMovieId(Long movieId);

    Optional<TheatreMovie> findByTheatreIdAndMovieId(Long theatreId, Long movieId);

    boolean existsByTheatreIdAndMovieId(Long theatreId, Long movieId);
    
    boolean existsByMovieId(Long movieId);

    boolean existsByTheatreId(Long theatreId);
}
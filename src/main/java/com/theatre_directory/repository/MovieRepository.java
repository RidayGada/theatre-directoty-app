package com.theatre_directory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theatre_directory.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    boolean existsByTitleIgnoreCase(String title);

}
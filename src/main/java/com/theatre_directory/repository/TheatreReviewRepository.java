package com.theatre_directory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theatre_directory.entity.TheatreReview;

@Repository
public interface TheatreReviewRepository extends JpaRepository<TheatreReview, Long> {

    List<TheatreReview> findByTheatreId(Long theatreId);

    List<TheatreReview> findByUserId(Long userId);

    long countByTheatreId(Long theatreId);
    
    Optional<TheatreReview> findByUserIdAndTheatreId(Long userId, Long theatreId);

}
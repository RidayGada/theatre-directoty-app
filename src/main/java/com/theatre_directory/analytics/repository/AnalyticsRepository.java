package com.theatre_directory.analytics.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.theatre_directory.analytics.dto.CityTheatreCountDto;
import com.theatre_directory.analytics.dto.MostReviewedTheatreDto;
import com.theatre_directory.analytics.dto.PopularMovieDto;
import com.theatre_directory.analytics.dto.RatingDistributionDto;
import com.theatre_directory.analytics.dto.TopRatedTheatreDto;
//import com.theatre_directory.entity.Movie;
//import com.theatre_directory.entity.Theatre;
//import com.theatre_directory.entity.TheatreMovie;
//import com.theatre_directory.entity.TheatreReview;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class AnalyticsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<TopRatedTheatreDto> getTopRatedTheatres() {

        String jpql = """
            SELECT new com.theatre_directory.analytics.dto.TopRatedTheatreDto(
                t.name,
                AVG(r.rating),
                COUNT(r.id)
            )
            FROM Theatre t
            JOIN TheatreReview r
                ON t.id = r.theatreId
            GROUP BY t.id, t.name
            ORDER BY AVG(r.rating) DESC, COUNT(r.id) DESC
            """;

        return entityManager
                .createQuery(jpql, TopRatedTheatreDto.class)
                .getResultList();
    }

    public List<MostReviewedTheatreDto> getMostReviewedTheatres() {

        String jpql = """
            SELECT new com.theatre_directory.analytics.dto.MostReviewedTheatreDto(
                t.name,
                COUNT(r.id)
            )
            FROM Theatre t
            JOIN TheatreReview r
                ON t.id = r.theatreId
            GROUP BY t.id, t.name
            ORDER BY COUNT(r.id) DESC
            """;

        return entityManager
                .createQuery(jpql, MostReviewedTheatreDto.class)
                .getResultList();
    }

    public List<CityTheatreCountDto> getCityWiseTheatreCount() {

        String jpql = """
            SELECT new com.theatre_directory.analytics.dto.CityTheatreCountDto(
                t.city,
                COUNT(t.id)
            )
            FROM Theatre t
            GROUP BY t.city
            ORDER BY COUNT(t.id) DESC
            """;

        return entityManager
                .createQuery(jpql, CityTheatreCountDto.class)
                .getResultList();
    }

    public List<PopularMovieDto> getMoviesRunningInMostTheatres() {

        String jpql = """
            SELECT new com.theatre_directory.analytics.dto.PopularMovieDto(
                m.title,
                COUNT(tm.id)
            )
            FROM Movie m
            JOIN TheatreMovie tm
                ON m.id = tm.movieId
            GROUP BY m.id, m.title
            ORDER BY COUNT(tm.id) DESC
            """;

        return entityManager
                .createQuery(jpql, PopularMovieDto.class)
                .getResultList();
    }

    public List<RatingDistributionDto> getRatingDistribution() {

        String jpql = """
            SELECT new com.theatre_directory.analytics.dto.RatingDistributionDto(
                r.rating,
                COUNT(r.id)
            )
            FROM TheatreReview r
            GROUP BY r.rating
            ORDER BY r.rating DESC
            """;

        return entityManager
                .createQuery(jpql, RatingDistributionDto.class)
                .getResultList();
    }
}
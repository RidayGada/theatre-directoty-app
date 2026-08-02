package com.theatre_directory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theatre_directory.entity.Theatre;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {

    List<Theatre> findByCity(String city);

    List<Theatre> findByScreensBetween(int minScreens, int maxScreens);

}
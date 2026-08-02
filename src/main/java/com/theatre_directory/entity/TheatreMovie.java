package com.theatre_directory.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "theatre_movies")
public class TheatreMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long theatreId;

    @Column(nullable = false)
    private Long movieId;
    
    public TheatreMovie() {
    	
    }

	public TheatreMovie(Long id, Long theatreId, Long movieId) {
		super();
		this.id = id;
		this.theatreId = theatreId;
		this.movieId = movieId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(Long theatreId) {
		this.theatreId = theatreId;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
    
    
}

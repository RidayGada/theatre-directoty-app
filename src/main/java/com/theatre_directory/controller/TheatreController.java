package com.theatre_directory.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.theatre_directory.dto.request.TheatreRequest;
import com.theatre_directory.dto.response.TheatreResponse;
import com.theatre_directory.service.TheatreService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/theatres")
public class TheatreController {

	private final TheatreService theatreService;

	public TheatreController(TheatreService theatreService) {
		this.theatreService = theatreService;
	}
	
	@PostMapping
	public TheatreResponse createTheatre(@RequestBody TheatreRequest request) {
		return theatreService.createTheatre(request);
	}
	
	@GetMapping
	public List<TheatreResponse> getAllTheatres(){
		return theatreService.getAllTheatres();
	}
	
	@GetMapping("/filter")
	public List<TheatreResponse> filterTheatres(
	        @RequestParam int minScreens,
	        @RequestParam int maxScreens) {

	    return theatreService.filterTheatres(minScreens, maxScreens);
	}
	
	@PutMapping("{id}")
	public TheatreResponse updateTheatre(
	        @PathVariable Long id,
	        @RequestBody TheatreRequest request) {

	    return theatreService.updateTheatre(id, request);
	}
	
	@DeleteMapping("{id}")
	public void deleteTheatre(@PathVariable Long id) {
	    theatreService.deleteTheatre(id);
	}
	
}

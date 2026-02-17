package com.movie.moviedata.dto;

import java.util.List;

public class ComparisonResponse {
    private List<MovieComparisonDTO> movies;
    private List<String> towns;

    // Constructors
    public ComparisonResponse() {}

    public ComparisonResponse(List<MovieComparisonDTO> movies, List<String> towns) {
        this.movies = movies;
        this.towns = towns;
    }

    // Getters and Setters
    public List<MovieComparisonDTO> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieComparisonDTO> movies) {
        this.movies = movies;
    }

    public List<String> getTowns() {
        return towns;
    }

    public void setTowns(List<String> towns) {
        this.towns = towns;
    }
}

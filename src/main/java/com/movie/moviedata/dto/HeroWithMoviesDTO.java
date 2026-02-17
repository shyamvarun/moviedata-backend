package com.movie.moviedata.dto;

import java.util.List;

public class HeroWithMoviesDTO {
    private String heroCode;
    private String heroName;
    private List<MovieDTO> movies;

    // Constructor
    public HeroWithMoviesDTO(String heroCode, String heroName, List<MovieDTO> movies) {
        this.heroCode = heroCode;
        this.heroName = heroName;
        this.movies = movies;
    }

    // Getters and Setters
    public String getHeroCode() {
        return heroCode;
    }

    public void setHeroCode(String heroCode) {
        this.heroCode = heroCode;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public List<MovieDTO> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDTO> movies) {
        this.movies = movies;
    }
}

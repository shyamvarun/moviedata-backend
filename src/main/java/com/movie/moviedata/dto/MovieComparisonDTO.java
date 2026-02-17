package com.movie.moviedata.dto;

import java.util.Map;

public class MovieComparisonDTO {
    private String movieCode;
    private String title;
    private String heroName;
    private Map<String, Double> collections; // town -> amount
    private double total;

    // Constructors
    public MovieComparisonDTO() {}

    public MovieComparisonDTO(String movieCode, String title, String heroName, 
                             Map<String, Double> collections, double total) {
        this.movieCode = movieCode;
        this.title = title;
        this.heroName = heroName;
        this.collections = collections;
        this.total = total;
    }

    // Getters and Setters
    public String getMovieCode() {
        return movieCode;
    }

    public void setMovieCode(String movieCode) {
        this.movieCode = movieCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public Map<String, Double> getCollections() {
        return collections;
    }

    public void setCollections(Map<String, Double> collections) {
        this.collections = collections;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

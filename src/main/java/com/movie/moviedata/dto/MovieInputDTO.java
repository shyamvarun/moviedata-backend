package com.movie.moviedata.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class MovieInputDTO {
    
    @NotBlank(message = "Movie code is required")
    private String movieCode;
    
    @NotBlank(message = "Movie title is required")
    private String title;
    
    @NotBlank(message = "Hero code is required")
    private String heroCode;
    
    @NotBlank(message = "Day code is required")
    private String dayCode;
    
    private String releaseDate;
    
    @NotEmpty(message = "At least one collection entry required")
    @Valid
    private List<TownCollectionDTO> collections;
    
    // Default constructor
    public MovieInputDTO() {}
    
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
    
    public String getHeroCode() {
        return heroCode;
    }
    
    public void setHeroCode(String heroCode) {
        this.heroCode = heroCode;
    }
    
    public String getDayCode() {
        return dayCode;
    }
    
    public void setDayCode(String dayCode) {
        this.dayCode = dayCode;
    }
    
    public String getReleaseDate() {
        return releaseDate;
    }
    
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    public List<TownCollectionDTO> getCollections() {
        return collections;
    }
    
    public void setCollections(List<TownCollectionDTO> collections) {
        this.collections = collections;
    }
}

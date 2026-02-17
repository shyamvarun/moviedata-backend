package com.movie.moviedata.dto;

public class MovieDTO {
    private String movieCode;
    private String title;

    // Constructor
    public MovieDTO(String movieCode, String title) {
        this.movieCode = movieCode;
        this.title = title;
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
}

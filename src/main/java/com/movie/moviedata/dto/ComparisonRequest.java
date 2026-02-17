package com.movie.moviedata.dto;

import java.util.List;

public class ComparisonRequest {
    private List<String> movieCodes;
    private List<String> towns;
    private String district;
    private String dayCode;

    // Constructors
    public ComparisonRequest() {}

    public ComparisonRequest(List<String> movieCodes, List<String> towns, String district, String dayCode) {
        this.movieCodes = movieCodes;
        this.towns = towns;
        this.district = district;
        this.dayCode = dayCode;
    }

    // Getters and Setters
    public List<String> getMovieCodes() {
        return movieCodes;
    }

    public void setMovieCodes(List<String> movieCodes) {
        this.movieCodes = movieCodes;
    }

    public List<String> getTowns() {
        return towns;
    }

    public void setTowns(List<String> towns) {
        this.towns = towns;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDayCode() {
        return dayCode;
    }

    public void setDayCode(String dayCode) {
        this.dayCode = dayCode;
    }
}

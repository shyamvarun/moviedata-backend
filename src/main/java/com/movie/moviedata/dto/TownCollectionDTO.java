package com.movie.moviedata.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TownCollectionDTO {
    
    @NotBlank(message = "Town name is required")
    private String townName;
    
    @NotNull(message = "Amount is required")
    private Double amountLakhs;
    
    // Default constructor
    public TownCollectionDTO() {}
    
    // Getters and Setters
    public String getTownName() {
        return townName;
    }
    
    public void setTownName(String townName) {
        this.townName = townName;
    }
    
    public Double getAmountLakhs() {
        return amountLakhs;
    }
    
    public void setAmountLakhs(Double amountLakhs) {
        this.amountLakhs = amountLakhs;
    }
}

package com.movie.moviedata.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "movie_collections",
       uniqueConstraints = @UniqueConstraint(
           columnNames = {"movie_id", "town_id", "day_code"}
       ))
public class MovieCollection {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "town_id", nullable = false)
    private Town town;
    
    @Column(name = "day_code", nullable = false, length = 30)
    private String dayCode;
    
    @Column(name = "amount_lakhs", nullable = false, precision = 10, scale = 2)
    private BigDecimal amountLakhs;

    // Constructors
    public MovieCollection() {}
    
    public MovieCollection(Movie movie, Town town, String dayCode, BigDecimal amountLakhs) {
        this.movie = movie;
        this.town = town;
        this.dayCode = dayCode;
        this.amountLakhs = amountLakhs;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public String getDayCode() {
        return dayCode;
    }

    public void setDayCode(String dayCode) {
        this.dayCode = dayCode;
    }

    public BigDecimal getAmountLakhs() {
        return amountLakhs;
    }

    public void setAmountLakhs(BigDecimal amountLakhs2) {
        this.amountLakhs = amountLakhs2;
    }
}

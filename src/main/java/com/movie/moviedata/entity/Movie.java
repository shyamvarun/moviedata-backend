package com.movie.moviedata.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;  // ✅ CHANGED FROM Integer TO Long
    
    @Column(name = "movie_code", unique = true, nullable = false)
    private String movieCode;
    
    @Column(nullable = false)
    private String title;
    
    @Column(name = "release_date")
    private LocalDate releaseDate;
    
    @ManyToOne
    @JoinColumn(name = "hero_id", nullable = false)
    private Hero hero;
    
    @OneToMany(mappedBy = "movie")
    private List<MovieCollection> collections = new ArrayList<>();
    
    // Constructors
    public Movie() {}
    
    public Movie(String movieCode, String title, Hero hero) {
        this.movieCode = movieCode;
        this.title = title;
        this.hero = hero;
    }
    
    // Getters and Setters
    public Long getMovieId() {  // ✅ CHANGED FROM Integer TO Long
        return movieId;
    }
    
    public void setMovieId(Long movieId) {  // ✅ CHANGED FROM Integer TO Long
        this.movieId = movieId;
    }
    
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
    
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
    
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    public Hero getHero() {
        return hero;
    }
    
    public void setHero(Hero hero) {
        this.hero = hero;
    }
    
    public List<MovieCollection> getCollections() {
        return collections;
    }
    
    public void setCollections(List<MovieCollection> collections) {
        this.collections = collections;
    }
}

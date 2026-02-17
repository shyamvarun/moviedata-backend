package com.movie.moviedata.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "heroes")
public class Hero {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hero_id")
    private Long heroId;
    
    @Column(name = "hero_code", unique = true, nullable = false, length = 50)
    private String heroCode;
    
    @Column(name = "hero_name", nullable = false, length = 100)
    private String heroName;
    
    // One hero can have many movies
    @OneToMany(mappedBy = "hero", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Movie> movies = new ArrayList<>();

    // Constructors
    public Hero() {}
    
    public Hero(String heroCode, String heroName) {
        this.heroCode = heroCode;
        this.heroName = heroName;
    }

    // Getters and Setters
    public Long getHeroId() {
        return heroId;
    }

    public void setHeroId(Long heroId) {
        this.heroId = heroId;
    }

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

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}

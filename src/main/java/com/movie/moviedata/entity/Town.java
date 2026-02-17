package com.movie.moviedata.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "towns")
public class Town {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "town_id")
    private Long townId;
    
    @Column(name = "town_name", unique = true, nullable = false, length = 100)
    private String townName;
    
    @Column(name = "territory", nullable = false, length = 100)
    private String territory;
    
    @Column(name = "state", nullable = false, length = 50)
    private String state;

    // Constructors
    public Town() {}
    
    public Town(String townName, String territory) {
        this.townName = townName;
        this.territory = territory;
        this.state = "Unknown"; // default
    }
    
    public Town(String townName, String territory, String state) {
        this.townName = townName;
        this.territory = territory;
        this.state = state;
    }

    // Getters and Setters
    public Long getTownId() {
        return townId;
    }

    public void setTownId(Long townId) {
        this.townId = townId;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

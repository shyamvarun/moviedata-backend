package com.movie.moviedata.repository;

import com.movie.moviedata.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {
    Optional<Town> findByTownName(String townName);
    List<Town> findByTownNameIn(List<String> townNames);  // ADD THIS
    List<Town> findByTerritory(String territory);         // ADD THIS
}

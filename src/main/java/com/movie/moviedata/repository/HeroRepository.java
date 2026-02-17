package com.movie.moviedata.repository;

import com.movie.moviedata.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {
    Optional<Hero> findByHeroCode(String heroCode);
}

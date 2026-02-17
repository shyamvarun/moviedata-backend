package com.movie.moviedata.repository;

import com.movie.moviedata.entity.Movie;
import com.movie.moviedata.entity.MovieCollection;
import com.movie.moviedata.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieCollectionRepository extends JpaRepository<MovieCollection, Long> {
    Optional<MovieCollection> findByMovie_MovieIdAndTown_TownIdAndDayCode(
        Long movieId, Long townId, String dayCode
    );
    
    List<MovieCollection> findByTown_TownIdAndDayCode(Long townId, String dayCode);
}

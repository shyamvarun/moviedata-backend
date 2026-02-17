package com.movie.moviedata.controller;

import com.movie.moviedata.dto.*;
import com.movie.moviedata.entity.*;
import com.movie.moviedata.repository.*;
import com.movie.moviedata.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private HeroRepository heroRepository;

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieCollectionRepository movieCollectionRepository;

    // Compare movies endpoint
    @PostMapping("/compare")
    public ResponseEntity<ComparisonResponse> compareMovies(
            @RequestBody ComparisonRequest request) {
        ComparisonResponse response = movieService.compareMovies(request);
        return ResponseEntity.ok(response);
    }

    // Get top 10 movies by town
    @GetMapping("/top-movies-by-town")
    public ResponseEntity<Map<String, List<MovieComparisonDTO>>> getTopMoviesByTown(
            @RequestParam(required = false) String dayCode,
            @RequestParam(defaultValue = "100") int limit) {
        String day = (dayCode != null) ? dayCode : "DAY1";
        Map<String, List<MovieComparisonDTO>> result = movieService.getTopMoviesByTown(day, limit);
        return ResponseEntity.ok(result);
    }

    // Get heroes with their movies
    @GetMapping("/heroes-with-movies")
    public ResponseEntity<List<HeroWithMoviesDTO>> getHeroesWithMovies() {
        List<Hero> heroes = heroRepository.findAll();
        List<HeroWithMoviesDTO> result = heroes.stream()
            .map(hero -> {
                List<MovieDTO> movieDTOs = hero.getMovies().stream()
                    .map(m -> new MovieDTO(m.getMovieCode(), m.getTitle()))
                    .collect(Collectors.toList());
                return new HeroWithMoviesDTO(
                    hero.getHeroCode(),
                    hero.getHeroName(),
                    movieDTOs
                );
            })
            .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    // Get towns grouped by territory
    @GetMapping("/towns-by-territory")
    public ResponseEntity<Map<String, List<String>>> getTownsByTerritory() {
        List<Town> towns = townRepository.findAll();
        Map<String, List<String>> territoryMap = towns.stream()
            .collect(Collectors.groupingBy(
                Town::getTerritory,
                Collectors.mapping(Town::getTownName, Collectors.toList())
            ));
        return ResponseEntity.ok(territoryMap);
    }

    // Test endpoint
    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Backend is working! ✅");
    }

    // ✅ NEW: Add movie with multiple collections
    @PostMapping("/add-movie-with-collections")
    public ResponseEntity<Map<String, Object>> addMovieWithCollections(
            @RequestBody MovieInputDTO movieInput) {
        try {
            movieService.ingestCollection(movieInput);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Movie and collections added/updated successfully");
            response.put("movieCode", movieInput.getMovieCode());
            response.put("dayCode", movieInput.getDayCode());
            response.put("townsUpdated", movieInput.getCollections().size());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Add single collection
    @PostMapping("/add-collection")
    public ResponseEntity<Map<String, Object>> addSingleCollection(@RequestBody Map<String, Object> request) {
        try {
            String movieCode = (String) request.get("movieCode");
            String townName = (String) request.get("townName");
            String dayCode = (String) request.get("dayCode");
            Double amountLakhs = Double.parseDouble(request.get("amountLakhs").toString());

            // Find movie
            Movie movie = movieRepository.findByMovieCode(movieCode)
                .orElseThrow(() -> new RuntimeException("Movie not found: " + movieCode));

            // Find town
            Town town = townRepository.findByTownName(townName)
                .orElseThrow(() -> new RuntimeException("Town not found: " + townName));

            // Check if collection exists
            Optional<MovieCollection> existing = movieCollectionRepository
                .findByMovie_MovieIdAndTown_TownIdAndDayCode(
                    movie.getMovieId(), town.getTownId(), dayCode
                );

            MovieCollection collection;
            if (existing.isPresent()) {
                collection = existing.get();
                collection.setAmountLakhs(amountLakhs);
            } else {
                collection = new MovieCollection();
                collection.setMovie(movie);
                collection.setTown(town);
                collection.setDayCode(dayCode);
                collection.setAmountLakhs(amountLakhs);
            }

            movieCollectionRepository.save(collection);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Collection saved successfully");
            response.put("data", collection);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}

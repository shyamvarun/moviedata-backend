package com.movie.moviedata.service;

import com.movie.moviedata.dto.*;
import com.movie.moviedata.entity.*;
import com.movie.moviedata.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {
    
    private final MovieRepository movieRepository;
    private final HeroRepository heroRepository;
    private final TownRepository townRepository;
    private final MovieCollectionRepository collectionRepository;
    private final DistrictTownService districtService;
    
    // Constructor
    public MovieService(MovieRepository movieRepository,
                       HeroRepository heroRepository,
                       TownRepository townRepository,
                       MovieCollectionRepository collectionRepository,
                       DistrictTownService districtService) {
        this.movieRepository = movieRepository;
        this.heroRepository = heroRepository;
        this.townRepository = townRepository;
        this.collectionRepository = collectionRepository;
        this.districtService = districtService;
    }
    
    @Transactional
    public void ingestCollection(MovieInputDTO dto) {
        System.out.println("=== STARTING INGESTION ===");
        System.out.println("Movie: " + dto.getMovieCode() + ", Hero: " + dto.getHeroCode() + ", Day: " + dto.getDayCode());
        
        // Get or create hero
        Hero hero = heroRepository.findByHeroCode(dto.getHeroCode())
            .orElseGet(() -> {
                System.out.println("Creating new hero: " + dto.getHeroCode());
                Hero h = new Hero(dto.getHeroCode(), dto.getHeroCode().toUpperCase());
                return heroRepository.save(h);
            });
        
        // Get or create movie
        Movie movie = movieRepository.findByMovieCode(dto.getMovieCode())
            .orElseGet(() -> {
                System.out.println("Creating new movie: " + dto.getMovieCode());
                Movie m = new Movie(dto.getMovieCode(), dto.getTitle(), hero);
                
                if (dto.getReleaseDate() != null && !dto.getReleaseDate().isEmpty()) {
                    m.setReleaseDate(LocalDate.parse(dto.getReleaseDate()));
                }
                
                return movieRepository.save(m);
            });
        
        // Process collections
        for (TownCollectionDTO tc : dto.getCollections()) {
            Town town = townRepository.findByTownName(tc.getTownName())
                .orElseGet(() -> {
                    System.out.println("Creating new town: " + tc.getTownName());
                    Town t = new Town(tc.getTownName(), "Unknown");
                    return townRepository.save(t);
                });
            
            BigDecimal amountLakhs = BigDecimal.valueOf(tc.getAmountLakhs());
            
            Optional<MovieCollection> existing = collectionRepository
                .findByMovie_MovieIdAndTown_TownIdAndDayCode(
                    movie.getMovieId(), town.getTownId(), dto.getDayCode()
                );
            
            if (existing.isPresent()) {
                MovieCollection mc = existing.get();
                mc.setAmountLakhs(amountLakhs);
                collectionRepository.save(mc);
            } else {
                MovieCollection mc = new MovieCollection(movie, town, dto.getDayCode(), amountLakhs);
                collectionRepository.save(mc);
            }
        }
        
        System.out.println("=== INGESTION COMPLETE ===");
    }
    
    public ComparisonResponse compareMovies(ComparisonRequest request) {
        List<Movie> movies = new ArrayList<>();
        
        // Fetch all requested movies
        for (String movieCode : request.getMovieCodes()) {
            movieRepository.findByMovieCode(movieCode)
                .ifPresent(movies::add);
        }
        
        if (movies.isEmpty()) {
            throw new RuntimeException("No movies found");
        }
        
        // Get all towns
        List<Town> towns;
        if (request.getTowns() != null && !request.getTowns().isEmpty()) {
            towns = townRepository.findByTownNameIn(request.getTowns());
        } else if (request.getDistrict() != null) {
            towns = townRepository.findByTerritory(request.getDistrict());
        } else {
            towns = townRepository.findAll();
        }
        
        List<String> townNames = towns.stream()
            .map(Town::getTownName)
            .sorted()
            .collect(Collectors.toList());
        
        // Build response for ALL movies (even those with no data)
        List<MovieComparisonDTO> movieDTOs = new ArrayList<>();
        
        for (Movie movie : movies) {
            Map<String, Double> collections = new HashMap<>();
            double total = 0.0;
            
            for (Town town : towns) {
                Optional<MovieCollection> collection = collectionRepository
                    .findByMovie_MovieIdAndTown_TownIdAndDayCode(
                        movie.getMovieId(),
                        town.getTownId(),
                        request.getDayCode()
                    );
                
                if (collection.isPresent()) {
                    double amount = collection.get().getAmountLakhs().doubleValue();
                    collections.put(town.getTownName(), amount);
                    total += amount;
                } else {
                    collections.put(town.getTownName(), null);
                }
            }
            
            MovieComparisonDTO dto = new MovieComparisonDTO();
            dto.setMovieCode(movie.getMovieCode());
            dto.setTitle(movie.getTitle());
            dto.setHeroName(movie.getHero().getHeroName());
            dto.setCollections(collections);
            dto.setTotal(total);
            
            movieDTOs.add(dto);
        }
        
        ComparisonResponse response = new ComparisonResponse();
        response.setMovies(movieDTOs);
        response.setTowns(townNames);
        
        return response;
    }
    
    // ✅ NEW METHOD: Get top 10 movies for each town separately
    public Map<String, List<MovieComparisonDTO>> getTopMoviesByTown(String dayCode, int limit) {
        List<Town> allTowns = townRepository.findAll();
        Map<String, List<MovieComparisonDTO>> result = new LinkedHashMap<>();
        
        for (Town town : allTowns) {
            // Get all collections for this town and dayCode
            List<MovieCollection> collections = collectionRepository
                .findByTown_TownIdAndDayCode(town.getTownId(), dayCode);
            
            // Convert to DTOs and sort by amount
            List<MovieComparisonDTO> topMovies = collections.stream()
                .sorted((c1, c2) -> c2.getAmountLakhs().compareTo(c1.getAmountLakhs()))
                .limit(limit)
                .map(mc -> {
                    MovieComparisonDTO dto = new MovieComparisonDTO();
                    dto.setMovieCode(mc.getMovie().getMovieCode());
                    dto.setTitle(mc.getMovie().getTitle());
                    dto.setHeroName(mc.getMovie().getHero().getHeroName());
                    
                    Map<String, Double> singleCollection = new HashMap<>();
                    singleCollection.put(town.getTownName(), mc.getAmountLakhs().doubleValue());
                    dto.setCollections(singleCollection);
                    dto.setTotal(mc.getAmountLakhs().doubleValue());
                    
                    return dto;
                })
                .collect(Collectors.toList());
            
            result.put(town.getTownName(), topMovies);
        }
        
        return result;
    }
}

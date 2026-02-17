package com.movie.moviedata.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DistrictTownService {
    
    // Box-office territory-based mapping (traditional Telugu film distribution areas)
    private static final Map<String, List<String>> DISTRICT_TOWNS = Map.ofEntries(
        
        // ═══════════════════════════════════════════════════════════
        // ANDHRA PRADESH TERRITORIES
        // ═══════════════════════════════════════════════════════════
        
        // EAST GODAVARI
        Map.entry("East", List.of(
            "Rajahmundry", "Kakinada", "Amalapuram", "Peddapuram",
            "Tuni", "Rampachodavaram", "Mandapeta", "Razole",
            "Rajanagaram", "Samalkot", "Pithapuram"
        )),
        
        // WEST GODAVARI
        Map.entry("West", List.of(
            "Bhimavaram", "Tanuku", "Tadepalligudem", "Eluru", "Palakollu",
            "Narasapuram", "Kovvur", "Nidadavole", "Akividu", "Undi",
            "Attili", "Chintalapudi", "Jangareddigudem", "Polavaram"
        )),
        
        // KRISHNA (NTR District)
        Map.entry("Krishna", List.of(
            "Vijayawada", "Machilipatnam", "Gudivada", "Jaggaiahpet",
            "Nuzvid", "Nandigama", "Tiruvuru", "Mylavaram", "Kankipadu",
            "Avanigadda", "Pedana", "Vuyyuru", "Gannavaram"
        )),
        
        // GUNTUR
        Map.entry("Guntur", List.of(
            "Guntur", "Tenali", "Narasaraopet", "Bapatla", "Sattenapalle",
            "Mangalagiri", "Ponnur", "Repalle", "Chilakaluripet", "Vinukonda",
            "Macherla", "Piduguralla", "Ongole", "Chirala", "Markapur",
            "Kandukur", "Addanki", "Kanigiri"
        )),
        
        // NELLORE (SPSR Nellore + Prakasam areas)
        Map.entry("Nellore", List.of(
            "Nellore", "Kavali", "Gudur", "Atmakur", "Sullurpeta",
            "Venkatagiri", "Udayagiri", "Kovur"
        )),
        
        // UTTARA ANDHRA (North Coastal AP - Vizag, Vizianagaram, Srikakulam)
        Map.entry("Uttara Andhra", List.of(
            // Visakhapatnam
            "Visakhapatnam", "Anakapalle", "Narsipatnam", "Yelamanchili",
            "Bheemunipatnam", "Gajuwaka", "Pendurthi",
            // Vizianagaram
            "Vizianagaram", "Bobbili", "Parvathipuram", "Salur",
            "Gajapathinagaram", "Cheepurupalle",
            // Srikakulam
            "Srikakulam", "Amadalavalasa", "Palasa", "Ichchapuram",
            "Narasannapeta", "Tekkali"
        )),
        
        // CEDED (Rayalaseema - Anantapur, Kurnool, Kadapa, Chittoor)
        Map.entry("Ceded", List.of(
            // Anantapur
            "Anantapur", "Dharmavaram", "Hindupur", "Guntakal",
            "Tadipatri", "Kadiri", "Penukonda",
            // Kurnool
            "Kurnool", "Nandyal", "Adoni", "Yemmiganur", "Dhone",
            // Kadapa (YSR)
            "Kadapa", "Proddatur", "Jammalamadugu", "Rajampet", "Pulivendula",
            // Chittoor
            "Tirupati", "Chittoor", "Madanapalle", "Srikalahasti",
            "Puttur", "Nagari"
        )),
        
        
        // ═══════════════════════════════════════════════════════════
        // TELANGANA TERRITORIES
        // ═══════════════════════════════════════════════════════════
        
        // NIZAM DISTRICTS (Telangana excluding Hyderabad)
        Map.entry("Nizam Districts", List.of(
            // Warangal
            "Warangal", "Hanamkonda", "Kazipet", "Jangaon", "Mahabubabad",
            // Karimnagar
            "Karimnagar", "Jagitial", "Peddapalli", "Ramagundam", "Sircilla",
            // Khammam
            "Khammam", "Kothagudem", "Bhadrachalam", "Madhira", "Yellandu",
            // Nalgonda
            "Nalgonda", "Suryapet", "Miryalaguda", "Bhongir", "Devarakonda",
            // Nizamabad
            "Nizamabad", "Kamareddy", "Bodhan", "Armoor", "Banswada",
            // Adilabad
            "Adilabad", "Mancherial", "Nirmal", "Bhainsa", "Bellampalli",
            // Mahbubnagar
            "Mahbubnagar", "Wanaparthy", "Gadwal", "Nagarkurnool",
            // Medak
            "Sangareddy", "Siddipet", "Patancheru", "Zaheerabad"
        )),
        
        // NIZAM CITY (Greater Hyderabad)
        Map.entry("Nizam City", List.of(
            "Hyderabad", "Secunderabad", "Kukatpally", "LB Nagar",
            "Uppal", "Dilsukhnagar", "Ameerpet", "Abids",
            "Mehdipatnam", "Kompally", "Shamshabad", "Gachibowli",
            "HITEC City", "Madhapur", "Miyapur", "Charminar",
            "Malkajgiri", "Alwal", "Patancheru"
        ))
    );
    
    /**
     * Get list of towns for a box-office territory
     * 
     * @param territory Territory name (East, West, Krishna, etc.)
     * @param townsOverride Optional list to override default mapping
     * @return List of town names
     */
    public List<String> getTowns(String territory, List<String> townsOverride) {
        if (townsOverride != null && !townsOverride.isEmpty()) {
            return townsOverride;
        }
        
        return DISTRICT_TOWNS.getOrDefault(territory, Collections.emptyList());
    }
    
    /**
     * Get all available territories
     */
    public Set<String> getAllTerritories() {
        return DISTRICT_TOWNS.keySet();
    }
    
    /**
     * Check if a territory exists in the mapping
     */
    public boolean territoryExists(String territory) {
        return DISTRICT_TOWNS.containsKey(territory);
    }
}

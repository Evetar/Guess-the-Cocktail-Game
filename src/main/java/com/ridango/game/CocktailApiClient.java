package com.ridango.game;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CocktailApiClient {

    private static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/random.php";
    private final RestTemplate restTemplate;

    // Constructor to inject RestTemplate
    public CocktailApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Method to get a random cocktail from the API
    public Cocktail getRandomCocktail() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(BASE_URL);
        CocktailResponse response = restTemplate.getForObject(uriBuilder.toUriString(), CocktailResponse.class);

        if (response != null && response.getDrinks() != null && !response.getDrinks().isEmpty()) {
            return response.getDrinks().get(0); // Return the first cocktail
        } else {
            throw new RuntimeException("Failed to retrieve cocktail from API");
        }
    }
}


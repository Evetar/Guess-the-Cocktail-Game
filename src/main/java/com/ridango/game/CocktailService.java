package com.ridango.game;

import com.ridango.game.Cocktail;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Random;

@Service
public class CocktailService {
    private static final String API_URL = "https://www.thecocktaildb.com/api/json/v1/1/random.php";
    private final RestTemplate restTemplate = new RestTemplate();

    public Cocktail getRandomCocktail() {
        CocktailResponse response = restTemplate.getForObject(API_URL, CocktailResponse.class);
        return response.getDrinks().get(0);
    }
}

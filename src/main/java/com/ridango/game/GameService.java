package com.ridango.game;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.ridango.game.CocktailApiClient;


@Service
public class GameService {

    private final CocktailApiClient cocktailApiClient;  // Client to call the cocktail API
    private int attemptsLeft;
    private int score;
    private int highScore;
    private Cocktail currentCocktail;
    private List<String> usedCocktails;
    private String revealedName;

    public GameService(CocktailApiClient cocktailApiClient) {
        this.cocktailApiClient = cocktailApiClient;
        this.usedCocktails = new ArrayList<>();
        startNewGame();
    }

    public void startNewGame() {
        score = 0;
        highScore = Math.max(highScore, score);  // Update high score before resetting
        usedCocktails.clear();
        nextCocktail();
    }

    public boolean guessCocktail(String guess) {
        if (guess.equalsIgnoreCase(currentCocktail.getStrDrink())) {
            score += attemptsLeft;  // Increase score based on attempts left
            nextCocktail();  // Move to the next cocktail
            return true;
        } else {
            attemptsLeft--;
            if (attemptsLeft <= 0) {
                highScore = Math.max(highScore, score);  // Update high score if game ends
                return false;  // Game over
            }
            revealNextHint();  // Reveal a new letter in the cocktail name
            return false;
        }
    }

    private void nextCocktail() {
        currentCocktail = getRandomCocktail();
        usedCocktails.add(currentCocktail.getStrDrink());  // Add to used cocktails
        attemptsLeft = 5;
        revealedName = generateUnderscores(currentCocktail.getStrDrink().length());
    }

    private Cocktail getRandomCocktail() {
        Cocktail cocktail;
        do {
            cocktail = cocktailApiClient.getRandomCocktail();
        } while (usedCocktails.contains(cocktail.getStrDrink()));  // Ensure no repeats
        return cocktail;
    }

    private String generateUnderscores(int length) {
        StringBuilder underscores = new StringBuilder();
        for (int i = 0; i < length; i++) {
            underscores.append("_ ");
        }
        return underscores.toString().trim();
    }

    private void revealNextHint() {
        char[] revealedArray = revealedName.replace(" ", "").toCharArray();
        String actualName = currentCocktail.getStrDrink();

        // Reveal random letters in the name, ensuring no duplicates are revealed
        Random random = new Random();
        for (int i = 0; i < 2; i++) {  // Reveal 2 new letters on each wrong guess
            int index;
            do {
                index = random.nextInt(actualName.length());
            } while (revealedArray[index] != '_');  // Avoid revealing an already revealed letter

            revealedArray[index] = actualName.charAt(index);
        }

        // Rebuild the revealed name with spaces
        StringBuilder sb = new StringBuilder();
        for (char c : revealedArray) {
            sb.append(c).append(" ");
        }
        revealedName = sb.toString().trim();
    }

    public String revealHint() {
        return "Current guess: " + revealedName
                + "\nInstructions: " + currentCocktail.getStrInstructions()
                + "\nCategory: " + currentCocktail.getStrCategory()
                + "\nGlass: " + currentCocktail.getStrGlass()
                + "\nIngredients: " + getIngredients();
    }

    private String getIngredients() {
        // Combine all ingredients from the cocktail API response
        StringBuilder ingredients = new StringBuilder();
        if (currentCocktail.getStrIngredient1() != null) ingredients.append(currentCocktail.getStrIngredient1()).append(", ");
        if (currentCocktail.getStrIngredient2() != null) ingredients.append(currentCocktail.getStrIngredient2()).append(", ");
        if (currentCocktail.getStrIngredient3() != null) ingredients.append(currentCocktail.getStrIngredient3()).append(", ");
        if (currentCocktail.getStrIngredient4() != null) ingredients.append(currentCocktail.getStrIngredient4()).append(", ");
        // Trim final comma and return
        return ingredients.toString().replaceAll(", $", "");
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public int getCurrentScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }
}

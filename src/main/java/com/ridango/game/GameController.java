package com.ridango.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/start-game")
    public String startGame() {
        gameService.startNewGame();
        return "Game started!";
    }

    @GetMapping("/guess")
    public String guess(@RequestParam String guess) {
        boolean correct = gameService.guessCocktail(guess);
        if (correct) {
            return "Correct! Score: " + gameService.getCurrentScore();
        } else {
            return "Wrong! " + gameService.revealHint() + " Attempts left: " + gameService.getAttemptsLeft();
        }
    }

    @GetMapping("/high-score")
    public int getHighScore() {
        return gameService.getHighScore();
    }
}



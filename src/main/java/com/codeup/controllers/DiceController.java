package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DiceController {
    private int getRandomInt(int min, int max) {
        return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
    }

    @GetMapping("/roll-dice/{guess}")
    public String rollDice(@PathVariable int guess, Model model) {
        int roll = getRandomInt(1, 6);

        model.addAttribute("roll", roll);
        model.addAttribute("guess", guess);
        model.addAttribute("userGuessedCorrectly", roll == guess);

        return "dice-rolling-result";
    }
}

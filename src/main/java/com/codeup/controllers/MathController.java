/*
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{number1}/and/{number2}")
    public String add(@PathVariable int number1, @PathVariable int number2, Model model) {
//        return number1 + number2;
        model.addAttribute("number1", number1);
        model.addAttribute("number2", number2);

        model.addAttribute("result", number1 + number2);

        model.addAttribute("numbers", new int[]{1, 2, 3, 4, 5});

        model.addAttribute("myBoolean", false);

        return "arithmetic/addition/result";
    }










    @GetMapping("/subtract/{number1}/from/{number2}")
    @ResponseBody
    public int subtract(@PathVariable int number1, @PathVariable int number2) {
        return number1 - number2;
    }

    @GetMapping("/multiply/{number1}/and/{number2}")
    @ResponseBody
    public int multiply(@PathVariable int number1, @PathVariable int number2) {
        return number1 * number2;
    }

    @GetMapping("/divide/{number1}/by/{number2}")
    @ResponseBody
    public double divide(@PathVariable int number1, @PathVariable int number2) {
        return (double)  number1 / number2;
    }
}

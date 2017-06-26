package com.codeup.controllers;

import com.codeup.models.Ad;
import com.codeup.models.User;
import com.codeup.repositories.AdsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AdsController {
    private AdsRepository adsDao;

    @Autowired
    public AdsController(AdsRepository adsDao) {
        this.adsDao = adsDao;
    }

    @GetMapping("/ads")
    public String index(Model model) {

        Iterable<Ad> ads = adsDao.findAll();

        // Just a small test to find an Ad by it's title.
//        Ad ad = adsDao.findByTitle("test");
//        System.out.println(ad.getDescription());

        // In order to searc by title we can retrieve a list of ads that matches the title provided by the users input.
//        List<Ad> ads = adsDao.findByTitleIsLike("%test%");

        model.addAttribute("ads", ads);


        return "ads/index";
    }

    @GetMapping("/ads/{id}")
    public String show(@PathVariable long id, Model model) {
        Ad ad = adsDao.findOne(id);
        model.addAttribute("ad", ad);
        return  "ads/show";
    }

    @GetMapping("/ads/create")
    public String showAdForm(Model model) {
        model.addAttribute("ad", new Ad()); // We only need empty models on get requests
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public String saveAd(
        @Valid Ad ad,
        Errors validation,
        Model model  // Model model = new Model();
    ) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("ad", ad);
            return "ads/create";
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ad.setAuthor(user);
        adsDao.save(ad);
        model.addAttribute("ad", ad);
        return "redirect:/ads";
    }
}

package com.codeup.controllers;

import com.codeup.models.Ad;
import com.codeup.svcs.AdSvc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdsController {
    private AdSvc adsDao;

    public AdsController(AdSvc adsDao) {
        this.adsDao = adsDao;
    }

    @GetMapping("/ads")
    public String index(Model model) {
        List<Ad> ads = adsDao.findAll();
        model.addAttribute("ads", ads);
        // TODO: create this view / html file
        return "ads/index";
    }

    @GetMapping("/ads/{id}")
    @ResponseBody
    public String show(@PathVariable long id) {
        return "viewing ad #" + id;
    }

    @GetMapping("/ads/create")
    public String showAdForm(Model model) {
        model.addAttribute("ad", new Ad()); // We only need empty models on get requests
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public String saveAd(
        @RequestParam(name = "title") String title, // String title = request.getParameter("title")
        @RequestParam(name = "description") String description,
        Model model  // Model model = new Model();
    ) {
        Ad ad = new Ad(title, description);
        model.addAttribute("ad", ad);
        return "ads/create";
    }
}

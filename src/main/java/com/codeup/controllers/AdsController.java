package com.codeup.controllers;

import com.codeup.models.Ad;
import com.codeup.svcs.AdSvc;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    public String showAdForm() {
        return null;
    }

    public String saveAd() {
        return null;
    }
}

package com.codeup.repositories;

import com.codeup.models.Ad;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by fer on 6/21/17.
 */
public interface AdsRepository extends CrudRepository<Ad, Long> {
    // All the custom methods can be declared in here.

    // Finds an Ad by it's title
    Ad findByTitle(String title);
    // Finds a list of ads that matches something like the title.
    List<Ad> findByTitleIsLike(String title);
}

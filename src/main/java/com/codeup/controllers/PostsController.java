/*
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.controllers;

import com.codeup.models.Post;
import com.codeup.svcs.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: add posts svc

@Controller
public class PostsController {

    private final PostSvc postSvc;

    @Autowired
    public PostsController(PostSvc postSvc) {
        this.postSvc = postSvc;
    }

    @GetMapping("/posts")
    public String viewAll(Model model) {
        Iterable<Post> posts = postSvc.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewIndividualPost(@PathVariable long id, Model model) {
        // Inside the method that shows an individual post, create a new post object and pass it to the view.
        Post post = postSvc.findOne(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")  // what we type in the browser
    public String showPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create"; // this is the location of the template in the templates directory
    }

    @PostMapping("/posts/create")
    public String savePost(
        @RequestParam(name = "title") String title,
        @RequestParam(name = "body") String body,
        Model model
    ) {
        Post post = new Post(title, body);
        postSvc.save(post);
        model.addAttribute("post", post);
        return "posts/create";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        // TODO: Find this post in the data source using the service
        Post post = postSvc.findOne(id);
        // TODO: Pass the post found to the view
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@ModelAttribute Post post){
        postSvc.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @PostMapping("/post/delete")
    public String deletePost(@ModelAttribute Post post, Model model){
        postSvc.deletePost(post.getId());
        model.addAttribute("msg", "Your post was deleted correctly");
        return "return the view with a success message";
    }
}

package com.codeup.svcs;

import com.codeup.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by fer on 6/20/17.
 */
@Service("postSvc")
public class PostSvc {

    List<Post> posts = new ArrayList<>();

    public PostSvc(){
        createPosts();
    }

    public List<Post> findAll(){
        return posts;
    }

    public Post findOne(long id){
        return posts.get((int) id - 1);
    }

    public Post save(Post post){
        post.setId(( long) posts.size() + 1);
        posts.add(post);
        return post;
    }

    private void createPosts(){
        save(new Post("ps4", "shiny and new"));
        save(new Post("xbox juan", "not so shiny and new"));
    }

    public void createPost(String title, String body){
        save(new Post(title, body));
    }



}

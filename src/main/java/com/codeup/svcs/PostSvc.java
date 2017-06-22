package com.codeup.svcs;

import com.codeup.models.Post;
import com.codeup.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by fer on 6/20/17.
 */
@Service("postSvc")
public class PostSvc {
    // Autowire an instance of X in class Y mean
    // 1. Add a private property of type X within the class Y
    // 2. Create/update the constructor of the class Y where you added the property,
    // to receive one more argument of type X
    // 3. Add the @Autowired annotation to the constructor if needed.

    private PostsRepository postsDao;  // Step 1


    List<Post> posts = new ArrayList<>();

    @Autowired  // Constructor injection (add one more parameter to the constructor)
    public PostSvc(PostsRepository postsDao){  // Step 2
        this.postsDao = postsDao;
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

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

    @Autowired  // Constructor injection (add one more parameter to the constructor)
    public PostSvc(PostsRepository postsDao){  // Step 2
        this.postsDao = postsDao;
    }

    public Iterable<Post> findAll(){
        return postsDao.findAll();  // select * from posts
    }

    public Post findOne(long id){
        return postsDao.findOne(id); // select * from posts where id = ?
    }

    public Post save(Post post){
        postsDao.save(post); // insert into posts(title, body) values (?, ?)
        return post;
    }
}

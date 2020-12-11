package com.pelucasistemas.anoringa.controller;

import com.pelucasistemas.anoringa.exception.ResourceNotFoundException;
import com.pelucasistemas.anoringa.model.post.Post;
import com.pelucasistemas.anoringa.requestmodels.PostRequest;
import com.pelucasistemas.anoringa.model.user.User;
import com.pelucasistemas.anoringa.repository.PostRepository;
import com.pelucasistemas.anoringa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500","0.0.0.0","http://test.mydomain.com/"})
    @GetMapping("/posts")
    public List<Post> getAllUser() {
        return postRepository.findAll();
    }



    private boolean userAuth(String username,String password){
        System.out.println("New Auth");
        System.out.println(username);
        System.out.println(password);


        try {
            User findedUser = userRepository.findByUsername(username);

            System.out.println("Username");
            System.out.println(findedUser.getUsername());
            System.out.println("Token");
            System.out.println(findedUser.getPassword());

            if(findedUser.getPassword().equals(password)){
                System.out.println("OK");
                // DO Something
            }
            else {
                System.out.println("KO");
                // DO Something
            }
        } catch (NullPointerException e) {
            System.out.print("Caught the NullPointerException");
        }

        return false;
    }

    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody PostRequest postRequest) throws Exception {
        //System.out.println("New Request");
        //System.out.println(post.getToken());
        //System.out.println(Validator.sendToValitation(user.getToken()));
        
        //String testeable = "123";
        //Validator.sendToValitation(user.getToken()


        System.out.println("New Search");
        System.out.println(postRequest.getUsername());

        try {
            User findedUser = userRepository.findByUsername(postRequest.getUsername());

            System.out.println("Username");
            System.out.println(findedUser.getUsername());
            System.out.println("Token");
            System.out.println(findedUser.getPassword());

            if(findedUser.getPassword().equals(postRequest.getPassword())){
                System.out.println("OK");

                Post newPost = new Post(postRequest.getTitle(),postRequest.getTitle(),postRequest.getPhoto(),findedUser);
                return postRepository.save(newPost);
            }
            else {
                System.out.println("KO");

                User usererror = new User("ERROR","ERROR","ERROR");
                Post posterror = new Post("ERROR","ERROR","ERROR",usererror);

                return posterror;
            }




        } catch (NullPointerException e) {
            System.out.print("Caught the NullPointerException");
        }


        return null;
    }

    @CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500","0.0.0.0"})
    @GetMapping("/posts/{id}")
    public Post getUserById(@PathVariable(value = "id") Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", postId));
    }

    @PutMapping("/posts/{id}")
    public Post updateUser(@PathVariable(value = "id") Long userId,
                                           @Valid @RequestBody User userDetails) {

        Post post = postRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        //user.setUsername(userDetails.getUsername());
        //user.setIp(userDetails.getIp());
        //post.setLastActivity();

        Post updatedPost = postRepository.save(post);
        return updatedPost;
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        postRepository.delete(post);

        return ResponseEntity.ok().build();
    }
}

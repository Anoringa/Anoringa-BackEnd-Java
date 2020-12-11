package com.pelucasistemas.anoringa.controller;

import com.pelucasistemas.anoringa.exception.ResourceNotFoundException;
import com.pelucasistemas.anoringa.model.comment.Comment;
import com.pelucasistemas.anoringa.model.post.Post;
import com.pelucasistemas.anoringa.requestmodels.CommentRequest;
import com.pelucasistemas.anoringa.model.user.User;
import com.pelucasistemas.anoringa.repository.CommentRepository;
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
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/comments")
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




    @PostMapping("/comments")
    public Comment createPost(@Valid @RequestBody CommentRequest commentRequest) throws Exception {
        //System.out.println("New Request");
        //System.out.println(post.getToken());
        //System.out.println(Validator.sendToValitation(user.getToken()));

        //String testeable = "123";
        //Validator.sendToValitation(user.getToken()


        System.out.println("New Search");
        System.out.println(commentRequest.getUsername());

        try {
            User findedUser = userRepository.findByUsername(commentRequest.getUsername());

            System.out.println("Username");
            System.out.println(findedUser.getUsername());
            System.out.println("Token");
            System.out.println(findedUser.getPassword());

            if(findedUser.getPassword().equals(commentRequest.getPassword())){
                System.out.println("OK");

                Post findedPost = postRepository.findByIdEquals(commentRequest.getPostid());

                Comment newComment = new Comment(findedUser,findedPost,commentRequest.getContent());

                return commentRepository.save(newComment);
            }
            else {
                System.out.println("KO");

                User usererror = new User("ERROR","ERROR","ERROR");
                Post posterror = new Post("ERROR","ERROR","ERROR",usererror);
                Comment commentedError = new Comment(usererror,posterror,"ERROR");

                return commentedError;
            }




        } catch (NullPointerException e) {
            System.out.print("Caught the NullPointerException");
        }


        return null;
    }

    @GetMapping("/comments/{id}")
    public Post getUserById(@PathVariable(value = "id") Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", postId));
    }

    @PutMapping("/comments/{id}")
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

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        postRepository.delete(post);

        return ResponseEntity.ok().build();
    }
}

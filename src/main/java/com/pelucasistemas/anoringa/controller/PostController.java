package com.pelucasistemas.anoringa.controller;

import com.google.gson.Gson;
import com.pelucasistemas.anoringa.exception.ResourceNotFoundException;
import com.pelucasistemas.anoringa.model.comment.Comment;
import com.pelucasistemas.anoringa.model.comment.childs.CommentInfo;
import com.pelucasistemas.anoringa.model.post.Post;
import com.pelucasistemas.anoringa.model.post.childs.PostData;
import com.pelucasistemas.anoringa.model.post.childs.PostLite;
import com.pelucasistemas.anoringa.repository.CommentRepository;
import com.pelucasistemas.anoringa.requestmodels.PostRequest;
import com.pelucasistemas.anoringa.model.user.User;
import com.pelucasistemas.anoringa.repository.PostRepository;
import com.pelucasistemas.anoringa.repository.UserRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
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

    @Autowired
    CommentRepository commentRepository;


    //@GetMapping("/posts/all")
    @CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500","http://mediawiki.test","http://127.0.0.1"})
    @RequestMapping(value = "/posts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllPostsOriginal() throws JSONException, IOException {

            List<Post> listaPost = postRepository.findAll();

            //JSONArray sortedJSONArray = postRepository.findAll();

            String json = new Gson().toJson(listaPost);

            List<Object> listOfStudents = new ArrayList<>();

            //String listOfStudents = new Gson()
            for (Post postesasda : listaPost) {

                PostLite postInterated = new PostLite(postesasda);
                //System.out.println("New PostLite");
                //System.out.println(postInterated.getPostData());

                listOfStudents.add(postInterated.getPostData());
            }
            JSONArray jsArray = new JSONArray(listOfStudents);


            return listOfStudents.toString();
        }
    /*
    @GetMapping("/allposts")
    public List<PostInfo> getAllPosts() {
        List<Post> listaPost = postRepository.findAll();

        List<PostInfo> listOfStudents = new ArrayList<>();
        for (Post postesasda : listaPost) {

            List<Comment> listaDeComentarios = commentRepository.findAllByPostEquals(postesasda);
            List<CommentInfo> listaDeComentariosInfo = new ArrayList<>();

            for (Comment cometarius : listaDeComentarios) {
                CommentInfo commentInfoInterated = new CommentInfo(cometarius);
                listaDeComentariosInfo.add(commentInfoInterated);

            }

            PostInfo postInterated = new PostInfo(postesasda,listaDeComentariosInfo);
            listOfStudents.add(postInterated);
        }
        return listOfStudents;
    }
    */
    /*
    @GetMapping("/posts/all")
    public String getAllPosts() throws JSONException {

        List<Post> listaPost = postRepository.findAll();

        List<JSONObject> listOfStudents = new ArrayList<>();
        for (Post postesasda : listaPost) {


            PostLite postInterated = new PostLite(postesasda);
            System.out.println("New PostLite");
            System.out.println(postInterated.getPostData());

            listOfStudents.add(postInterated.getPostData());
        }
        return listOfStudents.toString();
    }
    */
    //@GetMapping("/posts/all")
    @CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500","http://mediawiki.test","http://127.0.0.1"})
    @RequestMapping(value = "/posts/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllPosts() throws JSONException, IOException {

        List<Post> listaPost = postRepository.findAll();

        //JSONArray sortedJSONArray = postRepository.findAll();

        String json = new Gson().toJson(listaPost);

        List<Object> listOfStudents = new ArrayList<>();

        //String listOfStudents = new Gson()
        for (Post postesasda : listaPost) {

            PostLite postInterated = new PostLite(postesasda);
            //System.out.println("New PostLite");
            //System.out.println(postInterated.getPostData());

            listOfStudents.add(postInterated.getPostData());
        }
        JSONArray jsArray = new JSONArray(listOfStudents);


        return listOfStudents.toString();
    }
    /*
    @GetMapping("/posts/all/test")
    public Map<String, Object> handle(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", request.getAttribute("javax.servlet.error.status_code"));
        map.put("reason", request.getAttribute("javax.servlet.error.message"));
        return map;
    }
    */

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

                Post newPost = new Post(postRequest.getTitle(),postRequest.getContent(),postRequest.getPhoto(),findedUser);
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

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    //@GetMapping("/posts/{id}")
    public String getUserById(@PathVariable(value = "id") Long postId) throws JSONException {
        Post post =  postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", postId));


        List<Comment> listaDeComentarios = commentRepository.findAllByPostEquals(post);

        ArrayList<JSONObject> listaDeComentariosInfo = new ArrayList<>();
        JSONArray arrayDeComentariosInfo = new JSONArray();


        for (Comment cometarius : listaDeComentarios) {
            CommentInfo commentInfoInterated = new CommentInfo(cometarius);
            //listaDeComentariosInfo.add(commentInfoInterated.getCommentData());
            arrayDeComentariosInfo.put(commentInfoInterated.getCommentData());
            System.out.println(commentInfoInterated);

        }
        PostData postit = new PostData(post,arrayDeComentariosInfo);
        System.out.println(postit);
        //return postit.getPostData().toString();
        //return new ResponseEntity<String>(new Gson().toJson(postit.getPostData()), HttpStatus.OK);
        //return new Gson().toJson(postit.getPostData());
        return postit.getPostData().toString();
    }

    @GetMapping("/posts/real/{id}")
    public Post getUserByIdOriginal(@PathVariable(value = "id") Long postId) {
        Post post =  postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", postId));
        return post;
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

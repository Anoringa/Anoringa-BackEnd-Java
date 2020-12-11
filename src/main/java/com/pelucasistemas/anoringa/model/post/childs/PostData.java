package com.pelucasistemas.anoringa.model.post.childs;

import com.pelucasistemas.anoringa.model.comment.childs.CommentInfo;
import com.pelucasistemas.anoringa.model.post.Post;
import com.pelucasistemas.anoringa.model.post.PostViewer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class PostData implements PostViewer {

    Post post;
    JSONArray comments;

    public PostData(Post post, JSONArray listaDeComentarios) {
        this.post = post;
        this.comments = listaDeComentarios;
    }


    @Override
    public JSONObject getPostData() throws JSONException {


            //JSONObject jo = this.post.toJSON();
            JSONObject jo = new JSONObject();
            jo.put("id", post.getId());
            jo.put("title", post.getTitle());
            jo.put("content", post.getContent());
            jo.put("photo", post.getPhoto());




            /*
            List<String> listaDeComentariosInfo = new ArrayList<>();

            for (CommentViewer cometarius : this.comments) {
                listaDeComentariosInfo.add(cometarius.getCommentData().toString());
            }

            */
        /*
            List<CommentInfo> strings = this.comments.stream()
                    .map(obj -> {
                        try {
                            obj.getCommentData();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return obj;}).collect(Collectors.toList());;


            Stream<Object> strongs = this.comments.stream().map(x -> {
                try {return x.getCommentData();}
                catch (JSONException e) {e.printStackTrace();}
                return null;
            });
        List<Object> result2 = strongs.collect(Collectors.toList());
        */


                //https://stackoverflow.com/questions/43815221/java-8-apply-function-to-all-elements-of-stream-without-breaking-stream-chain

            /*
            PostData postit = new PostData(post,listaDeComentariosInfo);

            List<String> strings = this.comments
                    .stream()
                    .map(CommentInfo::toString).collect(Collectors.toList());
            */
        System.out.println("this.comments");
        System.out.println(this.comments);
            System.out.println(this.comments.toString());
            jo.put("comments", this.comments);

            jo.put("username", post.getUser().getUsername());
            return jo;

    }
}

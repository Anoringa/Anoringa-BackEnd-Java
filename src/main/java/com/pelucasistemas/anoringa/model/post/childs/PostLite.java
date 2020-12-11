package com.pelucasistemas.anoringa.model.post.childs;

import com.pelucasistemas.anoringa.model.post.Post;
import com.pelucasistemas.anoringa.model.post.PostViewer;
import org.json.JSONException;
import org.json.JSONObject;


public class PostLite implements PostViewer {

    Post post;
    public PostLite(Post post) {
        this.post = post;
    }
    @Override
    public JSONObject getPostData() throws JSONException {
        JSONObject jo = new JSONObject();
        jo.put("id", post.getId());
        jo.put("title", post.getTitle());
        jo.put("content", post.getContent());
        jo.put("photo", post.getPhoto());
        return jo;
    }
}
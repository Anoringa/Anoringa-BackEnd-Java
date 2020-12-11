package com.pelucasistemas.anoringa.model.comment.childs;

import com.pelucasistemas.anoringa.model.comment.Comment;
import com.pelucasistemas.anoringa.model.comment.CommentViewer;
import org.json.JSONException;
import org.json.JSONObject;


public class CommentData implements CommentViewer {

    Comment comment;

    CommentData(Comment comment) {
        this.comment = comment;
    }

    @Override
    public JSONObject getCommentData() throws JSONException {
        //JSONObject jo = this.post.toJSON();
        JSONObject jo = new JSONObject();
        //jo.put("title", comment.getPost().getId());

        jo.put("id", this.comment.getId());
        jo.put("content", this.comment.getContent());
        jo.put("username",  this.comment.getUser().getUsername());
        jo.put("publication", this.comment.getPublicated());
        return jo;
    }
}

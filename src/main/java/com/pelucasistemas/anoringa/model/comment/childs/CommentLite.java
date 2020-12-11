package com.pelucasistemas.anoringa.model.comment.childs;

import com.pelucasistemas.anoringa.model.comment.Comment;
import com.pelucasistemas.anoringa.model.comment.CommentViewer;
import com.pelucasistemas.anoringa.model.post.Post;
import org.json.JSONException;
import org.json.JSONObject;


public class CommentLite implements CommentViewer {

    Comment comment;
    CommentLite(Comment comment) {
        this.comment = comment;
    }

    @Override
    public JSONObject getCommentData() throws JSONException {
        return null;
    }
}
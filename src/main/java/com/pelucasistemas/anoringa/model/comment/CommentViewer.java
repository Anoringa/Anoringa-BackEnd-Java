package com.pelucasistemas.anoringa.model.comment;

import org.json.JSONException;
import org.json.JSONObject;

public interface CommentViewer {
    public JSONObject getCommentData() throws JSONException;
}
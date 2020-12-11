package com.pelucasistemas.anoringa.model.user;

import org.json.JSONException;
import org.json.JSONObject;

public interface UserViewer {
    public JSONObject getUserData() throws JSONException;
}
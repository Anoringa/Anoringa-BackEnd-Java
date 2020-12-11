package com.pelucasistemas.anoringa.model.user.childs;

import com.pelucasistemas.anoringa.model.user.User;
import com.pelucasistemas.anoringa.model.user.UserViewer;
import org.json.JSONException;
import org.json.JSONObject;


public class UserLite implements UserViewer {

    private User user;

    public void UserLite(User user) {
        this.user = user;
    }

    @Override
    public JSONObject getUserData() throws JSONException {
        JSONObject jo = new JSONObject();
        jo.put("username", user.getUsername());
        return jo;
    }
}
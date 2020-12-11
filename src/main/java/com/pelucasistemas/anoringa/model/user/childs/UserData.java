package com.pelucasistemas.anoringa.model.user.childs;

import com.pelucasistemas.anoringa.model.user.User;
import com.pelucasistemas.anoringa.model.user.UserViewer;
import org.json.JSONException;
import org.json.JSONObject;


public class UserData implements UserViewer {

    private User user;

    public void UserData(User user) {
        // Costly operation
        this.user = user;
    }

    @Override
    public JSONObject getUserData() throws JSONException {
        return user.toJSON();
    }
}
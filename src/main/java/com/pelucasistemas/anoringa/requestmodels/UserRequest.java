package com.pelucasistemas.anoringa.requestmodels;

public class UserRequest {
    String token;

    public UserRequest() {
    }

    public UserRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

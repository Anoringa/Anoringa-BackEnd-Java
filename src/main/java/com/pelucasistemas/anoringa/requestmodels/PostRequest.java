package com.pelucasistemas.anoringa.requestmodels;

public class PostRequest {
    String username;
    String password;
    String title;
    String photo;
    String content;

    public PostRequest() {
    }

    public PostRequest(String password,String username,String title,String content,String photo) {
        this.password = password;
        this.username = username;
        this.title = title;
        this.content = content;
        this.photo = photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

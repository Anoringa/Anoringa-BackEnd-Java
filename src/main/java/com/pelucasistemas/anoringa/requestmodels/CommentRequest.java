package com.pelucasistemas.anoringa.requestmodels;

public class CommentRequest {
    String username;
    String password;
    Long postid;
    String content;

    public CommentRequest() {
    }

    public CommentRequest(String password, String username, Long postid, String content) {
        this.password = password;
        this.username = username;
        this.postid = postid;
        this.content = content;
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

    public Long getPostid() {
        return postid;
    }

    public void setPostid(Long postid) {
        this.postid = postid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.pelucasistemas.anoringa.model.comment.childs;

import com.pelucasistemas.anoringa.model.comment.Comment;
import com.pelucasistemas.anoringa.model.comment.CommentViewer;
import com.pelucasistemas.anoringa.model.post.Post;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

public class CommentInfo implements CommentViewer {

    Comment comment;

    public CommentInfo(Comment comment) {
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

/*
public class PostInfo {
    private List<Comment> Comments;
    private Long id;
    private String username;
    private String photo;
    private String title;
    private String content;
    private Date firstActivity;
    private Date lastActivity;
    private List<CommentInfo> comments;

    public PostInfo(Post post, List<CommentInfo> listaDeComentarios ) {
        this.id = post.getId();
        this.username = post.getUser().getUsername();
        this.photo = post.getPhoto();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.firstActivity = post.getFirstActivity();
        this.lastActivity = post.getLastActivity();
        this.comments = listaDeComentarios;
    }
    // Overload
    public PostInfo(Post post) {
        this.id = post.getId();
        this.username = post.getUser().getUsername();
        this.photo = post.getPhoto();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.firstActivity = post.getFirstActivity();
        this.lastActivity = post.getLastActivity();
    }
    public PostInfo(Long id, String username, String photo, String title, String content, Date firstActivity, Date lastActivity, List<Comment> comments) {
        this.id = id;
        this.username = username;
        this.photo = photo;
        this.title = title;
        this.content = content;
        this.firstActivity = firstActivity;
        this.lastActivity = lastActivity;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getFirstActivity() {
        return firstActivity;
    }

    public void setFirstActivity(Date firstActivity) {
        this.firstActivity = firstActivity;
    }

    public Date getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Date lastActivity) {
        this.lastActivity = lastActivity;
    }
*/

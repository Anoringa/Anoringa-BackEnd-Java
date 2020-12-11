package com.pelucasistemas.anoringa.model.post;

import com.pelucasistemas.anoringa.model.EntidadPersistente;
import com.pelucasistemas.anoringa.model.user.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Entity
@Table(name = "posts")
@EntityListeners(AuditingEntityListener.class)
public class Post extends EntidadPersistente {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    /*
    @OneToMany(mappedBy="posts")
    private List<Comment> comments;
*/
    @NotBlank
    private String photo;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date firstActivity;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastActivity;

    public Post() {
    }

    public Post(String title, String content, String photo,User user) {
        this.title = title;
        this.content = content;
        this.photo = photo;
        this.user = user;
    }




    public JSONObject toJSON() throws JSONException {

        JSONObject jo = new JSONObject();

        jo.put("id", this.getId());
        jo.put("title", this.title);
        jo.put("content", this.content);
        jo.put("photo", this.photo);
        jo.put("username", this.user.getUsername());
        jo.put("firstActivity", this.firstActivity);
        jo.put("lastActivity", this.lastActivity);
        //jo.put("comments", this.comments);
        return jo;
    }

    public User getUser() {
        return user;
    }



    public void setUser(User user) {
        this.user = user;
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
}

package com.pelucasistemas.anoringa.model.comment;

import com.pelucasistemas.anoringa.model.EntidadPersistente;
import com.pelucasistemas.anoringa.model.post.Post;
import com.pelucasistemas.anoringa.model.user.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@Entity
@Table(name = "comments")
@EntityListeners(AuditingEntityListener.class)
public class Comment extends EntidadPersistente {

    /*
    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "user_id")
    private User user;

    */


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @NotBlank
    @Column(name = "content")
    private String content;

    //@ManyToOne
    //@JoinColumn(columnDefinition = "post_id", referencedColumnName = "post_id", nullable=false)
    //@JoinColumn(columnDefinition = "post_id", nullable=false)
    //@JoinColumn(name = "post_id", referencedColumnName = "id")
    //private Post post;

    //@ManyToOne
    //@JoinColumn(name = "user_id", referencedColumnName = "id")
    //private User user;

    //@ManyToOne
    //@JoinColumn(columnDefinition = "user_id", nullable=false)
    //private User user;


    /*
    @NotBlank
    private String content;
*/
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date publicated;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastUpdate;

    public Comment() {
    }

    public Comment(User user,Post post, String content) {
        this.content = content;
        this.user = user;
        this.post = post;
    }

    public JSONObject toJSON() throws JSONException {

        JSONObject jo = new JSONObject();

        jo.put("id", this.getId());
        jo.put("content", this.content);
        jo.put("user", this.user);
        jo.put("post", this.post);

        jo.put("publicated", this.publicated);
        jo.put("lastActivity", this.lastUpdate);

        //jo.put("comments", this.comments);
        return jo;
    }
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublicated() {
        return publicated;
    }

    public void setPublicated(Date publicated) {
        this.publicated = publicated;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}

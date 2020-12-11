package com.pelucasistemas.anoringa.model.user;

import com.pelucasistemas.anoringa.aimtools.JWebToken;
import com.pelucasistemas.anoringa.model.EntidadPersistente;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User extends EntidadPersistente {
    /*
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    */

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String ip;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date firstActivity;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date lastActivity;

    public User() {
    }

    public User(String username, String password, String ip) {
        this.username = username;
        this.password = password;
        this.ip = ip;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject jo = new JSONObject();
        jo.put("username", this.username);
        jo.put("password", this.password);
        jo.put("ip", this.ip);
        jo.put("firstActivity", this.firstActivity);
        jo.put("lastActivity", this.lastActivity);
        return jo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public void setLastActivity() {
        this.lastActivity = new Date(); ;
    }
    public String getUserInfo() {
        return this.username;
    }

    public String genJWT() throws JSONException {

        final int EXPIRY_DAYS = 90;

        JSONObject jwtPayload = new JSONObject();
        jwtPayload.put("status", 0);

        JSONArray audArray = new JSONArray();
        audArray.put("admin");
        jwtPayload.put("sub", "John");

        jwtPayload.put("aud", audArray);
        LocalDateTime ldt = LocalDateTime.now().plusDays(EXPIRY_DAYS);
        jwtPayload.put("exp", ldt.toEpochSecond(ZoneOffset.UTC)); //this needs to be configured

        String tokenjwt = new JWebToken(jwtPayload).toString();
        return tokenjwt;
    }


    public boolean checkJWT(String bearerToken) throws JSONException, NoSuchAlgorithmException {
        //verify and use
        JWebToken incomingToken = new JWebToken(bearerToken);
        if (!incomingToken.isValid()) {
            System.out.println(incomingToken.isValid());
            System.out.println(incomingToken);
            List<String> audience = incomingToken.getAudience();
            System.out.println(audience);
            String subject = incomingToken.getSubject();
            System.out.println(subject);
            return true;
        } else {
            System.out.println(incomingToken.isValid());
            System.out.println(incomingToken);
            List<String> audience = incomingToken.getAudience();
            System.out.println(audience);
            String subject = incomingToken.getSubject();
            System.out.println(subject);
            return false;
        }
    }
}

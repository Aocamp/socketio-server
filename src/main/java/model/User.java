package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.json.JSONObject;

public class User {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("userLogin")
    @Expose
    private String userLogin;
    @SerializedName("support")
    @Expose
    private int support;

    public User(){

    }

    public User(Long id, String userLogin, int support) {
        this.id = id;
        this.userLogin = userLogin;
        this.support = support;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getSupport() {
        return support;
    }

    public void setSupport(int support) {
        this.support = support;
    }
}

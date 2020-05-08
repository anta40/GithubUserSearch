package com.divbyzero.app.githubusersearch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("avatar_url")
    @Expose
    private String avatar_url;

    public void setLogin(String login){
        this.login = login;
    }

    public void setAvatarUrl(String url){
        this.avatar_url = url;
    }

    public String getLogin(){
        return login;
    }

    public String getAvatarUrl(){
        return avatar_url;
    }
}

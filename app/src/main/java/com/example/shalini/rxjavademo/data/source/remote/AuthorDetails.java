package com.example.shalini.rxjavademo.data.source.remote;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Shalini Prajesh on 14/2/18.
 */

public class AuthorDetails {
    @SerializedName("Author")
    private String author;
    @SerializedName("Contact")
    private String contact;
    @SerializedName("Home")
    private String home;
    @SerializedName("Docs")
    private String docs;

    public String getAuthor() {
        return author;
    }

    public String getContact() {
        return contact;
    }

    public String getHome() {
        return home;
    }

    public String getDocs() {
        return docs;
    }
}

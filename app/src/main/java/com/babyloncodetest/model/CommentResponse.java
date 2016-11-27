package com.babyloncodetest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by maximiliano.ferraiuolo on 23/11/2016.
 */

public class CommentResponse {

    @SerializedName("id")
    public int commentId;
    @SerializedName("postId")
    public int postId;
    public String email;
    public String body;
    public String name;


    public CommentResponse(String name, String email, String body, int postId, int commentId) {
        this.commentId = commentId;
        this.postId = postId;
        this.email = email;
        this.body = body;
        this.name = name;
     }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    public String getName() {
        return name;
    }

    public int getCommentId() {
        return commentId;
    }

    public int getPostId() {
        return postId;
    }

 }

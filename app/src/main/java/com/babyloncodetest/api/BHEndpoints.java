package com.babyloncodetest.api;

import com.babyloncodetest.model.CommentResponse;
import com.babyloncodetest.model.PostResponse;
import com.babyloncodetest.model.User;

import org.w3c.dom.Comment;

import java.util.List;

import retrofit2.http.GET;

import rx.Observable;

/**
 * Created by maximiliano.ferraiuolo on 23/11/2016.
 */

public interface BHEndpoints {

    @GET("/posts")
    Observable<List<PostResponse>> getPosts();

    @GET("/comments")
    Observable<List<CommentResponse>> getComments();

    @GET("/users")
    Observable<List<User>> getUsers();
}

package com.babyloncodetest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by maximiliano.ferraiuolo on 23/11/2016.
 */
public class PostResponse implements Parcelable {

    @SerializedName("id")
    public int postId;
    public String title;
    public String body;
    public int userId;
    public String comments_status;


    public PostResponse(String title, String body, int userId, int postId) {
        this.userId = userId;
        this.title = title;
        this.body = body;
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getUserId() {
        return userId;
    }

    public int getPostId() {
        return postId;
    }


    public PostResponse getPostById(List<PostResponse> posts, int id) {

        for (PostResponse post : posts) {
            if (post.getPostId() == id) {
                return post;
            }
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.postId);
        dest.writeString(this.title);
        dest.writeString(this.body);
        dest.writeInt(this.userId);
        dest.writeString(this.comments_status);

    }

    protected PostResponse(Parcel in) {
        this.postId = in.readInt();
        this.title = in.readString();
        this.body = in.readString();
        this.userId = in.readInt();
        this.comments_status = in.readString();
    }

    public static final Parcelable.Creator<PostResponse> CREATOR = new Parcelable.Creator<PostResponse>() {
        @Override
        public PostResponse createFromParcel(Parcel source) {
            return new PostResponse(source);
        }

        @Override
        public PostResponse[] newArray(int size) {
            return new PostResponse[size];
        }
    };
}
package com.babyloncodetest.interactor;

import com.babyloncodetest.model.CommentResponse;

import java.util.List;

/**
 * Created by maximiliano.ferraiuolo on 25/11/2016.
 */

public interface PostDetailInteractorCallback {

    void fetchComments(PostDetailInteractorCallback.RequestCallback callback, int postId);

    void detachView();


    interface RequestCallback {

        void onFetchCommentsSuccess(List<CommentResponse> comments);

        void onFetchCommentsFailed(String error);

    }
}

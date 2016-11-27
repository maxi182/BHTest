package com.babyloncodetest.interactor;

import com.babyloncodetest.model.PostResponse;

import java.util.HashMap;
import java.util.List;

/**
 * Created by maximiliano.ferraiuolo on 24/11/2016.
 */

public interface PostListInteractorCallback {


    void fetchPosts(RequestCallback callback);

    void detachView();


    interface RequestCallback {

        void onFetchPostsSuccess(List<PostResponse> posts, HashMap<Integer, String> userEmails);

        void onFetchPostsFailed(String error);

    }
}

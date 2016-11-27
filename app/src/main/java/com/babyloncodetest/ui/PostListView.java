package com.babyloncodetest.ui;

import com.babyloncodetest.model.PostResponse;

import java.util.HashMap;
import java.util.List;

/**
 * Created by maximiliano.ferraiuolo on 24/11/2016.
 */

public interface PostListView {

    void showPostListResponse(List<PostResponse> posts, HashMap<Integer, String> userEmails);

    void showErrorResponse(String exception);

    void showProgress();

    void hideProgress();

}

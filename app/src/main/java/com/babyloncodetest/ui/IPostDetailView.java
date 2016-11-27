package com.babyloncodetest.ui;

import com.babyloncodetest.model.CommentResponse;

import org.w3c.dom.Comment;
import java.util.List;

/**
 * Created by maximiliano.ferraiuolo on 24/11/2016.
 */

public interface IPostDetailView {

    void onCommentsResponse(List<CommentResponse> comments);

    void showCommentsWithPost(List<Object> items);

    void showErrorResponse(String exception);

    void showProgress();

    void hideProgress();

}

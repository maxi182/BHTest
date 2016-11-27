package com.babyloncodetest.presenter;

import com.babyloncodetest.model.CommentResponse;
import com.babyloncodetest.model.PostResponse;

import java.util.List;

/**
 * Created by maximiliano.ferraiuolo on 24/11/2016.
 */

public interface PostDetailPresenter extends IBasePresenter {

    void getCommentList(int postId);

    void bindTypes(List<CommentResponse> comments, PostResponse post, String description);

    void bindPost(PostResponse post, String description);


}

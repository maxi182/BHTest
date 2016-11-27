package com.babyloncodetest.presenter;

import android.support.annotation.VisibleForTesting;

import com.babyloncodetest.interactor.PostDetailInteractorCallback;
import com.babyloncodetest.interactor.PostDetailInteractorImpl;
import com.babyloncodetest.model.CommentResponse;
import com.babyloncodetest.model.PostResponse;
import com.babyloncodetest.ui.IPostDetailView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by maximiliano.ferraiuolo on 23/11/2016.
 */

public class PostDetailPresenterImpl implements PostDetailPresenter, PostDetailInteractorCallback.RequestCallback {


    private PostDetailInteractorCallback mPostDetailInteractorCallback;
    private WeakReference<IPostDetailView> postDetailView;


    public PostDetailPresenterImpl(IPostDetailView postDetailView) {
        this.postDetailView = new WeakReference<>(postDetailView);
        this.mPostDetailInteractorCallback = new PostDetailInteractorImpl();
    }

    @Override
    public void onFetchCommentsSuccess(List<CommentResponse> comments) {
        if (isViewAttached()) {
            getView().hideProgress();
            getView().onCommentsResponse(comments);
        }
    }

    @Override
    public void onFetchCommentsFailed(String error) {
        if (isViewAttached()) {
            getView().hideProgress();
            getView().showErrorResponse(error);
        }
    }

    @Override
    public void getCommentList(int postId) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        mPostDetailInteractorCallback.fetchComments(this, postId);
    }

    @VisibleForTesting
    @Override
    public void bindTypes(List<CommentResponse> comments, PostResponse post, String description) {

        List<Object> mItems = new ArrayList<>();
        post.comments_status = (description.concat(String.valueOf(getCommentsCant(comments))));
        mItems.add(post);
        mItems.addAll(comments);
        showCommentsWithPost(mItems);
    }

    @Override
    public void bindPost(PostResponse post, String description) {
        List<Object> mItems = new ArrayList<>();
        post.comments_status = description;
        mItems.add(post);
        showCommentsWithPost(mItems);
    }

    private void showCommentsWithPost(List<Object> items) {
        if (isViewAttached()) {
            getView().showCommentsWithPost(items);
        }
    }

    @VisibleForTesting
    public int getCommentsCant(List<CommentResponse> comments) {

        return comments.isEmpty() ? 0 : comments.size();
    }

    private IPostDetailView getView() {
        return (postDetailView != null) ? postDetailView.get() : null;
    }

    private boolean isViewAttached() {
        return (postDetailView != null) && (postDetailView.get() != null);
    }

    @Override
    public void detachView() {

        if (postDetailView != null) {
            postDetailView.clear();
            postDetailView = null;
        }
        mPostDetailInteractorCallback.detachView();
    }
}

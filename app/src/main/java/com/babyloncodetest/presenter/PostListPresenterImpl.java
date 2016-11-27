package com.babyloncodetest.presenter;

import com.babyloncodetest.interactor.PostListInteractorCallback;
import com.babyloncodetest.interactor.PostListInteractorImpl;
import com.babyloncodetest.model.PostResponse;
import com.babyloncodetest.ui.PostListView;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

/**
 * Created by maximiliano.ferraiuolo on 24/11/2016.
 */

public class PostListPresenterImpl implements PostListPresenter, PostListInteractorCallback.RequestCallback {


    private PostListInteractorCallback mPostListInteractorCallback;
    private WeakReference<PostListView> postListView;


    public PostListPresenterImpl(PostListView postListView) {
        this.postListView = new WeakReference<>(postListView);
        this.mPostListInteractorCallback = new PostListInteractorImpl();
    }

    @Override
    public void getPostList() {
        if (isViewAttached()) {
            getView().showProgress();
        }
        mPostListInteractorCallback.fetchPosts(this);
    }

    private boolean isViewAttached() {
        return (postListView != null) && (postListView.get() != null);
    }

    @Override
    public void onFetchPostsSuccess(List<PostResponse> posts, HashMap<Integer, String> userEmails) {

        if (isViewAttached()) {
            getView().hideProgress();
            getView().showPostListResponse(posts, userEmails);
        }
    }

    @Override
    public void onFetchPostsFailed(String exception) {

        if (isViewAttached()) {
            getView().hideProgress();
            getView().showErrorResponse(exception);
        }
    }

    private PostListView getView() {
        return (postListView != null) ? postListView.get() : null;
    }

    @Override
    public void detachView() {

        if (postListView != null) {
            postListView.clear();
            postListView = null;
        }
        mPostListInteractorCallback.detachView();
    }
}

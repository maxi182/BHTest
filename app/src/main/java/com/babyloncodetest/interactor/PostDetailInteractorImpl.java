package com.babyloncodetest.interactor;

import com.babyloncodetest.api.RestClient;
import com.babyloncodetest.model.CommentResponse;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by maximiliano.ferraiuolo on 25/11/2016.
 */

public class PostDetailInteractorImpl implements PostDetailInteractorCallback {

    private Subscription mSubscription;

    @Override
    public void fetchComments(final RequestCallback callback, final int postId) {

        Observable<List<CommentResponse>> call = RestClient.getApiService()
                .getComments();

        mSubscription = call
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CommentResponse>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        callback.onFetchCommentsFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(List<CommentResponse> comments) {

                        if (!comments.isEmpty()) {

                            callback.onFetchCommentsSuccess(parseComments(comments, postId));
                        }
                    }
                });
    }

    private List<CommentResponse> parseComments(List<CommentResponse> comments, int postId) {
        List<CommentResponse> commentsList = new ArrayList<>();
        for (CommentResponse comment : comments) {
            if (comment.getPostId() == postId) {
                commentsList.add(comment);
            }
        }
        return commentsList;
    }

    @Override
    public void detachView() {
        unsubscribe();
    }

    private void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}

package com.babyloncodetest.interactor;

import com.babyloncodetest.api.RestClient;
import com.babyloncodetest.model.PostResponse;
import com.babyloncodetest.model.User;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by maximiliano.ferraiuolo on 24/11/2016.
 */

public class PostListInteractorImpl implements PostListInteractorCallback {

    private Subscription mSubscription;

    @Override
    public void fetchPosts(final RequestCallback callback) {

        Observable<List<PostResponse>> call = RestClient.getApiService()
                .getPosts();

        mSubscription = call
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<PostResponse>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        callback.onFetchPostsFailed(e.getMessage());
                    }

                    @Override
                    public void onNext(List<PostResponse> posts) {

                        if (!posts.isEmpty()) {
                            fetchUsers(posts, callback);
                        }
                    }
                });
    }

    public void fetchUsers(final List<PostResponse> posts, final RequestCallback callback) {

        Observable<List<User>> call = RestClient.getApiService().getUsers();

        mSubscription = call
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                    }
                    @Override
                    public void onNext(List<User> users) {

                        if (!users.isEmpty()) {
                            callback.onFetchPostsSuccess(posts, loadUserMap(users));
                        }
                    }
                });
    }

    private HashMap<Integer, String> loadUserMap(List<User> users) {
        HashMap<Integer, String> mUserEmailMap = new HashMap<>();
        for (User user : users) {
            mUserEmailMap.put(user.userid, user.email);
        }
        return mUserEmailMap;
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

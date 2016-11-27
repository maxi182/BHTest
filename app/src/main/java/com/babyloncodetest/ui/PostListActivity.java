package com.babyloncodetest.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.babyloncodetest.R;
import com.babyloncodetest.databinding.ActivityPostsBinding;
import com.babyloncodetest.model.PostResponse;
import com.babyloncodetest.presenter.PostListPresenter;
import com.babyloncodetest.presenter.PostListPresenterImpl;
import com.babyloncodetest.ui.adapter.PostListAdapter;
import com.babyloncodetest.ui.listener.IOnPostClickListener;

import java.util.HashMap;
import java.util.List;


/**
 * Created by maximiliano.ferraiuolo on 23/11/2016.
 */

public class PostListActivity extends AppCompatActivity implements PostListView, IOnPostClickListener {

    private ActivityPostsBinding mBinding;
    private PostListAdapter mPostsAdapter;
    private PostListPresenter mPresenter;


    public static Intent getNewIntent(Context context) {
        return new Intent(context, PostListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.BHTheme);
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_posts);
        mPresenter = new PostListPresenterImpl(this);

        initView();
        mPresenter.getPostList();
    }

    private void initView() {
        setSupportActionBar(mBinding.toolbar);

        mPostsAdapter = new PostListAdapter(this);
        mBinding.list.setHasFixedSize(true);
        mBinding.list.setLayoutManager(new LinearLayoutManager(this));
        mBinding.list.setAdapter(mPostsAdapter);

        mBinding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getPostList();
            }
        });
    }


    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onPostClick(PostResponse post, String email) {

        startActivity(PostDetailActivity.getNewIntent(this, post, email));
    }

    @Override
    public void showPostListResponse(List<PostResponse> posts, HashMap<Integer, String> userEmails) {
        mBinding.swipe.setRefreshing(false);

        mBinding.empty.emptyView.setVisibility(View.GONE);
        mPostsAdapter.setPosts(posts, userEmails);
    }

    @Override
    public void showErrorResponse(String exception) {
        mBinding.list.setVisibility(View.GONE);
        mBinding.empty.emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        mBinding.progress.progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mBinding.progress.progressView.setVisibility(View.GONE);
    }

}

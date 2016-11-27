package com.babyloncodetest.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.babyloncodetest.R;
import com.babyloncodetest.databinding.ActivityDatailBinding;
import com.babyloncodetest.model.CommentResponse;
import com.babyloncodetest.model.PostResponse;
import com.babyloncodetest.presenter.PostDetailPresenter;

import com.babyloncodetest.presenter.PostDetailPresenterImpl;
import com.babyloncodetest.ui.adapter.CommentListAdapter;

import java.util.List;

/**
 * Created by maximiliano.ferraiuolo on 24/11/2016.
 */

public class PostDetailActivity extends AppCompatActivity implements IPostDetailView {


    public static final String ARG_POST = "post";
    public static final String ARG_EMAIL = "email";

    private ActivityDatailBinding mBinding;
    private CommentListAdapter mCommentAdapter;
    private PostDetailPresenter mPresenter;


    public static Intent getNewIntent(Context context, PostResponse post, String email) {
        Intent intent = new Intent(context, PostDetailActivity.class);
        intent.putExtra(ARG_POST, post);
        intent.putExtra(ARG_EMAIL, email);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.BHTheme);
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_datail);
        mPresenter = new PostDetailPresenterImpl(this);

        PostResponse post = unWrapPost(getIntent());
        initView(post);


    }

    private void initView(PostResponse post) {
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(post.getTitle());

        mCommentAdapter = new CommentListAdapter();
        mBinding.commentList.setHasFixedSize(true);
        mBinding.commentList.setLayoutManager(new LinearLayoutManager(this));
        mBinding.commentList.setAdapter(mCommentAdapter);

        mPresenter.bindPost(post, getResources().getString(R.string.loading_comments));
        mPresenter.getCommentList(post.getPostId());
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onCommentsResponse(List<CommentResponse> comments) {
        PostResponse post = unWrapPost(getIntent());
        mPresenter.bindTypes(comments, post, getResources().getString(R.string.comments));
    }

    @Override
    public void showCommentsWithPost(List<Object> items) {
        String email = getIntent().getStringExtra(ARG_EMAIL);
        mCommentAdapter.setCommentsList(items, email);
    }

    @Override
    public void showErrorResponse(String exception) {
        PostResponse post = unWrapPost(getIntent());
        mPresenter.bindPost(post, getResources().getString(R.string.no_comments));
    }

    private PostResponse unWrapPost(Intent intent) {
        return null != intent ? (PostResponse) intent.getParcelableExtra(ARG_POST) : null;
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

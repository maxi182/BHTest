package com.babyloncodetest.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.babyloncodetest.R;

import com.babyloncodetest.databinding.ItemCommentBinding;
import com.babyloncodetest.databinding.ItemPostBinding;
import com.babyloncodetest.model.CommentResponse;
import com.babyloncodetest.model.PostResponse;
import com.babyloncodetest.ui.adapter.viewholder.CommentViewHolder;
import com.babyloncodetest.ui.adapter.viewholder.PostViewHolder;

import java.util.Collections;
import java.util.List;

/**
 * Created by maximiliano.ferraiuolo on 24/11/2016.
 */

public class CommentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int ITEM_POST = 1;
    private final int ITEM_COMMENT = 2;

    private List<Object> mList;
    private String mUserEmail;

    public CommentListAdapter() {
        mList = Collections.emptyList();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return viewType == ITEM_COMMENT ? new CommentViewHolder(getItemCommentBinding(parent))
                : new PostViewHolder(getItemPostBinding(parent));
    }

    private ItemCommentBinding getItemCommentBinding(ViewGroup viewGroup) {
        return DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_comment, viewGroup, false);
    }

    private ItemPostBinding getItemPostBinding(ViewGroup viewGroup) {
        return DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_post, viewGroup, false);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        switch (viewHolder.getItemViewType()) {
            case ITEM_COMMENT:
                CommentViewHolder vhComment = (CommentViewHolder) viewHolder;
                vhComment.bind((CommentResponse) mList.get(position));
                break;
            case ITEM_POST:
                PostViewHolder vhPost = (PostViewHolder) viewHolder;
                vhPost.bind((PostResponse) mList.get(position), mUserEmail, true);
                break;
            default:
                break;
        }
    }

    public void setCommentsList(List<Object> items, String email) {
        this.mList = items;
        this.mUserEmail = email;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (mList.get(position) instanceof CommentResponse) ? ITEM_COMMENT : ITEM_POST;
    }
}

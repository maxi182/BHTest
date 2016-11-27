package com.babyloncodetest.ui.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.babyloncodetest.R;
import com.babyloncodetest.databinding.ItemPostBinding;
import com.babyloncodetest.model.PostResponse;
import com.babyloncodetest.ui.adapter.viewholder.PostViewHolder;
import com.babyloncodetest.ui.listener.IOnPostClickListener;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by maximiliano.ferraiuolo on 24/11/2016.
 */

public class PostListAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private List<PostResponse> mList;
    private HashMap<Integer, String> mUserEmails;
    private final IOnPostClickListener mListener;


    public PostListAdapter(IOnPostClickListener listener) {
        mListener = listener;
        mList = Collections.emptyList();
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPostBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_post, parent, false);
        return new PostViewHolder(binding, mListener);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.bind(mList.get(position), mUserEmails, false);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setPosts(List<PostResponse> posts, HashMap<Integer, String> userEmails) {
        this.mList = posts;
        this.mUserEmails = userEmails;
        this.notifyDataSetChanged();
    }

}

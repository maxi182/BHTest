package com.babyloncodetest.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;

import com.babyloncodetest.databinding.ItemCommentBinding;
import com.babyloncodetest.model.CommentResponse;

/**
 * Created by maximiliano.feraiuolo on 24/11/2016.
 */

public class CommentViewHolder extends RecyclerView.ViewHolder {

    private final ItemCommentBinding mBinding;

    public CommentViewHolder(ItemCommentBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void bind(final CommentResponse item) {
        mBinding.setComment(item);

    }
}

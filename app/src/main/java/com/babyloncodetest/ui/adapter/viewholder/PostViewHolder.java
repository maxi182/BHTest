package com.babyloncodetest.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.babyloncodetest.R;
import com.babyloncodetest.databinding.ItemPostBinding;
import com.babyloncodetest.model.PostResponse;
import com.babyloncodetest.ui.listener.IOnPostClickListener;
import com.babyloncodetest.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.HashMap;

/**
 * Created by maximiliano.ferraiuolo on 24/11/2016.
 */

public class PostViewHolder extends RecyclerView.ViewHolder {

    private final ItemPostBinding mBinding;
    private final IOnPostClickListener mListener;

    public PostViewHolder(ItemPostBinding binding, IOnPostClickListener listener) {
        super(binding.getRoot());
        mBinding = binding;
        mListener = listener;
    }

    public PostViewHolder(ItemPostBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
        mListener = null;
    }

    public void bind(final PostResponse item, HashMap<Integer, String> userEmails, boolean showBody) {
        bind(item, getEmail(item.getUserId(), userEmails), showBody);
    }

    public void bind(final PostResponse item, final String email, boolean showBody) {
        mBinding.setPost(item);

        mBinding.textBody.setVisibility(showBody ? View.VISIBLE : View.GONE);

        Glide.with(itemView.getContext())
                .load(Utils.AVATAR_BASE_URL.concat(email))
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_vector_person)
                .centerCrop()
                .into(mBinding.imgPic);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onPostClick(item, email);
                }
            }
        });
    }

    private String getEmail(int id, HashMap<Integer, String> userEmails) {

        return userEmails.get(id);
    }
}

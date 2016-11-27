package com.babyloncodetest.postList;

import com.babyloncodetest.model.PostResponse;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by maximiliano.ferraiuolo on 26/11/2016.
 */

public class PostTest {

    PostResponse postResponse;
    List<PostResponse> mPosts;

    @Before
    public void SetUp() {

        mPosts = Lists.newArrayList(
                new PostResponse("postname1", "body", 1, 10),
                new PostResponse("postname2", "body", 2, 11),
                new PostResponse("postname3", "body", 3, 12));

        postResponse = new PostResponse("postname1", "body", 1, 10);
    }

    @Test
    public void getPostByIdTest() throws Exception {
        assertEquals(postResponse.getPostById(mPosts, 10), mPosts.get(0));
        assertEquals(postResponse.getPostById(mPosts, 11), mPosts.get(1));
    }
}

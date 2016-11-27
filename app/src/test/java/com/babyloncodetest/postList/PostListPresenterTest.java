package com.babyloncodetest.postList;

/**
 * Created by maximiliano.ferraiuolo on 25/11/2016.
 */

import com.babyloncodetest.model.PostResponse;
import com.babyloncodetest.presenter.PostListPresenterImpl;
import com.babyloncodetest.ui.PostListView;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;

import static junit.framework.Assert.assertNotNull;

public class PostListPresenterTest {

    private static List<PostResponse> posts = Lists.newArrayList(new PostResponse("postname", "body", 1, 2));

    private static HashMap<Integer, String> userEmails = new HashMap<>();

    @Mock
    private PostListView mView;

    private PostListPresenterImpl mPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userEmails.put(1, "maxi08@gmail.com");
        userEmails.put(2, "max@gmail.com");

        mPresenter = new PostListPresenterImpl(mView);
    }

    @Test
    public void findExistingValue() {
        assertNotNull(userEmails.get(1));
        assertNotNull(userEmails.get(2));
    }

    @Test
    public void onDataFetched() throws Exception {

        mPresenter.onFetchPostsSuccess(posts, userEmails);

        Mockito.verify(mView).showPostListResponse(posts, userEmails);

    }

}

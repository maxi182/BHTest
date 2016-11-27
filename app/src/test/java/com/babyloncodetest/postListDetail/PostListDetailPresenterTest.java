package com.babyloncodetest.postListDetail;

import android.content.Context;
import android.net.NetworkInfo;

import com.babyloncodetest.R;
import com.babyloncodetest.model.CommentResponse;
import com.babyloncodetest.model.PostResponse;
import com.babyloncodetest.presenter.PostDetailPresenterImpl;
import com.babyloncodetest.ui.IPostDetailView;
import com.babyloncodetest.util.Utils;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static junit.framework.Assert.assertNull;

/**
 * Created by maximiliano.ferraiuolo on 25/11/2016.
 */

public class PostListDetailPresenterTest {

    private static List<CommentResponse> comments = Lists.newArrayList(
            new CommentResponse("name", "email", "body", 1, 10),
            new CommentResponse("name", "email", "body", 2, 11),
            new CommentResponse("name", "email", "body", 3, 12));

    private static PostResponse post = new PostResponse("title", "body", 1, 2);
    private static List<Object> items = new ArrayList<>();


    @Mock
    private IPostDetailView mView;

    private PostDetailPresenterImpl mPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        items.add(post);
        items.addAll(comments);
        mPresenter = new PostDetailPresenterImpl(mView);
    }


    @Test
    public void onBindTypes() throws Exception {

        mPresenter.bindTypes(comments, post, "comments");
        Mockito.verify(mView).showCommentsWithPost(items);

    }

    @Mock
    Context mMockContext;

    @Test
    public void getCantComments() {

        when(mMockContext.getString(R.string.cant_comments)).thenReturn("5");
        assertThat(String.valueOf(mPresenter.getCommentsCant(comments)), is("5"));

    }

    @Test
    public void testNetworkAvailable() throws Exception {

        final NetworkInfo networkInfo = Mockito.mock(NetworkInfo.class);

        Mockito.when(networkInfo.isConnected()).thenReturn(true);

        Utils.isNetworkAvailable(mMockContext);

        Mockito.verify(networkInfo).isConnected();
        assertNull(networkInfo);

    }
}

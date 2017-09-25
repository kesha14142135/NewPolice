package com.klg.newpolice.ui.comment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.klg.newpolice.R;
import com.klg.newpolice.data.database.model.Comment;
import com.klg.newpolice.ui.aboutchild.AboutChildActivity;
import com.klg.newpolice.ui.comment.adapter.CommentsDataAdapter;
import com.klg.newpolice.ui.missingchildren.interfase.OnLoadMoreListener;

import java.util.List;

public class CommentFragment extends Fragment implements CommentContract.View, SwipeRefreshLayout.OnRefreshListener {
    //ui element
    private RecyclerView mRecyclerViewComments;
    private ProgressBar mProgressBar;
    private SwipeRefreshLayout mSwipeLayout;
    private TextView mTextViewNonItem;
    //control variable
    private CommentsDataAdapter mDataAdapter;
    private CommentContract.Presenter mPresenter;
    private Context mContext;
    private int mIdChild;

    public static CommentFragment newInstance() {
        return new CommentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        updateViewDependencies(view);
        mIdChild = ((CommentActivity) mContext).getChildId();
        mPresenter = new CommentsPresenter(this, getContext());
        mPresenter.getComments(mIdChild);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setPresenter(CommentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showComments(List<Comment> comments) {
        listIsEmpty(comments.size());
        mDataAdapter = new CommentsDataAdapter(comments, mRecyclerViewComments);
        mRecyclerViewComments.setAdapter(mDataAdapter);
        mDataAdapter.setOnLoadMoreListener(() -> mPresenter.getNewComments(mIdChild));
        mSwipeLayout.setRefreshing(false);
    }

    @Override
    public void showMoreComments(List<Comment> comments) {
        mDataAdapter.addAll(comments);
        mDataAdapter.setLoaded(false);
    }

    @Override
    public void visibleProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void invisibleProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onRefresh() {
        mDataAdapter.getComments().clear();
        mPresenter.updateComments(mIdChild);
    }

    private void listIsEmpty(int size) {
        if (size == 0) {
            mTextViewNonItem.setVisibility(View.VISIBLE);
        } else {
            mTextViewNonItem.setVisibility(View.INVISIBLE);
        }
    }

    private void updateViewDependencies(View view) {
        mRecyclerViewComments = view.findViewById(R.id.recycler_view_comments);
        mRecyclerViewComments.setHasFixedSize(true);
        mRecyclerViewComments.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mTextViewNonItem = view.findViewById(R.id.text_view_non_item);
        mProgressBar = view.findViewById(R.id.progress_bar_comment);
        mSwipeLayout = view.findViewById(R.id.swipe_refresh_comments);
        mSwipeLayout.setOnRefreshListener(this);
    }
}

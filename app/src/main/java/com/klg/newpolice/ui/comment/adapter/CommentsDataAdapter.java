package com.klg.newpolice.ui.comment.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.klg.newpolice.R;
import com.klg.newpolice.data.database.model.Comment;
import com.klg.newpolice.ui.missingchildren.interfase.OnLoadMoreListener;

import java.util.List;

/**
 * Created by ubuntu on 9/13/17.
 */

public class CommentsDataAdapter extends RecyclerView.Adapter<CommentsViewHolder> {
    private List<Comment> mComments;
    private boolean mIsLoading;
    private OnLoadMoreListener mOnLoadMoreListener;
    private LinearLayoutManager mLinearLayoutManager;
    private int mVisibleThreshold;
    private int mLastVisibleItem;
    private int mTotalItemCount;

    public CommentsDataAdapter(List<Comment> comments, RecyclerView recyclerView) {
        mComments = comments;
        mIsLoading = false;
        mVisibleThreshold = 1;
        mLinearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mTotalItemCount = mLinearLayoutManager.getItemCount();
                mLastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
                if (!mIsLoading && mTotalItemCount <= (mLastVisibleItem + mVisibleThreshold)) {
                    if (mOnLoadMoreListener != null) {
                        mOnLoadMoreListener.onLoadMore();
                    }
                    mIsLoading = true;
                }
            }
        });
    }

    @Override
    public CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_view, parent, false);
        return new CommentsViewHolder(view);    }

    @Override
    public void onBindViewHolder(CommentsViewHolder holder, int position) {
        holder.bindWith(mComments.get(position));
    }
    public void addAll(List<Comment> list) {
        mComments.addAll(list);
        notifyDataSetChanged();
    }
    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }
    @Override
    public int getItemCount() {
        return mComments == null ? 0 : mComments.size();
    }
    public void setLoaded(boolean isLoading) {
        mIsLoading = isLoading;
    }

    public List<Comment> getComments() {
        return mComments;
    }
}

package com.klg.newpolice.ui.missingchildren.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.klg.newpolice.R;
import com.klg.newpolice.data.database.model.MissChild;
import com.klg.newpolice.ui.missingchildren.interfase.OnItemClickListener;
import com.klg.newpolice.ui.missingchildren.interfase.OnLoadMoreListener;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataViewHolder> {
    private List<MissChild> mMissChildren;
    private OnLoadMoreListener mOnLoadMoreListener;
    private LinearLayoutManager mLinearLayoutManager;
    private OnItemClickListener mOnItemClickListener;
    private int mVisibleThreshold;
    private int mLastVisibleItem;
    private int mTotalItemCount;
    private boolean mIsLoading;

    public DataAdapter(RecyclerView recyclerView, List<MissChild> missChildren, @NonNull OnItemClickListener listener) {
        mMissChildren = missChildren;
        mVisibleThreshold = 1;
        mOnItemClickListener = listener;
        mIsLoading = false;
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

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        mOnLoadMoreListener = onLoadMoreListener;
    }

    public void addAll(List<MissChild> children) {
        mMissChildren.addAll(children);
        notifyDataSetChanged();
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_miss_child, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        holder.bindWith(mMissChildren.get(position));
        holder.setItemClickListener(position, mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mMissChildren == null ? 0 : mMissChildren.size();
    }

    public void setLoaded(boolean isLoading) {
        mIsLoading = isLoading;
    }

    public List<MissChild> getDataList() {
        return mMissChildren;
    }
}

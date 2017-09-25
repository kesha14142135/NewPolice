package com.klg.newpolice.ui.missingchildren;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.klg.newpolice.R;
import com.klg.newpolice.data.database.model.MissChild;
import com.klg.newpolice.ui.aboutchild.AboutChildActivity;
import com.klg.newpolice.ui.missingchildren.adapter.DataAdapter;
import com.klg.newpolice.ui.missingchildren.interfase.OnItemClickListener;
import com.klg.newpolice.ui.missingchildren.interfase.OnLoadMoreListener;

import java.util.List;

public class MissingChildrenFragment extends Fragment implements MissingChildrenContract.View, SwipeRefreshLayout.OnRefreshListener, OnItemClickListener {
    //control
    private MissingChildrenContract.Presenter mPresenter;
    private DataAdapter mDataAdapter;
    //ui
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerViewMissChildren;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public static MissingChildrenFragment newInstance() {
        return new MissingChildrenFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_missing_children, container, false);
        updateViewDependencies(view);
        mPresenter = new MissingChildrenPresenter(this, getContext());
        mPresenter.getChildren();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(MissingChildrenContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showMissChildren(List<MissChild> children) {
        mDataAdapter = new DataAdapter(mRecyclerViewMissChildren, children, this);
        mRecyclerViewMissChildren.setAdapter(mDataAdapter);
        mDataAdapter.setOnLoadMoreListener(() -> mPresenter.getNewChildren());
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showNewMissChildren(List<MissChild> children) {
        mDataAdapter.addAll(children);
        mDataAdapter.setLoaded(false);
    }

    @Override
    public void onRefresh() {
        if (mDataAdapter != null) {
            mDataAdapter.getDataList().clear();
        }
        mPresenter.updateChildren();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), AboutChildActivity.class);
        intent.putExtra(getString(R.string.id_miss_child), mDataAdapter.getDataList().get(position).getId());
        startActivity(intent);
    }

    private void updateViewDependencies(View view) {
        mProgressBar = view.findViewById(R.id.progress_bar_missing_children);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_missing_children);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerViewMissChildren = view.findViewById(R.id.recycler_view_missing_children);
        mRecyclerViewMissChildren.setHasFixedSize(true);
        mRecyclerViewMissChildren.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
}

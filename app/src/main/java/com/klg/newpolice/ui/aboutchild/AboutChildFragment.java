package com.klg.newpolice.ui.aboutchild;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.klg.newpolice.R;
import com.klg.newpolice.data.database.model.MissChild;
import com.klg.newpolice.ui.comment.CommentActivity;

public class AboutChildFragment extends Fragment implements AboutChildContract.View, View.OnClickListener {
    //ui
    private TextView mTextViewName;
    private TextView mTextViewBirth;
    private TextView mTextViewGender;
    private TextView mTextViewChildDescription;
    private TextView mTextViewRegion;
    private TextView mTextViewSituation;
    private TextView mTextViewTimeOfLoss;
    private TextView mTextViewTimeOfRequest;
    private TextView mTextViewStatus;
    //control variable
    private AboutChildContract.Presenter mPresenter;
    private Context mContext;
    private int mIdChild;

    public static AboutChildFragment newInstance() {
        return new AboutChildFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_child, container, false);
        updateViewDependencies(view);
        mPresenter = new AboutChildPresenter(this, getContext());
        mIdChild = ((AboutChildActivity) mContext).getChildId();
        mPresenter.getMissChild(mIdChild);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_show_comments: {
                Intent intent = new Intent(getActivity(), CommentActivity.class);
                intent.putExtra(getString(R.string.id_miss_child), mIdChild);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void setPresenter(AboutChildContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showMissChild(MissChild child) {
        mTextViewName.setText(String.format(getString(R.string.child_name), child.getName()));
        mTextViewBirth.setText(String.format(getString(R.string.child_birth), child.getDateOfBirth()));
        mTextViewGender.setText(String.format(getString(R.string.child_gender), child.getGender()));
        mTextViewChildDescription.setText(String.format(getString(R.string.child_description), child.getChildDescription()));
        mTextViewRegion.setText(String.format(getString(R.string.region), child.getRegion()));
        mTextViewSituation.setText(String.format(getString(R.string.situation), child.getSituationDescription()));
        mTextViewTimeOfLoss.setText(String.format(getString(R.string.time_loss), child.getTimeOfLoss()));
        mTextViewTimeOfRequest.setText(String.format(getString(R.string.time_request), child.getTimeOfRequest()));
        mTextViewStatus.setText(String.format(getString(R.string.status), child.getStatus()));
    }

    private void updateViewDependencies(View view) {
        mTextViewName = view.findViewById(R.id.text_view_name);
        mTextViewBirth = view.findViewById(R.id.text_view_date_of_birth);
        mTextViewGender = view.findViewById(R.id.text_view_gender);
        mTextViewChildDescription = view.findViewById(R.id.text_view_child_description);
        mTextViewRegion = view.findViewById(R.id.text_view_region);
        mTextViewSituation = view.findViewById(R.id.text_view_situation_description);
        mTextViewTimeOfLoss = view.findViewById(R.id.text_view_time_of_loss);
        mTextViewTimeOfRequest = view.findViewById(R.id.text_view_time_of_request);
        mTextViewStatus = view.findViewById(R.id.text_view_status);
        (view.findViewById(R.id.button_show_comments)).setOnClickListener(this);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AboutChildActivity)getActivity()).setSupportActionBar(toolbar);
        if (((AboutChildActivity)getActivity()).getSupportActionBar() != null){
            ((AboutChildActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}

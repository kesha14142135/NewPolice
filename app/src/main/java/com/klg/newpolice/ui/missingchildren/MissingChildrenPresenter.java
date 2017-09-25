package com.klg.newpolice.ui.missingchildren;

import android.content.Context;

import com.klg.newpolice.data.AppDataManager;
import com.klg.newpolice.data.DataManager;
import com.klg.newpolice.data.database.model.MissChild;

import java.util.List;

/**
 * Created by sergejkozin on 9/22/17.
 */

public class MissingChildrenPresenter implements MissingChildrenContract.Presenter {

    private MissingChildrenContract.View mView;
    private Context mContext;
    private DataManager mManager;

    public MissingChildrenPresenter(MissingChildrenContract.View view, Context context) {
        mView = view;
        mContext = context;
        mManager = new AppDataManager(mContext);
    }

    @Override
    public void subscribe() {
        if (mManager == null) {
            mManager = new AppDataManager(mContext);
        }
    }

    @Override
    public void unsubscribe() {
        mManager = null;
    }

    @Override
    public void getChildren() {
        mView.showMissChildren(
                mManager.getMissChildrenInDb()
        );
    }

    @Override
    public void getNewChildren() {
        mManager.receiveMissChildrenFromServerToWriteDb(new DataManager.CallBackMissChildren() {
            @Override
            public void onSuccess(List<MissChild> children) {
                mView.showNewMissChildren(children);
            }

            @Override
            public void onFailure() {
//TODO error get new miss children
            }
        });
    }

    @Override
    public void updateChildren() {
        mManager.updateMissChildrenFromServerToWriteDb(new DataManager.CallBackMissChildren() {
            @Override
            public void onSuccess(List<MissChild> children) {
                mView.showMissChildren(children);
            }

            @Override
            public void onFailure() {
//TODO error update miss children
            }
        });
    }
}

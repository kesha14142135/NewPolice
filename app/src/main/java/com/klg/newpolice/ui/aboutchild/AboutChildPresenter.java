package com.klg.newpolice.ui.aboutchild;

import android.content.Context;

import com.klg.newpolice.data.AppDataManager;
import com.klg.newpolice.data.DataManager;
import com.klg.newpolice.ui.missingchildren.MissingChildrenContract;

/**
 * Created by sergejkozin on 9/22/17.
 */

public class AboutChildPresenter implements AboutChildContract.Presenter {

    private AboutChildContract.View mView;
    private Context mContext;
    private DataManager mManager;

    public AboutChildPresenter(AboutChildContract.View view, Context context) {
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
    public void getMissChild(int id) {
        mView.showMissChild(
                mManager.getMissChildById(id)
        );
    }
}

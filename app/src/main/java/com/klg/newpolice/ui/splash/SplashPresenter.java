package com.klg.newpolice.ui.splash;

import android.content.Context;

import com.klg.newpolice.data.AppDataManager;
import com.klg.newpolice.data.DataManager;

import io.reactivex.annotations.NonNull;

/**
 * Created by janisharali on 27/01/17.
 */

public class SplashPresenter implements SplashContract.Presenter {

    @NonNull
    private final SplashContract.View mView;
    @NonNull
    private final DataManager dataManager;

    public SplashPresenter(@NonNull SplashContract.View view) {
        mView = view;
        dataManager = new AppDataManager((Context) view);
    }

    @Override
    public void subscribe() {
        if (dataManager.isUserAuthorisation()) {
            mView.openMissingChildrenActivity();
        } else {
            mView.openAuthorisationActivity();
        }
    }

    @Override
    public void unsubscribe() {

    }
}

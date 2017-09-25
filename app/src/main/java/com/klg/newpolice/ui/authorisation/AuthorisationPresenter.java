package com.klg.newpolice.ui.authorisation;

import android.content.Context;

import com.klg.newpolice.data.AppDataManager;
import com.klg.newpolice.data.DataManager;

import io.reactivex.annotations.NonNull;

/**
 * Created by sergejkozin on 9/22/17.
 */

public class AuthorisationPresenter implements AuthorisationContract.Presenter {
    @NonNull
    private DataManager mManager;
    @NonNull
    private AuthorisationContract.View mView;
    private Context mContext;

    public AuthorisationPresenter(AuthorisationContract.View view, Context context) {
        mView = view;
        mContext = context;
    }

    @Override
    public void subscribe() {
        mManager = new AppDataManager(mContext);
    }

    @Override
    public void unsubscribe() {
        mManager = null;
    }

    @Override
    public void authorisationUser(String login, String password) {
        mView.visibleProgressBar();
        mManager.isUserValid(login, password, new DataManager.CallBackUserValid() {
            @Override
            public void onSuccess() {
                mView.invisibleProgressBar();
                mView.authorisationComplite();
            }

            @Override
            public void onFailure() {
                mView.invisibleProgressBar();
                mView.authorisationFailure();
            }
        });
    }
}

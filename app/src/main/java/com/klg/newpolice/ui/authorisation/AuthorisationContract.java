package com.klg.newpolice.ui.authorisation;

import com.klg.newpolice.base.BasePresenter;
import com.klg.newpolice.base.BaseView;
import com.klg.newpolice.ui.splash.SplashContract;

/**
 * Created by sergejkozin on 9/22/17.
 */

public interface AuthorisationContract {
    interface View extends BaseView<AuthorisationContract.Presenter> {
        void authorisationComplite();

        void authorisationFailure();

        void visibleProgressBar();

        void invisibleProgressBar();
    }

    interface Presenter extends BasePresenter {
        void authorisationUser(String login, String password);
    }
}

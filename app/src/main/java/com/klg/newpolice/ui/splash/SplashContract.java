package com.klg.newpolice.ui.splash;

import com.klg.newpolice.base.BasePresenter;
import com.klg.newpolice.base.BaseView;

/**
 * Created by sergejkozin on 9/22/17.
 */

public interface SplashContract {
    interface View extends BaseView<Presenter> {
        void openAuthorisationActivity();

        void openMissingChildrenActivity();
    }

    interface Presenter extends BasePresenter {

    }
}

package com.klg.newpolice.ui.aboutchild;

import com.klg.newpolice.base.BasePresenter;
import com.klg.newpolice.base.BaseView;
import com.klg.newpolice.data.database.model.MissChild;

/**
 * Created by sergejkozin on 9/22/17.
 */

public interface AboutChildContract {
    interface View extends BaseView<AboutChildContract.Presenter> {

        void showMissChild(MissChild child);
    }

    interface Presenter extends BasePresenter {
        void getMissChild(int id);
    }
}

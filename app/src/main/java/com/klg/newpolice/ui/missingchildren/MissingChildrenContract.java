package com.klg.newpolice.ui.missingchildren;

import com.klg.newpolice.base.BasePresenter;
import com.klg.newpolice.base.BaseView;
import com.klg.newpolice.data.database.model.MissChild;

import java.util.List;

/**
 * Created by sergejkozin on 9/22/17.
 */

public interface MissingChildrenContract {
    interface View extends BaseView<MissingChildrenContract.Presenter> {

        void showMissChildren(List<MissChild> children);

        void showNewMissChildren(List<MissChild> children);
    }

    interface Presenter extends BasePresenter {

        void getChildren();

        void getNewChildren();

        void updateChildren();
    }
}

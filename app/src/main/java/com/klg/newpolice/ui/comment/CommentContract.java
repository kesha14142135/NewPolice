package com.klg.newpolice.ui.comment;

import com.klg.newpolice.base.BasePresenter;
import com.klg.newpolice.base.BaseView;
import com.klg.newpolice.data.database.model.Comment;

import java.util.List;

/**
 * Created by sergejkozin on 9/22/17.
 */

public interface CommentContract {
    interface View extends BaseView<CommentContract.Presenter> {

        void showComments(List<Comment> comments);

        void showMoreComments(List<Comment> comments);

        void visibleProgressBar();

        void invisibleProgressBar();
    }

    interface Presenter extends BasePresenter {
        void getComments(int id);

        void getNewComments(int id);

        void updateComments(int id);
    }
}

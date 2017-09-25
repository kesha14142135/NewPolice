package com.klg.newpolice.ui.comment;

import android.content.Context;

import com.klg.newpolice.data.AppDataManager;
import com.klg.newpolice.data.DataManager;
import com.klg.newpolice.data.database.model.Comment;
import com.klg.newpolice.ui.authorisation.AuthorisationContract;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Created by sergejkozin on 9/22/17.
 */

public class CommentsPresenter implements CommentContract.Presenter {
    @NonNull
    private DataManager mManager;
    @NonNull
    private CommentContract.View mView;
    private Context mContext;

    public CommentsPresenter(CommentContract.View view, Context context) {
        mView = view;
        mContext = context;
        mManager = new AppDataManager(mContext);
    }

    @Override
    public void subscribe() {
        if (mManager == null)
            mManager = new AppDataManager(mContext);
    }

    @Override
    public void unsubscribe() {
        mManager = null;
    }

    @Override
    public void getComments(int id) {
        mView.visibleProgressBar();
        mManager.receiveCommentFromServer(id, new DataManager.CallBackComment() {
            @Override
            public void onSuccess(List<Comment> comments) {
                mView.invisibleProgressBar();
                mView.showComments(comments);
            }

            @Override
            public void onFailure() {
                mView.invisibleProgressBar();
            }
        });
    }

    @Override
    public void getNewComments(int id) {
        mView.visibleProgressBar();
        mManager.receiveCommentFromServer(id, new DataManager.CallBackComment() {
            @Override
            public void onSuccess(List<Comment> comments) {
                mView.invisibleProgressBar();
                mView.showMoreComments(comments);
            }

            @Override
            public void onFailure() {
                mView.invisibleProgressBar();
            }
        });
    }

    @Override
    public void updateComments(int id) {
        mView.visibleProgressBar();
        mManager.updateCommentFromServer(id, new DataManager.CallBackComment() {
            @Override
            public void onSuccess(List<Comment> comments) {
                mView.invisibleProgressBar();
                mView.showComments(comments);
            }

            @Override
            public void onFailure() {
                mView.invisibleProgressBar();
            }
        });
    }
}

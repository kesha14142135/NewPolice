package com.klg.newpolice.data;

import android.content.Context;

import com.klg.newpolice.data.api.ApiHelper;
import com.klg.newpolice.data.api.ApiAppHelper;
import com.klg.newpolice.data.api.model.CommentResponce;
import com.klg.newpolice.data.api.model.MissChildResponce;
import com.klg.newpolice.data.database.DbAppHelper;
import com.klg.newpolice.data.database.DbHelper;
import com.klg.newpolice.data.database.model.Comment;
import com.klg.newpolice.data.database.model.MissChild;
import com.klg.newpolice.data.preference.PreferenceAppHelper;
import com.klg.newpolice.data.preference.PreferenceHelper;
import com.klg.newpolice.util.StringUtils;
import com.klg.newpolice.util.rx.AppSchedulerProvider;
import com.klg.newpolice.util.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by sergejkozin on 9/23/17.
 */

public class AppDataManager implements DataManager {

    private ApiHelper mApi;
    private DbHelper mDatabase;
    private PreferenceHelper mPreference;
    private SchedulerProvider mScheduler;
    private static final int sLimit = 10;
    private static int sOffsetComment = 0;

    public AppDataManager(Context context) {
        mApi = new ApiAppHelper();
        mDatabase = new DbAppHelper();
        mPreference = new PreferenceAppHelper(context);
        mScheduler = new AppSchedulerProvider();
    }

    @Override
    public void isUserValid(String login, String password, CallBackUserValid callBack) {
        Flowable<List<MissChildResponce>> missChildren = mApi.getMissedChildren(StringUtils.getBasicAuthHeader(login, password), mPreference.getOffset(), sLimit);
        missChildren.subscribeOn(mScheduler.io())
                .observeOn(mScheduler.ui())
                .subscribe(missChildrenResponce -> {
                            mPreference.setCredential(StringUtils.getBasicAuthHeader(login, password));
                            mPreference.setOffset(mPreference.getOffset() + sLimit);
                            mDatabase.setMissChildren(
                                    convertMissChildResponceToMissChildren(missChildrenResponce)
                            );
                            callBack.onSuccess();
                        },
                        throwable -> callBack.onFailure());
    }

    @Override
    public boolean isUserAuthorisation() {
        return mPreference.credentialIsNotEmpty();
    }

    @Override
    public void receiveCommentFromServer(int id, CallBackComment callBack) {
        getCommentFromServer(id, callBack);
    }

    @Override
    public void updateCommentFromServer(int id, CallBackComment callBack) {
        sOffsetComment = 0;
        getCommentFromServer(id, callBack);
    }

    @Override
    public void receiveMissChildrenFromServerToWriteDb(CallBackMissChildren callBack) {
        getMissChildrenFromServerToWriteDb(callBack);
    }

    @Override
    public void updateMissChildrenFromServerToWriteDb(CallBackMissChildren callBack) {
        mDatabase.deleteAllMissChildren();
        mPreference.setOffset(0);
        getMissChildrenFromServerToWriteDb(callBack);
    }

    @Override
    public List<MissChild> getMissChildrenInDb() {
        return mDatabase.getMissChildren();
    }

    @Override
    public MissChild getMissChildById(int id) {
        return mDatabase.getMissChild(id);
    }

    private void getMissChildrenFromServerToWriteDb(CallBackMissChildren callBack) {
        Flowable<List<MissChildResponce>> missChildren = mApi.getMissedChildren(mPreference.getCredential(), mPreference.getOffset(), sLimit);
        missChildren.subscribeOn(mScheduler.io())
                .observeOn(mScheduler.computation())
                .map(this::convertMissChildResponceToMissChildren)
                .observeOn(mScheduler.ui())
                .subscribe(children -> {
                            mPreference.setOffset(mPreference.getOffset() + sLimit);
                            mDatabase.setMissChildren(children);
                            callBack.onSuccess(children);
                        },
                        throwable -> callBack.onFailure()
                );
    }

    private void getCommentFromServer(int id, CallBackComment callBack) {
        Flowable<List<CommentResponce>> comments = mApi.getComments(mPreference.getCredential(), id, sOffsetComment, sLimit);
        comments.subscribeOn(mScheduler.io())
                .observeOn(mScheduler.computation())
                .map(this::convertCommentResponceToComment)
                .observeOn(mScheduler.ui())
                .subscribe(comments1 -> {
                            sOffsetComment += sLimit;
                            callBack.onSuccess(comments1);
                        },
                        throwable -> callBack.onFailure()
                );
    }

    private List<MissChild> convertMissChildResponceToMissChildren(List<MissChildResponce> childrenResponce) {
        List<MissChild> children = new ArrayList<>();
        for (MissChildResponce child : childrenResponce) {
            MissChild c = new MissChild();
            c.setId(child.getId());
            c.setName(child.getName());
            c.setDateOfBirth(child.getDateOfBirth());
            c.setGender(child.getGender());
            c.setChildDescription(child.getChildDescription());
            c.setRegion(child.getRegion());
            c.setSituationDescription(child.getSituationDescription());
            c.setTimeOfLoss(child.getTimeOfLoss());
            c.setTimeOfRequest(child.getTimeOfRequest());
            c.setStatus(child.getStatus());
            children.add(c);
        }
        return children;
    }

    private List<Comment> convertCommentResponceToComment(List<CommentResponce> commentsResponces) {
        List<Comment> comments = new ArrayList<>();
        for (CommentResponce comment : commentsResponces) {
            Comment c = new Comment();
            c.setId(comment.getId());
            c.setSenderName(comment.getSenderName());
            c.setText(comment.getText());
            c.setTimeOfCreate(comment.getTimeOfCreate());
            comments.add(c);
        }
        return comments;
    }
}

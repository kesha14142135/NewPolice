package com.klg.newpolice.data.database.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sergejkozin on 9/22/17.
 */

public class Comment extends RealmObject {

    @PrimaryKey
    private Integer mId;

    private String mSenderName;

    private String mText;

    private String mTimeOfCreate;

    public Comment() {
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getSenderName() {
        return mSenderName;
    }

    public void setSenderName(String senderName) {
        mSenderName = senderName;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getTimeOfCreate() {
        return mTimeOfCreate;
    }

    public void setTimeOfCreate(String timeOfCreate) {
        mTimeOfCreate = timeOfCreate;
    }

}

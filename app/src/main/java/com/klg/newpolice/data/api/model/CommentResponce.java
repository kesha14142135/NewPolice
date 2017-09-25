package com.klg.newpolice.data.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sergejkozin on 9/22/17.
 */

public class CommentResponce {
    @SerializedName("id")
    @Expose
    private Integer mId;
    @SerializedName("senderName")
    @Expose
    private String mSenderName;
    @SerializedName("text")
    @Expose
    private String mText;
    @SerializedName("timeOfCreate")
    @Expose
    private String mTimeOfCreate;

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

package com.klg.newpolice.data.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sergejkozin on 9/22/17.
 */

public class MissChildResponce {

    @SerializedName("id")
    @Expose
    private Integer mId;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("dateOfBirth")
    @Expose
    private String mDateOfBirth;
    @SerializedName("gender")
    @Expose
    private String mGender;
    @SerializedName("childDescription")
    @Expose
    private String mChildDescription;
    @SerializedName("region")
    @Expose
    private String mRegion;
    @SerializedName("situationDescription")
    @Expose
    private String mSituationDescription;
    @SerializedName("timeOfLoss")
    @Expose
    private String mTimeOfLoss;
    @SerializedName("timeOfRequest")
    @Expose
    private String mTimeOfRequest;
    @SerializedName("status")
    @Expose
    private String mStatus;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDateOfBirth() {
        return mDateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        mDateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public String getChildDescription() {
        return mChildDescription;
    }

    public void setChildDescription(String childDescription) {
        mChildDescription = childDescription;
    }

    public String getRegion() {
        return mRegion;
    }

    public void setRegion(String region) {
        mRegion = region;
    }

    public String getSituationDescription() {
        return mSituationDescription;
    }

    public void setSituationDescription(String situationDescription) {
        mSituationDescription = situationDescription;
    }

    public String getTimeOfLoss() {
        return mTimeOfLoss;
    }

    public void setTimeOfLoss(String timeOfLoss) {
        mTimeOfLoss = timeOfLoss;
    }

    public String getTimeOfRequest() {
        return mTimeOfRequest;
    }

    public void setTimeOfRequest(String timeOfRequest) {
        mTimeOfRequest = timeOfRequest;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }
}

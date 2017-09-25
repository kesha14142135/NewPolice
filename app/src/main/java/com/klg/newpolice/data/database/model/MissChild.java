package com.klg.newpolice.data.database.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sergejkozin on 9/22/17.
 */

public class MissChild extends RealmObject {

    @PrimaryKey
    private Integer mId;
    private String mName;
    private String mDateOfBirth;
    private String mGender;
    private String mChildDescription;
    private String mRegion;
    private String mSituationDescription;
    private String mTimeOfLoss;
    private String mTimeOfRequest;
    private String mStatus;

    public MissChild() {
    }

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

    public String getAge() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date birthDate = df.parse(mDateOfBirth);
        Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);
        Calendar today = Calendar.getInstance();
        int yearDifference = today.get(Calendar.YEAR)
                - birth.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < birth.get(Calendar.MONTH)) {
            yearDifference--;
        } else {
            if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)
                    && today.get(Calendar.DAY_OF_MONTH) < birth
                    .get(Calendar.DAY_OF_MONTH)) {
                yearDifference--;
            }
        }
        return String.valueOf(yearDifference);
    }
}

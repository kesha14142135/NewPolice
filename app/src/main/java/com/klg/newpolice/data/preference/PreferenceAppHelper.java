package com.klg.newpolice.data.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.klg.newpolice.R;

/**
 * Created by sergejkozin on 9/2/17.
 */

public class PreferenceAppHelper implements PreferenceHelper {
    private SharedPreferences mPreference;
    private Context mContext;

    public PreferenceAppHelper(Context context) {
        mContext = context;
        mPreference = context.getSharedPreferences(mContext.getResources().getString(R.string.pref_credential), Context.MODE_PRIVATE);
    }

    @Override
    public void setCredential(String credential) {
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putString(mContext.getResources().getString(R.string.credential), credential);
        editor.apply();
    }

    @Override
    public String getCredential() {
        return mPreference.getString(mContext.getResources().getString(R.string.credential), "");
    }

    @Override
    public boolean credentialIsNotEmpty() {
        return !mPreference.getString(mContext.getResources().getString(R.string.credential), "").isEmpty();
    }

    @Override
    public void setOffset(int offset) {
        SharedPreferences.Editor editor = mPreference.edit();
        editor.putInt(mContext.getResources().getString(R.string.offset), offset);
        editor.apply();
    }

    @Override
    public int getOffset() {
        return mPreference.getInt(mContext.getResources().getString(R.string.offset),0);
    }
}

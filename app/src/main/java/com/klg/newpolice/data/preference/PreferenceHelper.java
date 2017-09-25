package com.klg.newpolice.data.preference;

/**
 * Created by sergejkozin on 9/22/17.
 */

public interface PreferenceHelper {

    void setCredential(String credential);

    String getCredential();

    boolean credentialIsNotEmpty();

    void setOffset(int offset);

    int getOffset();
}

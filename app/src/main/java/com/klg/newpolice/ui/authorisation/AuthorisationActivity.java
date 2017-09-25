package com.klg.newpolice.ui.authorisation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.klg.newpolice.R;
import com.klg.newpolice.util.ActivityUtils;

public class AuthorisationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorisation);

        AuthorisationFragment statisticsFragment = (AuthorisationFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frame_layout_authorisation);
        if (statisticsFragment == null) {
            statisticsFragment = AuthorisationFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    statisticsFragment, R.id.frame_layout_authorisation);
        }
    }
}

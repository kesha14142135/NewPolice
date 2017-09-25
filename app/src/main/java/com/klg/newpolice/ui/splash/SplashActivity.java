package com.klg.newpolice.ui.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.klg.newpolice.R;
import com.klg.newpolice.ui.authorisation.AuthorisationActivity;
import com.klg.newpolice.ui.missingchildren.MissingChildrenActivity;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    private SplashContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mPresenter = new SplashPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void openAuthorisationActivity() {
        Intent intent = new Intent(this, AuthorisationActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void openMissingChildrenActivity() {
        Intent intent = new Intent(this, MissingChildrenActivity.class);
        startActivity(intent);
        finish();
    }
}

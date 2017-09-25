package com.klg.newpolice.ui.aboutchild;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.klg.newpolice.R;
import com.klg.newpolice.util.ActivityUtils;

public class AboutChildActivity extends AppCompatActivity {

    private int mChildId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_child);

        Bundle extras = getIntent().getExtras();
        mChildId = extras.getInt(getString(R.string.id_miss_child));

        AboutChildFragment statisticsFragment = (AboutChildFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frame_layout_about_child);
        if (statisticsFragment == null) {
            statisticsFragment = AboutChildFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    statisticsFragment, R.id.frame_layout_about_child);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        onBackPressed();
        return true;
    }

    public int getChildId() {
        return mChildId;
    }
}

package com.klg.newpolice.ui.missingchildren;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.klg.newpolice.R;
import com.klg.newpolice.util.ActivityUtils;

public class MissingChildrenActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing_children);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.miss_children);
        }
        MissingChildrenFragment statisticsFragment = (MissingChildrenFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frame_layout_missing_children);
        if (statisticsFragment == null) {
            statisticsFragment = MissingChildrenFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    statisticsFragment, R.id.frame_layout_missing_children);
        }
    }
}

package com.klg.newpolice.ui.comment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.klg.newpolice.R;
import com.klg.newpolice.util.ActivityUtils;

public class CommentActivity extends AppCompatActivity {

    private int mChildId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        Bundle extras = getIntent().getExtras();
        assert extras != null;
        mChildId = extras.getInt(getString(R.string.id_miss_child));

        updateViewDependencies();

        CommentFragment statisticsFragment = (CommentFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frame_layout_comment);
        if (statisticsFragment == null) {
            statisticsFragment = CommentFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    statisticsFragment, R.id.frame_layout_comment);
        }
    }

    private void updateViewDependencies() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.comments);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    public int getChildId() {
        return mChildId;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}

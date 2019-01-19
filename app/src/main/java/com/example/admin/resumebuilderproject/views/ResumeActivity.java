package com.example.admin.resumebuilderproject.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.badoualy.stepperindicator.StepperIndicator;
import com.example.admin.resumebuilderproject.R;
import com.example.admin.resumebuilderproject.adapter.SectionsPagerAdapter;
import com.example.admin.resumebuilderproject.custom_view.CustomViewPager;

public class ResumeActivity extends AppCompatActivity {
    SectionsPagerAdapter mSectionsPagerAdapter;
    public static CustomViewPager mViewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resume_activity);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (CustomViewPager) findViewById(R.id.container);
        final StepperIndicator indicator = findViewById(R.id.stepper_indicator);
        indicator.setStepCount(3);
        mViewPager.setSwipeable(false);

        mViewPager.setAdapter(mSectionsPagerAdapter);

        // We keep last page for a "finishing" page
        indicator.setViewPager(mViewPager, mViewPager.getAdapter().getCount());

    }
}

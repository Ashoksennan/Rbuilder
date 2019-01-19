package com.example.admin.resumebuilderproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.admin.resumebuilderproject.fragments.steps.StepOne;
import com.example.admin.resumebuilderproject.fragments.steps.StepThree;
import com.example.admin.resumebuilderproject.fragments.steps.StepTwo;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:
                    return StepOne.newInstance(position+1);
                case 1:
                    return StepTwo.newInstance(position+1);
                case 2:
                    return StepThree.newInstance(position+1);
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return 3;
        }
    }
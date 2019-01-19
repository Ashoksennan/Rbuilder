package com.example.admin.resumebuilderproject.fragments.steps;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.resumebuilderproject.R;
import com.example.admin.resumebuilderproject.views.MainActivity;
import com.example.admin.resumebuilderproject.views.ResumeActivity;

public class StepOne extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    Button btn_first_next;
    public StepOne() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static StepOne newInstance(int sectionNumber) {
        StepOne fragment = new StepOne();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.step_one_fragment, container, false);

        btn_first_next = (Button)rootView.findViewById(R.id.btn_first_next);
        btn_first_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = ResumeActivity.mViewPager.getCurrentItem();
                ResumeActivity.mViewPager.setCurrentItem(current+1);
            }
        });
        return rootView;
    }
}

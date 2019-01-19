package com.example.admin.resumebuilderproject.fragments.steps;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.admin.resumebuilderproject.R;
import com.example.admin.resumebuilderproject.views.ResumeActivity;

public class StepThree extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    Button btn_preview;
    public StepThree() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static StepThree newInstance(int sectionNumber) {
        StepThree fragment = new StepThree();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.step_three_fragment, container, false);
        btn_preview = (Button)rootView.findViewById(R.id.btn_preview);

        btn_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = ResumeActivity.mViewPager.getCurrentItem();
                ResumeActivity.mViewPager.setCurrentItem(current-1);
            }
        });
        return rootView;
    }
}

package com.example.admin.resumebuilderproject.fragments.steps;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipDrawable;
import android.support.design.chip.ChipGroup;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.resumebuilderproject.R;
import com.example.admin.resumebuilderproject.contracts.RoomPrePopulateInterface;
import com.example.admin.resumebuilderproject.model.Skills;
import com.example.admin.resumebuilderproject.presenters.AutoCompletePresenter;
import com.example.admin.resumebuilderproject.room.SkillsTable;
import com.example.admin.resumebuilderproject.views.MainActivity;
import com.example.admin.resumebuilderproject.views.ResumeActivity;
import com.otaliastudios.autocomplete.Autocomplete;
import com.otaliastudios.autocomplete.AutocompleteCallback;
import com.otaliastudios.autocomplete.AutocompletePresenter;

public class StepTwo extends Fragment implements RoomPrePopulateInterface {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private RelativeLayout rl_et_skills;
    private TextView skill_count;
    View rootView;
    Autocomplete autocomplete;
    public StepTwo() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static StepTwo newInstance(int sectionNumber) {
        StepTwo fragment = new StepTwo();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.step_two_fragment, container, false);
        rl_et_skills = (RelativeLayout)rootView.findViewById(R.id.rl_et_skills);
        skill_count = (TextView) rootView.findViewById(R.id.skill_count);
        setupSkillsAutocomplete();
        onClicks();
        return rootView;
    }

    private void onClicks() {

    }

    private void setupSkillsAutocomplete() {
        final EditText edit = (EditText)rootView.findViewById(R.id.et_skills);

        final ChipGroup entryChipGroup = rootView.findViewById(R.id.chip_group);
        float elevation = 6f;
        Drawable backgroundDrawable = new ColorDrawable(Color.WHITE);
        AutocompletePresenter<SkillsTable> presenter = new AutoCompletePresenter(requireActivity(),this);
        AutocompleteCallback<SkillsTable> callback = new AutocompleteCallback<SkillsTable>() {
            @Override
            public boolean onPopupItemClicked(Editable editable, SkillsTable item) {
                editable.clear();
                editable.append(item.getNormalized_skill_name());
                final Chip entryChip = getChip(entryChipGroup, editable.toString());
                entryChipGroup.addView(entryChip);
                skill_count.setText(entryChipGroup.getChildCount()+" Skills");
                edit.getText().clear();
                return true;
            }

            public void onPopupVisibilityChanged(boolean shown) {}
        };

        autocomplete = Autocomplete.<SkillsTable>on(edit)
                .with(elevation)
                .with(backgroundDrawable)
                .with(presenter)
                .with(callback)
                .build();

        edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    final Chip entryChip = getChip(entryChipGroup,edit.getText().toString());
                    entryChipGroup.addView(entryChip);
                    edit.getText().clear();
                }
                return false;
            }
        });
    }
    private Chip getChip(final ChipGroup entryChipGroup, String text) {
        final Chip chip = new Chip(requireActivity());
        chip.setChipDrawable(ChipDrawable.createFromResource(requireActivity(), R.xml.my_chip));
        int paddingDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 10,
                getResources().getDisplayMetrics()
        );
        chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
        chip.setText(text);
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entryChipGroup.removeView(chip);
            }
        });
        return chip;
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}

package com.example.admin.resumebuilderproject.views;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import  android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.resumebuilderproject.R;
import com.example.admin.resumebuilderproject.adapter.TemplateListAdapter;
import com.example.admin.resumebuilderproject.contracts.RoomPrePopulateInterface;
import com.example.admin.resumebuilderproject.contracts.TemplateOptions_Interface;
import com.example.admin.resumebuilderproject.fragments.TemplatePreviewFragment;
import com.example.admin.resumebuilderproject.model.Skills;
import com.example.admin.resumebuilderproject.model.TemplateUsageBean;
import com.example.admin.resumebuilderproject.presenters.TemplateOptions_Presenter;
import com.example.admin.resumebuilderproject.room.DatabaseClient;
import com.example.admin.resumebuilderproject.room.SkillsTable;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TemplateOptions_Interface.view, TemplateListAdapter.ItemClickInterface, RoomPrePopulateInterface {
    TemplateOptions_Presenter templateOptions_presenter;
    TemplateListAdapter templateListAdapter;
    RecyclerView template_list_recyclerView;
    TextView basic_templates,modern_templates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onClicks();
        templateOptions_presenter = new TemplateOptions_Presenter(this);
        templateOptions_presenter.getListOfTemplates();
        getSkills();
    }

    private void onClicks() {
        basic_templates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basic_templates.setBackground(getResources().getDrawable(R.drawable.left_circular_rect_background));
                basic_templates.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                modern_templates.setBackground(getResources().getDrawable(R.drawable.right_circular_select_rect_background));
                modern_templates.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });
        modern_templates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modern_templates.setBackground(getResources().getDrawable(R.drawable.right_circular_rect_background));
                modern_templates.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                basic_templates.setBackground(getResources().getDrawable(R.drawable.left_circular_select_rect_background));
                basic_templates.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });
    }

    private void initView() {
        template_list_recyclerView = findViewById(R.id.template_recycler);
        basic_templates = findViewById(R.id.basic_templates);
        modern_templates = findViewById(R.id.modern_templates);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        template_list_recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void setListOfTemplates(List<TemplateUsageBean> templateUsageBeans) {
        templateListAdapter = new TemplateListAdapter(templateUsageBeans,this);
        template_list_recyclerView.setAdapter(templateListAdapter);

    }

    @Override
    public void clickItem(int templateId) {
        Toast.makeText(this, ""+templateId, Toast.LENGTH_SHORT).show();
        showDialog();
    }

    private void showDialog() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag(getString(R.string.Dialog));
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        TemplatePreviewFragment templatePreview = new TemplatePreviewFragment();
        templatePreview.show(getSupportFragmentManager(),getString(R.string.Dialog));

    }

    private void getSkills() {
        class GetTasks extends AsyncTask<Void, Void, List<SkillsTable>> {

            @Override
            protected List<SkillsTable> doInBackground(Void... voids) {
                List<SkillsTable> taskList = DatabaseClient
                        .getInstance(getApplicationContext(),MainActivity.this)
                        .getAppDatabase()
                        .skillsDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<SkillsTable> skillsTables) {
                super.onPostExecute(skillsTables);
                Log.d("response==>", "onPostExecute: "+skillsTables.size());
                for(int i=0;i<skillsTables.size();i++){
                    Log.d("response==>", "onPostExecute: "+skillsTables.get(i).getNormalized_skill_name());
                }
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

    private void deleteTask(final SkillsTable task) {
        class DeleteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext(),MainActivity.this).getAppDatabase()
                        .skillsDao()
                        .delete(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        }

        DeleteTask dt = new DeleteTask();
        dt.execute();

    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}

package com.example.admin.resumebuilderproject.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.admin.resumebuilderproject.R;
import com.example.admin.resumebuilderproject.contracts.RoomPrePopulateInterface;
import com.example.admin.resumebuilderproject.model.Skills;
import com.example.admin.resumebuilderproject.network.RetrofitInstance;
import com.example.admin.resumebuilderproject.network.RetrofitInterface;
import com.example.admin.resumebuilderproject.room.DatabaseClient;
import com.example.admin.resumebuilderproject.room.SkillsTable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity implements RoomPrePopulateInterface {
    public static List<SkillsTable> skillsLists = new ArrayList<>();
    private DatabaseClient databaseClient;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        getDBSkills();

    }
    public void getSkills(){
        final ProgressDialog pd = new ProgressDialog(SplashScreenActivity.this);
        pd.setMessage("Loading...");
        pd.show();
        Call<List<SkillsTable>> myList = RetrofitInstance.getInstance().create(RetrofitInterface.class).getSkillList();
        Log.d("myquery", "onQuery: "+myList.request().url());
        myList.enqueue(new Callback<List<SkillsTable>>() {
            @Override
            public void onResponse(Call<List<SkillsTable>> call, Response<List<SkillsTable>> response) {
                pd.dismiss();

                if(response.code() == 200){
                    skillsLists.addAll(response.body());
                    Log.d("hlo", "onResponse: "+skillsLists.size());
                    saveTask();
//                    DatabaseClient.getInstance(getApplicationContext(),SplashScreenActivity.this).populateDb();
                }else{
                    Log.d("Failure", "onFailure: "+response.message());
                }

            }

            @Override
            public void onFailure(Call<List<SkillsTable>> call, Throwable t) {
                Log.d("Failure", "onFailure: "+t.getMessage());
            }
        });
    }
    private void saveTask() {


        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                //adding to database
                DatabaseClient.getInstance(getApplicationContext(),SplashScreenActivity.this).getAppDatabase()
                        .skillsDao()
                        .insertAll(skillsLists);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

    private void getDBSkills() {
        class GetTasks extends AsyncTask<Void, Void, List<SkillsTable>> {

            @Override
            protected List<SkillsTable> doInBackground(Void... voids) {
                List<SkillsTable> taskList = DatabaseClient
                        .getInstance(getApplicationContext(),SplashScreenActivity.this)
                        .getAppDatabase()
                        .skillsDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<SkillsTable> skillsTables) {
                super.onPostExecute(skillsTables);
                Log.d("response==>", "onPostExecute: "+skillsTables.size());
               if(skillsTables.size()==0){
                   getSkills();
               }else{
                   Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                   startActivity(intent);
                   finish();
               }

            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

    @Override
    public void showProgress() {
        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();
    }

    @Override
    public void hideProgress() {
        Log.d("oui", "hideProgress: ");
        if(pd.isShowing())
            pd.dismiss();

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}

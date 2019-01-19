package com.example.admin.resumebuilderproject.presenters;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.resumebuilderproject.R;
import com.example.admin.resumebuilderproject.contracts.RoomPrePopulateInterface;
import com.example.admin.resumebuilderproject.model.Skills;
import com.example.admin.resumebuilderproject.network.RetrofitInstance;
import com.example.admin.resumebuilderproject.network.RetrofitInterface;
import com.example.admin.resumebuilderproject.room.DatabaseClient;
import com.example.admin.resumebuilderproject.room.SkillsTable;
import com.example.admin.resumebuilderproject.views.MainActivity;
import com.example.admin.resumebuilderproject.views.SplashScreenActivity;
import com.otaliastudios.autocomplete.RecyclerViewPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AutoCompletePresenter extends RecyclerViewPresenter<SkillsTable> {
    protected AutoCompleteRecyclerViewAdapter autoCompleteRecyclerViewAdapter;
    Context context;
    RoomPrePopulateInterface roomPrePopulateInterface;
    public AutoCompletePresenter(Context context,RoomPrePopulateInterface roomPrePopulateInterface) {
        super(context);
        this.context= context;
        this.roomPrePopulateInterface = roomPrePopulateInterface;
    }

    @Override
    protected RecyclerView.Adapter instantiateAdapter() {
        autoCompleteRecyclerViewAdapter = new AutoCompleteRecyclerViewAdapter();
        return autoCompleteRecyclerViewAdapter;
    }

    @Override
    protected void onQuery(@Nullable final CharSequence query) {

            class GetTasks extends AsyncTask<Void, Void, List<SkillsTable>> {

                @Override
                protected List<SkillsTable> doInBackground(Void... voids) {
                    List<SkillsTable> taskList = DatabaseClient
                            .getInstance(context.getApplicationContext(), roomPrePopulateInterface)
                            .getAppDatabase()
                            .skillsDao()
                            .getAll();
                    return taskList;
                }

                @Override
                protected void onPostExecute(List<SkillsTable> skillsTables) {
                    super.onPostExecute(skillsTables);
                    Log.d("response==>", "onPostExecute: "+skillsTables.size());
                    if(skillsTables.size()>0){
                        if(TextUtils.isEmpty(query)){
                            autoCompleteRecyclerViewAdapter.setData(skillsTables);
                        }else{
                            String query1 = query.toString().toLowerCase();
                            List<SkillsTable> filteredList = new ArrayList<>();
                            if(skillsTables.size()>0){
                                for(SkillsTable skills : skillsTables){
                                    if(skills.getNormalized_skill_name().contains(query1)){
                                        filteredList.add(skills);
                                    }
                                }
                            }
                            autoCompleteRecyclerViewAdapter.setData(filteredList);
                        }
                        autoCompleteRecyclerViewAdapter.notifyDataSetChanged();
                    }

                }
            }

            GetTasks gt = new GetTasks();
            gt.execute();
    }

    private class AutoCompleteRecyclerViewAdapter extends RecyclerView.Adapter<AutoCompleteRecyclerViewAdapter.ViwHolder> {
        List<SkillsTable> skillList;
        @NonNull
        @Override
        public AutoCompleteRecyclerViewAdapter.ViwHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ViwHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_skills,viewGroup,false));
        }

        @Override
        public void onBindViewHolder(@NonNull AutoCompleteRecyclerViewAdapter.ViwHolder viwHolder, int i) {
            if (isEmpty()) {
                return;
            }
            final SkillsTable skills = skillList.get(i);
            viwHolder.title.setText(skills.getNormalized_skill_name());
            viwHolder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dispatchClick(skills);
                }
            });
        }

        private boolean isEmpty() {
            return skillList == null || skillList.isEmpty();
        }

        public void setData(List<SkillsTable> data) {
            this.skillList = data;
        }

        @Override
        public int getItemCount() {
            return (isEmpty())?0:skillList.size();
        }

        public class ViwHolder extends RecyclerView.ViewHolder {
            private View root;
            private TextView title;
            public ViwHolder(@NonNull View itemView) {
                super(itemView);
                root = itemView;
                title = itemView.findViewById(R.id.title);
            }
        }
    }
}

package com.example.admin.resumebuilderproject.network;


import com.example.admin.resumebuilderproject.model.Skills;
import com.example.admin.resumebuilderproject.room.SkillsTable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("skills/autocomplete?contains")
    public Call<List<SkillsTable>> getSkillList();
}

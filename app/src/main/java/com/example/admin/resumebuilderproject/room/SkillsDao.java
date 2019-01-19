package com.example.admin.resumebuilderproject.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface SkillsDao {
 
    @Query("SELECT * FROM SkillsTable")
    List<SkillsTable> getAll();
 
    @Insert
    void insert(SkillsTable task);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SkillsTable> skillsTables);
 
    @Delete
    void delete(SkillsTable task);
 
    @Update
    void update(SkillsTable task);
    
}
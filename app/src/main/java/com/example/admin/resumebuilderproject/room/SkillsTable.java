package com.example.admin.resumebuilderproject.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class SkillsTable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "uuid")
    private String uuid;

    @ColumnInfo(name = "suggestion")
    private String suggestion;

    @ColumnInfo(name = "normalized_skill_name")
    private String normalized_skill_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getNormalized_skill_name() {
        return normalized_skill_name;
    }

    public void setNormalized_skill_name(String normalized_skill_name) {
        this.normalized_skill_name = normalized_skill_name;
    }

    public SkillsTable(int id, String uuid, String suggestion, String normalized_skill_name) {
        this.id = id;
        this.uuid = uuid;
        this.suggestion = suggestion;
        this.normalized_skill_name = normalized_skill_name;
    }
}

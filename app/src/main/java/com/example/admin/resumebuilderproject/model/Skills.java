package com.example.admin.resumebuilderproject.model;

public class Skills {
    private String uuid;
    private String suggestion;
    private String normalized_skill_name;

    public Skills(String uuid, String suggestion, String normalized_skill_name) {
        this.uuid = uuid;
        this.suggestion = suggestion;
        this.normalized_skill_name = normalized_skill_name;
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
}

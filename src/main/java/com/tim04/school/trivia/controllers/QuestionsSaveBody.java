package com.tim04.school.trivia.controllers;

public class QuestionsSaveBody {
    private String description;
    private String level;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

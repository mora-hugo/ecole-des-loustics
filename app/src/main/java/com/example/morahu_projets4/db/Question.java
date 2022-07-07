package com.example.morahu_projets4.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "question")
public class Question {
    @PrimaryKey(autoGenerate = true)
    private int question_id;
    private String intitule;
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Question(String intitule) {
        this.intitule = intitule;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public String getIntitule() {
        return intitule;
    }


}

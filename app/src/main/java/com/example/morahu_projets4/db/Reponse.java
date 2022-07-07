package com.example.morahu_projets4.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "reponse",
        foreignKeys = @ForeignKey(entity = Question.class,
        parentColumns = "question_id",
        childColumns = "id_question",
        onDelete = ForeignKey.CASCADE))
public class Reponse {
    @PrimaryKey(autoGenerate = true)
    private int reponse_id;
    private String intitule;

    public String getJuste() {
        return juste;
    }

    public void setJuste(String juste) {
        this.juste = juste;
    }

    private String juste;

    private int id_question;

    public void setReponse_id(int reponse_id) {
        this.reponse_id = reponse_id;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }



    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public String getIntitule() {
        return intitule;
    }

    public boolean isJuste() {

        return Boolean.parseBoolean(juste);
    }
    public int getReponse_id() {
        return reponse_id;
    }
    public int getId_question() {
        return id_question;
    }
}

package com.example.morahu_projets4.db;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import java.util.List;
@Entity
public class QuestionWithReponses {
    @Embedded
    public Question question;
    @Relation(
            parentColumn = "question_id",
            entityColumn = "id_question"
    )
    public List<Reponse> reponses;

}

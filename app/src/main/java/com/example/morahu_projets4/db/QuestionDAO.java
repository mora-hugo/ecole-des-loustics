package com.example.morahu_projets4.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface QuestionDAO {


        @Query("SELECT * FROM question")
        public List<QuestionWithReponses> getQuestionWithReponses();

        @Insert
        long insert(Question question);

        @Insert
        long[] insertAll(Question... question);

        @Delete
        void delete(Question question);

        @Update
        void update(Question question);


}

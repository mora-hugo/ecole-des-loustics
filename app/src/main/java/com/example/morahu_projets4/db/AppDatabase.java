package com.example.morahu_projets4.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class,Question.class,Reponse.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDAO userDao();
    public abstract QuestionDAO questionDao();

}
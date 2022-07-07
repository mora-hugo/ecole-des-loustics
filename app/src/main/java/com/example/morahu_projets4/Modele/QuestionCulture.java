package com.example.morahu_projets4.Modele;

import android.content.Context;
import android.os.AsyncTask;

import com.example.morahu_projets4.db.DatabaseClient;
import com.example.morahu_projets4.db.Question;
import com.example.morahu_projets4.db.QuestionWithReponses;
import com.example.morahu_projets4.db.Reponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class QuestionCulture {
    private Question question;
    private HashSet<Reponse> reponses = new HashSet<>();

    public void setReponseUtilisateur(String reponseUtilisateur) {
        this.reponseUtilisateur = reponseUtilisateur;
    }

    private String reponseUtilisateur;
    private String tag;

    private static HashSet<QuestionCulture> QUESTIONS = new HashSet<>();

    public static HashSet<QuestionCulture> getQUESTIONS() {
        return QUESTIONS;
    }

    public static void GENERATE_QUESTION(Context context,String tag) {
        QUESTIONS.clear();
        //Appel asyncrone pour les questions
        class GetQuestions extends AsyncTask<Void, Void, List<QuestionWithReponses>> {

            @Override
            protected List<QuestionWithReponses> doInBackground(Void... voids) {
                List<QuestionWithReponses> questionsList = DatabaseClient.getInstance(context).getAppDatabase()
                        .questionDao()
                        .getQuestionWithReponses();


                return questionsList;
            }

            @Override
            protected void onPostExecute(List<QuestionWithReponses> questions) {
                super.onPostExecute(questions);

                for(QuestionWithReponses quest : questions) {
                    if(quest.question.getTag().equalsIgnoreCase(tag)) {
                        QuestionCulture.ADD_QUESTION(new QuestionCulture(quest.question,new HashSet<Reponse>(quest.reponses)));

                    }
                }

            }
        }
        GetQuestions gt = new GetQuestions();
        gt.execute();

    }
    //Vide les questions
    public static void CLEAR() {
        QUESTIONS.clear();
    }

    //Melange les questions et les réponses
    public static void SHUFFLE() {
        Random random = new Random();
        List<QuestionCulture> shuffleList = new ArrayList<QuestionCulture>(QUESTIONS);
        Collections.shuffle(shuffleList,random);

        QUESTIONS = new HashSet<QuestionCulture>(shuffleList);
        for(QuestionCulture questions : QUESTIONS) {
            for(Reponse reponse : questions.reponses) {
                System.out.println(reponse.getIntitule());

            }
            System.out.println("----------------");
            questions.shuffle_reponses();
            for(Reponse reponse : questions.reponses) {
                System.out.println(reponse.getIntitule());

            }
        }

    }
    //Methode appelé par SHUFFLE, laissé en public dans l'espoir de la réutiliser plus tard
    public void shuffle_reponses() {
        Random random = new Random();
        List<Reponse> reponses = new ArrayList<Reponse>(this.reponses);
        Collections.shuffle(reponses,random);
        this.reponses.clear();
        for(Reponse reponse : reponses) {
            this.reponses.add(reponse);
        }


    }
    //Calcul des erreurs
    public static int GET_NB_ERREURS() {
        int erreurs = 0;
        for(QuestionCulture question : QUESTIONS) {
            if(!question.reponseOk())  erreurs++; //Si erreur, incrementé 1
        }
        return erreurs;
    }

    public static void ADD_QUESTION(QuestionCulture question) {
        QUESTIONS.add(question);

    }

    public static QuestionCulture GET_QUESTION(int i) {
        return (QuestionCulture) QUESTIONS.toArray()[i];
    }

    public QuestionCulture(Question question, HashSet<Reponse> reponses) {
        this.question = question;
        this.reponses = reponses;

    }

    public String getTag() {
        return tag;
    }

    public String getReponseUtilisateur() {
        return reponseUtilisateur;
    }

    public Question getQuestion() {
        return question;
    }

    public HashSet<Reponse> getReponses() {
        return reponses;
    }

    public Reponse getReponse(int i) {
        return (Reponse)this.getReponses().toArray()[i];
    }
    //Tests de la bdd
    public Reponse getReponseJuste()  {
        for(Reponse reponse : this.getReponses()) {
            if(reponse.isJuste()) {
               return reponse;
            }
        }
        return null;
    }
    //Renvoie la reponse pour la tester
    private String getFormatedReponse() {
        return this.reponseUtilisateur.toLowerCase().trim();
    }
    public boolean reponseOk() {
        if(this.getReponseJuste() == null) {

            return false;

        }
        else {
            System.out.println(getReponseJuste().getIntitule().toLowerCase().trim() +"-----"+this.getFormatedReponse());
            return getReponseJuste().getIntitule().toLowerCase().trim().equalsIgnoreCase(this.getFormatedReponse());
        }
    }


}

package com.example.morahu_projets4;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.morahu_projets4.Modele.QuestionCulture;
import com.example.morahu_projets4.db.Reponse;

public class CultureJeu extends AppCompatActivity {
    public final static String TAG = "TAG";
    public final static String MODE_EXAMEN = "ex";

    public static int NB_QUESTIONS;
    private int current_question = 1;
    private long time_remaining = 0;
    private final static long STARTING_TIME = 10000;

    private Button retour;
    private Button valider;
    private TextView enonce;
    private TextView titre;
    private LinearLayout linear;
    private CountDownTimer timer;
    private ProgressBar bar;
    private boolean finish = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_jeu);
        retour = findViewById(R.id.culture_retour);
        enonce = findViewById(R.id.culture_output);
        linear = findViewById(R.id.culture_layout_btn);
        titre = findViewById(R.id.culture_question);
        bar = findViewById(R.id.progressBar);
        System.out.println("Mode :"  +getIntent().getStringExtra(MODE_EXAMEN));
        NB_QUESTIONS = QuestionCulture.getQUESTIONS().size();
        QuestionCulture.SHUFFLE();
        afficheQuestion(QuestionCulture.GET_QUESTION(current_question-1));



    }
    //Procedure permettant la création des boutons à chaque nouvelle question
    private void createButtons() {


        linear.removeAllViews();
        for(Reponse reponse: QuestionCulture.GET_QUESTION(current_question-1).getReponses()) {

            LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.culture_reponse_bouton,null);
            Button btn = ((Button)linearLayout.findViewById(R.id.culture_inflate_btn));
            btn.setText(reponse.getIntitule());
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickValider(view);
                }
            });

            linear.addView(linearLayout);
        }

    }
    //Procédure appelé par les onclicks des boutons
    public void clickValider(View view) {

        String reponse = ((Button)view).getText().toString();
        if (view == this.retour){ //Si la vue est le bouton retour
            QuestionCulture.GET_QUESTION(current_question -1).setReponseUtilisateur(reponse);
            current_question--;
            timer.cancel();

            afficheQuestion(QuestionCulture.GET_QUESTION(current_question-1));

        }
        else //Sinon bouton suivant
            if(current_question >= NB_QUESTIONS && !finish) {
                QuestionCulture.GET_QUESTION(current_question -1).setReponseUtilisateur(reponse);

                Intent intent = new Intent(this, JeuxFin.class);
                final int nb_erreurs = QuestionCulture.GET_NB_ERREURS();

                intent.putExtra(JeuxFin.CULTURE,"OUI");
                intent.putExtra(JeuxFin.NB_ERREURS,String.valueOf(nb_erreurs));

                startActivity(intent);
                finish();
                finish = true;
            }
            else if(!finish){
                QuestionCulture.GET_QUESTION(current_question -1).setReponseUtilisateur(reponse);
                current_question++;

                timer.cancel();

                afficheQuestion(QuestionCulture.GET_QUESTION(current_question-1));

            }


    }


    private void updateTimer() {

            bar.setProgress((int)((time_remaining * 100) / STARTING_TIME));



        }

    //Affiche la question suivante
    private void afficheQuestion(QuestionCulture question) {


            //Creation d'un timer
            timer = new CountDownTimer(10000, 1000) {
                @Override
                public void onTick(long l) {
                    System.out.println("ok :" );
                    System.out.println(finish);
                    time_remaining = l;
                    updateTimer();
                }

                @Override
                public void onFinish() {

                    if (current_question >= NB_QUESTIONS && !finish) {
                        QuestionCulture.GET_QUESTION(current_question - 1).setReponseUtilisateur("No Answer");

                        Intent intent = new Intent(CultureJeu.this, JeuxFin.class);
                        final int nb_erreurs = QuestionCulture.GET_NB_ERREURS();
                        ;
                        intent.putExtra(JeuxFin.NB_ERREURS, String.valueOf(nb_erreurs));
                        intent.putExtra(JeuxFin.CULTURE,"OUI");
                        finish();
                        startActivity(intent);
                        timer.cancel();
                        finish = true;
                    } else if(!finish){
                        QuestionCulture.GET_QUESTION(current_question - 1).setReponseUtilisateur("No Answer");
                        current_question++;
                        timer.cancel();
                        afficheQuestion(QuestionCulture.GET_QUESTION(current_question - 1));
                    }



                }
            };
            //On allume pas le timer si on est pas en mode examen
            if(getIntent().getStringExtra(MODE_EXAMEN) != null && finish == false) {
                timer.start();
            }

            createButtons();
            this.titre.setText(this.current_question + "/" + NB_QUESTIONS);
            this.enonce.setText(question.getQuestion().getIntitule());
            if(getIntent().getStringExtra(MODE_EXAMEN) == null) { //Si mode examen, pas de bouton retour
                if (current_question <= 1) {
                    this.retour.setEnabled(false);
                } else {
                    this.retour.setEnabled(true);
                }
            }
            else {
                this.retour.setEnabled(false);
            }

        }

}
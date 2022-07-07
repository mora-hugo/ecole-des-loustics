package com.example.morahu_projets4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JeuxFin extends AppCompatActivity {
    public static String NB_ERREURS = "operation";
    public static String CULTURE = "exo";

    private int nb_erreurs = 0;
    private Button btn_retour_exo;
    private Button btn_autre_exo;
    private TextView text_nb_erreurs;
    private TextView text_msg_erreurs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematique_fin);

        nb_erreurs = Integer.parseInt(getIntent().getStringExtra(NB_ERREURS));
        btn_retour_exo = findViewById(R.id.fin_math_retour_exo);
        btn_autre_exo = findViewById(R.id.fin_math_autre_exo);
        text_msg_erreurs = findViewById(R.id.math_fin_message);
        text_nb_erreurs = findViewById(R.id.math_nb_erreurs);
        text_msg_erreurs.setText(nb_erreurs > 0 ? "Dommage !" : "Bravo");
        text_nb_erreurs.setText(nb_erreurs == 0 ? "" : "Vous avez " + nb_erreurs + " erreurs");
        //Si pas d'erreur ou en mode exam, pas de retour à l'exo
        System.out.println(" yo : " +  getIntent().getStringExtra(CULTURE));
        if(nb_erreurs == 0 || getIntent().getStringExtra(CULTURE) != null) {
            btn_retour_exo.setEnabled(false);
        }
        else {
            btn_retour_exo.setEnabled(true);
        }
    }
    //Appelé par l'appel d'un bouton
    public void clickBouton(View view) {
        if ( view == btn_retour_exo) {
            finish();
        }
        else if (view == btn_autre_exo) {
            Intent intent = new Intent(this, Exercices.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}
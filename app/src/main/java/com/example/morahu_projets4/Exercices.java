package com.example.morahu_projets4;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.morahu_projets4.Modele.Button;
import com.example.morahu_projets4.Modele.QuestionCulture;

import java.util.ArrayList;
import java.util.HashSet;

public class Exercices extends AppCompatActivity {
    private ArrayList<Button> jeux = new ArrayList<Button>();
    private LinearLayout jeuLayout;

    private TextView titre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercices);
        //Creation des jeux
        jeux.add(new Button(R.drawable.calcul, MathematiqueChoisirExo.class,"Mathematiques"));
        jeux.add(new Button(R.drawable.culture,CultureChoisirExo.class,"Culture"));
        jeuLayout = findViewById(R.id.jeux);
        titre = findViewById(R.id.titre_nom);
        MyApplication appli = (MyApplication) getApplication();
        try {
            titre.setText(appli.isConnected() ? appli.getUser().getNomComplet() : "Visiteur");

        }catch(Exception e) {
            titre.setText("Visiteur");
        }
        initJeux();
        updateBDD();
    }

    private void updateBDD() {
        QuestionCulture.CLEAR();
        QuestionCulture.GENERATE_QUESTION(this.getApplicationContext(),"ez");
        QuestionCulture.SHUFFLE();
    }



    private void initJeux() {
        //info a recup quand bdd
        LinearLayout layout = findViewById(R.id.jeux);
        Button.insertOnLayout(this,getLayoutInflater(),jeux,layout,false,null);
    }
}
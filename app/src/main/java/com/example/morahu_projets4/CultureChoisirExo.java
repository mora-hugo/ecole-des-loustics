package com.example.morahu_projets4;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.morahu_projets4.Modele.Button;

import java.util.ArrayList;

public class CultureChoisirExo extends AppCompatActivity {
    private LinearLayout linearLayout;
    private ArrayList<Button> boutons = new ArrayList<Button>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture_choisir_exo);
        linearLayout = findViewById(R.id.culture_jeux);

        //Creation des boutons - et génération de la bdd un peu avant pour qu'elle soit utilisable
        boutons.add(new Button(R.drawable.histoire_tag,CultureJeu.class,"Histoire",CultureJeu.TAG,"story"));
        boutons.add(new Button(R.drawable.histoire_tag,CultureJeu.class,"Français",CultureJeu.TAG,"fr"));
        boutons.add(new Button(R.drawable.histoire_tag,CultureJeu.class,"Pays",CultureJeu.TAG,"country"));
        boutons.add(new Button(R.drawable.histoire_tag,CultureJeu.class,"IUT",CultureJeu.TAG,"iut"));

        System.out.println(((Switch)findViewById(R.id.examen)).isChecked());
        Button.insertOnLayout(this,this.getLayoutInflater(),boutons,linearLayout,true,findViewById(R.id.examen));


    }


}
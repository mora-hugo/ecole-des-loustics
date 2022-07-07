package com.example.morahu_projets4;

import android.os.Bundle;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.morahu_projets4.Modele.Button;

import java.util.ArrayList;

public class MathematiqueChoisirExo extends AppCompatActivity {
    GridLayout layout;

    ArrayList<Button> jeuxMaths = new ArrayList<Button>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematique_choisir_exo);
        layout = findViewById(R.id.gridLayoutBoutonsMath);

        jeuxMaths.add(new Button(R.drawable.addition,MathematiqueJeu.class,"",MathematiqueJeu.OPERATION,"add"));
        jeuxMaths.add(new Button(R.drawable.soustraction,MathematiqueJeu.class,"",MathematiqueJeu.OPERATION,"sub"));
        jeuxMaths.add(new Button(R.drawable.division,MathematiqueJeu.class,"",MathematiqueJeu.OPERATION,"div"));
        jeuxMaths.add(new Button(R.drawable.multiplication,MathematiqueTableMultiIntro.class,""));

        Button.insertOnLayout(this,getLayoutInflater(),jeuxMaths,layout,false,null);
    }

}
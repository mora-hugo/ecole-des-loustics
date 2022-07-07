package com.example.morahu_projets4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class MathematiqueTableMultiIntro extends AppCompatActivity {
    private Button bouton;
    private NumberPicker picker;
    private Switch buttonSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematique);
        bouton = findViewById(R.id.btn);
        picker = findViewById(R.id.picker);
        picker.setMinValue(1);
        picker.setMaxValue(9);
        buttonSwitch = findViewById(R.id.mathematique_switch);
    }

    public void onclick(View view) {
        if(view == bouton) {
            int nb = picker.getValue();
            if(buttonSwitch.isChecked()) {
                Intent intent = new Intent(this, MathematiqueJeu.class);
                intent.putExtra(MathematiqueJeu.OPERATION,"prod"); //Ajout du mode produit ( multiplication)
                intent.putExtra(MathematiqueJeu.Table,String.valueOf(nb)); //Indication de la table
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(this, MathematiqueJeuEntrainement.class);
                intent.putExtra(MathematiqueJeuEntrainement.OPERATION,"prod");
                intent.putExtra(MathematiqueJeuEntrainement.Table,String.valueOf(nb));
                startActivity(intent);
            }



        }
    }
}
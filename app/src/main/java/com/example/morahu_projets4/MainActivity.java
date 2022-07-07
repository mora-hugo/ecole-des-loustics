package com.example.morahu_projets4;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    private Button eleve;
    private Button anonyme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eleve = findViewById(R.id.boutonEleve);
        anonyme = findViewById(R.id.boutonVisiteur);
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1
        ); //Demande de la permission d'écrire dans l'external storage
    }
    //Apellé par les clicks sur les boutons
    public void clickBouton(View view) {

        if(view == eleve) {
            Intent intent = new Intent(this, ListeUtilisateur.class);
            startActivity(intent);

        }
        else if(view == anonyme) {
            Intent intent = new Intent(this, Exercices.class);
            ((MyApplication)getApplication()).setConnected(false);
            startActivity(intent);

        }
    }
}
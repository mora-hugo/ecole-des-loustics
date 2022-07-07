package com.example.morahu_projets4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.morahu_projets4.Modele.Calcul;
import com.example.morahu_projets4.Modele.Operation;

import java.util.ArrayList;

public class MathematiqueJeuEntrainement extends AppCompatActivity {
    private final static int NB_CALCULS = 10;
    public final static String OPERATION = "opration";
    public final static String Table = "Table";
    private ArrayList<EditText> editTexts= new ArrayList<>();
    private Button valider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematique_jeu_entrainement);
        valider = findViewById(R.id.mathematique_exercice_bouton);
        ((TextView)findViewById(R.id.titre_table_entrainement)).setText("Table de " + (!getIntent().hasExtra(Table) ? "?" : Integer.parseInt(getIntent().getStringExtra(Table))));

        Calcul.CLEAR();//Vider les calculs
        Calcul.GENERATE_CALCULS(NB_CALCULS, Operation.getOperationFromName(getIntent().getStringExtra(OPERATION),!getIntent().hasExtra(Table) ? null : Integer.parseInt(getIntent().getStringExtra(Table)))); //Creation calcul
        LinearLayout layout = findViewById(R.id.mathematique_exercice_layout);

        for(Calcul calcul : Calcul.getCalculs()) {

            LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.mathematique_entrainement,null);
            ((TextView)linearTMP.findViewById(R.id.mathematique_exercice_textview)).setText(calcul.toString());
            editTexts.add(((EditText)linearTMP.findViewById(R.id.mathematique_exercice_editText)));
            layout.addView(linearTMP);
        }


    }

    //appelé par les onclick des boutons
    public void click(View view) {
        if(view == valider) {
            for(int i = 0; i < editTexts.size();i++) {
                //Pour chaque valeure, tester si elle n'est pas vide et sinon l'ajouter aux réponses
                Integer editTextValue = editTexts.get(i).getText().toString().equals("") ? null : Integer.valueOf(editTexts.get(i).getText().toString());
                if(editTextValue == null) {
                    editTexts.get(i).setError("Veuillez entrer un numéro");
                    editTexts.get(i).requestFocus();
                    return;
                }
                Calcul.getCalcul(i).setReponse(editTextValue);
            }
            final int nb_erreurs = Calcul.GET_NB_ERREURS(); //Calcul des erreurs
            Intent intent = new Intent(this, JeuxFin.class);
            //Fin de l'activite et appel des autres
            if(nb_erreurs == 0) { // Pour ne pas revenir sur l'activite
                finish();
            }
            intent.putExtra(JeuxFin.NB_ERREURS,String.valueOf(nb_erreurs));
            startActivity(intent);

        }
    }
}
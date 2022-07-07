package com.example.morahu_projets4;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.morahu_projets4.Modele.Calcul;
import com.example.morahu_projets4.Modele.Operation;

public class MathematiqueJeu extends AppCompatActivity {
    public static String OPERATION = "operation";
    public static String Table = "table";

    private static final int NB_CALCULS = 10;
    private int current_calcul = 1;


    private EditText reponseEditText;
    private Button boutonValider;
    private Button boutonRetour;
    private TextView calcul_affichage;
    private TextView titre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematique_jeu);
        //Ajouts des vues aux attributs
        reponseEditText = findViewById(R.id.calcul_input);
        boutonValider = findViewById(R.id.calcul_valider);
        boutonRetour = findViewById(R.id.calcul_retour);
        titre = findViewById(R.id.calcul_titre);
        calcul_affichage = findViewById(R.id.calcul_output);

        //Generations questions
        Calcul.CLEAR();
        Calcul.GENERATE_CALCULS(NB_CALCULS,Operation.getOperationFromName(getIntent().getStringExtra(OPERATION),!getIntent().hasExtra(MathematiqueJeu.Table) ? null : Integer.parseInt(getIntent().getStringExtra(Table))));
        Calcul.SHUFFLE();
        afficheCalcul(Calcul.getCalcul(current_calcul-1));
        //Listener pour que le boutons entree passe à la question suivante

    }
    //Affiche le calcul en parametre
    private void afficheCalcul(Calcul calcul) {
        this.titre.setText(calcul.getOperation().getNom() + " - " + this.current_calcul + "/" + NB_CALCULS);
        this.calcul_affichage.setText(calcul.toString());
        this.reponseEditText.setText(calcul.getReponse() == null ? "" : String.valueOf(calcul.getReponse()));
        if(current_calcul<=1) {
            this.boutonRetour.setEnabled(false);
        }
        else {
            this.boutonRetour.setEnabled(true);
        }
    }
    //Joue une erreur
    private void showError(Exception e) {
        reponseEditText.setError(e.getMessage());
        reponseEditText.requestFocus();

    }
    //Lit de texte de l'editText, si il n'y en a pas, throw une exception
    private String getText() throws Exception {
        String reponse = this.reponseEditText.getText().toString();
        if(reponse.length() == 0) {
            throw new Exception("Nombre pas bon");
        }
        return reponse;
    }
    //Appelé par les onclick
    public void clickValider(View view) {
        String reponse ="";
        //Try catch pour la fonction getText()
        try {
            reponse = getText();
        } catch(Exception e) {
            if(view == boutonValider) {showError(e);return;}
        }
        if(view == this.boutonValider) {
            if(current_calcul >= NB_CALCULS) { //Si dernier calcul
                Calcul.getCalcul(current_calcul-1).setReponse(Integer.parseInt(reponse)); //Methode permettant de trouver le calcul courant
                Intent intent = new Intent(this, JeuxFin.class);

                final int nb_erreurs = Calcul.GET_NB_ERREURS(); //Calcul des erreurs

                if(nb_erreurs == 0) { // Pour ne pas revenir sur l'activite

                    finish();
                }
                intent.putExtra(JeuxFin.NB_ERREURS,String.valueOf(nb_erreurs));
                startActivity(intent);
            }
            else {

                Calcul.getCalcul(current_calcul-1).setReponse(Integer.parseInt(reponse));
                current_calcul++;

                afficheCalcul(Calcul.getCalcul(current_calcul-1));

            }
        }
        else if (view == this.boutonRetour){
            reponseEditText.setError(null);//removes error

            //Retour
            try {
                Calcul.getCalcul(current_calcul-1).setReponse(Integer.parseInt(getText()));

            }
            catch (Exception e) {
                //Les retours sans avoir rempli le champ est autorisé
            }
            finally {
                current_calcul--;

                afficheCalcul(Calcul.getCalcul(current_calcul-1));
                reponseEditText.setText(String.valueOf(Calcul.getCalcul(current_calcul-1).getReponse()));
            }
        }
    }
}
package com.example.morahu_projets4.Modele;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.morahu_projets4.CultureJeu;
import com.example.morahu_projets4.R;

import java.util.List;
import java.util.concurrent.Callable;

public class Button { //Classe permettant de faire les gros boutons
    private int imageId;


    private Class classe;
    private String libelle;
    private boolean isClickable = true;
    private String info;
    private String variable;
    private String variableExamen;
    public Boolean getExamen() {
        return examen;
    }

    public void setExamen(Boolean examen) {
        this.examen = examen;
    }

    private Boolean examen;
    public Button(int imageId, Class classe, String libelle, String variable,String info,String variableExamen) {

        this.variable = variable;
        this.imageId = imageId;
        this.classe = classe;
        this.libelle = libelle;
        this.info = info;
        this.variableExamen = variableExamen;
    }
    public Button(int imageId, Class classe, String libelle, String variable,String info) {

        this.variable = variable;
        this.imageId = imageId;
        this.classe = classe;
        this.libelle = libelle;
        this.info = info;
    }
    public Button(int imageId, Class classe, String libelle) {
        this.imageId = imageId;
        this.classe = classe;
        this.libelle = libelle;
    }

    public Button(int imageId, String libelle) {

        this.imageId = imageId;
        this.libelle = libelle;
        this.isClickable = false;
    }

    public boolean hasInfo() {return info != null;}
    public String getInfo() {return info;}
    public int getImageId() {
        return imageId;
    }

    public String getLibelle() {
        return libelle;
    }

    public Class getClasse() {
        return classe;
    }


    public String getVariable() {
        return variable;
    }

    public static void insertOnLayout(AppCompatActivity
                                               activity, LayoutInflater inflater, List<Button> boutons, ViewGroup view) {
        for (Button jeu : boutons) {
            ConstraintLayout linearTMP = (ConstraintLayout) inflater.inflate(R.layout.activity_boucle_jeu,null);
            TextView texte = (TextView) linearTMP.findViewById(R.id.texte_jeu);
            texte.setText(jeu.getLibelle());

            ImageView image = (ImageView) linearTMP.findViewById(R.id.image_jeu);
            image.setImageResource(jeu.getImageId());
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, jeu.getClasse());
                    if(jeu.hasInfo()) {
                        intent.putExtra(jeu.getVariable(),jeu.getInfo());
                    }
                    activity.startActivity(intent);
                }
            });

            view.addView(linearTMP);


        }
    }

    public static void insertOnLayout(AppCompatActivity
                                              activity, LayoutInflater inflater, List<Button> boutons, ViewGroup view,Boolean isTag,Switch btn) {
        for (Button jeu : boutons) {
            ConstraintLayout linearTMP = (ConstraintLayout) inflater.inflate(R.layout.activity_boucle_jeu,null);
            TextView texte = (TextView) linearTMP.findViewById(R.id.texte_jeu);
            texte.setText(jeu.getLibelle());

            ImageView image = (ImageView) linearTMP.findViewById(R.id.image_jeu);
            image.setImageResource(jeu.getImageId());
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isTag) {
                        try { //Appelé une fonction si elle est en parametre
                            Button.getCallableWithTag(activity,jeu.getInfo()).call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    Intent intent = new Intent(activity, jeu.getClasse());
                    if(jeu.hasInfo()) {
                        intent.putExtra(jeu.getVariable(),jeu.getInfo());
                    }
                    if(btn != null && btn.isChecked()) {

                        intent.putExtra(CultureJeu.MODE_EXAMEN,"OUI");
                    }
                    activity.startActivity(intent);
                }
            });

            view.addView(linearTMP);


        }
    }
    //Renvoie une fonction permettant de générer les questions de culture
    private static Callable<Void> getCallableWithTag(AppCompatActivity activity, String tag) {
        return new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                QuestionCulture.GENERATE_QUESTION(activity,tag);
                return null;
            }
        };
    }


}

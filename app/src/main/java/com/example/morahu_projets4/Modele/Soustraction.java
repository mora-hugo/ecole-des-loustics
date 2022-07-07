package com.example.morahu_projets4.Modele;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

public class Soustraction implements Operation {
    @Override
    public int getResult(int terme1, int terme2) {
        return terme1 - terme2;
    }

    @Override
    public Calcul generate_calcul() {
        Calcul calcul;
        Random random = new Random();
        do {
            calcul = new Calcul(random.nextInt(9-1)+1 ,random.nextInt(9-1)+1 ,this);
            System.out.println(calcul.getResult());
        }while(calcul.getResult() <= 0); //Pas possible d'avoir une reponse négatif ou nulle
        return calcul;

    }

    @Override
    public String getNom() {
        return "Soustraction";
    }
    @Override
    public String getOperand() {
        return "-";
    }
}

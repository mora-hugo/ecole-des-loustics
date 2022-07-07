package com.example.morahu_projets4.Modele;

import java.util.Random;

public class Division implements Operation{
    @Override
    public int getResult(int terme1, int terme2) {
        return terme1 / terme2;
    }

    @Override
    public Calcul generate_calcul() {
        Random random = new Random();
        int multiplicateur = random.nextInt(5-2)+2;
        return new Calcul(multiplicateur*random.nextInt(10-multiplicateur)+multiplicateur,multiplicateur,this);
    }

    @Override
    public String getNom() {
        return "Division";
    }
    @Override
    public String getOperand() {
        return "/";
    }
}

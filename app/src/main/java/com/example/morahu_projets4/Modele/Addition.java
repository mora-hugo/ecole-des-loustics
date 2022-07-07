package com.example.morahu_projets4.Modele;

import java.util.Random;

public class Addition implements Operation {
    @Override
    public int getResult(int terme1, int terme2) {
        return terme1 + terme2;
    }

    @Override
    public Calcul generate_calcul() {
        Random random = new Random();
        return new Calcul(random.nextInt(9-1)+1 ,random.nextInt(9-1)+1 ,this);
    }

    @Override
    public String getNom() {
        return "Addition";
    }
    @Override
    public String getOperand() {
        return "+";
    }
}

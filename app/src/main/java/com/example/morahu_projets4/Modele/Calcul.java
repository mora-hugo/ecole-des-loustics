package com.example.morahu_projets4.Modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Calcul {
    int terme1;
    int terme2;
    Operation operation;

    public Operation getOperation() {
        return operation;
    }

    Integer reponse;

    public Integer getReponse() {
        return reponse;
    }

    public void setReponse(int reponse) {
        this.reponse = reponse;
    }

    private static ArrayList<Calcul> CALCULS = new ArrayList<>();

    public static ArrayList<Calcul> getCalculs() {
        return CALCULS;
    }

    public static Calcul getCalcul(int index) {
        return (Calcul)CALCULS.toArray()[index];
    }

    public static void GENERATE_CALCULS(int number,Operation operation) {

        for (int i = 0; i < number; i++) {

            Calcul.addCalcul(operation.generate_calcul());
            for(Calcul calcul : getCalculs()) {
                System.out.println(calcul);
            }
            System.out.println("----------------");
        }
    }

    public static void CLEAR() {
        Calcul.getCalculs().clear();
    }
    public static void SHUFFLE() {
        Random random = new Random();
        List<Calcul> shuffleList = new ArrayList<Calcul>(CALCULS);
        Collections.shuffle(shuffleList,random);
        CALCULS = new ArrayList<Calcul>(shuffleList);
    }

    public static int GET_NB_ERREURS() {
        int erreurs = 0;
        for(Calcul calcul : Calcul.getCalculs()) {
            if(!calcul.reponseOk())  erreurs++; //Si erreur, incrementé 1
        }
        return erreurs;
    }
    public static void addCalcul(Calcul calcul) {
        CALCULS.add(calcul);
    }


    public Calcul(int terme1, int terme2, Operation operation) {
        this.terme1 = terme1;
        this.terme2 = terme2;
        this.operation = operation;
    }
    @Override
    public String toString() {
        return getTerme1() + " " + getOperation().getOperand() + " " + getTerme2() + " = ";
    }
    public boolean reponseOk() {
        return this.getResult() == this.reponse;
    }

    public int getResult() {
        return this.operation.getResult(this.terme1,this.terme2);
    }

    public int getTerme1() {
        return terme1;
    }

    public int getTerme2() {
        return terme2;
    }
}

package com.example.morahu_projets4.Modele;

public class Multiplication implements Operation{
    private int table;
    private int current_op = 1;

    public int getTable() {
        return table;
    }

    public Multiplication(int table) {
        this.table = table;
    }
    @Override
    public int getResult(int terme1, int terme2) {
        return terme1 * terme2;
    }

    @Override
    public String getNom() {
        return "Multiplication";
    }

    @Override
    public Calcul generate_calcul() {

        return new Calcul(table,current_op++,this);

    }
    @Override
    public String getOperand() {
        return "x";
    }


}

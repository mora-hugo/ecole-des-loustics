package com.example.morahu_projets4.Modele;

public interface Operation {
        int getResult(int terme1, int terme2);

        Calcul generate_calcul(); // Certaines opérations nécessites une creation différentes pour qu'elle soient cohérentes
        String getNom();

        String getOperand();
        //A partir du nom en extra, retourne une opération adequat
        static Operation getOperationFromName(String name,Integer params) {
            switch(name) {
                case "add":
                    return new Addition();
                case "sub":
                    return new Soustraction();
                case "div":
                    return new Division();
                default:
                    return new Multiplication(params);
            }
        }
}

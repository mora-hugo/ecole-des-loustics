package com.example.morahu_projets4.db;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class DatabaseClient {

    // Instance unique permettant de faire le lien avec la base de données
    private static DatabaseClient instance;

    // Objet représentant la base de données de votre application
    private AppDatabase appDatabase;

    // Constructeur
    private DatabaseClient(final Context context) {

        // Créer l'objet représentant la base de données de votre application
        // à l'aide du "Room database builder"
        // MyToDos est le nom de la base de données
        //appDatabase = Room.databaseBuilder(context, AppDatabase.class, "MyToDos").build();

        ////////// REMPLIR LA BD à la première création à l'aide de l'objet roomDatabaseCallback
        // Ajout de la méthode addCallback permettant de populate (remplir) la base de données à sa création
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "EcoleDesLoustics").addCallback(roomDatabaseCallback).build();
    }

    // Méthode statique
    // Retourne l'instance de l'objet DatabaseClient
    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    // Retourne l'objet représentant la base de données de votre application
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    // Objet permettant de populate (remplir) la base de données à sa création
    RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {

        // Called when the database is created for the first time.
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //Question sur l'iut
            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Dans quel rue se situe l'IUT\",\"iut\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Doyen Gosse\",\"true\", 1);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Berriat\",\"false\", 1);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Victor Hugo\",\"false\", 1);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Quel est le meilleur prof\",\"iut\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Francis Brunet Manquat\",\"true\", 2);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Mon prof d'anglais de 6ème\",\"false\", 2);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Mon prof d'histoire de 2nd\",\"false\", 2);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Quel est le prof le plus beau\",\"iut\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Francis Brunet Manquat\",\"true\", 3);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Mon prof d'anglais de 6ème\",\"false\", 3);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Mon prof d'histoire de 2nd\",\"false\", 3);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Quel est le prof le plus intelligent\",\"iut\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Francis Brunet Manquat\",\"true\", 4);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Mon prof d'anglais de 6ème\",\"false\", 4);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Mon prof d'histoire de 2nd\",\"false\", 4);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Quel est le prof le plus cultivé\",\"iut\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Francis Brunet Manquat\",\"true\", 5);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Mon prof d'anglais de 6ème\",\"false\", 5);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Mon prof d'histoire de 2nd\",\"false\", 5);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Quel prof va me mettre une bonne note\",\"iut\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Francis Brunet Manquat\",\"true\", 6);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Mon prof d'anglais de 6ème\",\"false\", 6);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Mon prof d'histoire de 2nd\",\"false\", 6);");

            //Questions sur les capitales

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Capital de la France\",\"country\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Paris\",\"true\", 7);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Seoul\",\"false\", 7);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Madrid\",\"false\", 7);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Capital de la Chine\",\"country\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Pékin\",\"true\", 8);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Moscou\",\"false\", 8);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Bangkok\",\"false\", 8);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Capital de l'Argentine\",\"country\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Buenos Aires\",\"true\", 9);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Pyongyang\",\"false\", 9);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Bogota\",\"false\", 9);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Capital de Singapour\",\"country\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Singapour\",\"true\", 10);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Kaboul\",\"false\", 10);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Alger\",\"false\", 10);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Capital de l'Ethiopie\",\"country\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Addis-Abeba\",\"true\", 11);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Luanda\",\"false\", 11);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Bakou\",\"false\", 11);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Capital du Japon\",\"country\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Tokyo\",\"true\", 12);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Luanda\",\"false\", 12);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Bakou\",\"false\", 12);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Capital du Chili\",\"country\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Santiago\",\"true\", 13);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Ankara\",\"false\", 13);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Dacca\",\"false\", 13);");

            //Question d'histoire

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Quelle sont les dates de la 1ère Guerre Mondiale\",\"story\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"1914 - 1918\",\"true\", 14);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"1939 - 1945\",\"false\", 14);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"1714 - 1718\",\"false\", 14);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Quelle sont les dates de la seconde Guerre Mondiale\",\"story\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"1939 - 1945\",\"true\", 15);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"1914 - 1918\",\"false\", 15);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"1714 - 1718\",\"false\", 15);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"En quelle année se déroule la 1ère élection présidentielle Française\",\"story\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"1848\",\"true\", 16);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"1809\",\"false\", 16);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"1883\",\"false\", 16);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Quel président a eu le mandat le plus long\",\"story\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"François Mitterand\",\"true\", 17);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Charles de Gaulle\",\"false\", 17);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Emmanuel Macron\",\"false\", 17);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Quand l’URSS a-t-elle disparue\",\"story\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"1991\",\"true\", 18);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"1802\",\"false\", 18);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"1980\",\"false\", 18);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Qui est le plus jeune président Français ?\",\"story\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Emmanuel Macron\",\"true\", 19);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Valéry Giscard d'Estaing\",\"false\", 19);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Jean Castex\",\"false\", 19);");

            //Question de Français

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"… de te plaindre !\",\"fr\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Arrête\",\"true\", 20);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Arrêtes\",\"false\", 20);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"Arête\",\"false\", 20);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Elle a … attentivement les leçons\",\"fr\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"écouté\",\"true\", 21);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"écoutée\",\"false\", 21);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"écoutées\",\"false\", 21);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Elles se sont … compte de leurs erreurs.\",\"fr\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"rendu\",\"true\", 22);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"rendues\",\"false\", 22);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"rendue\",\"false\", 22);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Je regrette qu’il … pris cette décision.\",\"fr\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"ait\",\"true\", 23);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"aie\",\"false\", 23);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"est\",\"false\", 23);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Quelle forme est correcte ?\",\"fr\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"J’enverrai\",\"true\", 24);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"J’envoierai \",\"false\", 24);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"J’envoyerai\",\"false\", 24);");

            db.execSQL("INSERT INTO question (intitule, tag) VALUES(\"Que signifie l’expression :  « Sauter aux yeux » ?\",\"fr\");");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"C’est évident !\",\"true\", 25);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"C’est bizarre !\",\"false\", 25);");
            db.execSQL("INSERT INTO reponse (intitule, juste,id_question) VALUES(\"C’est incroyable !\",\"false\", 25);");
        }
    };
}

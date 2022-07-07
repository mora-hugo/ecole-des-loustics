package com.example.morahu_projets4.db;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Log;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.File;
import java.io.FileOutputStream;

@Entity(tableName = "user")
public class User {
    private String nom;
    private String prenom;


    @PrimaryKey(autoGenerate = true)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Drawable getImage() {



        String path = Environment.getExternalStorageDirectory()+"/images_projet/"+String.valueOf(this.id)+".png";
        Drawable createFromPath = Drawable.createFromPath(path);
        return createFromPath;

            

    }

    public void setImage(Drawable image) {

            Bitmap bitmap = ((BitmapDrawable)image).getBitmap();

            //choisir le dossier comme dossier de stockge du fichier
            File extStore = Environment.getExternalStorageDirectory();

            File dir = new File(Environment.getExternalStorageDirectory(),"images_projet");
            if(!dir.exists()) {
                if (!dir.mkdirs()) {
                    Log.d("App", "failed to create directory");
                }
            }
            //chemin du fichier incluant le nom du fichier
            String path = extStore.getAbsolutePath() + "/images_projet/" + String.valueOf(this.id)+".png";


            //création du fichier
            try {
                File myFile = new File(path);
                FileOutputStream fOut = new FileOutputStream(myFile);
                getResizedBitmap(bitmap,100).compress(Bitmap.CompressFormat.PNG,100,fOut);


                fOut.close();

            } catch (Exception e) {
                e.printStackTrace();
            }



    }


    public String getNomComplet() {
        return nom + " " + prenom;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    //Fonction utilitaire permettant de changer la taille
    private Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}




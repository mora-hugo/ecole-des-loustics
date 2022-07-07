package com.example.morahu_projets4;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.morahu_projets4.db.DatabaseClient;
import com.example.morahu_projets4.db.User;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AjouterUtilisateur extends AppCompatActivity {

    private DatabaseClient mDb;

    private Button valider;
    private EditText prenom;
    private EditText nom;
    private ImageView image;
    public final static int PICK_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_utilisateur);
        mDb = DatabaseClient.getInstance(getApplicationContext());
        //Ajouts des vues aux attributs
        valider = findViewById(R.id.addUser_valider);
        prenom = findViewById(R.id.addUser_prenom);
        nom = findViewById(R.id.addUser_nom);
        image = findViewById(R.id.pick_a_picture);

    }

    public void click(View view) {
        if (view == valider) {
            final String prenom = this.prenom.getText().toString().trim();
            final String nom = this.nom.getText().toString().trim();
            if (prenom.isEmpty()) {
                this.prenom.setError("Prénom requis");
                this.prenom.requestFocus();
                return;
            }

            if (nom.isEmpty()) {
                this.nom.setError("Nom requis");
                this.nom.requestFocus();
                return;
            }
            //Requete asyncronne pour inserer un user
            class SaveTask extends AsyncTask<Void, Void, User> {

                @Override
                protected User doInBackground(Void... voids) {

                    // creating a task
                    User user = new User(prenom,nom);

                    // mettre à jour l'id de la tache
                    long id = mDb.getAppDatabase()
                            .userDao()
                            .insert(user);


                    // Nécessaire si on souhaite avoir accès à l'id plus tard dans l'activité
                    user.setId(id);
                    user.setImage(image.getDrawable());

                    return user;
                }

                @Override
                protected void onPostExecute(User user) {
                    super.onPostExecute(user);

                    // Quand la tache est créée, on arrête l'activité AddTaskActivity (on l'enleve de la pile d'activités)
                    setResult(RESULT_OK);
                    ((MyApplication) getApplication()).setUser(user);
                    setResult(RESULT_OK);
                    finish();
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                }
            }
            SaveTask st = new SaveTask();
            st.execute();
        }
        else if(view == image){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Choisir un avatar"), PICK_IMAGE);
        }
    }
    //Des que l'utilisateur à ajouter une photo
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Erreur", Toast.LENGTH_LONG);
                return;
            }
            InputStream inputStream = null;
            try {
                inputStream = getContentResolver().openInputStream(data.getData());
                this.image.setImageDrawable(Drawable.createFromStream(inputStream, data.getData().toString()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}




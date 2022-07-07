
package com.example.morahu_projets4;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.morahu_projets4.db.DatabaseClient;
import com.example.morahu_projets4.db.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ListeUtilisateur extends AppCompatActivity {
    private DatabaseClient mDb;
    private ListView listview;
    private HashSet<User> users = new HashSet<>();
    public static final int REQUEST = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_utilisateur);
        listview = findViewById(R.id.list_of_users);
        mDb = DatabaseClient.getInstance(getApplicationContext());
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((MyApplication)getApplication()).setUser((User)listview.getAdapter().getItem(i));
                Intent intent = new Intent(ListeUtilisateur.this, Exercices.class);
                startActivity(intent);
                finish();
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                PopupMenu popup = new PopupMenu(ListeUtilisateur.this, view);
                MenuInflater inflater = popup.getMenuInflater();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        User user = (User)adapterView.getItemAtPosition(i);
                        List<User> list = new ArrayList<User>();
                        list.add(user);
                        //Appel asyncronne pour détruire un utilisateur
                        class DeleteUsers extends AsyncTask<Void, Void, List<User>> {

                            @Override
                            protected List<User> doInBackground(Void... voids) {
                                mDb.getAppDatabase()
                                        .userDao()
                                        .delete(user);
                                return mDb.getAppDatabase()
                                        .userDao()
                                        .getAll();
                            }

                            @Override
                            protected void onPostExecute(List<User> tasks) {
                                super.onPostExecute(tasks);

                                // Mettre à jour l'adapter avec la liste de taches

                                listview.setAdapter(new UserAdapter(ListeUtilisateur.this,tasks));
                            }
                        }


                        // Création d'un objet de type DeleteUsers et execution de la demande asynchrone
                        DeleteUsers gt = new DeleteUsers();
                        gt.execute();
                        return true;
                    }
                });
                inflater.inflate(R.menu.liste_utilisateur_popup_menu, popup.getMenu());
                popup.show(); //Affichage de la pop up si long click
                return true;
            }
        });
        showUsers();


    }

    public void showUsers() {
        class GetUsers extends AsyncTask<Void, Void, List<User>> {

            @Override
            protected List<User> doInBackground(Void... voids) {
                List<User> userList = mDb.getAppDatabase()
                        .userDao()
                        .getAll();
                return userList;
            }

            @Override
            protected void onPostExecute(List<User> tasks) {
                super.onPostExecute(tasks);

                // Mettre à jour l'adapter avec la liste de taches

                listview.setAdapter(new UserAdapter(ListeUtilisateur.this,tasks));
            }
        }

        //////////////////////////
        // IMPORTANT bien penser à executer la demande asynchrone
        // Création d'un objet de type GetTasks et execution de la demande asynchrone
        GetUsers gt = new GetUsers();
        gt.execute();
    }
    public void addUserBtn(View view) {

        Intent intent = new Intent(this,AjouterUtilisateur.class);
        startActivityForResult(intent,REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST && resultCode == RESULT_OK) {
            showUsers();
        }
    }
    //Adapter pour la liste des users
    public class UserAdapter extends BaseAdapter {
        private Context context;
        private List<User> users;
        private LayoutInflater inflater;
        public UserAdapter(Context context, List<User> usersList) {
            this.context = context;
            this.users = usersList;
            this.inflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public User getItem(int i) {
            return users.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflater.inflate(R.layout.adapter_item,null);
            User user = this.getItem(i);
            TextView texte = view.findViewById(R.id.item_name);
            texte.setText(user.getNomComplet());
            ImageView image = view.findViewById(R.id.item_icon);
            System.out.println();
            Drawable imageDrawable = user.getImage(); //Get l'image du user
            if(imageDrawable != null) {
                image.setImageDrawable(user.getImage());
            }
            return view;
        }
    }


}
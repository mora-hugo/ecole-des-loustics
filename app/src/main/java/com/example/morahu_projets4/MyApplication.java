package com.example.morahu_projets4;

import android.app.Application;

import com.example.morahu_projets4.db.User;

public class MyApplication extends Application {

    private User current_user; //Utilisateur courant
    private boolean isVisitor;

    public void setConnected(boolean connected) {
        this.isVisitor = !connected;
    }
    public boolean isConnected() {
        return isVisitor == false;
    }

    public User getUser() {
        return current_user;
    }
    public void setUser(User user) {
        this.current_user = user;
        setConnected(true);
    }
}
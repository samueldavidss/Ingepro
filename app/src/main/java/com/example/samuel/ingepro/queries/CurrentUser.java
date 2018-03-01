package com.example.samuel.ingepro.queries;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Samuel on 28-Feb-18.
 */

public class CurrentUser {

    private FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    public FirebaseUser getCurrentUser() {
        return currentUser;
    }

    public String nameUser(){
        return getCurrentUser().getDisplayName();
    }


    public String getUid(){
        return currentUser.getUid();
    }

    public String email(){
        return getCurrentUser().getEmail();
    }

    public String getEmail(){
        return currentUser.getEmail();
    }

    public String sanitaEmail (){

        return currentUser.getEmail().replace("@","AT").replace(".","DOT");

    }
}

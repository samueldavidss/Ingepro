package com.example.samuel.ingepro.queries;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Samuel on 28-Feb-18.
 */

public class References {
    public References() {
    }

    DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference();

    public DatabaseReference userReference (){
        return firebaseDatabase.child("user");
    }

    public DatabaseReference productsReference(){
        return firebaseDatabase.child("Products");
    }

    public DatabaseReference buyReference(){
        return firebaseDatabase.child("buy");
    }
    public DatabaseReference product(String name){

        return productsReference().child(name);
    }

}

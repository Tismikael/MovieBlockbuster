package com.example.movieblockbuster;

import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

public class Database {

    private FirebaseDatabase root;
    private DatabaseReference reference;
    private String instance;

    public Database(){
        instance = "https://movieblockbuster-a5800-default-rtdb.firebaseio.com/";
        root = FirebaseDatabase.getInstance(instance);

    }

    public void storeUserInfo(User user){
        setUserReference();
        reference.child(user.getId()).setValue(user).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("Database", "user info saved successfully");
            }else {
                Log.e("Database error", "Failed to save user info " + task.getException().getMessage());
            }
        });
    }


    public void setUserReference() {
        reference = root.getReference("users");
    }
    public DatabaseReference getReference() { return reference; }

//    public void setReference(DatabaseReference reference) { this.reference = reference; }
}


//reference = root.getReference("users");

package com.example.movieblockbuster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity{

    private TextView redirect;
    private Button signInButton;

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private FirebaseAuth mAuth;
    private Database database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        mAuth = FirebaseAuth.getInstance();
        redirect = findViewById(R.id.sign_up_redirect);

        redirect.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateAccountActivity.class);
            startActivity(intent);
        });

        signInButton = findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(this::signIn);
    }

    private void signIn(View view) {

        database = new Database();

        TextInputLayout emailLayout = findViewById(R.id.sign_in_email);
        TextInputEditText emailLayoutText = (TextInputEditText) emailLayout.getEditText();
        email = emailLayoutText.getText().toString();

        TextInputLayout passwordLayout = findViewById(R.id.sign_in_password);
        TextInputEditText passwordLayoutText = (TextInputEditText) passwordLayout.getEditText();
        password = passwordLayoutText.getText().toString();

        // check if either email or password input field is empty
        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please fill out both fields to continue", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).
                addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        FirebaseUser user = mAuth.getCurrentUser();

                        if (user != null){
                            String userId = user.getUid();
                            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();

                            database.setUserReference();

                            DatabaseReference userReference = database.getReference();
                            userReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        firstName = snapshot.child("firstName").getValue(String.class);
                                        lastName = snapshot.child("lastName").getValue(String.class);

                                        Log.d("firstname", firstName);
                                        Log.d("lastname", lastName);

                                        // user session login
                                        UserSession.login(new User(firstName, lastName, email, userId));

                                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(SignInActivity.this, "User data not found", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Log.e("Database Error","Error retrieving user data: " + error.getMessage());
                                }
                            });
                        }else {
                            Toast.makeText(SignInActivity.this, "Login failed.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignInActivity.this, "Login failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}


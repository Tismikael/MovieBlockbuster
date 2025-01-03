package com.example.movieblockbuster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountActivity extends AppCompatActivity{

    private TextView redirectSignIn;
    private Button createAccountButton;

    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String password = "";
    private String confirmPassword = "";
    private static final String emailRegex = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    private User user;
    private FirebaseAuth mAuth;
    private Database database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        mAuth = FirebaseAuth.getInstance();
        database = new Database();

        redirectSignIn = findViewById(R.id.redirect_sign_in);
        createAccountButton = findViewById(R.id.create_account_button);


        redirectSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        });

        createAccountButton.setOnClickListener(this::registerUser);

    }

    public boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    // get user info input and check for validity
    public boolean validateUser(View view) {

        boolean canRegister = true;
        // name regex
        String regex = "^[A-Z][a-z]*$";

        // first name
        TextInputLayout fnLayout = findViewById(R.id.first_name);
        TextInputEditText fnLayoutText = (TextInputEditText) fnLayout.getEditText();
        firstName = fnLayoutText.getText().toString();

        // check
        if (firstName.isEmpty()) {
            fnLayout.setError("first name is required");
            canRegister = false;
        }else if (!firstName.matches(regex)){
            fnLayout.setError("Name must start with an uppercase letter followed by lowercase letters");
            canRegister = false;
        } else{
            fnLayout.setError(null);
            fnLayout.setHelperText(null);
        }

        // last name
        TextInputLayout lnLayout = findViewById(R.id.last_name);
        TextInputEditText lnLayoutText = (TextInputEditText) lnLayout.getEditText();
        lastName = lnLayoutText.getText().toString();

        // check
        if (lastName.isEmpty()) {
            lnLayout.setError("last name is required");
            canRegister = false;
        } else if( !lastName.matches(regex)){
            lnLayout.setError("Name must start with an uppercase letter followed by lowercase letters");
            canRegister = false;
        } else{
            lnLayout.setError(null);
            lnLayout.setHelperText(null);
        }

        // email
        TextInputLayout emailLayout = findViewById(R.id.email);
        TextInputEditText emailLayoutText = (TextInputEditText) emailLayout.getEditText();
        email = emailLayoutText.getText().toString();
        if (email.isEmpty()){
            emailLayout.setError("Email is required");
            canRegister = false;
        }else if ( !isValidEmail(email)){
            emailLayout.setError("Invalid email format");
            canRegister = false;
        }else {
            emailLayout.setError(null);
            emailLayout.setHelperText(null);
        }

        // password
        TextInputLayout passwordLayout = findViewById(R.id.password);
        TextInputEditText passwordLayoutText = (TextInputEditText) passwordLayout.getEditText();
        password = passwordLayoutText.getText().toString();
        int minPassLength = 8;
        if (password.isEmpty()) {
            passwordLayout.setError("Password is required");
            canRegister = false;
        }else if(password.length() < minPassLength){
            passwordLayout.setError("Password is too short");
            Toast.makeText(this, "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
            canRegister = false;
        }else {
            passwordLayout.setError(null);
            passwordLayout.setHelperText(null);
        }

        TextInputLayout confirmPasswordLayout = findViewById(R.id.confirm_password);
        TextInputEditText confirmPasswordLayoutText = (TextInputEditText) confirmPasswordLayout.getEditText();
        confirmPassword = confirmPasswordLayoutText.getText().toString();
        if (confirmPassword.isEmpty()) {
            confirmPasswordLayout.setError("Password is required");
            canRegister = false;
        } else if (!confirmPassword.equals(password)){
            confirmPasswordLayout.setError("Passwords do not match");
            canRegister = false;
        } else {
            confirmPasswordLayout.setError(null);
            confirmPasswordLayout.setHelperText(null);
        }

        return canRegister;
    }


    public void setUpUserInfo(String id){

        user = new User(firstName, lastName, email, id);

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setId(id);

    }
    public void redirectUserToMainPage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void registerUser(View view) {
        if(validateUser(view)){
            mAuth.createUserWithEmailAndPassword(email, password).
                    addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            String userId = mAuth.getCurrentUser().getUid();
                            setUpUserInfo(userId);
                            UserSession.login(new User(firstName, lastName, email, userId));
                            database.storeUserInfo(UserSession.getCurrentUser());
                            Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();
                            redirectUserToMainPage(view);
                        } else {
                            Toast.makeText(this, "Failed to create account", Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    }
}


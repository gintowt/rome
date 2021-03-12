package com.roma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {

    EditText usernameEditText;
    EditText passwordEditText;
    Button login;
    Button newPassword;
    private FirebaseAuth mAuth;
    Button register;
    ImageButton facebook;
    ImageButton google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        usernameEditText = (EditText) findViewById(R.id.user);
        passwordEditText = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        newPassword = (Button) findViewById(R.id.forgot);
        register = (Button) findViewById(R.id.create);
        facebook = (ImageButton) findViewById(R.id.facebook);
        google = (ImageButton) findViewById(R.id.google);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if(!email.isEmpty() && !password.isEmpty()) {
                    loginUser();
                    Intent mainIntent = new Intent(LogIn.this, MainScreen.class);
                    startActivity(mainIntent);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createIntent = new Intent(LogIn.this, Registration.class);
                startActivity(createIntent);
            }
        });

       newPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LogIn.this, R.style.MyDialogTheme);
                final View customLayout = getLayoutInflater().inflate(R.layout.saved_dialog, null);
                alertDialogBuilder.setView(customLayout);
                alertDialogBuilder.setTitle("New password.");
                alertDialogBuilder.setMessage("Do you want to change your password?");
                //alertDialogBuilder.setView(edittext);
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // FirebaseDatabase.getInstance().getReference("SavedTrip").setValue(tripDetailsList);
                        EditText editText = customLayout.findViewById(R.id.trip_name);
                        String trip_name = editText.getText().toString().trim();
                        if (!trip_name.isEmpty()) {
                            //FirebaseDatabase.getInstance().getReference("TripDetails").child("name").setValue(trip_name);
                        }
                        else{
                            Toast.makeText(LogIn.this, "Name is empty. Fill the box.", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(LogIn.this, "You clicked on cancel", Toast.LENGTH_LONG).show();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }

    private void loginUser(){
        String email = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if(email.isEmpty())
        {
            usernameEditText.setError("Email is required.");
            usernameEditText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            usernameEditText.setError("Please provide valid email!");
            usernameEditText.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            passwordEditText.setError("Password is required.");
            passwordEditText.requestFocus();
            return;
        }

        if(passwordEditText.length() < 6){
            passwordEditText.setError("Min password length should be 6 characters.");
            passwordEditText.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(LogIn.this, "Log in successfully!", Toast.LENGTH_LONG).show();
                    }

                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LogIn.this, ""+e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
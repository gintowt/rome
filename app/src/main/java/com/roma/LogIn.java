package com.roma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class LogIn extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    Button newpassword;
    Button register;
    ImageButton facebook;
    ImageButton google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        username = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        newpassword = (Button) findViewById(R.id.forgot);
        register = (Button) findViewById(R.id.create);
        facebook = (ImageButton) findViewById(R.id.facebook);
        google = (ImageButton) findViewById(R.id.google);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(LogIn.this, MainScreen.class);
                startActivity(mainIntent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createIntent = new Intent(LogIn.this, Registration.class);
                startActivity(createIntent);
            }
        });

       /* newpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newpassIntent = new Intent(LogIn.this, Password.class);
                startActivity(newpassIntent);
            }
        });
*/
    }
}
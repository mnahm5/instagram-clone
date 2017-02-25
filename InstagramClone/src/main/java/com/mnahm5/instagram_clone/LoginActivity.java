package com.mnahm5.instagram_clone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        redirect();

        setTitle("Login");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void RegisterLink(View view)
    {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    public void Login(View view)
    {
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        ParseUser.logInInBackground(
                etUsername.getText().toString(),
                etPassword.getText().toString(),
                new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e == null) {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Login Successful",
                                    Toast.LENGTH_SHORT
                            ).show();
                            redirect();
                        }
                        else {
                            Toast.makeText(
                                    getApplicationContext(),
                                    e.getMessage(),
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                    }
                }
        );
    }

    public void redirect()
    {
        if (ParseUser.getCurrentUser() != null) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }
    }
}

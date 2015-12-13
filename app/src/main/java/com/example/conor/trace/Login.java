package com.example.conor.trace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity  {

    private EditText emailField, passwordField, scoreField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = (EditText) findViewById(R.id.email);
        passwordField = (EditText) findViewById(R.id.password);
        //scoreField = (EditText) findViewById(R.id.score);
    }

    public void login(View view) {

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        ((Store) this.getApplication()).setEmail(email);
        ((Store) this.getApplication()).setPassword(password);
        new SigninActivity(this).execute(email, password);
    }

    public void postScore(View view) {
        System.out.println("2");
        String email = ((Store) this.getApplication()).getEmail();
        String password = ((Store) this.getApplication()).getPassword();
        String score = scoreField.getText().toString();
        new PostActivity(this).execute(email, password, score);
    }
}

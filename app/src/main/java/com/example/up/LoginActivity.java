package com.example.up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
    }
    public void nextMain(View v)
    {
        if(email.getText().toString().equals("") || password.getText().toString().equals(""))
        {
            Toast.makeText(LoginActivity.this, "Все поля должны быть заполнены!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Pattern p = Pattern.compile("@", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(email.getText().toString());
            boolean b = m.find();
            if(b)
            {
                startActivity(new Intent(this, MainActivity.class));
            }
            else
            {
                Toast.makeText(LoginActivity.this, "Поле для Email обязательно должно содержать символ '@'", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void nextRegister(View v)
    {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
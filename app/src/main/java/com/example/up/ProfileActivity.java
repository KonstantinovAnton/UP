package com.example.up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
    public  void nextMenu(View view)
    {
        startActivity(new Intent(this, MenuActivity.class));
    }

    public  void nextHome(View view)
    {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void nextListen(View view)
    {
        startActivity(new Intent(this, ListenActivity.class));
    }

    public void nextLogin(View view)
    {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
package com.example.up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ListenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);
    }
    public void nextHome(View view)
    {
        startActivity(new Intent(this, MainActivity.class));
    }
    public void nextProfile(View view)
    {
        startActivity(new Intent(this, ProfileActivity.class));
    }
}
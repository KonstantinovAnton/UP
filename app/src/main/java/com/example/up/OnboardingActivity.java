package com.example.up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
    }
    public void nextRegistrarion(View v)
    {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void nextLogin(View v)
    {
        startActivity(new Intent(this, LoginActivity.class));
    }
}
package com.example.up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PhotoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        ImageView im = findViewById(R.id.photoEdit);

        Bundle bundle = getIntent().getExtras();
        int id =  bundle.getInt("id");

        switch (id){
            case 2131362300:
                im.setImageResource(R.drawable.user_p);
                break;
            case 2131362302:
                im.setImageResource(R.drawable.user_p3);
                break;
            case 2131362303:
                im.setImageResource(R.drawable.user_p2);
                break;
        }
    }
    public  void gotoBack(View view)
    {
        startActivity(new Intent(this, ProfileActivity.class));
    }
}
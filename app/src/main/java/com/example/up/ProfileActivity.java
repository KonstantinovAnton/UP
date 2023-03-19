package com.example.up;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ProfileActivity extends AppCompatActivity {

    ImageView image;
    TextView tvName;

    OutputStream outputStream;

    private AdapterPhoto pAdapter;
    private List<MaskPhoto> list = new ArrayList<>();

    public static MaskPhoto maskProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

       // tvName = findViewById(R.id.tvNameProfile);
       // tvName.setText(OnboardingActivity.nickName);

        image = findViewById(R.id.avatar);
       /* new AdapterQuote.DownloadImageTask((ImageView) image)
                .execute(OnboardingActivity.avatar);

        GridView gvImage = findViewById(R.id.lvImageProfile);
        pAdapter = new AdapterPhoto(ProfileActivity.this, list);
        gvImage.setAdapter(pAdapter);
        GetImageProfile();

        gvImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                maskProfileImage = list.get(i);
                startActivity(new Intent(ProfileActivity.this, Photo.class));

        }); }*/
    }

    private void GetImageProfile()
    {
        list.clear();
        pAdapter.notifyDataSetInvalidated();
        String path = getApplicationInfo().dataDir + "/MyFiles";
        File directory = new File(path);
        File[] files = directory.listFiles();
        int j = 0;
        for (int i = 0; i < files.length; i++)
        {
            Long last = files[i].lastModified();
            MaskPhoto tempProduct = new MaskPhoto(
                    j,
                    files[i].getAbsolutePath(),
                    files[i],
                    getFullTime(last)
            );
            list.add(tempProduct);
            pAdapter.notifyDataSetInvalidated();
        }
        /*
        MaskProfileImage tempProduct = new MaskProfileImage(
                j,
                null,
                files[files.length-1],
                "null"
        );
        list.add(tempProduct);
        pAdapter.notifyDataSetInvalidated();

         */
    }

    public static final String getFullTime(final long timeInMillis)
    {
        final SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timeInMillis);
        c.setTimeZone(TimeZone.getDefault());
        return format.format(c.getTime());
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

    public void gotoPhoto(View view)
    {

        Intent intent = new Intent(this, PhotoActivity.class);
        intent.putExtra("id", view.getId()) ;
        startActivity(intent);
    }

    public void nextLogin(View view)
    {
        SharedPreferences prefs = getSharedPreferences( // Сохранение данных
                "Date", Context.MODE_PRIVATE);
        prefs.edit().putString("Avatar", "").apply();
        prefs.edit().putString("NickName", "").apply();

        startActivity(new Intent(this, LoginActivity.class));
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Bitmap bitmap = null;
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Uri selectedImage = result.getData().getData();
                        try
                        {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        File dir = new File(getApplicationInfo().dataDir + "/MyFiles/");
                        dir.mkdirs();
                        File file = new File(dir, System.currentTimeMillis() + ".jpg");
                        try {
                            outputStream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                            outputStream.flush();
                            outputStream.close();
                            Toast.makeText(ProfileActivity.this, "Изображение сохранилось", Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            Toast.makeText(ProfileActivity.this, "Что-то пошло не так", Toast.LENGTH_LONG).show();
                        }
                        GetImageProfile();
                    }
                }
            });
}
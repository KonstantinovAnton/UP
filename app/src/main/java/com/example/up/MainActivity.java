package com.example.up;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AdapterQuote pAdapter;
    private List<MaskQuote> listQuote = new ArrayList<>();

    private AdapterFeeling adapterMaskFeeling;
    private List<MaskFeeling> listFeelings = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView ivProducts = findViewById(R.id.lvQuotes);
        pAdapter = new AdapterQuote(MainActivity.this, listQuote);
        ivProducts.setAdapter(pAdapter);
        new GetQuotes().execute();

        RecyclerView lvFeelings = findViewById(R.id.rvFeelings);
        adapterMaskFeeling = new AdapterFeeling(MainActivity.this, listFeelings);
        // lvFeelings.setAdapter(adapterMaskFeeling);
        new GetFeelings().execute();
    }
    private class GetQuotes extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("http://mskko2021.mad.hakta.pro/api/quotes");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }
                return result.toString();
            }
            catch (Exception exception)
            {
                return null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                listQuote.clear();
                pAdapter.notifyDataSetInvalidated();

                JSONObject object = new JSONObject(s);
                JSONArray tempArray  = object.getJSONArray("data");

                for (int i = 0;i<tempArray.length();i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    MaskQuote tempProduct = new MaskQuote(
                            productJson.getInt("id"),
                            productJson.getString("title"),
                            productJson.getString("image"),
                            productJson.getString("description")
                    );
                    listQuote.add(tempProduct);
                    pAdapter.notifyDataSetInvalidated();
                }
            }
            catch (Exception exception)
            {
                Toast.makeText(MainActivity.this, "При выводе данных возникла ошибка", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class GetFeelings  extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("http://mskko2021.mad.hakta.pro/api/feelings ");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }
                return result.toString();
            }
            catch (Exception exception)
            {
                return null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                listFeelings.clear();
                adapterMaskFeeling.notifyDataSetInvalidated();

                JSONObject object = new JSONObject(s);
                JSONArray tempArray  = object.getJSONArray("data");

                for (int i = 0;i<tempArray.length();i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    MaskFeeling tempProduct = new MaskFeeling(
                            productJson.getInt("id"),
                            productJson.getString("title"),
                            productJson.getInt("position"),
                            productJson.getString("image")
                    );
                    listFeelings.add(tempProduct);
                    adapterMaskFeeling.notifyDataSetInvalidated();
                }
            }
            catch (Exception exception)
            {
                Toast.makeText(MainActivity.this, "При выводе данных возникла ошибка", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public  void nextMenu(View view)
    {
        startActivity(new Intent(this, MenuActivity.class));
    }

    public  void nextProfile(View view)
    {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    public void nextListen(View view)
    {
        startActivity(new Intent(this, ListenActivity.class));
    }

}
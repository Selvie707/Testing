package com.example.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bnvBottomMenu;
    private RecyclerView rvMovie;
    private ProgressBar pbMovie;
    private ArrayList<Result> alMovie;
    private LinearLayoutManager llmMovie;
    private AdapterMain adapterMain;
    private ImageView ivAndroid;

    private int mView = 0; // 0 card, 1 grid
    //static variabel
    static final String STATE_MODE = "MODE VIEW";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(STATE_MODE, mView);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovie = findViewById(R.id.rv_main);
        pbMovie = findViewById(R.id.pb_main);

        llmMovie = new LinearLayoutManager(MainActivity.this);
        rvMovie.setLayoutManager(llmMovie);

        RetriverMovie();

        //

        bnvBottomMenu = findViewById(R.id.nv_menuBottom);
        bnvBottomMenu.setSelectedItemId(R.id.menuHome);

        bnvBottomMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuHome:
                        return true;
                    case R.id.menuSetting:
                        startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    private void RetriverMovie() {
        pbMovie.setVisibility(View.VISIBLE);

        Api api = RetroServer.getRetrofit().create(Api.class);
        Call<Root> retriverProcess = api.getMovie("0a9d3ed8c00f265589f595b6f3b92228");

        retriverProcess.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                alMovie = response.body().getResults();
                adapterMain = new AdapterMain(alMovie);
                rvMovie.setAdapter(adapterMain);
                pbMovie.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal guys", Toast.LENGTH_SHORT).show();
                pbMovie.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RetriverMovie();
    }
}
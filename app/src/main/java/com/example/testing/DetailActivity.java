package com.example.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.testing.model.Genre;
import com.example.testing.model.Root;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private BottomNavigationView bnvBottomMenu;
    private RecyclerView rvMovie;
    private ProgressBar pbMovie;
    private ArrayList<Genre> alMovie;
    private LinearLayoutManager llmMovie;
    private AdapterGenre adapterGenre;
    private ImageView ivAndroid;

    private RecyclerView rvGenre;
    private ImageView ivPhoto;
    private TextView tvTitle, tvOverview;

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
        setContentView(R.layout.activity_detail);

        rvMovie = findViewById(R.id.rv_detailrecentlywatched_genre);

        llmMovie = new LinearLayoutManager(DetailActivity.this);
        rvMovie.setLayoutManager(llmMovie);

        Intent intent = getIntent();
        String id = intent.getStringExtra("varId");
        String genre = intent.getStringExtra("varGenre");

        RetriverMovie();

        ivPhoto = findViewById(R.id.iv_detail_photo);
        tvTitle = findViewById(R.id.tv_detail_title);
        tvOverview = findViewById(R.id.tv_detail_overview);
        rvGenre = findViewById(R.id.rv_detailrecentlywatched_genre);

        Api api = RetroServer.getRetrofit().create(Api.class);
        Call<Root> detailApi = api.getDetail(Integer.parseInt(id),
                ("0a9d3ed8c00f265589f595b6f3b92228"));

        detailApi.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                tvTitle.setText(response.body().getTitle());
                tvOverview.setText(response.body().getOverview());
                Glide.with(DetailActivity.this).load(response.body().getPoster_path()).into(ivPhoto);
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });
    }

    private void RetriverMovie() {

        Intent intent = getIntent();
        String id = intent.getStringExtra("varId");

        Api apii = RetroServer.getRetrofit().create(Api.class);
        Call<Root> retriverGenre = apii.getDetail(Integer.parseInt(id), ("0a9d3ed8c00f265589f595b6f3b92228"));

        retriverGenre.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                alMovie = response.body().getGenres();
                adapterGenre = new AdapterGenre(alMovie);
                rvGenre.setAdapter(adapterGenre);
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Gagal guys", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RetriverMovie();
    }
}
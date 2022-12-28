package com.example.testing;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.ViewHolder> {
    private ArrayList<Result> alResult;

    public AdapterMain(ArrayList<Result> alResult) {
        this.alResult = alResult;
    }

    @NonNull
    @Override
    public AdapterMain.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMain.ViewHolder holder, int position) {
        Result movie = alResult.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvYear.setText(movie.getRelease_date());
        Glide.with(holder.itemView.getContext())
                .load(movie.getPoster_path())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.ivphoto);
        
        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = String.valueOf(movie.getId());
                String genre = String.valueOf(movie.getGenre_ids());

                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("varId", id);
                intent.putExtra("varGenre", genre);

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return alResult.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        
        LinearLayout llMain;
        ImageView ivphoto;
        TextView tvTitle, tvYear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            llMain = itemView.findViewById(R.id.ll_holdermovie);
            ivphoto = itemView.findViewById(R.id.iv_holdermovie_photo);
            tvTitle = itemView.findViewById(R.id.tv_holdermovie_title);
            tvYear = itemView.findViewById(R.id.tv_holdermovie_year);
        }
    }
}

package com.example.testing;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.testing.model.Genre;

import java.util.ArrayList;

public class AdapterGenre extends RecyclerView.Adapter<AdapterGenre.ViewHolder> {
    private ArrayList<Genre> alResult;

    public AdapterGenre(ArrayList<Genre> alResult) {
        this.alResult = alResult;
    }

    @NonNull
    @Override
    public AdapterGenre.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_detail_genre, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGenre.ViewHolder holder, int position) {
        Genre genre = alResult.get(position);
        holder.etGenre.setText(genre.getName());
    }

    @Override
    public int getItemCount() {
        return alResult.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        
        EditText etGenre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            etGenre = itemView.findViewById(R.id.et_viewholderdetailgenre_genre);
        }
    }
}

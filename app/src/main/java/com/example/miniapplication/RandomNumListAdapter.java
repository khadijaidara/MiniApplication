package com.example.miniapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniapplication.ui.Produit;

import java.util.List;
import java.util.Random;

public class RandomNumListAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private Random random;
    List<Produit> list ;

    public RandomNumListAdapter(List<Produit> list ) {
        this.list=list;
     }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.frame_textview;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Produit produit = list.get(position);
        holder.getView().setText(produit.getDate()+"-"+produit.getLieu()+"-"+produit.getNom()+"-"+produit.getPrix());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
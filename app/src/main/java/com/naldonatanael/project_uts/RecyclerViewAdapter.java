package com.naldonatanael.project_uts;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.naldonatanael.project_uts.databinding.AdapterRecyclerViewBinding;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<Layanan> result;
    AdapterRecyclerViewBinding binding;
    public RecyclerViewAdapter(){}

    public RecyclerViewAdapter(Context context, List<Layanan> result){
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.adapter_recycler_view,parent,false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        result = new DaftarLayanan().LAYANAN;
        binding.setLyn(result.get(position));
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public MyViewHolder(@NonNull AdapterRecyclerViewBinding binding){
            super(binding.getRoot());
        }
        public void onClick(View view) {
            Toast.makeText(context, "Fungsi Ini Belum Tersedia h3h3", Toast.LENGTH_SHORT).show();
        }
    }
}

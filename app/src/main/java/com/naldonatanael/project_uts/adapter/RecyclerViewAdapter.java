package com.naldonatanael.project_uts.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.naldonatanael.project_uts.DaftarLayanan;
import com.naldonatanael.project_uts.Layanan;
import com.naldonatanael.project_uts.R;
import com.naldonatanael.project_uts.dao.LayananDAO;
import com.naldonatanael.project_uts.databinding.AdapterRecyclerViewBinding;

import java.util.List;
import java.util.stream.Collectors;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.LayananViewHolder> {
    private List<LayananDAO> dataList;
    private List<LayananDAO> filteredDataList;
    private Context context;

    public RecyclerViewAdapter(List<LayananDAO> dataList) {
        this.dataList = dataList;
        this.filteredDataList = dataList;
        this.context = context;
    }



    @NonNull
    @Override
    public RecyclerViewAdapter.LayananViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_recycler_view, parent, false);
        return new RecyclerViewAdapter.LayananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.LayananViewHolder holder, int position) {
        final LayananDAO lyn = filteredDataList.get(position);
        holder.tvLayanan.setText(lyn.getNamaLayanan());
        holder.tvRambut.setText(lyn.getRambut());
        holder.tvBerat.setText(lyn.getBerat());
        holder.tvDurasi.setText(lyn.getDurasi());
        holder.tvTarif.setText((int) lyn.getTarif());

        Glide.with(holder.twGambar.getContext())
                .load(lyn.getImgURL())
                .into(holder.twGambar);


    }

    @Override
    public int getItemCount() {
        return filteredDataList.size();
    }

    static class LayananViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLayanan,tvRambut,tvBerat,tvDurasi,tvTarif;
        private ImageView twGambar;
        private LinearLayout mParent;

        public LayananViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLayanan = itemView.findViewById(R.id.tvLayanan);
            tvRambut= itemView.findViewById(R.id.tvRambut);
            tvBerat = itemView.findViewById(R.id.tvBerat);
            tvDurasi = itemView.findViewById(R.id.tvDurasi);
            tvTarif = itemView.findViewById(R.id.tvTarif);
            twGambar = itemView.findViewById(R.id.twGambar);
            mParent = itemView.findViewById(R.id.linearLayout);
        }
    }


}

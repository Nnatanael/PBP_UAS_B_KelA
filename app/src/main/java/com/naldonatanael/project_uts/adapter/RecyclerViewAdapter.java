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
import com.naldonatanael.project_uts.fragment.LayananDetailFragment;

import java.util.List;
import java.util.stream.Collectors;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.LayananViewHolder> {
    private List<LayananDAO> dataList;
//    private List<LayananDAO> filteredDataList;
    private Context context;

    public RecyclerViewAdapter(List<LayananDAO> dataList,Context context) {
        this.dataList = dataList;
//        this.filteredDataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.LayananViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_recycler_view, parent, false);
        return new RecyclerViewAdapter.LayananViewHolder(view);
    }

    @Override
    public void onBindViewHolder( RecyclerViewAdapter.LayananViewHolder holder, int position) {
        final LayananDAO lyn = dataList.get(position);
        holder.twNamaLayanan.setText(lyn.getNamaLayanan());
        holder.twRambut.setText(lyn.getRambut());
        holder.twBerat.setText(lyn.getBerat());
        holder.twDurasi.setText(lyn.getDurasi());
        holder.twTarif.setText(String.valueOf(lyn.getTarif()));

//        Glide.with(context)
//                .load(lyn.getImgURL())
//                .into(holder.twGambar);


        holder.mParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
                LayananDetailFragment dialog = new LayananDetailFragment();
                dialog.show(manager, "dialog");

                Bundle args = new Bundle();
                args.putString("id", lyn.getId());
                dialog.setArguments(args);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class LayananViewHolder extends RecyclerView.ViewHolder {
        private TextView twNamaLayanan, twRambut, twBerat, twDurasi, twTarif;
//        private ImageView twGambar;
        private LinearLayout mParent;

        public LayananViewHolder(@NonNull View itemView) {
            super(itemView);
            twNamaLayanan = itemView.findViewById(R.id.tvLayanan);
            twRambut = itemView.findViewById(R.id.tvRambut);
            twBerat = itemView.findViewById(R.id.tvBerat);
            twDurasi = itemView.findViewById(R.id.tvDurasi);
//            twGambar = itemView.findViewById(R.id.twGambar);
            twTarif =itemView.findViewById(R.id.tvTarif);
            mParent = itemView.findViewById(R.id.mParentAdapt);
        }
    }

//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(final CharSequence charSequence) {
//
//                filteredDataList = charSequence == null ? dataList :
//                        dataList.stream().filter(data -> data.getNamaLayanan().toLowerCase().contains(charSequence.toString().toLowerCase())).collect(Collectors.toList());
//.
//                FilterResults results = new FilterResults();
//                results.values = filteredDataList;
//                return results;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                filteredDataList = (List<LayananDAO>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }
}

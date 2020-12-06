package com.naldonatanael.project_uts.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.naldonatanael.project_uts.R;
import com.naldonatanael.project_uts.add.BookingLayanan;
import com.naldonatanael.project_uts.api.ApiClient;
import com.naldonatanael.project_uts.api.ApiInterface;
import com.naldonatanael.project_uts.response.ObjectLayananResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LayananDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LayananDetailFragment extends DialogFragment {

    private TextView twNamaLayanan, twRambut, twBerat,twTarif;
    private String sIdLayanan;
    private String sNamaLayanan;
    private String sRambut;
    private String sBerat;
    private double sTarif;
    private String sGambar;
    private ImageView twGambar;
    private ImageButton ibClose;
    private ProgressDialog progressDialog;
    private Button btnLayanan;

    public static LayananDetailFragment newInstance() {return new LayananDetailFragment();}

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_layanan_detail, container, false);

        btnLayanan = view.findViewById(R.id.btnLayanan);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.show();

        ibClose = view.findViewById(R.id.ibClose);
        ibClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        twNamaLayanan = view.findViewById(R.id.tvNamaLayanan);
        twRambut= view.findViewById(R.id.twRambut);
        twBerat= view.findViewById(R.id.twBerat);
        twTarif=view.findViewById(R.id.twTarif);
        twGambar = view.findViewById(R.id.twGambar);

        sIdLayanan = getArguments().getString("id", "");
        loadKamarById(sIdLayanan);

        btnLayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), BookingLayanan.class);
                i.putExtra("LayananID", sIdLayanan);
                startActivity(i);
                dismiss();
            }
        });

        return view;
    }

    private void loadKamarById(String id){
        ApiInterface apiServiceLayanan = ApiClient.getClient().create(ApiInterface.class);
        Call<ObjectLayananResponse> getLayanan = apiServiceLayanan.getLayananById(id, "data");

        getLayanan.enqueue(new Callback<ObjectLayananResponse>() {
            @Override
            public void onResponse(Call<ObjectLayananResponse> call, Response<ObjectLayananResponse> response) {
                sNamaLayanan = response.body().getLayanan().getNamaLayanan();
                sRambut = response.body().getLayanan().getRambut();
                sBerat = response.body().getLayanan().getBerat();
                sTarif = response.body().getLayanan().getTarif();
                sGambar = response.body().getLayanan().getImgURL();

                twNamaLayanan.setText(sNamaLayanan);
                twRambut.setText(sRambut);
                twBerat.setText(sBerat);
                twTarif.setText(String.valueOf(sTarif));
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ObjectLayananResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }
}
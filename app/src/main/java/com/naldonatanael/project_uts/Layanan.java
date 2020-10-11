package com.naldonatanael.project_uts;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class Layanan{
    public String namaLayanan;
    public String rambut;
    public String berat;
    public String durasi;
    public double tarif;
    public String imgURL;

    public Layanan(String namaLayanan, String rambut, String berat, String durasi, double tarif, String imgURL) {
        this.namaLayanan = namaLayanan;
        this.rambut = rambut;
        this.berat = berat;
        this.durasi = durasi;
        this.tarif = tarif;
        this.imgURL = imgURL;
    }

    public String getNamaLayanan() {
        return namaLayanan;
    }

    public void setNamaLayanan(String namaLayanan) {
        this.namaLayanan = namaLayanan;
    }

    public String getRambut() {
        return rambut;
    }

    public void setRambut(String rambut) {
        this.rambut = rambut;
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String lamaLayanan) {
        this.durasi = durasi;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public void setStringTarif(String tarif){
        if(!tarif.isEmpty()){
            this.tarif = Double.parseDouble(tarif);
        }else{
            this.tarif=0;
        }
    }

    public String getStringIpk(){return String.valueOf(tarif);}

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @BindingAdapter("profileImages")
    public static void loadImage(ImageView view, String imgURL){
        Glide.with(view.getContext())
                .load(imgURL).apply(new RequestOptions().centerCrop())
                .into(view);
    }
}

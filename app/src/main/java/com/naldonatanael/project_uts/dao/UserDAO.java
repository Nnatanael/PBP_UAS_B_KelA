package com.naldonatanael.project_uts.dao;

import com.google.gson.annotations.SerializedName;

public class UserDAO {

    @SerializedName("id")
    private String id;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("nama")
    private String nama;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("noTelp")
    private String noTelp;

    @SerializedName("image")
    private String image;

    public UserDAO(String id, String email, String password, String nama, String alamat, String noTelp, String image) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nama = nama;
        this.alamat = alamat;
        this.noTelp = noTelp;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

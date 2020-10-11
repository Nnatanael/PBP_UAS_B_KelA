package com.naldonatanael.project_uts;

import java.util.ArrayList;

public class DaftarLayanan {
    public ArrayList<Layanan> LAYANAN;

    public DaftarLayanan(){
        LAYANAN = new ArrayList();
        LAYANAN.add(GroomingAnjing);
        LAYANAN.add(GroomingKucing);
        LAYANAN.add(SpaAnjing);
        LAYANAN.add(SpaKucing);
        LAYANAN.add(ScallingAnjing);
        LAYANAN.add(ScallingKucing);
    }

    public static final Layanan GroomingAnjing = new Layanan("Grooming Anjing", "Pendek",
            "0 - 4,9 Kg", "1 1/2 Jam", 150000,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/6/60/YellowLabradorLooking.jpg/250px-YellowLabradorLooking.jpg");

    public static final Layanan GroomingKucing = new Layanan("Grooming Kucing", "Pendek",
            "0 - 4,9 Kg", "1 1/2 Jam", 150000,
            "https://cdn1-www.cattime.com/assets/uploads/2011/12/file_2744_british-shorthair-460x290-460x290.jpg");

    public static final Layanan SpaAnjing = new Layanan("Spa Anjing", "Pendek",
            "0 - 4,9 Kg", "2 Jam", 200000,
            "https://anjingdijual.com/files/jenis-anjing/foto/golden-retriever/g1.jpg");

    public static final Layanan SpaKucing = new Layanan("Spa Kucing", "Pendek",
            "0 - 4,9 Kg", "2 Jam", 200000,
            "https://asset-a.grid.id//crop/0x0:0x0/360x240/photo/2019/10/31/71888328.jpg");

    public static final Layanan ScallingAnjing = new Layanan("Scalling Anjing", "Pendek / Panjang",
            "0 - 9,9 Kg", "1 Jam", 125000,
            "https://upload.wikimedia.org/wikipedia/commons/thumb/6/60/YellowLabradorLooking.jpg/250px-YellowLabradorLooking.jpg");

    public static final Layanan ScallingKucing = new Layanan("Scalling Kucing", "Pendek / Panjang",
            "0 - 9,9 Kg", "1 Jam", 125000,
            "https://cdn1-www.cattime.com/assets/uploads/2011/12/file_2744_british-shorthair-460x290-460x290.jpg");

}

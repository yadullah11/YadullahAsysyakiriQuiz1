package com.example.yadullahasysyakiriquiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView tvNamaPelanggan = findViewById(R.id.tvNamaPelanggan);
        TextView tvTipePelanggan = findViewById(R.id.tvTipePelanggan);
        TextView tvKodeBarang = findViewById(R.id.tvKodeBarang);
        TextView tvNamaBarang = findViewById(R.id.tvNamaBarang);
        TextView tvHarga = findViewById(R.id.tvHarga);
        TextView tvTotalHarga = findViewById(R.id.tvTotalHarga);
        TextView tvDiskonHarga = findViewById(R.id.tvDiskonHarga);
        TextView tvDiskonMember = findViewById(R.id.tvDiskonMember);
        TextView tvJumlahBayar = findViewById(R.id.tvJumlahBayar);
        Button btnShare = findViewById(R.id.btnShare);

        Intent intent = getIntent();
        String namaPelanggan = intent.getStringExtra("namaPelanggan");
        String tipePelanggan = intent.getStringExtra("tipePelanggan");
        String kodeBarang = intent.getStringExtra("kodeBarang");
        int jumlahBarang = intent.getIntExtra("jumlahBarang", 0);

        double hargaBarang = 0;
        double totalHarga = 0;
        double diskonHarga = 0;
        double diskonMember = 0;
        final double jumlahBayar;

        if (kodeBarang != null) {
            switch (kodeBarang) {
                case "SGS":
                    hargaBarang = 12999999;
                    break;
                case "PCO":
                    hargaBarang = 2730551;
                    break;
                case "AAE":
                    hargaBarang = 8676981;
                    break;
            }
            totalHarga = hargaBarang * jumlahBarang;

            if (totalHarga > 10000000) {
                diskonHarga = 100000;
            }
            if (tipePelanggan != null) {
                switch (tipePelanggan) {
                    case "Gold":
                        diskonMember = 0.1 * totalHarga;
                        break;
                    case "Silver":
                        diskonMember = 0.05 * totalHarga;
                        break;
                    case "Reguler":
                        diskonMember = 0.02 * totalHarga;
                        break;
                }
            }

            jumlahBayar = totalHarga - diskonHarga - diskonMember;

            tvNamaPelanggan.setText("Selamat Datang " + namaPelanggan);
            tvTipePelanggan.setText("Membership Pembeli : " + tipePelanggan);
            tvKodeBarang.setText("Kode Barang : " + kodeBarang);
            tvNamaBarang.setText("Nama Barang : " + getNamaBarang(kodeBarang));
            tvHarga.setText("Harga : Rp " + String.format("%,.0f", hargaBarang));
            tvTotalHarga.setText("Total Harga : Rp " + String.format("%,.0f", totalHarga));
            tvDiskonHarga.setText("Diskon Harga : Rp " + String.format("%,.0f", diskonHarga));
            tvDiskonMember.setText("Diskon Member : Rp " + String.format("%,.0f", diskonMember));
            tvJumlahBayar.setText("Jumlah Bayar : Rp " + String.format("%,.0f", jumlahBayar));

            btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String namaProduk = getNamaBarang(kodeBarang);
                    String deskripsiProduk = getDeskripsiProduk(kodeBarang, jumlahBayar);
                    String shareMessage = "Nama Barang: " + namaProduk + "\nDeskripsi: " + deskripsiProduk;

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(intent, "Bagikan melalui"));
                }
            });
        }
    }

    private String getNamaBarang(String kodeBarang) {
        switch (kodeBarang) {
            case "SGS":
                return "Samsung Galaxy S20";
            case "PCO":
                return "POCO M3";
            case "AAE":
                return "Acer Aspire E14";
            default:
                return "";
        }
    }
    private String getDeskripsiProduk(String kodeBarang, double jumlahBayar) {
        String deskripsi = "";
        switch (kodeBarang) {
            case "SGS":
                deskripsi = "\nMelakukan Transaksi sebesar Rp. " + String.format("%,.0f", jumlahBayar) + "pada AppMob Store";
                break;
            case "PCO":
                deskripsi = "\nMelakukan Transaksi sebesar Rp. " + String.format("%,.0f", jumlahBayar) + "pada AppMob Store";
                break;
            case "AAE":
                deskripsi = "\nMelakukan Transaksi sebesar Rp. " + String.format("%,.0f", jumlahBayar) + "pada AppMob Store";
                break;
        }
        return deskripsi;
    }
}

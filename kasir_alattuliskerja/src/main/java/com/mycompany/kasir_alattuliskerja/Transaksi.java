/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kasir_alattuliskerja;

/**
 *
 * @author selvi
 */
import java.util.ArrayList;

public class Transaksi {

    // Menyimpan daftar barang yang dibeli
    private ArrayList<Barang> daftarBeli = new ArrayList<>();

    // Menyimpan jumlah masing-masing barang
    private ArrayList<Integer> jumlahBeli = new ArrayList<>();

    // Method untuk menambahkan barang ke transaksi
    public void tambahBarang(Barang barang, int jumlah) {

        // Memeriksa apakah barang sudah ada di transaksi
        for (int i = 0; i < daftarBeli.size(); i++) {

            if (daftarBeli.get(i) == barang) {

                int jumlahBaru = jumlahBeli.get(i) + jumlah;

                // Memeriksa stok barang
                if (jumlahBaru > barang.getStok()) {
                    System.out.println("Stok tidak mencukupi!");
                    return;
                }

                jumlahBeli.set(i, jumlahBaru);
                System.out.println("Jumlah barang berhasil ditambahkan!");
                return;
            }
        }

        // Memeriksa stok barang
        if (jumlah > barang.getStok()) {
            System.out.println("Stok tidak mencukupi!");
            return;
        }

        // Memasukkan barang ke daftar pembelian
        daftarBeli.add(barang);
        jumlahBeli.add(jumlah);

        System.out.println("Barang berhasil ditambahkan!");
    }

    // Method untuk menghitung total pembayaran
    public int hitungTotal() {

        int total = 0;

        for (int i = 0; i < daftarBeli.size(); i++) {

            total += daftarBeli.get(i).getHarga()
                    * jumlahBeli.get(i);
        }

        return total;
    }

    // Method untuk memeriksa apakah transaksi kosong
    public boolean isKosong() {
        return daftarBeli.isEmpty();
    }

    // Method untuk memproses pembayaran
    public void prosesPembayaran(String namaPembeli, int uangBayar) {

        int total = hitungTotal();

        // Memeriksa kecukupan uang pembayaran
        if (uangBayar < total) {
            System.out.println("Uang pembayaran tidak cukup!");
            System.out.println("Kekurangan: Rp" + (total - uangBayar));
            return;
        }

        // Mengurangi stok setelah pembayaran berhasil
        for (int i = 0; i < daftarBeli.size(); i++) {

            daftarBeli.get(i).kurangiStok(jumlahBeli.get(i));
        }

        int kembalian = uangBayar - total;

        // Menampilkan struk pembayaran
        System.out.println("\n==========================================");
        System.out.println("          TOKO ALAT TULIS KERJA");
        System.out.println("              STRUK PEMBELIAN");
        System.out.println("==========================================");
        System.out.println("Nama Pembeli : " + namaPembeli);
        System.out.println("------------------------------------------");

        System.out.printf("%-15s %-8s %-10s %-10s%n",
                "Barang", "Jumlah", "Harga", "Subtotal");

        // Menampilkan semua barang yang dibeli
        for (int i = 0; i < daftarBeli.size(); i++) {

            Barang barang = daftarBeli.get(i);

            int jumlah = jumlahBeli.get(i);
            int subtotal = barang.getHarga() * jumlah;

            System.out.printf("%-15s %-8d Rp%-8d Rp%-8d%n",
                    barang.getNama(), jumlah,
                    barang.getHarga(), subtotal);
        }

        System.out.println("------------------------------------------");
        System.out.println("Total Belanja : Rp" + total);
        System.out.println("Uang Bayar    : Rp" + uangBayar);
        System.out.println("Kembalian     : Rp" + kembalian);
        System.out.println("==========================================");
        System.out.println("          TRANSAKSI BERHASIL");
        System.out.println("       Terima kasih telah berbelanja!");
        System.out.println("==========================================");

        // Mengosongkan daftar pembelian
        daftarBeli.clear();
        jumlahBeli.clear();
    }
}
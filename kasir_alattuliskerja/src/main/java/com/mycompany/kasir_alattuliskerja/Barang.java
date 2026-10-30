/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kasir_alattuliskerja;

/**
 *
 * @author selvi
 */
public class Barang {

    // Atribut Parent Class
    protected String kode;
    protected String nama;
    protected int harga;
    protected int stok;

    // Constructor
    public Barang(String kode, String nama, int harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    // Getter
    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    // Mengurangi stok
    public void kurangiStok(int jumlah) {
        stok -= jumlah;
    }

    // Menampilkan informasi barang
    public void tampilkanBarang() {
        System.out.printf("%-8s %-15s Rp%-10d %d%n",
                kode, nama, harga, stok);
    }
}
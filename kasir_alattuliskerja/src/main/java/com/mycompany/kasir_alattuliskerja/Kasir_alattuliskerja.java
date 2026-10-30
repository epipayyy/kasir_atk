/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.kasir_alattuliskerja;

/**
 *
 * @author selvi
 */
import java.util.Scanner;

public class Kasir_alattuliskerja {

    static Scanner input = new Scanner(System.in);

    // Membuat objek menggunakan Child Class BarangATK
    static Barang[] daftarBarang = {
        new BarangATK("1", "Buku Tulis", 5000, 20),
        new BarangATK("2", "Pulpen", 3000, 30),
        new BarangATK("3", "Pensil", 2000, 25),
        new BarangATK("4", "Penghapus", 1500, 20),
        new BarangATK("5", "Penggaris", 4000, 15),
        new BarangATK("6", "Map Plastik", 2500, 20)
    };

    public static void tampilkanBarang() {

        System.out.println("\n========== DAFTAR BARANG ATK ==========");
        System.out.printf("%-8s %-15s %-12s %-8s%n",
                "Kode", "Nama Barang", "Harga", "Stok");

        for (Barang barang : daftarBarang) {
            barang.tampilkanBarang();
        }

        System.out.println("=======================================");
    }

    public static Barang cariBarang(String kode) {

        for (Barang barang : daftarBarang) {

            if (barang.getKode().equals(kode)) {
                return barang;
            }
        }

        return null;
    }

    public static int bacaAngka(String pesan) {

        while (true) {

            System.out.print(pesan);

            try {
                return Integer.parseInt(input.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    public static void main(String[] args) {

        Transaksi transaksi = new Transaksi();

        int pilihan;

        do {

            System.out.println("\n========================================");
            System.out.println("       TOKO ALAT TULIS KERJA");
            System.out.println("========================================");
            System.out.println("1. Tampilkan Daftar Barang");
            System.out.println("2. Pembelian Barang");
            System.out.println("0. Keluar");
            System.out.println("========================================");

            pilihan = bacaAngka("Pilih menu: ");

            switch (pilihan) {

                case 1:
                    tampilkanBarang();
                    break;

                case 2:
                    System.out.println("\n========== PEMBELIAN BARANG ==========");

                    System.out.print("Nama Pembeli: ");
                    String namaPembeli = input.nextLine();

                    if (namaPembeli.trim().isEmpty()) {
                        System.out.println("Nama pembeli tidak boleh kosong!");
                        break;
                    }

                    tampilkanBarang();

                    System.out.println("\nPilih barang yang ingin dibeli.");
                    System.out.println("Ketik 0 jika selesai memilih barang.");

                    while (true) {

                        int nomor = bacaAngka("\nMasukkan nomor barang: ");

                        if (nomor == 0) {
                            break;
                        }

                        Barang barang = cariBarang(String.valueOf(nomor));

                        if (barang != null) {

                            int jumlah = bacaAngka("Jumlah beli: ");

                            if (jumlah <= 0) {
                                System.out.println("Jumlah harus lebih dari 0!");
                            } else {
                                transaksi.tambahBarang(barang, jumlah);
                            }

                        } else {
                            System.out.println("Nomor barang tidak ditemukan!");
                        }
                    }

                    if (!transaksi.isKosong()) {

                        System.out.println("\nTotal Belanja: Rp" + transaksi.hitungTotal());

                        int bayar = bacaAngka("Uang Pembayaran: Rp");

                        transaksi.prosesPembayaran(namaPembeli, bayar);

                    } else {
                        System.out.println("Tidak ada barang yang dibeli!");
                    }

                    break;

                case 0:
                    System.out.println("\nTerima kasih telah berkunjung!");
                    System.out.println("TOKO ALAT TULIS KERJA");
                    break;

                default:
                    System.out.println("Pilihan menu tidak tersedia!");
            }

        } while (pilihan != 0);

        input.close();
    }
}
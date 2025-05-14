import java.util.ArrayList;
import java.util.Scanner;

public class NilaiMahasiswa {
    static Scanner scanner = new Scanner(System.in);
    
    static class Mahasiswa {
        String nama, nim;
        double tugas, uts, uas, nilaiAkhir;
        char grade;
    }

    static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();

    public static void main(String[] args) {
        int pilihan;
        do {
            tampilMenu();
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); 

            switch (pilihan) {
                case 1 -> inputData();
                case 2 -> tampilData();
                case 3 -> cariMahasiswa();
                case 4 -> hapusMahasiswa();
                case 5 -> System.out.println("Terima kasih!");
                default -> System.out.println("Menu tidak valid!");
            }
        } while (pilihan != 5);
    }

    static void tampilMenu() {
        System.out.println("\n=== MENU MANAJEMEN NILAI MAHASISWA ===");
        System.out.println("1. Input Data Mahasiswa");
        System.out.println("2. Tampilkan Data Mahasiswa");
        System.out.println("3. Cari Mahasiswa berdasarkan NIM");
        System.out.println("4. Hapus Mahasiswa berdasarkan NIM");
        System.out.println("5. Keluar");
    }

    static void inputData() {
        System.out.print("Masukkan jumlah mahasiswa: ");
        int jumlah = scanner.nextInt();
        scanner.nextLine(); // Buang newline

        for (int i = 0; i < jumlah; i++) {
            System.out.println("\nMahasiswa ke-" + (i + 1));
            Mahasiswa mhs = new Mahasiswa();

            System.out.print("Nama : ");
            mhs.nama = scanner.nextLine();

            System.out.print("NIM : ");
            mhs.nim = scanner.nextLine();

            mhs.tugas = inputNilai("Nilai Tugas");
            mhs.uts = inputNilai("Nilai UTS");
            mhs.uas = inputNilai("Nilai UAS");

            // Hitung nilai akhir dan grade
            mhs.nilaiAkhir = hitungNilaiAkhir(mhs.tugas, mhs.uts, mhs.uas);
            mhs.grade = tentukanGrade(mhs.nilaiAkhir);

            daftarMahasiswa.add(mhs);
        }
    }

    static double inputNilai(String keterangan) {
        double nilai;
        do {
            System.out.print(keterangan + " : ");
            nilai = scanner.nextDouble();
            if (nilai < 0 || nilai > 100) {
                System.out.println("Nilai harus antara 0 - 100!");
            }
        } while (nilai < 0 || nilai > 100);
        scanner.nextLine(); // Buang newline setelah input nilai
        return nilai;
    }

    static double hitungNilaiAkhir(double tugas, double uts, double uas) {
        return (0.3 * tugas) + (0.3 * uts) + (0.4 * uas);
    }

    static char tentukanGrade(double nilai) {
        if (nilai >= 85) return 'A';
        else if (nilai >= 70) return 'B';
        else if (nilai >= 60) return 'C';
        else if (nilai >= 50) return 'D';
        else return 'E';
    }

    static void tampilData() {
        System.out.println("\n=== DATA MAHASISWA ===");
        if (daftarMahasiswa.isEmpty()) {
            System.out.println("Belum ada data.");
        } else {
            for (int i = 0; i < daftarMahasiswa.size(); i++) {
                Mahasiswa m = daftarMahasiswa.get(i);
                System.out.println("Mahasiswa ke-" + (i + 1));
                System.out.println("Nama        : " + m.nama);
                System.out.println("NIM         : " + m.nim);
                System.out.println("Nilai Akhir : " + m.nilaiAkhir);
                System.out.println("Grade       : " + m.grade);
                System.out.println("----------------------------");
            }
        }
    }

    static void cariMahasiswa() {
        System.out.print("Masukkan NIM yang dicari: ");
        String cariNim = scanner.nextLine();
        boolean ditemukan = false;

        for (Mahasiswa m : daftarMahasiswa) {
            if (m.nim.equalsIgnoreCase(cariNim)) {
                System.out.println("\n=== DATA DITEMUKAN ===");
                System.out.println("Nama        : " + m.nama);
                System.out.println("NIM         : " + m.nim);
                System.out.println("Nilai Akhir : " + m.nilaiAkhir);
                System.out.println("Grade       : " + m.grade);
                ditemukan = true;
                break;
            }
        }

        if (!ditemukan) {
            System.out.println("Data mahasiswa dengan NIM tersebut tidak ditemukan.");
        }
    }

    static void hapusMahasiswa() {
        System.out.print("Masukkan NIM yang ingin dihapus: ");
        String hapusNim = scanner.nextLine();
        boolean dihapus = false;

        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            if (daftarMahasiswa.get(i).nim.equalsIgnoreCase(hapusNim)) {
                daftarMahasiswa.remove(i);
                dihapus = true;
                System.out.println("Data berhasil dihapus.");
                break;
            }
        }

        if (!dihapus) {
            System.out.println("Data mahasiswa dengan NIM tersebut tidak ditemukan.");
        }
    }
}

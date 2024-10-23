# Sistem Parkir (Masuk dan Keluar)

Sistem manajemen parkir sederhana menggunakan JavaFX dan Laragon.MySQL -HeidiSQL Portable.
Sistem ini dapat mencatat kendaraan masuk dan keluar, menghitung biaya parkir berdasarkan durasi, serta membuat laporan pendapatan.

## Dibuat Oleh
- Nama: Nikolaus Franz
- NPM: 2023130033

## Features
- Pencatatan kendaraan masuk dan keluar
- Perhitungan biaya parkir otomatis:
  * Motor: Rp 2.000/jam
  * Mobil: Rp 5.000/jam
- Identifikasi plat nomor kendaraan dan wilayahnya
- Laporan pendapatan harian/bulanan
- Custom waktu untuk testing/demo - Karena sistem di buat untuk demonstrasi.
- Reset database - Karena sistem di buat untuk demonstrasi.

## Requirements
- JDK 1.8.0_111
- MySQL/Laragon
- NetBeans IDE 1.4
- Scene Builder (untuk edit FXML)

## Cara Install
1. Clone repository ini
2. Import database dari folder `database/parking_system.sql`
3. Buka project di NetBeans
4. Pastikan MySQL JDBC Driver sudah ada di folder lib ( mysql-connector-j-9.1.0.jar ) File berada di dalam lib folder dalam root project
5. Run project

## Konfigurasi Database Default
- Host: localhost
- Port: 3306
- Database: parking_system
- Username: root
- Password: (kosong)

## Struktur Project

ParkingSystem/
├── src/parkingsystem/
│   ├── ParkingSystem.java
│   ├── ParkingSystem.fxml
│   ├── ParkingSystemController.java
│   ├── ParkingEntry.java
│   ├── ReportEntry.java
│   └── PlateInfo.java
├── database/
│   └── parking_system.sql
└── lib/
└── mysql-connector-j-9.1.0

## Catatan Penggunaan
- Pastikan MySQL/Laragon sudah running sebelum menjalankan aplikasi
- Untuk demo/testing, bisa menggunakan fitur custom waktu
- Data bisa direset menggunakan tombol "Reset Database"

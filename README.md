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

## Struktur Project Berdasarkan Path

ParkingSystem/src/parkingsystem/ParkingSystem.java; 
ParkingSystem/src/parkingsystem/ParkingSystem.fxml; 
ParkingSystem/src/parkingsystem/ParkingSystemController.java; 
ParkingSystem/src/parkingsystem/ParkingEntry.java; 
ParkingSystem/src/parkingsystem/ReportEntry.java; 
ParkingSystem/src/parkingsystem/PlateInfo.java; 
ParkingSystem/database/arking_system.sql; 
ParkingSystem/lib/mysql-connector-j-9.1.0; 

## Catatan Penggunaan
- Pastikan MySQL/Laragon sudah running sebelum menjalankan aplikasi
- Untuk demo/testing, bisa menggunakan fitur custom waktu
- Data bisa direset menggunakan tombol "Reset Database"

## Skema DB

-- Database: parking_system

-- Tabel: parking_rates
+---------------+-----------------------+------+-----+---------+----------------+
| Field         | Type                 | Null | Key | Default | Extra          |
+---------------+-----------------------+------+-----+---------+----------------+
| id            | int                  | NO   | PRI | NULL    | auto_increment |
| vehicle_type  | enum('Motor','Mobil')| NO   |     | NULL    |                |
| rate_per_hour | decimal(10,2)        | NO   |     | NULL    |                |
+---------------+-----------------------+------+-----+---------+----------------+

-- Tabel: parking_transactions
+--------------+-----------------------+------+-----+---------+----------------+
| Field        | Type                 | Null | Key | Default | Extra          |
+--------------+-----------------------+------+-----+---------+----------------+
| id           | int                  | NO   | PRI | NULL    | auto_increment |
| plate_number | varchar(20)          | NO   |     | NULL    |                |
| vehicle_type | enum('Motor','Mobil')| NO   |     | NULL    |                |
| entry_time   | datetime             | NO   |     | NULL    |                |
| exit_time    | datetime             | YES  |     | NULL    |                |
| parking_fee  | decimal(10,2)        | YES  |     | NULL    |                |
| status       | enum('Active',       | YES  |     | Active  |                |
|              | 'Completed')         |      |     |         |                |
+--------------+-----------------------+------+-----+---------+----------------+

Relasi:
- Tabel parking_rates dan parking_transactions terhubung melalui vehicle_type
- parking_rates menyimpan tarif per jam untuk setiap jenis kendaraan
- parking_transactions menyimpan semua transaksi parkir

Data Default parking_rates:
+----+--------------+--------------+
| id | vehicle_type | rate_per_hour|
+----+--------------+--------------+
|  1 | Motor        |     2000.00  |
|  2 | Mobil        |     5000.00  |
+----+--------------+--------------+

Status di parking_transactions:
- Active: Kendaraan masih parkir
- Completed: Kendaraan sudah keluar dan transaksi selesai

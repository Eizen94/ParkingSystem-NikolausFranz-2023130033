/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package parkingsystem;

import java.net.URL;
import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

/**
 *
 * @author Nikolaus Franz
 */
public class ParkingSystemController implements Initializable {
    
    // Komponen FXML untuk tab Parkir Masuk
    @FXML private ComboBox<String> vehicleTypeCombo;
    @FXML private TextField plateNumberField;
    @FXML private CheckBox useCustomEntryTime;
    @FXML private HBox customEntryTimeBox;
    @FXML private DatePicker entryDate;
    @FXML private TextField entryTime;
    
    // Komponen FXML untuk tab Parkir Keluar
    @FXML private TableView<ParkingEntry> activeVehiclesTable;
    @FXML private TableColumn<ParkingEntry, Integer> idCol;
    @FXML private TableColumn<ParkingEntry, String> plateNumberCol;
    @FXML private TableColumn<ParkingEntry, String> vehicleTypeCol;
    @FXML private TableColumn<ParkingEntry, LocalDateTime> entryTimeCol;
    @FXML private CheckBox useCustomExitTime;
    @FXML private HBox customExitTimeBox;
    @FXML private DatePicker exitDate;
    @FXML private TextField exitTime;
    
    // Komponen FXML untuk tab Laporan
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private TableView<ReportEntry> reportTable;
    @FXML private TableColumn<ReportEntry, LocalDate> reportDateCol;
    @FXML private TableColumn<ReportEntry, Integer> totalMotorCol;
    @FXML private TableColumn<ReportEntry, Integer> totalMobilCol;
    @FXML private TableColumn<ReportEntry, Double> totalIncomeCol;
    
    // Koneksi Database
    private Connection conn;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/parking_system";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    
    // Map untuk informasi plat nomor
    private final Map<String, PlateInfo> plateInfoMap = new HashMap<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializePlateInfo();
        setupDatabase();
        setupTables();
        setupCustomTimeControls();
        loadActiveParking();
    }
    
    private void initializePlateInfo() {
        // Sumatera
        plateInfoMap.put("BL", new PlateInfo("BL", "Sumatera", "Aceh"));
        plateInfoMap.put("BB", new PlateInfo("BB", "Sumatera", "Sumatera Utara bagian barat"));
        plateInfoMap.put("BK", new PlateInfo("BK", "Sumatera", "Sumatera Utara bagian timur"));
        plateInfoMap.put("BA", new PlateInfo("BA", "Sumatera", "Sumatera Barat"));
        plateInfoMap.put("BM", new PlateInfo("BM", "Sumatera", "Riau"));
        plateInfoMap.put("BH", new PlateInfo("BH", "Sumatera", "Jambi"));
        plateInfoMap.put("BD", new PlateInfo("BD", "Sumatera", "Bengkulu"));
        plateInfoMap.put("BP", new PlateInfo("BP", "Sumatera", "Kepulauan Riau"));
        plateInfoMap.put("BG", new PlateInfo("BG", "Sumatera", "Sumatera Selatan"));
        plateInfoMap.put("BN", new PlateInfo("BN", "Sumatera", "Bangka-Belitung"));
        plateInfoMap.put("BE", new PlateInfo("BE", "Sumatera", "Lampung"));

        // Banten & DKI Jakarta
        plateInfoMap.put("A", new PlateInfo("A", "Banten", "Banten, Serang, Tangerang"));
        plateInfoMap.put("B", new PlateInfo("B", "DKI Jakarta", "Jakarta, Depok, Bekasi"));

        // Jawa Barat
        plateInfoMap.put("D", new PlateInfo("D", "Jawa Barat", "Bandung"));
        plateInfoMap.put("E", new PlateInfo("E", "Jawa Barat", "Cirebon, Majalengka, Indramayu, Kuningan"));
        plateInfoMap.put("F", new PlateInfo("F", "Jawa Barat", "Bogor, Cianjur, Sukabumi"));
        plateInfoMap.put("T", new PlateInfo("T", "Jawa Barat", "Purwakarta, Karawang, Subang"));
        plateInfoMap.put("Z", new PlateInfo("Z", "Jawa Barat", "Garut, Tasikmalaya, Ciamis"));

        // Jawa Tengah
        plateInfoMap.put("G", new PlateInfo("G", "Jawa Tengah", "Pekalongan, Brebes"));
        plateInfoMap.put("H", new PlateInfo("H", "Jawa Tengah", "Semarang"));
        plateInfoMap.put("K", new PlateInfo("K", "Jawa Tengah", "Pati, Kudus"));
        plateInfoMap.put("R", new PlateInfo("R", "Jawa Tengah", "Banyumas"));
        plateInfoMap.put("AA", new PlateInfo("AA", "Jawa Tengah", "Magelang"));
        plateInfoMap.put("AD", new PlateInfo("AD", "Jawa Tengah", "Surakarta"));

        // D.I. Yogyakarta
        plateInfoMap.put("AB", new PlateInfo("AB", "D.I. Yogyakarta", "Yogyakarta"));

        // Jawa Timur
        plateInfoMap.put("L", new PlateInfo("L", "Jawa Timur", "Surabaya"));
        plateInfoMap.put("M", new PlateInfo("M", "Jawa Timur", "Madura"));
        plateInfoMap.put("N", new PlateInfo("N", "Jawa Timur", "Malang"));
        plateInfoMap.put("P", new PlateInfo("P", "Jawa Timur", "Jember"));
        plateInfoMap.put("S", new PlateInfo("S", "Jawa Timur", "Bojonegoro"));
        plateInfoMap.put("W", new PlateInfo("W", "Jawa Timur", "Sidoarjo"));
        plateInfoMap.put("AE", new PlateInfo("AE", "Jawa Timur", "Madiun"));
        plateInfoMap.put("AG", new PlateInfo("AG", "Jawa Timur", "Kediri"));

        // Bali dan Nusa Tenggara
        plateInfoMap.put("DK", new PlateInfo("DK", "Bali", "Bali"));
        plateInfoMap.put("DR", new PlateInfo("DR", "NTB", "Lombok"));
        plateInfoMap.put("EA", new PlateInfo("EA", "NTB", "Sumbawa"));
        plateInfoMap.put("DH", new PlateInfo("DH", "NTT", "Kupang"));
        plateInfoMap.put("EB", new PlateInfo("EB", "NTT", "Flores"));
        plateInfoMap.put("ED", new PlateInfo("ED", "NTT", "Sumba"));

        // Kalimantan
        plateInfoMap.put("KB", new PlateInfo("KB", "Kalimantan Barat", "Pontianak"));
        plateInfoMap.put("DA", new PlateInfo("DA", "Kalimantan Selatan", "Banjarmasin"));
        plateInfoMap.put("KH", new PlateInfo("KH", "Kalimantan Tengah", "Palangkaraya"));
        plateInfoMap.put("KT", new PlateInfo("KT", "Kalimantan Timur", "Samarinda"));
        plateInfoMap.put("KU", new PlateInfo("KU", "Kalimantan Utara", "Tanjung Selor"));

        // Sulawesi
        plateInfoMap.put("DB", new PlateInfo("DB", "Sulawesi Utara", "Manado"));
        plateInfoMap.put("DM", new PlateInfo("DM", "Gorontalo", "Gorontalo"));
        plateInfoMap.put("DN", new PlateInfo("DN", "Sulawesi Tengah", "Palu"));
        plateInfoMap.put("DT", new PlateInfo("DT", "Sulawesi Tenggara", "Kendari"));
        plateInfoMap.put("DD", new PlateInfo("DD", "Sulawesi Selatan", "Makassar"));
        plateInfoMap.put("DC", new PlateInfo("DC", "Sulawesi Barat", "Mamuju"));

        // Maluku & Papua
        plateInfoMap.put("DE", new PlateInfo("DE", "Maluku", "Ambon"));
        plateInfoMap.put("DG", new PlateInfo("DG", "Maluku Utara", "Ternate"));
        plateInfoMap.put("PA", new PlateInfo("PA", "Papua", "Jayapura"));
        plateInfoMap.put("PB", new PlateInfo("PB", "Papua Barat", "Manokwari"));
    }

    private void setupDatabase() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException e) {
            showError("Database Error", "Tidak dapat terhubung ke database: " + e.getMessage());
        }
    }
    
    private void setupTables() {
        // Setup kolom tabel kendaraan aktif
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        plateNumberCol.setCellValueFactory(new PropertyValueFactory<>("plateNumber"));
        vehicleTypeCol.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        entryTimeCol.setCellValueFactory(new PropertyValueFactory<>("entryTime"));
        
        // Format tampilan waktu
        entryTimeCol.setCellFactory(column -> {
            return new TableCell<ParkingEntry, LocalDateTime>() {
                private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(formatter.format(item));
                    }
                }
            };
        });
        
        // Setup tooltip untuk nomor polisi
        plateNumberCol.setCellFactory(column -> {
            return new TableCell<ParkingEntry, String>() {
                @Override
                protected void updateItem(String plateNumber, boolean empty) {
                    super.updateItem(plateNumber, empty);
                    if (empty || plateNumber == null) {
                        setText(null);
                        setTooltip(null);
                    } else {
                        setText(plateNumber);
                        String plateCode = plateNumber.split(" ")[0];
                        PlateInfo info = plateInfoMap.get(plateCode);
                        if (info != null) {
                            Tooltip tooltip = new Tooltip(
                                "Wilayah: " + info.getRegion() + "\n" +
                                "Kota/Area: " + info.getAreas()
                            );
                            setTooltip(tooltip);
                        }
                    }
                }
            };
        });
        
        // Setup kolom tabel laporan
        reportDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        totalMotorCol.setCellValueFactory(new PropertyValueFactory<>("totalMotor"));
        totalMobilCol.setCellValueFactory(new PropertyValueFactory<>("totalMobil"));
        totalIncomeCol.setCellValueFactory(new PropertyValueFactory<>("totalIncome"));
        
        // Format tampilan uang
        totalIncomeCol.setCellFactory(column -> {
            return new TableCell<ReportEntry, Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(String.format("Rp %.2f", item));
                    }
                }
            };
        });
    }
    
    private void setupCustomTimeControls() {
        useCustomEntryTime.selectedProperty().addListener((obs, oldVal, newVal) -> 
            customEntryTimeBox.setVisible(newVal));
        
        useCustomExitTime.selectedProperty().addListener((obs, oldVal, newVal) -> 
            customExitTimeBox.setVisible(newVal));
        
        // Set default values
        entryDate.setValue(LocalDate.now());
        exitDate.setValue(LocalDate.now());
        entryTime.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        exitTime.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
    
    @FXML
    private void handleResetDatabase(ActionEvent event) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
            "Ini akan menghapus semua data parkir. Lanjutkan?",
            ButtonType.YES, ButtonType.NO);
        confirm.setHeaderText("Reset Database");
        
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                try {
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate("TRUNCATE TABLE parking_transactions");
                    stmt.executeUpdate("ALTER TABLE parking_transactions AUTO_INCREMENT = 1");
                    
                    loadActiveParking();
                    showInfo("Sukses", "Database berhasil direset!");
                } catch (SQLException e) {
                    showError("Database Error", e.getMessage());
                }
            }
        });
    }
    
    @FXML
    private void handleVehicleEntry(ActionEvent event) {
        String vehicleType = vehicleTypeCombo.getValue();
        String plateNumber = plateNumberField.getText().trim().toUpperCase().replaceAll("\\s+", " ");
        
        if (vehicleType == null || vehicleType.isEmpty()) {
            showError("Error", "Pilih jenis kendaraan terlebih dahulu!");
            return;
        }
        
        if (plateNumber.isEmpty()) {
            showError("Error", "Masukkan nomor polisi!");
			
			return;
        }
        
        if (!isValidPlateNumber(plateNumber)) {
            showError("Error", "Format nomor polisi tidak valid!\n\n" +
                     "Format yang benar:\n" +
                     "1. Kode Kota (1-2 huruf)\n" +
                     "2. Nomor (1-4 angka)\n" +
                     "3. Kode Wilayah (1-3 huruf, opsional)\n\n" +
                     "Contoh: D 1, B 1234 CD, BK 123 A");
            return;
        }
        
        // Dapatkan informasi wilayah dari plat nomor
        String plateCode = plateNumber.split(" ")[0];
        PlateInfo info = plateInfoMap.get(plateCode);
        
        try {
            LocalDateTime entryDateTime;
            if (useCustomEntryTime.isSelected()) {
                try {
                    LocalDate date = entryDate.getValue();
                    LocalTime time = LocalTime.parse(entryTime.getText(),
                        DateTimeFormatter.ofPattern("HH:mm:ss"));
                    entryDateTime = LocalDateTime.of(date, time);
                } catch (Exception e) {
                    showError("Error", "Format waktu tidak valid! Gunakan format HH:mm:ss");
                    return;
                }
            } else {
                entryDateTime = LocalDateTime.now();
            }
            
            String sql = "INSERT INTO parking_transactions (plate_number, vehicle_type, entry_time) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, plateNumber);
            stmt.setString(2, vehicleType);
            stmt.setTimestamp(3, Timestamp.valueOf(entryDateTime));
            stmt.executeUpdate();
            
            // Buat pesan sukses dengan informasi wilayah
            StringBuilder message = new StringBuilder("Kendaraan berhasil dicatat!");
            if (info != null) {
                message.append("\n\nInformasi Plat Nomor:")
                       .append("\nWilayah: ").append(info.getRegion())
                       .append("\nKota/Area: ").append(info.getAreas());
            }
            
            showInfo("Sukses", message.toString());
            vehicleTypeCombo.setValue(null);
            plateNumberField.clear();
            loadActiveParking();
        } catch (SQLException e) {
            showError("Database Error", e.getMessage());
        }
    }
    
    @FXML
    private void handleVehicleExit(ActionEvent event) {
        ParkingEntry selected = activeVehiclesTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Error", "Pilih kendaraan yang akan keluar terlebih dahulu!");
            return;
        }
        
        try {
            LocalDateTime exitDateTime;
            if (useCustomExitTime.isSelected()) {
                try {
                    LocalDate date = exitDate.getValue();
                    LocalTime time = LocalTime.parse(exitTime.getText(),
                        DateTimeFormatter.ofPattern("HH:mm:ss"));
                    exitDateTime = LocalDateTime.of(date, time);
                } catch (Exception e) {
                    showError("Error", "Format waktu tidak valid! Gunakan format HH:mm:ss");
                    return;
                }
            } else {
                exitDateTime = LocalDateTime.now();
            }
            
            // Validasi waktu keluar tidak boleh sebelum waktu masuk
            if (exitDateTime.isBefore(selected.getEntryTime())) {
                showError("Error", "Waktu keluar tidak boleh sebelum waktu masuk!");
                return;
            }
            
            double parkingFee = calculateParkingFee(selected.getVehicleType(), 
                selected.getEntryTime(), exitDateTime);
            
            String sql = "UPDATE parking_transactions SET exit_time = ?, parking_fee = ?, status = 'Completed' WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setTimestamp(1, Timestamp.valueOf(exitDateTime));
            stmt.setDouble(2, parkingFee);
            stmt.setInt(3, selected.getId());
            stmt.executeUpdate();
            
            long durationMinutes = java.time.Duration.between(selected.getEntryTime(), exitDateTime).toMinutes();
            long hours = durationMinutes / 60;
            long minutes = durationMinutes % 60;
            
            // Tambahkan informasi wilayah ke pesan
            String plateCode = selected.getPlateNumber().split(" ")[0];
            PlateInfo info = plateInfoMap.get(plateCode);
            StringBuilder message = new StringBuilder();
            message.append(String.format("Durasi Parkir: %d jam %d menit\nTotal Biaya: Rp %.2f",
                hours, minutes, parkingFee));
                
            if (info != null) {
                message.append("\n\nInformasi Plat Nomor:")
                       .append("\nWilayah: ").append(info.getRegion())
                       .append("\nKota/Area: ").append(info.getAreas());
            }
            
            showInfo("Informasi Biaya Parkir", message.toString());
            loadActiveParking();
        } catch (SQLException e) {
            showError("Database Error", e.getMessage());
        }
    }
	
	@FXML
    private void handleGenerateReport(ActionEvent event) {
        LocalDate start = startDate.getValue();
        LocalDate end = endDate.getValue();
        
        if (start == null || end == null) {
            showError("Error", "Pilih tanggal mulai dan selesai terlebih dahulu!");
            return;
        }
        
        if (start.isAfter(end)) {
            showError("Error", "Tanggal mulai tidak boleh setelah tanggal selesai!");
            return;
        }
        
        try {
            String sql = "SELECT " +
                "DATE(entry_time) as tanggal, " +
                "SUM(CASE WHEN vehicle_type = 'Motor' THEN 1 ELSE 0 END) as total_motor, " +
                "SUM(CASE WHEN vehicle_type = 'Mobil' THEN 1 ELSE 0 END) as total_mobil, " +
                "SUM(parking_fee) as total_income " +
                "FROM parking_transactions " +
                "WHERE DATE(entry_time) BETWEEN ? AND ? " +
                "AND status = 'Completed' " +
                "GROUP BY DATE(entry_time) " +
                "ORDER BY tanggal";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(start));
            stmt.setDate(2, java.sql.Date.valueOf(end));
            
            ResultSet rs = stmt.executeQuery();
            
            ObservableList<ReportEntry> reportData = FXCollections.observableArrayList();
            while (rs.next()) {
                reportData.add(new ReportEntry(
                    rs.getDate("tanggal").toLocalDate(),
                    rs.getInt("total_motor"),
                    rs.getInt("total_mobil"),
                    rs.getDouble("total_income")
                ));
            }
            
            reportTable.setItems(reportData);
        } catch (SQLException e) {
            showError("Database Error", e.getMessage());
        }
    }
    
    private double calculateParkingFee(String vehicleType, LocalDateTime entryTime, LocalDateTime exitTime) {
        try {
            // Hitung durasi dalam menit
            long durationMinutes = java.time.Duration.between(entryTime, exitTime).toMinutes();
            
            // Jika kurang dari 60 menit, hitung sebagai 1 jam
            // Jika lebih dari 60 menit, hitung jam penuh tanpa pembulatan
            long hours;
            if (durationMinutes < 60) {
                hours = 1;
            } else {
                hours = durationMinutes / 60; // integer division akan membuang desimal
            }
            
            // Ambil tarif per jam dari database
            String sql = "SELECT rate_per_hour FROM parking_rates WHERE vehicle_type = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, vehicleType);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                double ratePerHour = rs.getDouble("rate_per_hour");
                return ratePerHour * hours;
            }
            
            return 0.0;
        } catch (SQLException e) {
            showError("Error", "Gagal menghitung biaya parkir: " + e.getMessage());
            return 0.0;
        }
    }
    
    private boolean isValidPlateNumber(String plateNumber) {
        try {
            // Memisahkan string berdasarkan spasi
            String[] parts = plateNumber.split(" ");
            
            // Harus terdiri dari 2 atau 3 bagian
            if (parts.length < 2 || parts.length > 3) {
                return false;
            }
            
            // Bagian pertama (Kode Kota): 1-2 huruf
            if (!parts[0].matches("[A-Z]{1,2}")) {
                return false;
            }
            
            // Bagian kedua (Nomor): 1-4 digit
            if (!parts[1].matches("\\d{1,4}")) {
                return false;
            }
            
            // Bagian ketiga (Kode Wilayah): 1-3 huruf, opsional
            if (parts.length == 3 && !parts[2].matches("[A-Z]{1,3}")) {
                return false;
            }
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private void loadActiveParking() {
        try {
            String sql = "SELECT * FROM parking_transactions WHERE status = 'Active' ORDER BY entry_time";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            ObservableList<ParkingEntry> entries = FXCollections.observableArrayList();
            while (rs.next()) {
                entries.add(new ParkingEntry(
                    rs.getInt("id"),
                    rs.getString("plate_number"),
                    rs.getString("vehicle_type"),
                    rs.getTimestamp("entry_time").toLocalDateTime()
                ));
            }
            
            activeVehiclesTable.setItems(entries);
        } catch (SQLException e) {
            showError("Database Error", e.getMessage());
        }
    }
    
    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    private void showInfo(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
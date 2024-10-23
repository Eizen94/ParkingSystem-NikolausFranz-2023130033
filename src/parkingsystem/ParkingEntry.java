/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkingsystem;

import java.time.LocalDateTime;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Nikolaus Franz
 */
public class ParkingEntry {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty plateNumber;
    private final SimpleStringProperty vehicleType;
    private final SimpleObjectProperty<LocalDateTime> entryTime;
    
    public ParkingEntry(int id, String plateNumber, String vehicleType, LocalDateTime entryTime) {
        this.id = new SimpleIntegerProperty(id);
        this.plateNumber = new SimpleStringProperty(plateNumber);
        this.vehicleType = new SimpleStringProperty(vehicleType);
        this.entryTime = new SimpleObjectProperty<>(entryTime);
    }
    
    public int getId() {
        return id.get();
    }
    
    public String getPlateNumber() {
        return plateNumber.get();
    }
    
    public String getVehicleType() {
        return vehicleType.get();
    }
    
    public LocalDateTime getEntryTime() {
        return entryTime.get();
    }
    
    public SimpleIntegerProperty idProperty() {
        return id;
    }
    
    public SimpleStringProperty plateNumberProperty() {
        return plateNumber;
    }
    
    public SimpleStringProperty vehicleTypeProperty() {
        return vehicleType;
    }
    
    public SimpleObjectProperty<LocalDateTime> entryTimeProperty() {
        return entryTime;
    }
}
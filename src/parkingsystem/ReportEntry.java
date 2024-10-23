/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkingsystem;

import java.time.LocalDate;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Nikolaus Franz
 */
public class ReportEntry {
    private final SimpleObjectProperty<LocalDate> date;
    private final SimpleIntegerProperty totalMotor;
    private final SimpleIntegerProperty totalMobil;
    private final SimpleDoubleProperty totalIncome;
    
    public ReportEntry(LocalDate date, int totalMotor, int totalMobil, double totalIncome) {
        this.date = new SimpleObjectProperty<>(date);
        this.totalMotor = new SimpleIntegerProperty(totalMotor);
        this.totalMobil = new SimpleIntegerProperty(totalMobil);
        this.totalIncome = new SimpleDoubleProperty(totalIncome);
    }
    
    public LocalDate getDate() {
        return date.get();
    }
    
    public int getTotalMotor() {
        return totalMotor.get();
    }
    
    public int getTotalMobil() {
        return totalMobil.get();
    }
    
    public double getTotalIncome() {
        return totalIncome.get();
    }
    
    public SimpleObjectProperty<LocalDate> dateProperty() {
        return date;
    }
    
    public SimpleIntegerProperty totalMotorProperty() {
        return totalMotor;
    }
    
    public SimpleIntegerProperty totalMobilProperty() {
        return totalMobil;
    }
    
    public SimpleDoubleProperty totalIncomeProperty() {
        return totalIncome;
    }
}
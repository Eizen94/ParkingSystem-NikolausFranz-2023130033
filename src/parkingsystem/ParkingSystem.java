/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package parkingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Nikolaus Franz
 */
public class ParkingSystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        // Update path sesuai nama file baru
        Parent root = FXMLLoader.load(getClass().getResource("ParkingSystem.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Sistem Parkir"); // Tambahkan judul window
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
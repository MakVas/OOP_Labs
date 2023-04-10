package com.makvas.oop_lab2;

import com.makvas.oop_lab2.warships.Destroyer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class WindowController{
    private addObjectWindowController createController;
    private static WindowController instance;
    public ArrayList<Destroyer> destroyers = new ArrayList<>();

    public void addDestroyer(Destroyer destroyer) {destroyers.add(destroyer);}
    public static WindowController getInstance() {
        if (instance == null) {
            instance = new WindowController();
        }
        return instance;
    }

    public void createAddObjectWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addObjectWindow.fxml"));
        Parent root = loader.load();
        createController = loader.getController();
        createController.setWindowController(this);
        Scene scene = new Scene(root, 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Add Object");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        addObjectWindowController controller = loader.getController();
        controller.setStage(stage);
    }
    public void createChangeWindow()throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("changeObjectWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Change Object");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        changeObjectWindowController controller = loader.getController();
        controller.setComboBox(destroyers);
        controller.setWindowController(this);
        controller.setStage(stage);
    }
    public void deleteObject(int selectedIndex) {
        destroyers.remove(selectedIndex);
    }
    public void createDeleteWindow()throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("deleteObjectWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 400, 400);
        Stage stage = new Stage();
        stage.setTitle("Delete Object");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        deleteObjectWindowController controller = loader.getController();
        controller.setComboBox(destroyers);
        controller.setWindowController(this);
        controller.setStage(stage);
    }
    public void createInfoWindow()throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("infoWindow.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 400, 200);
        Stage stage = new Stage();
        stage.setTitle("Info");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        InfoWindowController controller = loader.getController();
        controller.setStage(stage);
    }

}
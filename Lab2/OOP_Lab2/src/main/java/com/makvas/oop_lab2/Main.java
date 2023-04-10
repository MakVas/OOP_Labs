package com.makvas.oop_lab2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Group group = new Group();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("window.fxml"));
        group.getChildren().add(loader.load());
        Scene scene = new Scene(group, 1280, 720);
        stage.setTitle("Lab2 (World of Warships)");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {launch();}
}
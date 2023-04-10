package com.makvas.oop_lab2;

import com.makvas.oop_lab2.warships.Destroyer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class deleteObjectWindowController {
    private WindowController windowController;
    public WindowController getWindowController() {return windowController;}
    public void setWindowController(WindowController windowController) {
        this.windowController = windowController;
    }
    @FXML
    private ComboBox<String> chooseWarship;
    @FXML
    private ImageView warshipImageView;
    @FXML
    private Label nameLabel;
    @FXML
    private ProgressBar hpBar;
    private Stage stage;
    public void setStage(Stage stage) {this.stage = stage;}
    private int selectedIndex;
    public void setComboBox(ArrayList<Destroyer> destroyers){
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Destroyer destroyer : destroyers) {
            items.add(destroyer.getName());
        }
        chooseWarship.setItems(items);

        chooseWarship.setOnAction(event -> {
            selectedIndex = chooseWarship.getSelectionModel().getSelectedIndex();
            String selectedName = chooseWarship.getValue();
            if (selectedName != null) {
                Destroyer selectedDestroyer = null;
                for (Destroyer destroyer : getWindowController().destroyers) {
                    if (destroyer.getName().equals(selectedName)) {
                        selectedDestroyer = destroyer;
                        break;
                    }
                }
                if (selectedDestroyer != null) {
                    warshipImageView.setVisible(true);
                    warshipImageView.setImage(selectedDestroyer.getDestoyerImage());
                    setShadow(warshipImageView, 5, 3,3,0,255,0);
                    nameLabel.setVisible(true);
                    nameLabel.setText(selectedDestroyer.getName());
                    nameLabel.setStyle("-fx-font-size: 15px;" +
                            "-fx-effect: dropshadow(one-pass-box, #0F0, 5, 1, 0, 0); " +
                            "-fx-text-fill: black");
                    hpBar.setVisible(true);
                    hpBar.setProgress(selectedDestroyer.getHP());
                    hpBar.setStyle("-fx-accent: #0F0;");
                }
            }
        });
    }
    private void setShadow(ImageView imageView, double radius, double x, double y, int red, int green, int blue){
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(radius);
        dropShadow.setOffsetX(x);
        dropShadow.setOffsetY(y);
        dropShadow.setColor(Color.rgb(red, green, blue));
        imageView.setEffect(dropShadow);
    }
    public void deleteVisualObject(Destroyer des){
        Main.group.getChildren().removeAll(des.getDestroyerImageView(), des.getDestroyerLabel(), des.getDestroyerHPbar());
    }
    public void deleteButton(){
        if (selectedIndex >= 0 && selectedIndex < windowController.destroyers.size()) {
            Destroyer selectedDestroyer = windowController.destroyers.get(selectedIndex);
            deleteVisualObject(selectedDestroyer);
            windowController.destroyers.remove(selectedIndex);
        }
        stage.close();
    }
}
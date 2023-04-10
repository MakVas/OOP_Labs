package com.makvas.oop_lab2;

import com.makvas.oop_lab2.warships.Destroyer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class changeObjectWindowController extends addObjectWindowController implements Initializable {
    @FXML
    private ComboBox<String> chooseWarship;
    private WindowController windowController;
    public WindowController getWindowController() {return windowController;}
    public void setWindowController(WindowController windowController) {
        super.setWindowController(windowController);
        this.windowController = windowController;
    }
    private Stage stage;
    int selectedIndex;
    public void setComboBox(ArrayList<Destroyer> destroyers){
        ObservableList<String> items = FXCollections.observableArrayList();
        for (Destroyer destroyer : destroyers) {
            items.add(destroyer.getName());
        }
        chooseWarship.setItems(items);
        chooseWarship.setOnAction(this::selectObject);

        chooseWarship.setOnAction(event -> {
            String selectedName = chooseWarship.getValue();
            if (selectedName != null) {
                Destroyer selectedDestroyer = null;
                for (Destroyer destroyer : windowController.destroyers) {
                    if (destroyer.getName().equals(selectedName)) {
                        selectedDestroyer = destroyer;
                        break;
                    }
                }
                if (selectedDestroyer != null) {
                    this.getWarshipImageView().setImage(getDestroyerImage());
                    this.setShadow(getWarshipImageView(), 5, 3,3,0,255,0);

                    this.getNameTF().setVisible(true);
                    this.getNameTF().setText(selectedDestroyer.getName());
                    this.getHpTF().setVisible(true);
                    this.getHpTF().setText(String.valueOf(selectedDestroyer.getHP()));
                    this.getDamageTF().setVisible(true);
                    this.getDamageTF().setText(String.valueOf(selectedDestroyer.getDamage()));
                    this.getCoordinateXTF().setVisible(true);
                    this.getCoordinateXTF().setText(String.valueOf(selectedDestroyer.getCoordinateX()));
                    this.getCoordinateYTF().setVisible(true);
                    this.getCoordinateYTF().setText(String.valueOf(selectedDestroyer.getCoordinateY()));
                }
            }
        });
    }
    public void selectObject(ActionEvent event){
        selectedIndex = chooseWarship.getItems().indexOf(chooseWarship.getValue());
    }
    public void changeObject() {
        Destroyer selectedDestroyer = windowController.destroyers.get(selectedIndex);
        selectedDestroyer.setName(getNameTF().getText());
        selectedDestroyer.setHP(Double.parseDouble(getHpTF().getText()));
        selectedDestroyer.setDamage(Double.parseDouble(getDamageTF().getText()));
        selectedDestroyer.setCoordinateX(Integer.parseInt(getCoordinateXTF().getText()));
        selectedDestroyer.setCoordinateY(Integer.parseInt(getCoordinateYTF().getText()));

        changeObjectVisual(selectedDestroyer.getName(), selectedDestroyer.getHP(), selectedDestroyer.getDamage());
        closeInfoWindow();
    }
    public void changeObjectVisual(String name,double hp, double damage){
        Destroyer o = windowController.destroyers.get(selectedIndex);
        o.getDestroyerLabel().setText(name);
        o.getDestroyerHPbar().setProgress(hp/100);
        o.getDestroyerImageView().setX(o.getCoordinateX());
        o.getDestroyerImageView().setY(o.getCoordinateY());
        o.getDestroyerHPbar().setLayoutX(o.getCoordinateX());
        o.getDestroyerHPbar().setLayoutY(o.getCoordinateY()+85);
        o.getDestroyerLabel().setLayoutX(o.getCoordinateX());
        o.getDestroyerLabel().setLayoutY(o.getCoordinateY()-20);
    }
    public void setStage(Stage stage) {this.stage = stage;}
    private void closeInfoWindow() {stage.close();}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener(getNameTF(), getNameLabel());
        addListener(getHpTF(), getHpBar());
        setMaxLength(getNameTF(), 20);
        setTextFieldFilter(getCoordinateXTF(), 4);
        setTextFieldFilter(getCoordinateYTF(), 4);
        setTextFieldFilter(getHpTF(), 5);
        setTextFieldFilter(getDamageTF(), 5);
    }
}
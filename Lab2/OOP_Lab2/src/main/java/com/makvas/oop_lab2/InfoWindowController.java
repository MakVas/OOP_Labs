package com.makvas.oop_lab2;

import javafx.stage.Stage;

public class InfoWindowController {
    private Stage stage;
    public void pressOK(){closeInfoWindow();}
    public void setStage(Stage stage) {this.stage = stage;}
    private void closeInfoWindow() {stage.close();}
}

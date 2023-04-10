package com.makvas.oop_lab2;

import com.makvas.oop_lab2.warships.Destroyer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;


public class addObjectWindowController implements Initializable {
    private Stage stage;
    private  WindowController windowController;
    public WindowController getWindowController() {return windowController;}
    public void setWindowController(WindowController windowController) {this.windowController = windowController;}
    @FXML
    private TextField nameTF;
    @FXML
    private TextField hpTF;
    @FXML
    private TextField damageTF;
    @FXML
    private TextField coordinateXTF;
    @FXML
    private TextField coordinateYTF;
    @FXML
    private Label nameLabel;
    @FXML
    private ComboBox<String> chooseWarshipType;
    @FXML
    private ImageView warshipImageView;
    @FXML
    private ProgressBar hpBar;
    private final Image destroyerImage = new Image("C:\\Users\\xdjmv\\OneDrive\\Рабочий стол\\ВНТУ\\1й курс\\ООП\\Lab2\\Content\\Destroyer.png");
    public void addObject(){
        try {
            String name = this.nameTF.getText();
            int x = Integer.parseInt(this.coordinateXTF.getText());
            int y = Integer.parseInt(this.coordinateYTF.getText());
            double hp = Double.parseDouble(this.hpTF.getText());
            double damage = Double.parseDouble(this.damageTF.getText());
            Destroyer des = new Destroyer (name, hp, damage, x, y);
            createDestroyerView(des, x, y);
            windowController.addDestroyer(des);
            Main.group.getChildren().addAll(des.getDestroyerImageView(),des.getDestroyerHPbar(), des.getDestroyerLabel());
            closeAddObjectWindow();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setContentText("Please enter a valid number for length and speed.");
            alert.showAndWait();
        }
    }
    public Label getNameLabel() {return nameLabel;}
    public void setNameLabel(Label nameLabel) {this.nameLabel = nameLabel;}
    public ImageView getWarshipImageView() {return warshipImageView;}
    public void setWarshipImageView(ImageView warshipImageView) {this.warshipImageView = warshipImageView;}
    public ProgressBar getHpBar() {return hpBar;}
    public void setHpBar(ProgressBar hpBar) {this.hpBar = hpBar;}
    public Image getDestroyerImage() {return destroyerImage;}
    public TextField getNameTF() {return nameTF;}
    public void setNameTF(TextField nameTF) {this.nameTF = nameTF;}
    public TextField getHpTF() {return hpTF;}
    public void setHpTF(TextField hpTF) {this.hpTF = hpTF;}
    public TextField getDamageTF() {return damageTF;}
    public void setDamageTF(TextField damageTF) {this.damageTF = damageTF;}
    public TextField getCoordinateXTF() {return coordinateXTF;}
    public void setCoordinateXTF(TextField coordinateXTF) {this.coordinateXTF = coordinateXTF;}
    public TextField getCoordinateYTF() {return coordinateYTF;}
    public void setCoordinateYTF(TextField coordinateYTF) {this.coordinateYTF = coordinateYTF;}
    public ComboBox<String> getChooseWarshipType() {return chooseWarshipType;}
    public void setChooseWarshipType(ComboBox<String> chooseWarshipType) {this.chooseWarshipType = chooseWarshipType;}
    protected void addListener(TextField textField, Label label) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            label.setText(newValue);
            label.setStyle("-fx-font-size: 15px;" +
                    "-fx-effect: dropshadow(one-pass-box, #0F0, 5, 1, 0, 0); " +
                    "-fx-text-fill: black");
        });
    }
    protected void addListener(TextField textField, ProgressBar progressBar) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            double value = Double.parseDouble(newValue);
            progressBar.setVisible(true);
            progressBar.setProgress(value/100);
            progressBar.setStyle("-fx-accent: #0F0;");
        });
    }

    protected void addListener(ComboBox<String> comboBox, Image image, ImageView imageView){
        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            imageView.setImage(image);
            setShadow(imageView, 5, 3,3,0,255,0);
        });
    }

    protected void setTextFieldFilter(TextField textField, int maxLength) {
        UnaryOperator<TextFormatter.Change> numericFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([0-9]*\\.?[0-9]+|[0-9]+\\.?[0-9]*)")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> formatter = new TextFormatter<>(change -> {
            String text = change.getControlNewText();
            if (text.length() <= maxLength) {
                change = numericFilter.apply(change);
                return change;
            }
            return null;
        });
        textField.setTextFormatter(formatter);
    }
    protected void setMaxLength(TextField textField, int maxLength) {
        UnaryOperator<TextFormatter.Change> lengthFilter = change -> {
            int newLength = change.getControlNewText().length();
            if (newLength <= maxLength) {
                return change;
            } else {
                return null;
            }
        };
        TextFormatter<String> formatter = new TextFormatter<>(lengthFilter);
        textField.setTextFormatter(formatter);
    }
    public void setStage(Stage stage) {this.stage = stage;}
    private void closeAddObjectWindow() {stage.close();}
    protected void setShadow(ImageView imageView, double radius, double x, double y, int red, int green, int blue){
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(radius);
        dropShadow.setOffsetX(x);
        dropShadow.setOffsetY(y);
        dropShadow.setColor(Color.rgb(red, green, blue));
        imageView.setEffect(dropShadow);
    }
    public void createDestroyerView(Destroyer des, int x, int y) {
        ImageView imageView = new ImageView(des.getDestoyerImage());
        setShadow(imageView, 5, 3,3,0,255,0);

        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitWidth(170);
        imageView.setFitHeight(70);

        Label nameLabel = new Label(des.getName());
        nameLabel.setStyle("-fx-font-size: 15px;" +
                "-fx-effect: dropshadow(one-pass-box, #0F0, 5, 1, 0, 0); " +
                "-fx-text-fill: black");
        nameLabel.setLayoutX(x);
        nameLabel.setLayoutY(y - 20);
        nameLabel.setPrefWidth(170);
        nameLabel.setAlignment(Pos.CENTER);

        ProgressBar hpBar = new ProgressBar();
        hpBar.setLayoutX(x);
        hpBar.setLayoutY(y + 85);
        hpBar.setProgress(des.getHP()/100);
        hpBar.setStyle("-fx-accent: #0F0;");
        hpBar.setPrefWidth(170);

        des.setDestroyerHPbar(hpBar);
        des.setDestroyerLabel(nameLabel);
        des.setDestroyerImageView(imageView);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener(nameTF, nameLabel);
        addListener(hpTF, hpBar);

        addListener(chooseWarshipType,destroyerImage, warshipImageView);
        setMaxLength(nameTF, 20);
        setTextFieldFilter(coordinateXTF, 4);
        setTextFieldFilter(coordinateYTF, 4);
        setTextFieldFilter(hpTF, 3);
        setTextFieldFilter(damageTF, 3);
        chooseWarshipType.getItems().addAll("Destroyer");
    }
}
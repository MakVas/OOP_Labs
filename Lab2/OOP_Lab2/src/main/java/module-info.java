module com.makvas.oop_lab2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.makvas.oop_lab2 to javafx.fxml;
    exports com.makvas.oop_lab2;
    exports com.makvas.oop_lab2.warships;
    opens com.makvas.oop_lab2.warships to javafx.fxml;
}
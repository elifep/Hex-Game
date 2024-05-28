module edu.erciyes.bz214.projectjava {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.erciyes.bz214.projectjava to javafx.fxml;
    exports edu.erciyes.bz214.projectjava;
}
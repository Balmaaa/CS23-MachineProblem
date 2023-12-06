module upm.cs23.grp1.application {
    requires javafx.controls;
    requires javafx.fxml;


    opens upm.cs23.grp1.application to javafx.fxml;
    exports upm.cs23.grp1.application;
}
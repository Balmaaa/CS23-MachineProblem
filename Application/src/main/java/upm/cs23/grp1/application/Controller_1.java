package upm.cs23.grp1.application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller_1
{
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
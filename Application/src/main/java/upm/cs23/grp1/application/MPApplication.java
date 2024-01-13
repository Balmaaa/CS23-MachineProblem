package upm.cs23.grp1.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class MPApplication extends Application
{
    @Override
    public void start(Stage PrimaryStage) throws IOException
    {
        Parent Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main-View.fxml")));
        Scene Scene = new Scene(Root);
        Scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Main-Design.css")).toExternalForm());
        PrimaryStage.getIcons().add(new Image("https://pngimg.com/d/chef_PNG134.png"));
        PrimaryStage.setTitle("Restaurant Inventory System - GRP 5");
        PrimaryStage.setScene(Scene);
        PrimaryStage.setOnCloseRequest(Event -> {
            Event.consume();
            ExitConfirmationPage(PrimaryStage);
        });
        PrimaryStage.setResizable(false);
        PrimaryStage.show();
        InventoryStage();
    }

    public void ExitConfirmationPage(Stage Stage)
    {
        Alert Alert = new Alert(javafx.scene.control.Alert.AlertType.WARNING);
        Alert.setTitle("Exit Confirmation");
        Alert.setHeaderText("You Will Be Exiting the Application");
        Alert.setContentText("Do You Want To Export CSV Before Exiting?");

        ButtonType YesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType NoButton = new ButtonType("No", ButtonBar.ButtonData.NO);
        ButtonType CancelButton = new ButtonType("Cancel Exit", ButtonBar.ButtonData.CANCEL_CLOSE);

        Alert.getButtonTypes().setAll(YesButton, NoButton, CancelButton);

        Optional<ButtonType> Result = Alert.showAndWait();
        if(Result.isPresent())
        {
            if(Result.get().equals(YesButton))
            {
                System.out.println("Program Terminated");
                Stage.close();
            }
            else if(Result.get().equals(NoButton))
            {
                System.exit(0);
            }
            else if(Result.get().equals(CancelButton))
            {
                System.out.println("Exit Operation Cancelled");
            }
        }
    }

    private static Stage InventoryStage = null;

    public static Stage GetInventoryStage()
    {
        return InventoryStage;
    }

    public void InventoryStage()
    {
        InventoryStage = new Stage();
        InventoryStage.getIcons().add(new Image("https://pngimg.com/d/chef_PNG134.png"));
        InventoryStage.setTitle("Restaurant Inventory - BALMACEDA");
        InventoryStage.setAlwaysOnTop(false);
        InventoryStage.setResizable(false);
        InventoryStage.initModality(Modality.NONE);
    }

    public static void main(String[] args)
    {
        launch();
    }
}
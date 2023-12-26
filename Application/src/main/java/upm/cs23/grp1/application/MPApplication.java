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
        PrimaryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/thumb/3/3d/University_of_the_Philippines_Manila_Seal.svg/800px-University_of_the_Philippines_Manila_Seal.svg.png"));
        PrimaryStage.setTitle("Restaurant Inventory System - GRP 5");
        PrimaryStage.setScene(Scene);
        PrimaryStage.setResizable(false);
        PrimaryStage.setOnCloseRequest(Event -> ExitConfirmationPage(PrimaryStage));
        PrimaryStage.show();
        InventoryStage();
    }

    public void ExitConfirmationPage(Stage Stage)
    {
        Alert Alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
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
        InventoryStage.getIcons().add(new Image("https://upload.wikimedia.org/wikipedia/en/thumb/3/3d/University_of_the_Philippines_Manila_Seal.svg/800px-University_of_the_Philippines_Manila_Seal.svg.png"));
        InventoryStage.setTitle("Restaurant Inventory - BALMACEDA");
        InventoryStage.setAlwaysOnTop(true);
        InventoryStage.setResizable(false);
        InventoryStage.initModality(Modality.APPLICATION_MODAL);
    }

    public static void main(String[] args)
    {
        launch();
    }
}
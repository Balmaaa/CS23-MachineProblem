package upm.cs23.grp1.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import static upm.cs23.grp1.application.Controller_1.*;
public class MPApplication extends Application
{
    /**
     * The entry point for the JAVAFX application.
     * @param PrimaryStage
     * @throws IOException
     */
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

    /**
     *Displays an exit confirmation page in the specified JavaFX Stage. This method is designed to prompt the user
     *for confirmation before exiting the application, allowing for graceful termination.
     * @param Stage
     */
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
                exportToCSV();
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

    private void exportToCSV()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null)
        {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
            {
                writer.write("SKU,Item,Weight/Volume,Category,Brand,Quantity,Description");
                writer.newLine();

                for (int i = 0; i < SKUList.size(); i++)
                {
                    writer.write(String.format("%s,%s,%s,%s,%s,%s,%s",
                            SKUList.get(i), ItemList.get(i), WeightVolumeList.get(i),
                            CategoryList.get(i), BrandList.get(i), QuantityList.get(i), DescriptionList.get(i)));
                    writer.newLine();
                }

                writer.flush();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                System.out.println("IOException occurred while exporting data to CSV");
            }
        }
    }

    private static Stage InventoryStage = null;

    /**
     *Initializes and displays the inventory stage for the application. This method is responsible for setting up
     *the user interface components related to inventory management and presenting the inventory stage to the user.
     */
    public void InventoryStage()
    {
        InventoryStage = new Stage();
        InventoryStage.getIcons().add(new Image("https://pngimg.com/d/chef_PNG134.png"));
        InventoryStage.setTitle("Restaurant Inventory - BALMACEDA");
        InventoryStage.setAlwaysOnTop(false);
        InventoryStage.setResizable(false);
        InventoryStage.initModality(Modality.NONE);
    }

    /**
     *It initializes the JavaFX application and starts the JavaFX runtime, invoking the start method to
     *configure and display the initial user interface.
     * @param args
     */
    public static void main(String[] args)
    {
        launch();
    }
}
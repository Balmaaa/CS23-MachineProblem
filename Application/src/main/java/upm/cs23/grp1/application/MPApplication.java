package upm.cs23.grp1.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

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
        PrimaryStage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}
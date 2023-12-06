package upm.cs23.grp1.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;


public class Controller_1
{
    private Stage Stage;
    private Scene Scene;

    //================================================================================================================//
    //                                            MAIN PAGE FUNCTIONS                                                 //
    //================================================================================================================//

    public void MainPage(ActionEvent Event) throws IOException
    {
        Parent Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main-View.fxml")));
        Stage = (Stage)((Node)Event.getSource()).getScene().getWindow();
        Scene = new Scene(Root);
        Stage.setScene(Scene);
        Stage.show();
    }


    //================================================================================================================//
    //                                          ADD ITEM PAGE FUNCTIONS                                               //
    //================================================================================================================//

    public void AddItemPage(ActionEvent Event) throws IOException
    {
        Parent Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddItemPage-View.fxml")));
        Stage = (Stage)((Node)Event.getSource()).getScene().getWindow();
        Scene = new Scene(Root);
        Stage.setScene(Scene);
        Stage.show();
    }


    //================================================================================================================//
    //                                         DELETE ITEM PAGE FUNCTIONS                                             //
    //================================================================================================================//

    public void DeleteItemPage(ActionEvent Event) throws IOException
    {
        Parent Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DeleteItemPage-View.fxml")));
        Stage = (Stage)((Node)Event.getSource()).getScene().getWindow();
        Scene = new Scene(Root);
        Stage.setScene(Scene);
        Stage.show();
    }

}
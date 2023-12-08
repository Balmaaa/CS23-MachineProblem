package upm.cs23.grp1.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;


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

    @FXML
    private Text AddedItemOutput;

    @FXML
    private Text GeneratedSKU;

    @FXML
    private TextField ItemName;

    @FXML
    private TextField Category;

    @FXML
    private TextField Brand;

    @FXML
    private TextField WeightVolume;

    @FXML
    private TextField Quantity;

    @FXML
    private TextArea AddDescription;


    @FXML
    private void AddItemToDisplay()
    {
        String ItemNameText = ItemName.getText();
        String CategoryText = Category.getText();
        String BrandText = Brand.getText();
        String WeightVolumeText = WeightVolume.getText();
        int QuantityNumber = Integer.parseInt(Quantity.getText());
        int SKUItemVal = (int) (Math.random() * (9999));
        String SKUItem = ItemNameText.substring(0, Math.min(ItemNameText.length(), 3)).toUpperCase();

        AddedItemOutput.setText(ItemNameText + " | " + CategoryText + " | " + BrandText + " | " + WeightVolumeText + " | " + QuantityNumber);
        GeneratedSKU.setText(CategoryText + "/" + SKUItem + "-" + SKUItemVal);
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
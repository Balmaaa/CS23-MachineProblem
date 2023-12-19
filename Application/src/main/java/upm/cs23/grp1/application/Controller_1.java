package upm.cs23.grp1.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;
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
        MenuItem MenuItem = (MenuItem) Event.getSource();
        String MenuItemText = MenuItem.getText();

        if("Main Page".equals(MenuItemText))
        {
            Parent Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main-View.fxml")));
            Stage = (Stage) MenuItem.getParentPopup().getOwnerWindow();
            Scene = new Scene(Root);
            Stage.setScene(Scene);
            Stage.show();
        }
    }

    //================================================================================================================//
    //                                          ADD ITEM PAGE FUNCTIONS                                               //
    //================================================================================================================//

    public void AddItemPage(ActionEvent Event) throws IOException
    {
        MenuItem MenuItem = (MenuItem) Event.getSource();
        String MenuItemText = MenuItem.getText();

        if("Add Item Page".equals(MenuItemText))
        {
            Parent Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddItemPage-View.fxml")));
            Stage = (Stage) MenuItem.getParentPopup().getOwnerWindow();
            Scene = new Scene(Root);
            Stage.setScene(Scene);
            Stage.show();
        }
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

    public static boolean Vowels(char C)
    {
        C = Character.toLowerCase(C);
        return C == 'a' || C == 'e' || C == 'i' || C == 'o' || C == 'u';
    }

    public void ClearInputFields()
    {
        ItemName.clear();
        Category.clear();
        Brand.clear();
        WeightVolume.clear();
        Quantity.clear();
        AddDescription.clear();
    }

    @FXML
    private void AddItemToDisplay()
    {
        String ItemNameText = ItemName.getText();
        String CategoryText = Category.getText();
        String BrandText = Brand.getText();
        String WeightVolumeText = WeightVolume.getText();
        String DescriptionText = AddDescription.getText();
        int QuantityNumber = Integer.parseInt(Quantity.getText());
        int SKUItemVal = (int) (Math.random() * (9999));
        String FixedFourDigitSKU = String.format("%04d", SKUItemVal);
        String SKUItem = ItemNameText.substring(0, Math.min(ItemNameText.length(), 3)).toUpperCase();

        StringBuilder SKUCategory = new StringBuilder();
        String SKUFix = CategoryText.toLowerCase();
        int ConsonantCount = 0;
        for(char C : SKUFix.toCharArray())
        {
            if(ConsonantCount == 3)
            {
                break;
            }

            if((C >= 'a' && C <= 'z') && !Vowels(C))
            {
                SKUCategory.append(C);
                ConsonantCount++;
            }
        }

        AddedItemOutput.setText("Item: " + ItemNameText + " | " + CategoryText + " | " + BrandText + " | " + WeightVolumeText + " | " + QuantityNumber);
        GeneratedSKU.setText("SKU: " + (SKUCategory.toString().toUpperCase()) + "/" + SKUItem + "-" + FixedFourDigitSKU);

        String SKUText = SKUCategory.toString().toUpperCase() + "/" + SKUItem + "-" + FixedFourDigitSKU;
        AddSKU(SKUText);
        AddItem(ItemNameText);
        AddWeight(WeightVolumeText);
        AddCategory(CategoryText);
        AddBrand(BrandText);
        AddQuantity(String.valueOf(QuantityNumber));
        AddDescription(DescriptionText);

        ClearInputFields();
    }

    //================================================================================================================//
    //                                         DELETE ITEM PAGE FUNCTIONS                                             //
    //================================================================================================================//

    public void DeleteItemPage(ActionEvent Event) throws IOException
    {
        MenuItem MenuItem = (MenuItem) Event.getSource();
        String MenuItemText = MenuItem.getText();

        if("Delete Item Page".equals(MenuItemText))
        {
            Parent Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DeleteItemPage-View.fxml")));
            Stage = (Stage) MenuItem.getParentPopup().getOwnerWindow();
            Scene = new Scene(Root);
            Stage.setScene(Scene);
            Stage.show();
        }
    }

    //================================================================================================================//
    //                                           INVENTORY PAGE FUNCTIONS                                             //
    //================================================================================================================//

    private final ArrayList<String> SKUList = new ArrayList<>();
    private final ArrayList<String> ItemList = new ArrayList<>();
    private final ArrayList<String> WeightVolumeList = new ArrayList<>();
    private final ArrayList<String> CategoryList = new ArrayList<>();
    private final ArrayList<String> BrandList = new ArrayList<>();
    private final ArrayList<String> QuantityList = new ArrayList<>();
    private final ArrayList<String> DescriptionList = new ArrayList<>();
    public void AddSKU(String SKU_NewData)
    {
        SKUList.add(SKU_NewData);
    }
    public void AddItem(String Item_NewData)
    {
        ItemList.add(Item_NewData);
    }
    public void AddWeight(String Weight_NewData)
    {
        WeightVolumeList.add(Weight_NewData);
    }
    public void AddCategory(String Category_NewData)
    {
        CategoryList.add(Category_NewData);
    }
    public void AddBrand(String Brand_NewData)
    {
        BrandList.add(Brand_NewData);
    }
    public void AddQuantity(String Quantity_NewData)
    {
        QuantityList.add(Quantity_NewData);
    }
    public void AddDescription(String Description_NewData)
    {
        DescriptionList.add(Description_NewData);
    }

    public void InventoryPage(ActionEvent Event) throws IOException
    {
        MenuItem MenuItem = (MenuItem) Event.getSource();
        String MenuItemText = MenuItem.getText();

        if("View Inventory".equals(MenuItemText))
        {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("InventoryPage-View.fxml"));
            Parent Root = Loader.load();
            MPApplication.GetInventoryStage().setScene(new Scene(Root));


            InventoryTableController InventoryController = Loader.getController();
            InventoryController.DataInitialize(SKUList, ItemList, WeightVolumeList, CategoryList, BrandList, QuantityList, DescriptionList);
            MPApplication.GetInventoryStage().show();
        }
    }
}
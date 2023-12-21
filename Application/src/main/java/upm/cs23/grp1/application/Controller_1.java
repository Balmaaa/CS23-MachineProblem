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
    //                                            PAGE TRANSITION FUNCTIONS                                           //
    //================================================================================================================//

    /**
     * <p>MainPage() == Allows the code to transition from any page to the Main Page</p>
     * <p>AddItemPage() == Allows the code to transition from any page to the Add Item Page</p>
     * <p>DeleteItemPage() == Allows the code to transition from any page to the Delete Item Page</p>
     * <p>InventoryPage() == Allows the code to transition from any page to the Inventory Page</p>
     * <p></p>
     * <p>Note: All of the Page Transitions would use the Menu Bar for Quicker Access</p>
     * @author Gabriel Balmaceda
     */

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

    public void AddItemPage(ActionEvent Event) throws IOException
    {
        MenuItem MenuItem = (MenuItem) Event.getSource();
        String MenuItemText = MenuItem.getText();

        if("Add Item Page".equals(MenuItemText))
        {
            Parent Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddItemPage-View.fxml")));
            Root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("AddItemPage-Design.css")).toExternalForm());
            Stage = (Stage) MenuItem.getParentPopup().getOwnerWindow();
            Scene = new Scene(Root);
            Stage.setScene(Scene);
            Stage.show();
        }
    }

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

    //================================================================================================================//
    //                                          ADD ITEM PAGE FUNCTIONS                                               //
    //================================================================================================================//

    /**
     * <p>The Add Item Page consists of multiple parts. The first notable part would be the initialization of FXML
     * elements, which include: Text, TextField, TextArea, Buttons, and etc. These elements are interconnected to one
     * another, provided that the system collect the data provided to these fields.</p>
     * <p>The ClearInputFields() Method is able to clear all the previous inputs made by the user within all text fields.
     * This allows a quicker yet convenient way of continuously adding items to the inventory without having to manually
     * remove the previous inputs one-by-one.</p>
     * <p>The AddItemToDisplay() Method is used when the user has completed filling up all the required information from
     * the text fields and text areas. Upon pressing the button, the code would begin by acquiring the text from the said
     * fields and converting such to a string. Afterwards, it will gather the necessary information:</p>
     * <p>(First Three Consonants of the Category/First Three Letters of the Item Name-Randomized Numbers Ranging from 0000-9999)</p>
     * <p>When All the Information is arranged and packed into one whole string, it will then be displayed in the interface.
     * To add, the information gathered is simultaneously added to the InventoryTable in the Inventory Page</p>
     * @author Gabriel Balmaceda
     */

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
}
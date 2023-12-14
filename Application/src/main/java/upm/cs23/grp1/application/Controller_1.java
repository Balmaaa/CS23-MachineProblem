package upm.cs23.grp1.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
        this.SKUList.add(SKU_NewData);
    }
    public void AddItem(String Item_NewData)
    {
        this.ItemList.add(Item_NewData);
    }
    public void AddWeight(String Weight_NewData)
    {
        this.WeightVolumeList.add(Weight_NewData);
    }
    public void AddCategory(String Category_NewData)
    {
        this.CategoryList.add(Category_NewData);
    }
    public void AddBrand(String Brand_NewData)
    {
        this.BrandList.add(Brand_NewData);
    }
    public void AddQuantity(String Quantity_NewData)
    {
        this.QuantityList.add(Quantity_NewData);
    }
    public void AddDescription(String Description_NewData)
    {
        this.DescriptionList.add(Description_NewData);
    }

    @FXML
    private TableView<InventoryData> InventoryTable;

    @FXML
    private TableColumn<InventoryData, String> SKUColumn;

    @FXML
    private TableColumn<InventoryData, String> ItemColumn;

    @FXML
    private TableColumn<InventoryData, String> WeightVolumeColumn;

    @FXML
    private TableColumn<InventoryData, String> CategoryColumn;

    @FXML
    private TableColumn<InventoryData, String> BrandColumn;

    @FXML
    private TableColumn<InventoryData, String> QuantityColumn;

    @FXML
    private TableColumn<InventoryData, String> DescriptionColumn;

    public void DataInitialize(ArrayList<String> SKUList, ArrayList<String> ItemList, ArrayList<String> WeightVolumeList, ArrayList<String> CategoryList, ArrayList<String> BrandList, ArrayList<String> QuantityList, ArrayList<String> DescriptionList)
    {
        ObservableList<InventoryData> Data = FXCollections.observableArrayList();
        int minLength = Math.min(Math.min(Math.min(Math.min(Math.min(SKUList.size(), ItemList.size()), WeightVolumeList.size()), CategoryList.size()), BrandList.size()), QuantityList.size());

        for(int i = 0; i < minLength; i++)
        {
            Data.add(new InventoryData(SKUList.get(i), ItemList.get(i), WeightVolumeList.get(i), CategoryList.get(i), BrandList.get(i), QuantityList.get(i), DescriptionList.get(i)));
        }

        InventoryTable.setItems(Data);
        SKUColumn.setCellValueFactory(new PropertyValueFactory<>("SKUData"));
        ItemColumn.setCellValueFactory(new PropertyValueFactory<>("ItemData"));
        WeightVolumeColumn.setCellValueFactory(new PropertyValueFactory<>("WeightData"));
        CategoryColumn.setCellValueFactory(new PropertyValueFactory<>("CategoryData"));
        BrandColumn.setCellValueFactory(new PropertyValueFactory<>("BrandData"));
        QuantityColumn.setCellValueFactory(new PropertyValueFactory<>("QuantityData"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("DescriptionData"));
    }

    public void InventoryPage(ActionEvent Event) throws IOException
    {
        MenuItem MenuItem = (MenuItem) Event.getSource();
        String MenuItemText = MenuItem.getText();

        if("View Inventory".equals(MenuItemText))
        {
            Parent Root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InventoryPage-View.fxml")));
            Stage = (Stage) MenuItem.getParentPopup().getOwnerWindow();
            Scene = new Scene(Root);
            Stage.setScene(Scene);
            Stage.show();
        }
    }
}
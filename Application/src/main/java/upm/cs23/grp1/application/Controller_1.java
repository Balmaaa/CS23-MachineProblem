package upm.cs23.grp1.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;


public class Controller_1
{
    private Stage Stage;
    private Scene Scene;
    private static InventoryTableController inventoryController;

    //================================================================================================================//
    //                                            PAGE TRANSITION FUNCTIONS                                           //
    //================================================================================================================//

    /**
     * <p>MainPage() == Transition from any page to the Main Page</p>
     * <p>AddItemPage() == Transition from any page to the Add Item Page</p>
     * <p>DeleteItemPage() == Transition from any page to the Delete Item Page</p>
     * <p>InventoryPage() == Transition from any page to the Inventory Page</p>
     * <p></p>
     * <p>Note: All of the Page Transitions would use the Menu Bar for Quicker Access</p>
     * <p></p>
     * @author Gabriel Balmaceda
     * @author Leonna De Vera
     * @author Louis Lumain
     * @author Emiel Magante
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

    public void InventoryPage(ActionEvent Event) throws IOException {
        MenuItem MenuItem = (MenuItem) Event.getSource();
        String MenuItemText = MenuItem.getText();

        if ("View Inventory".equals(MenuItemText)) {
            try {
                FXMLLoader Loader = new FXMLLoader(getClass().getResource("InventoryPage-View.fxml"));
                Parent Root = Loader.load();
                Root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("InventoryPage-Design.css")).toExternalForm());

                MPApplication.GetInventoryStage().setScene(new Scene(Root));

                inventoryController = Loader.getController();
                inventoryController.DataInitialize(SKUList, ItemList, WeightVolumeList, CategoryList, BrandList, QuantityList, DescriptionList);

                MPApplication.GetInventoryStage().show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void ExitConfirmationPage(ActionEvent Event)
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
                Stage Stage = (Stage) ((Node) Event.getSource()).getScene().getWindow();
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
     * <p>The AddItemToDisplay() Method is used when the user has accomplished filling up all the required information from
     * the text fields and text areas. Upon pressing the button, the code would begin by acquiring the text from the said
     * fields and converting such to a string. Afterwards, it will gather the necessary information:</p>
     * <p>(First Three Consonants of the Category/First Three Letters of the Item Name-Randomized Numbers Ranging from 0000-9999)</p>
     * <p>When All the Information is arranged and packed into one whole string, it will then be displayed in the interface.
     * To add, the information gathered is simultaneously added to the InventoryTable in the Inventory Page</p>
     */

    @FXML
    private Text ConsumeItemStock;

    @FXML
    private Text UpdateItemStock;

    @FXML
    private Text ErrorMessage;

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
   private TextField SKUStock;

   @FXML
   private TextField SKUStockQuant;

   @FXML
   private TextArea AddDescription;

   @FXML
   private Text deletionMessageText;

    public static boolean Vowels(char C)
    {
        C = Character.toLowerCase(C);
        return C == 'a' || C == 'e' || C == 'i' || C == 'o' || C == 'u';
    }

    public void ClearInputFields() {
        if (ItemName != null) {
            ItemName.clear();
        }
        if (Category != null) {
            Category.clear();
        }
        if (Brand != null) {
            Brand.clear();
        }
        if (WeightVolume != null) {
            WeightVolume.clear();
        }
        if (Quantity != null) {
            Quantity.clear();
        }
        if (AddDescription != null) {
            AddDescription.clear();
        }
        if (SKUStock != null) {
            SKUStock.clear();
        }
        if (SKUStockQuant != null) {
            SKUStockQuant.clear();
        }
    }


    @FXML
    private void AddItemToDisplay()
    {
        if(ItemName.getText().isEmpty() || Category.getText().isEmpty() || Brand.getText().isEmpty() ||
                WeightVolume.getText().isEmpty() || Quantity.getText().isEmpty() || AddDescription.getText().isEmpty())
        {
            ErrorMessage.setText("Fill In All Required Information");
            AddedItemOutput.setText("");
            GeneratedSKU.setText("");
            ClearInputFields();
        }
        else
        {
            String ItemNameText = ItemName.getText();
            String CategoryText = Category.getText();
            String BrandText = Brand.getText();
            String WeightVolumeText = WeightVolume.getText();
            String DescriptionText = AddDescription.getText();

            try
            {
                int QuantityNumber = Integer.parseInt(Quantity.getText());
                int SKUItemVal = (int) (Math.random() * (9999));
                String FixedFourDigitSKU = String.format("%04d", SKUItemVal);

                StringBuilder SKUCategory = new StringBuilder();
                String SKUFix = CategoryText.toLowerCase().replace(" ", "");
                int ConsonantCount = 0;
                for (char C : SKUFix.toCharArray())
                {
                    if (ConsonantCount == 3)
                    {
                        break;
                    }
                    if (!Vowels(C))
                    {
                        SKUCategory.append(C);
                        ConsonantCount++;
                    }
                }

                StringBuilder SKUItem = new StringBuilder();
                String SKUFixItem = ItemNameText.toLowerCase().replace(" ", "");
                int ConsonantCount_Item = 0;
                for (char C : SKUFixItem.toCharArray())
                {
                    if (ConsonantCount_Item == 3)
                    {
                        break;
                    }
                    if (!Vowels(C))
                    {
                        SKUItem.append(C);
                        ConsonantCount_Item++;
                    }
                }

                ErrorMessage.setText("");
                AddedItemOutput.setText("Item: " + ItemNameText + " | " + CategoryText + " | " + BrandText + " | " + WeightVolumeText + " | " + QuantityNumber);
                GeneratedSKU.setText("SKU: " + (SKUCategory.toString().toUpperCase()) + "/" + (SKUItem.toString().toUpperCase()) + "-" + FixedFourDigitSKU);

                String SKUText = (SKUCategory.toString().toUpperCase()) + "/" + (SKUItem.toString().toUpperCase()) + "-" + FixedFourDigitSKU;
                AddSKU(SKUText);
                AddItem(ItemNameText);
                AddWeight(WeightVolumeText);
                AddCategory(CategoryText);
                AddBrand(BrandText);
                AddQuantity(String.valueOf(QuantityNumber));
                AddDescription(DescriptionText);
            }
            catch(NumberFormatException E)
            {
                ErrorMessage.setText("Quantity Must Be A Number");
                AddedItemOutput.setText("");
                GeneratedSKU.setText("");
                ClearInputFields();
            }
            updateInventoryTable();
            ClearInputFields();
        }
    }

    //================================================================================================================//
    //                                          DELETE ITEM PAGE FUNCTIONS                                               //
    //================================================================================================================//
    @FXML
    private void DeleteItem(){
        String itemNameText = ItemName.getText().trim().toUpperCase();

        if (itemNameText.isEmpty()) {
            deletionMessageText.setText("Please enter valid Item Name or SKU");
            return;
        }

        int indexToRemove = -1;
        for (int i = 0; i < ItemList.size(); i++) {
            if (ItemList.get(i).equalsIgnoreCase(itemNameText) || SKUList.get(i).equalsIgnoreCase(itemNameText)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove != -1) {
            SKUList.remove(indexToRemove);
            ItemList.remove(indexToRemove);
            WeightVolumeList.remove(indexToRemove);
            CategoryList.remove(indexToRemove);
            BrandList.remove(indexToRemove);
            QuantityList.remove(indexToRemove);
            DescriptionList.remove(indexToRemove);

            deletionMessageText.setText("Item '" + itemNameText + "' successfully deleted.");
            updateInventoryTable();
        } else {
            deletionMessageText.setText("The entered item was not found in this inventory!");
        }
        ClearInputFields();
    }

    private void updateInventoryTable() {
        ObservableList<InventoryData> data = FXCollections.observableArrayList();

        for (int i = 0; i < SKUList.size(); i++) {
            data.add(new InventoryData(SKUList.get(i), ItemList.get(i), WeightVolumeList.get(i), CategoryList.get(i), BrandList.get(i), QuantityList.get(i), DescriptionList.get(i)));
        }

        if (inventoryController != null) {
            inventoryController.updateTable(data);
        }
    }






    //================================================================================================================//
    //                                           INVENTORY PAGE FUNCTIONS                                             //
    //================================================================================================================//

    public void ConsumeItemStock() {
        String skuInput = SKUStock.getText().replaceAll("[\\/-]", ""); // Remove '/' and '-' from SKU
        String consumeQuantityInput = SKUStockQuant.getText();

        if (skuInput.isEmpty() || consumeQuantityInput.isEmpty()) {
            System.out.println("Please enter both SKU and quantity.");
            return;
        }

        int index = findSKUIndex(skuInput);
        if (index != -1) {
            try {
                int consumeQuantityValue = Integer.parseInt(consumeQuantityInput);
                int currentQuantity = Integer.parseInt(QuantityList.get(index));

                if (currentQuantity >= consumeQuantityValue) {
                    QuantityList.set(index, String.valueOf(currentQuantity - consumeQuantityValue));
                    ConsumeItemStock.setText(consumeQuantityValue + " units of SKU " + SKUList.get(index) + " consumed.");
                } else {
                    ConsumeItemStock.setText("Not enough stock for SKU " + SKUList.get(index));
                }
            } catch (NumberFormatException e) {
                ConsumeItemStock.setText("Invalid quantity format.");
            }
        } else {
            ConsumeItemStock.setText("Item with SKU " + skuInput + " not found.");
        }
        updateInventoryTable();
        ClearInputFields();
    }

    public void UpdateItemStock() {
        String skuInput = SKUStock.getText().replaceAll("[\\/-]", ""); // Remove '/' and '-' from SKU
        String newQuantityInput = SKUStockQuant.getText();

        if (skuInput.isEmpty() || newQuantityInput.isEmpty()) {
            UpdateItemStock.setText("Please enter both SKU and new quantity.");
            return;
        }

        int index = findSKUIndex(skuInput);
        if (index != -1) {
            try {
                int currentQuantity = Integer.parseInt(QuantityList.get(index));
                int newQuantityValue = Integer.parseInt(newQuantityInput);

                QuantityList.set(index, String.valueOf(currentQuantity + newQuantityValue));

                UpdateItemStock.setText(newQuantityValue + " units added to SKU " + SKUList.get(index) + ". New stock: " + QuantityList.get(index));
            } catch (NumberFormatException e) {
                UpdateItemStock.setText("Invalid quantity format.");
            }
        } else {
            UpdateItemStock.setText("Item with SKU " + skuInput + " not found.");
        }
        updateInventoryTable();
        ClearInputFields();
    }

    // Helper method to find SKU index with or without '/' and '-'
    private int findSKUIndex(String skuInput) {
        for (int i = 0; i < SKUList.size(); i++) {
            String currentSKU = SKUList.get(i).replaceAll("[\\/-]", "");
            if (currentSKU.equalsIgnoreCase(skuInput)) {
                return i;
            }
        }
        return -1;
    }

    private static final ArrayList<String> SKUList = new ArrayList<>();
    private static final ArrayList<String> ItemList = new ArrayList<>();
    private static final ArrayList<String> WeightVolumeList = new ArrayList<>();
    private static final ArrayList<String> CategoryList = new ArrayList<>();
    private static final ArrayList<String> BrandList = new ArrayList<>();
    private static final ArrayList<String> QuantityList = new ArrayList<>();
    private static final ArrayList<String> DescriptionList = new ArrayList<>();

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
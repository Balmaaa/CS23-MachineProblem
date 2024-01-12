package upm.cs23.grp1.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class InventoryTableController
{
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
        int MinLength = Math.min(Math.min(Math.min(Math.min(Math.min(SKUList.size(), ItemList.size()), WeightVolumeList.size()), CategoryList.size()), BrandList.size()), QuantityList.size());
        for(int i = 0; i < MinLength; i++)
        {
            Data.add(new InventoryData(SKUList.get(i), ItemList.get(i), WeightVolumeList.get(i), CategoryList.get(i), BrandList.get(i), QuantityList.get(i), DescriptionList.get(i)));
        }

        InventoryTable.setItems(Data);
        SKUColumn.setCellValueFactory(CellData -> CellData.getValue().SKUProperty());
        ItemColumn.setCellValueFactory(CellData -> CellData.getValue().ItemNameProperty());
        WeightVolumeColumn.setCellValueFactory(CellData -> CellData.getValue().WeightVolumeProperty());
        CategoryColumn.setCellValueFactory(CellData -> CellData.getValue().CategoryProperty());
        BrandColumn.setCellValueFactory(CellData -> CellData.getValue().BrandProperty());
        QuantityColumn.setCellValueFactory(CellData -> CellData.getValue().QuantityProperty());
        DescriptionColumn.setCellValueFactory(CellData -> CellData.getValue().DescriptionProperty());
    }

    public void exportToCSV(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(InventoryTable.getScene().getWindow());

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                // Write CSV header
                writer.write("SKU,Item,Weight/Volume,Category,Brand,Quantity,Description");
                writer.newLine();

                // Write data rows
                for (InventoryData item : InventoryTable.getItems()) {
                    writer.write(String.format("%s,%s,%s,%s,%s,%s,%s",
                            item.getSKU(), item.getItemName(), item.getWeightVolume(),
                            item.getCategory(), item.getBrand(), item.getQuantity(), item.getDescription()));
                    writer.newLine();
                }

                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("IOException occured");
                // Handle the exception appropriately, e.g., show an alert
            }
        }
    }
}
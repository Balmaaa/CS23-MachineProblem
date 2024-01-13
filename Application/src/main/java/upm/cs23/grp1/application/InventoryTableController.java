package upm.cs23.grp1.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
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

    public void updateTable(ObservableList<InventoryData> newData) {
        InventoryTable.setItems(newData);
    }

    public TableView<InventoryData> getInventoryTable() {
        return InventoryTable;
    }

    public void exportToCSV(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(InventoryTable.getScene().getWindow());

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
            {
                writer.write("SKU,Item,Weight/Volume,Category,Brand,Quantity,Description");
                writer.newLine();

                for (InventoryData item : InventoryTable.getItems())
                {
                    writer.write(String.format("%s,%s,%s,%s,%s,%s,%s",
                            item.getSKU(), item.getItemName(), item.getWeightVolume(),
                            item.getCategory(), item.getBrand(), item.getQuantity(), item.getDescription()));
                    writer.newLine();
                }
                writer.flush();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                System.out.println("IOException occured");
            }
        }
    }

    public void importToCSV(ActionEvent actionEvent)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null)
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(file)))
            {
                String line;
                reader.readLine();

                ObservableList<InventoryData> importedData = FXCollections.observableArrayList();

                while ((line = reader.readLine()) != null)
                {
                    String[] values = line.split(",");
                    if (values.length == 7)
                    {
                        InventoryData item = new InventoryData(values[0], values[1], values[2], values[3], values[4], values[5], values[6]);
                        importedData.add(item);
                    }
                }

                InventoryTable.getItems().addAll(importedData);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                System.out.println("IOException occurred");
            }
        }
    }
}
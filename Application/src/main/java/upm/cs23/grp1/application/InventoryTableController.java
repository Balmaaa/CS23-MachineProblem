package upm.cs23.grp1.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

    public TableView<InventoryData> getInventoryTable() {
        return InventoryTable;
    }
}
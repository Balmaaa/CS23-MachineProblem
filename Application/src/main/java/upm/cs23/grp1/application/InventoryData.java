package upm.cs23.grp1.application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InventoryData
{
    private final StringProperty SKU;
    private final StringProperty ItemName;
    private final StringProperty WeightVolume;
    private final StringProperty Category;
    private final StringProperty Brand;
    private final StringProperty Quantity;
    private final StringProperty Description;

    public InventoryData(String SKU, String ItemName, String WeightVolume, String Category, String Brand, String Quantity, String Description) {
        this.SKU = new SimpleStringProperty(SKU);
        this.ItemName = new SimpleStringProperty(ItemName);
        this.WeightVolume = new SimpleStringProperty(WeightVolume);
        this.Category = new SimpleStringProperty(Category);
        this.Brand = new SimpleStringProperty(Brand);
        this.Quantity = new SimpleStringProperty(Quantity);
        this.Description = new SimpleStringProperty(Description);
    }

    public StringProperty SKUProperty() {return SKU;}
    public StringProperty ItemNameProperty() {return ItemName;}
    public StringProperty WeightVolumeProperty() {return WeightVolume;}
    public StringProperty CategoryProperty() {return Category;}
    public StringProperty BrandProperty() {return Brand;}
    public StringProperty QuantityProperty() {return Quantity;}
    public StringProperty DescriptionProperty() {return Description;}

}

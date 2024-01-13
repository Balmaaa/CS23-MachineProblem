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

    /**
     *
     * @param SKU
     * @param ItemName
     * @param WeightVolume
     * @param Category
     * @param Brand
     * @param Quantity
     * @param Description
     */
    public InventoryData(String SKU, String ItemName, String WeightVolume, String Category, String Brand, String Quantity, String Description)
    {
        this.SKU = new SimpleStringProperty(SKU);
        this.ItemName = new SimpleStringProperty(ItemName);
        this.WeightVolume = new SimpleStringProperty(WeightVolume);
        this.Category = new SimpleStringProperty(Category);
        this.Brand = new SimpleStringProperty(Brand);
        this.Quantity = new SimpleStringProperty(Quantity);
        this.Description = new SimpleStringProperty(Description);
    }

    /**
     *
     * @return
     */
    public StringProperty SKUProperty() {return SKU;}

    /**
     *
     * @return
     */
    public StringProperty ItemNameProperty() {return ItemName;}

    /**
     *
     * @return
     */
    public StringProperty WeightVolumeProperty() {return WeightVolume;}

    /**
     *
     * @return
     */
    public StringProperty CategoryProperty() {return Category;}

    /**
     *
     * @return
     */
    public StringProperty BrandProperty() {return Brand;}

    /**
     *
     * @return
     */
    public StringProperty QuantityProperty() {return Quantity;}

    /**
     *
     * @return
     */
    public StringProperty DescriptionProperty() {return Description;}

    /**
     *
     * @return
     */
    public String getSKU() {return SKU.get();}

    /**
     *
     * @return
     */
    public String getItemName() {return ItemName.get();}

    /**
     *
     * @return
     */
    public String getWeightVolume() {return WeightVolume.get();}

    /**
     *
     * @return
     */
    public String getCategory() {return Category.get();}

    /**
     *
     * @return
     */
    public String getBrand() {return Brand.get();}

    /**
     *
     * @return
     */
    public String getQuantity() {return Quantity.get();}

    /**
     *
     * @return
     */
    public String getDescription() {return Description.get();}
}
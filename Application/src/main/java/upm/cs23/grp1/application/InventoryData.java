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
     *This constructor initializes an
     *InventoryData object with the provided values for SKU, item name, weight/volume, category, brand,
     *quantity, and description.
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
     *Represents the SKU (Stock Keeping Unit) of an item using JavaFX's StringProperty
     * @return
     */
    public StringProperty SKUProperty() {return SKU;}

    /**
     *Holds the name of the item as a StringProperty in a JavaFX application.
     * @return
     */
    public StringProperty ItemNameProperty() {return ItemName;}

    /**
     *Manages the weight or volume information for an item through a StringProperty.
     * @return
     */
    public StringProperty WeightVolumeProperty() {return WeightVolume;}

    /**
     *Represents the category of an item using JavaFX's StringProperty.
     * @return
     */
    public StringProperty CategoryProperty() {return Category;}

    /**
     *Stores the brand information of an item using JavaFX's StringProperty.
     * @return
     */
    public StringProperty BrandProperty() {return Brand;}

    /**
     *Manages the quantity information of an item with the help of a StringProperty.
     * @return
     */
    public StringProperty QuantityProperty() {return Quantity;}

    /**
     *Holds the description of an item as a StringProperty in a JavaFX application.
     * @return
     */
    public StringProperty DescriptionProperty() {return Description;}

    /**
     *Getter method for accessing the SKU StringProperty in the InventoryData class.
     * @return
     */
    public String getSKU() {return SKU.get();}

    /**
     *Getter method for accessing the ItemName StringProperty in the InventoryData class.
     * @return
     */
    public String getItemName() {return ItemName.get();}

    /**
     *Getter method for accessing the WeightVolume StringProperty in the InventoryData class.
     * @return
     */
    public String getWeightVolume() {return WeightVolume.get();}

    /**
     *Getter method for accessing the Category StringProperty in the InventoryData class.
     * @return
     */
    public String getCategory() {return Category.get();}

    /**
     *Getter method for accessing the Brand StringProperty in the InventoryData class.
     * @return
     */
    public String getBrand() {return Brand.get();}

    /**
     *Getter method for accessing the Quantity StringProperty in the InventoryData class.
     * @return
     */
    public String getQuantity() {return Quantity.get();}

    /**
     *Getter method for accessing the Description StringProperty in the InventoryData class.
     * @return
     */
    public String getDescription() {return Description.get();}
}
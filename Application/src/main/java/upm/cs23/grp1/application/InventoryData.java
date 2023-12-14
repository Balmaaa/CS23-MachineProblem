package upm.cs23.grp1.application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InventoryData
{
    private final StringProperty SKUData;
    private final StringProperty ItemData;
    private final StringProperty WeightData;
    private final StringProperty CategoryData;
    private final StringProperty BrandData;
    private final StringProperty QuantityData;
    private final StringProperty DescriptionData;

    public String getSKUData()
    {
        return SKUData.get();
    }

    public StringProperty SKUDataProperty()
    {
        return SKUData;
    }

    public String getItemData()
    {
        return ItemData.get();
    }

    public StringProperty ItemDataProperty()
    {
        return ItemData;
    }

    public String getWeightData()
    {
        return WeightData.get();
    }

    public StringProperty WeightDataProperty()
    {
        return WeightData;
    }

    public String getCategoryData()
    {
        return CategoryData.get();
    }

    public StringProperty CategoryDataProperty()
    {
        return CategoryData;
    }

    public String getBrandData()
    {
        return BrandData.get();
    }

    public StringProperty BrandDataProperty()
    {
        return BrandData;
    }

    public String getQuantityData()
    {
        return QuantityData.get();
    }

    public StringProperty QuantityDataProperty()
    {
        return QuantityData;
    }

    public String getDescriptionData()
    {
        return DescriptionData.get();
    }

    public StringProperty DescriptionDataProperty()
    {
        return DescriptionData;
    }

    public InventoryData(String SKUData, String ItemData, String WeightData, String CategoryData, String BrandData, String QuantityData, String DescriptionData)
    {
        this.SKUData = new SimpleStringProperty(SKUData);
        this.ItemData = new SimpleStringProperty(ItemData);
        this.WeightData = new SimpleStringProperty(WeightData);
        this.CategoryData = new SimpleStringProperty(CategoryData);
        this.BrandData = new SimpleStringProperty(BrandData);
        this.QuantityData = new SimpleStringProperty(QuantityData);
        this.DescriptionData = new SimpleStringProperty(DescriptionData);
    }
}

package abpl.billingapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemList_ {

    @SerializedName("item_name")
    @Expose
    private String itemName;
    @SerializedName("item_rate")
    @Expose
    private String itemRate;
    @SerializedName("item_gst")
    @Expose
    private String itemGst;
    @SerializedName("item_qty")
    @Expose
    private String itemQty;
    @SerializedName("item_bill_rate")
    @Expose
    private String itemBillRate;
    @SerializedName("item_id")
    @Expose
    private Integer itemId;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemRate() {
        return itemRate;
    }

    public void setItemRate(String itemRate) {
        this.itemRate = itemRate;
    }

    public String getItemGst() {
        return itemGst;
    }

    public void setItemGst(String itemGst) {
        this.itemGst = itemGst;
    }

    public String getItemQty() {
        return itemQty;
    }

    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }

    public String getItemBillRate() {
        return itemBillRate;
    }

    public void setItemBillRate(String itemBillRate) {
        this.itemBillRate = itemBillRate;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

}

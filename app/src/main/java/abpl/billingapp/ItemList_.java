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
    @SerializedName("scheme")
    @Expose
    private String scheme;
    @SerializedName("discounted_bottel")
    @Expose
    private String discounted_bottel;

    @SerializedName("discounted_value")
    @Expose
    private String discounted_value;

    @SerializedName("taxable_amount")
    @Expose
    private String taxable_amount;

    @SerializedName("item_bill_rate")
    @Expose
    private String itemBillRate;

    @SerializedName("amount")
    @Expose
    private String amount;

    @SerializedName("cgst_value")
    @Expose
    private String cgst_value;
    @SerializedName("per_bottol_rate")
    @Expose
    private String per_bottol_rate;

    public String getPer_bottol_rate() {
        return per_bottol_rate;
    }

    public void setPer_bottol_rate(String per_bottol_rate) {
        this.per_bottol_rate = per_bottol_rate;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getDiscounted_bottel() {
        return discounted_bottel;
    }

    public void setDiscounted_bottel(String discounted_bottel) {
        this.discounted_bottel = discounted_bottel;
    }

    public String getDiscounted_value() {
        return discounted_value;
    }

    public void setDiscounted_value(String discounted_value) {
        this.discounted_value = discounted_value;
    }

    public String getTaxable_amount() {
        return taxable_amount;
    }

    public void setTaxable_amount(String taxable_amount) {
        this.taxable_amount = taxable_amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCgst_value() {
        return cgst_value;
    }

    public void setCgst_value(String cgst_value) {
        this.cgst_value = cgst_value;
    }

    public String getCessgst_value() {
        return cessgst_value;
    }

    public void setCessgst_value(String cessgst_value) {
        this.cessgst_value = cessgst_value;
    }

    @SerializedName("cessgst_value")
    @Expose

    private String cessgst_value;

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

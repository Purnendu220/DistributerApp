
package abpl.billingapp;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemList {

    @SerializedName("item_list")
    @Expose
    private ArrayList<ItemList_> itemList = null;

    public ArrayList<ItemList_> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<ItemList_> itemList) {
        this.itemList = itemList;
    }

}

package abpl.billingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by purnendu on 22-02-2017.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {


    private Context mContext;
    private ArrayList<ItemList_> mList;

    public ItemListAdapter(Context context, ArrayList<ItemList_> list) {
        this.mContext = context;
        this.mList = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item1, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return new ItemListAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ItemList_ data=mList.get(position);
        FontTypeFace.setRobotoThinTypeFace(mContext,holder.input_item_name,holder.input_item_qty,holder.input_item_sale_percase,holder.input_total_amount,holder.input_discount);
        //FontTypeFace.setRobotoMediumTypeFace(mContext,holder.prod_text_target_head,holder.prod_text_day_head,holder.prod_text_mtd_head,holder.prod_text_ytd_head,holder.pe_text_target_head,holder.pe_text_day_head,holder.pe_text_mtd_head,holder.pe_text_ytd_head,holder.me_text_target_head,holder.me_text_day_head,holder.me_text_mtd_head,holder.me_text_ytd_head);

        holder.input_item_name.setText(data.getItemName());
        holder.input_item_qty.setText(data.getItemQty());
        holder.input_item_sale_percase.setText(data.getItemBillRate());
        holder.input_total_amount.setText(data.getAmount());
        holder.input_discount.setText(data.getDiscounted_value());
        holder.input_taxable_amount.setText(data.getTaxable_amount());
        holder.input_gst.setText(data.getItemGst());
        holder.input_sgst.setText(data.getCgst_value());
        holder.input_cess.setText(data.getCessgst_value());
        holder.input_perbottol_rate.setText(data.getPer_bottol_rate());
        holder.input_discounted_bottol.setText(data.getDiscounted_bottel());
        holder.input_scheme.setText(data.getScheme());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }


        EditText input_item_name = (EditText) itemView.findViewById(R.id.input_item_name);
        EditText input_item_sale_percase = (EditText) itemView.findViewById(R.id.input_item_sale_percase);
        EditText input_total_amount = (EditText) itemView.findViewById(R.id.input_total_amount);
        EditText input_discount = (EditText) itemView.findViewById(R.id.input_discount);
        EditText input_taxable_amount = (EditText) itemView.findViewById(R.id.input_taxable_amount);
        EditText input_gst = (EditText) itemView.findViewById(R.id.input_gst);
        EditText input_sgst = (EditText) itemView.findViewById(R.id.input_sgst);
        EditText input_cess = (EditText) itemView.findViewById(R.id.input_cess);
        EditText input_perbottol_rate = (EditText) itemView.findViewById(R.id.input_perbottol_rate);
        EditText input_discounted_bottol = (EditText) itemView.findViewById(R.id.input_discounted_bottol);
        EditText input_scheme = (EditText) itemView.findViewById(R.id.input_scheme);
        EditText input_item_qty=(EditText) itemView.findViewById(R.id.input_item_qty);



    }

}

package abpl.billingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item, parent, false);
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
       // FontTypeFace.setRobotoThinTypeFace(mContext,holder.prod_text_target,holder.prod_text_ytd,holder.prod_text_mtd,holder.prod_text_day,holder.pe_text_target,holder.pe_text_ytd,holder.pe_text_mtd,holder.pe_data_day,holder.me_text_target,holder.me_text_ytd,holder.me_text_mtd,holder.me_text_day,holder.text_date2,holder.text_date1,holder.text_date,holder.text_line_name,holder.text_item_name, holder.text_user_name);
        //FontTypeFace.setRobotoMediumTypeFace(mContext,holder.prod_text_target_head,holder.prod_text_day_head,holder.prod_text_mtd_head,holder.prod_text_ytd_head,holder.pe_text_target_head,holder.pe_text_day_head,holder.pe_text_mtd_head,holder.pe_text_ytd_head,holder.me_text_target_head,holder.me_text_day_head,holder.me_text_mtd_head,holder.me_text_ytd_head);

        holder.text_item.setText(data.getItemName());
        holder.text_qty.setText(data.getItemQty());
        holder.text_rate.setText(data.getItemRate());
        holder.text_gst.setText(data.getItemGst());
        holder.text_total.setText(data.getItemBillRate());



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }


        TextView text_item = (TextView) itemView.findViewById(R.id.text_item);
        TextView text_qty = (TextView) itemView.findViewById(R.id.text_qty);
        TextView text_rate = (TextView) itemView.findViewById(R.id.text_rate);
        TextView text_gst = (TextView) itemView.findViewById(R.id.text_gst);

        TextView text_total = (TextView) itemView.findViewById(R.id.text_total);



    }

}

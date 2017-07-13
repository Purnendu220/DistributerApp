package abpl.billingapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ItemList mItemlist;
    ArrayList<ItemList_> mlist=new ArrayList<>();
    String[] categories;
    int[] gst_rates,cgst_rates,cess_rates,nob_arr;

    EditText text_rate,text_gst,text_qty,text_base_rate,text_gst_c,text_gst_cess,text_discounted_rates,text_total_rate,text_scheme;
    TextView text_total,text_total_bill;
    int total,item_gst_rates,item_cgst_rates,item_cessgst_rates,nob;
    RecyclerView list_view;
    private ItemListAdapter mJobListAdapter;
    Button button_item;
    private String mSelected_item;
    LinearLayout llmain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llmain=(LinearLayout)findViewById(R.id.parentRL);
        Spinner spinner_item=(Spinner)findViewById(R.id.spinner_item);
        text_rate=(EditText)findViewById(R.id.text_rate);
        text_gst=(EditText)findViewById(R.id.text_gst);
        text_qty=(EditText)findViewById(R.id.text_qty);
        text_base_rate=(EditText)findViewById(R.id.text_base_rate);
        text_discounted_rates=(EditText)findViewById(R.id.text_discounted_rates);
        text_scheme=(EditText)findViewById(R.id.text_scheme);

        text_total_rate=(EditText)findViewById(R.id.text_total_rate);

        text_total=(TextView)findViewById(R.id.text_total);
        text_total_bill=(TextView)findViewById(R.id.text_total_bill);
        button_item=(Button)findViewById(R.id.button_item);
        list_view=(RecyclerView)findViewById(R.id.candidate_joblisting_filterRV);
        text_gst_c=(EditText)findViewById(R.id.text_gst_c);
        text_gst_cess=(EditText)findViewById(R.id.text_gst_cess);


        FontTypeFace.setRobotoThinTypeFace(MainActivity.this,text_rate,text_gst,text_qty,text_base_rate,text_total,text_total_bill,button_item);
        categories=getResources().getStringArray(R.array.item_list);
        gst_rates=getResources().getIntArray(R.array.gst_rates);
        cgst_rates=getResources().getIntArray(R.array.cgst_rates);
        cess_rates=getResources().getIntArray(R.array.cess_rates);
        nob_arr=getResources().getIntArray(R.array.nob);
        setJobListAdapter(true);
        spinner_item.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Syso.error(categories[position]);
                mSelected_item=categories[position];
                item_gst_rates=gst_rates[position];
                item_cgst_rates=cgst_rates[position];
                item_cessgst_rates=cess_rates[position];
                nob=nob_arr[position];
                text_rate.setText(AppPreference.getInstance().getValue(categories[position]));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemlist=new ItemList();
                ItemList_ item=new ItemList_();
                item.setItemName(mSelected_item);
                item.setItemRate(text_rate.getText().toString());
                item.setItemGst(text_gst.getText().toString());
                item.setCgst_value(text_gst_c.getText().toString());
                item.setCessgst_value(text_gst_cess.getText().toString());
                item.setScheme(text_scheme.getText().toString());
                item.setDiscounted_bottel(getDiscountedBottol());
                item.setDiscounted_value(text_discounted_rates.getText().toString());
                item.setTaxable_amount(text_base_rate.getText().toString());
                item.setItemQty(text_qty.getText().toString());
                item.setItemBillRate(text_total.getText().toString());
                item.setAmount(text_total_rate.getText().toString());
                item.setPer_bottol_rate(getbaserate(Double.parseDouble(text_rate.getText().toString())));
                item.setItemId(mlist.size());
                mlist.add(item);
                mItemlist.setItemList(mlist);
                AppPreference.getInstance().setValue(mSelected_item,text_rate.getText().toString());
                AppPreference.getInstance().setItemList(GsonManager.toJSON(mItemlist));
                Syso.error(GsonManager.toJSON(mItemlist));
                text_gst.setText("");
                text_gst_c.setText("");
                text_gst_cess.setText("");
                text_qty.setText("");
                text_base_rate.setText("");
                text_total.setText("Total:-");
                text_total_rate.setText("");
                button_item.setEnabled(false);
                text_gst.setEnabled(false);
                text_gst.setInputType(InputType.TYPE_NULL);
                text_gst_c.setEnabled(false);
                text_gst_c.setInputType(InputType.TYPE_NULL);
                text_gst_cess.setEnabled(false);
                text_gst_cess.setInputType(InputType.TYPE_NULL);
                setJobListAdapter(false);



            }
        });
        text_rate.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                Syso.error(s+"-"+start+"-"+before+"-"+"-"+count);
                if(s.length()>0){
                    try {
                       String base= getbaserate(Double.parseDouble(s.toString()));
                        text_qty.setEnabled(true);
                        text_qty.setInputType(InputType.TYPE_CLASS_NUMBER);
                        text_base_rate.setText(base);
                        text_qty.setText("1");

                    }
                    catch(Exception e){
                        AlertUtils.getInstance().showToast(MainActivity.this,"Please enter numbers only");
                    }
                }
                else{
                    text_qty.setEnabled(false);
                    text_qty.setInputType(InputType.TYPE_NULL);
                    text_scheme.setEnabled(false);
                    text_scheme.setInputType(InputType.TYPE_NULL);

                }
            }
        });

        text_qty.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                Syso.error(s+"-"+start+"-"+before+"-"+"-"+count);
                if(s.length()>0) {
                    try {
                        String base = gettotalbaserate(Double.parseDouble(text_rate.getText().toString()));
                        String gst = getgstrate(Double.parseDouble(text_rate.getText().toString()));
                        String cgst = getCgstrate(Double.parseDouble(text_rate.getText().toString()));
                        String cessgst = getcessgstrate(Double.parseDouble(text_rate.getText().toString()));
                        text_gst.setText(gst);
                        text_gst_cess.setText(cessgst);
                        text_gst_c.setText(cgst);
                        text_base_rate.setText(base);
                       // text_total_bill.setText(base);
                        text_total.setText(calculateTotal(base, gst,cgst,cessgst));
                        button_item.setEnabled(true);
                        text_scheme.setInputType(InputType.TYPE_CLASS_NUMBER);

                        text_scheme.setEnabled(true);
                        if(text_scheme.getText().toString().length()>0){
                            text_scheme.setText(text_scheme.getText().toString());
                        }
                    }
                    catch (Exception e){
                        AlertUtils.getInstance().showToast(MainActivity.this,"Please enter numbers only");

                    }

                }else{
                    text_gst.setText("");
                    text_gst_c.setText("");
                    text_gst_cess.setText("");
                    text_base_rate.setText("");
                   // text_total_bill.setText("");
                    text_discounted_rates.setText("");
                    text_scheme.setText("");
                    button_item.setEnabled(false);
                    text_scheme.setInputType(InputType.TYPE_NULL);

                    text_scheme.setEnabled(false);

                }
            }
        });

        text_scheme.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                Syso.error(s+"-"+start+"-"+before+"-"+"-"+count);
                if(s.length()>0) {
                    try {
                      String discount=getDiscountedRate(s.toString());
                        String total=totalbill(discount);
                        text_total_rate.setText(total);
                        text_discounted_rates.setText(discount);

                    }
                    catch (Exception e){
                        AlertUtils.getInstance().showToast(MainActivity.this,"Please enter numbers only");

                    }

                }else{

                }
            }
        });
    }
     private String getDiscountedBottol(){
         try{
             return String.format("%.2f", Double.parseDouble(text_scheme.getText().toString())*Double.parseDouble(text_qty.getText().toString()));
         }catch (Exception e){
             return String.format("%.2f", 0);
         }
     }
    private String totalbill(String discount){
        double total=Double.parseDouble(discount)+Double.parseDouble(text_base_rate.getText().toString());
        return String.format("%.2f", total);

    }
    private String calculateTotal(String base,String gst,String cgst,String cess){
          double total =Double.parseDouble(base)+Double.parseDouble(gst)+Double.parseDouble(cgst)+Double.parseDouble(cess);
         return String.format("%.2f", total);
    }
    public String getbaserate(double value){
        double result= value*100/(100+(item_gst_rates+item_cgst_rates+item_cessgst_rates));
        return String.format("%.2f", result);
    }

    public String gettotalbaserate(double value){
        double result= (value*100/(100+(item_gst_rates+item_cgst_rates+item_cessgst_rates)))*Integer.parseInt(text_qty.getText().toString());
        return String.format("%.2f", result);
    }
    public String getgstrate(double value)
    {
       double result=(Double.parseDouble(gettotalbaserate(value))*item_gst_rates)/100;
        return String.format("%.2f", result);
    }
    public String getCgstrate(double value)
    {
        double result=(Double.parseDouble(gettotalbaserate(value))*item_cgst_rates)/100;
        return String.format("%.2f", result);
    }
    public String getcessgstrate(double value)
    {
        double result=(Double.parseDouble(gettotalbaserate(value))*item_cessgst_rates)/100;
        return String.format("%.2f", result);
    }
    public String getDiscountedRate(String value){
        try{
           String base= getbaserate(Double.parseDouble(text_rate.getText().toString()));
            double result=(Double.parseDouble(base)/nob)*Double.parseDouble(value)*Double.parseDouble(text_qty.getText().toString());
             return String.format("%.2f", result);
        }catch (Exception e){
            return String.format("%.2f", 0);

        }
    }

    private void setJobListAdapter(boolean isfirstload) {
        ArrayList<ItemList_> list;
        try{
            ItemList item_list= (ItemList) GsonManager.fromJSON(AppPreference.getInstance().getItemList(),ItemList.class);
     list=item_list.getItemList();
        }
        catch (Exception e){
            ItemList item_list= (ItemList) GsonManager.fromJSON(AppPreference.getInstance().getItemList(),ItemList.class);
          list=new ArrayList<>();
        }
        if(isfirstload){
            mlist.addAll(list);
        }

        if(list.size()>0){
            Collections.reverse(list);
            list_view.setVisibility(View.VISIBLE);
            // text_date.setVisibility(View.GONE);
            text_total_bill.setVisibility(View.VISIBLE);
            mJobListAdapter = new ItemListAdapter(MainActivity.this, list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            list_view.setLayoutManager(layoutManager);
            list_view.setAdapter(mJobListAdapter);
            text_total_bill.setText("Total Bill:-"+gettotalbill(list));
        }
        else{
            list_view.setVisibility(View.GONE);

            text_total_bill.setVisibility(View.GONE);
            //  text_date.setText("No Data Available for "+mSelectedDate);
        }
    }
    private String gettotalbill(ArrayList<ItemList_> list){
        double total=0;
        for(ItemList_ item:list){
            total +=Double.parseDouble(item.getItemBillRate());

        }
         return String.format("%.2f", total);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            AppPreference.getInstance().setItemList(null);
            mlist=new ArrayList<>();
            setJobListAdapter(false);
            return true;
        }
        if(id==R.id.action_show_Hide){
            if(llmain.getVisibility()==View.VISIBLE){
                //holder.detail.setVisibility(View.GONE);
                ExpandCollapseView.collapse(llmain);

            }
            else if(llmain.getVisibility()==View.GONE){
                //holder.detail.setVisibility(View.VISIBLE);
                ExpandCollapseView.expand(llmain);
            }
        }

        return super.onOptionsItemSelected(item);
    }
}

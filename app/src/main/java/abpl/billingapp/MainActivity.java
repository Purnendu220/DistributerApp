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
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ItemList mItemlist;
    ArrayList<ItemList_> mlist=new ArrayList<>();
    String[] categories;
    EditText text_rate,text_gst,text_qty;
    TextView text_total;
    int total;
    RecyclerView list_view;
    private ItemListAdapter mJobListAdapter;
    Button button_item;
    private String mSelected_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Spinner spinner_item=(Spinner)findViewById(R.id.spinner_item);
        text_rate=(EditText)findViewById(R.id.text_rate);
        text_gst=(EditText)findViewById(R.id.text_gst);
        text_qty=(EditText)findViewById(R.id.text_qty);
        text_total=(TextView)findViewById(R.id.text_total);
        button_item=(Button)findViewById(R.id.button_item);
        list_view=(RecyclerView)findViewById(R.id.candidate_joblisting_filterRV);

        categories=getResources().getStringArray(R.array.item_list);
        setJobListAdapter(true);
        spinner_item.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Syso.error(categories[position]);
                mSelected_item=categories[position];
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
                item.setItemQty(text_qty.getText().toString());
                item.setItemBillRate(text_total.getText().toString());
                item.setItemId(mlist.size());
                mlist.add(item);
                mItemlist.setItemList(mlist);
                AppPreference.getInstance().setValue(mSelected_item,text_rate.getText().toString());
                AppPreference.getInstance().setItemList(GsonManager.toJSON(mItemlist));
                setJobListAdapter(false);
                text_gst.setText("");
                text_qty.setText("");
                text_total.setText("Total:-");
                button_item.setEnabled(false);
                text_gst.setEnabled(false);
                text_gst.setInputType(InputType.TYPE_NULL);
                text_qty.setEnabled(false);
                text_qty.setInputType(InputType.TYPE_NULL);

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
                calculateTotal();
                if(s.length()>0){
                    text_gst.setEnabled(true);
                    text_gst.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
                else{
                    text_gst.setText("");
                    text_gst.setEnabled(false);
                    text_gst.setInputType(InputType.TYPE_NULL);

                }
            }
        });
        text_gst.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                Syso.error(s+"-"+start+"-"+before+"-"+"-"+count);
                calculateTotal();
                if(s.length()>0){
                    text_qty.setEnabled(true);
                    text_qty.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
                else{
                    text_qty.setEnabled(false);
                    text_qty.setInputType(InputType.TYPE_NULL);

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
                calculateTotal();

            }
        });
    }
    private void calculateTotal(){
        String rate=text_rate.getText().toString();
        String gst=text_gst.getText().toString();
        String qty=text_qty.getText().toString();
        if(rate.length()>0&&gst.length()>0&&qty.length()>0){
            text_total.setText("Total:-0000");
            button_item.setEnabled(true);

        }else{
            text_total.setText("Total:-");
            button_item.setEnabled(false);
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
            list_view.setVisibility(View.VISIBLE);
            // text_date.setVisibility(View.GONE);
            mJobListAdapter = new ItemListAdapter(MainActivity.this, list);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            list_view.setLayoutManager(layoutManager);
            list_view.setAdapter(mJobListAdapter);}
        else{
            list_view.setVisibility(View.GONE);
            // text_date.setVisibility(View.VISIBLE);
            //  text_date.setText("No Data Available for "+mSelectedDate);
        }
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

        return super.onOptionsItemSelected(item);
    }
}

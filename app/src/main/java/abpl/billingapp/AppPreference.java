package abpl.billingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppPreference {


    private String TAG = this.getClass().getSimpleName();
    private SharedPreferences mPrefs;
    private Editor mPrefsEditor;
    private static AppPreference INSTANCE;
    private final String TOKENVALUE = "tokenvalue";
    private final String ITEM_LIST="item_list";


    private AppPreference(Context context) {
        this.mPrefs = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        this.mPrefsEditor = mPrefs.edit();
    }

    public static AppPreference getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new AppPreference(AppContext.getInstance().getContext());
        }
        return INSTANCE;
    }
    public String getTokenValue()
    {
        return mPrefs.getString(TOKENVALUE, "");
    }

    public void setTokenValue(String token_value) {
        mPrefsEditor.putString(TOKENVALUE, token_value);
        mPrefsEditor.commit();
    }
    public String getValue(String key)
    {
        return mPrefs.getString(key, "");
    }

    public void setValue(String key,String token_value) {
        mPrefsEditor.putString(key, token_value);
        mPrefsEditor.commit();
    }
    public String getItemList()
    {
        return mPrefs.getString(ITEM_LIST, "{\"item_list\":[\n" +
                "]\n" +
                "}");
    }

    public void setItemList(String token_value) {
        mPrefsEditor.putString(ITEM_LIST, token_value);
        mPrefsEditor.commit();
    }
public void ClearPRef(){
    mPrefsEditor.clear();
    mPrefsEditor.commit();
}
    public boolean getstatus(String key) {
        return mPrefs.getBoolean(key, false);
    }

    public void setStatus(String key) {
        mPrefsEditor.putBoolean(key, true);
        mPrefsEditor.commit();
    }




}

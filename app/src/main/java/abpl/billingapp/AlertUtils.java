package abpl.billingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AlertUtils {

    public static AlertUtils instance;
    public static AlertUtils getInstance(){
        if(instance==null){
            instance=new AlertUtils();
        }
        return instance;
    }

    public  void showToast(Context context, String message) {
        if (message != null && context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    public  void showToast(Context context, int resID) {
        Toast.makeText(context, resID, Toast.LENGTH_SHORT).show();
    }

    public  void showSnackBar(Context context, String message, View view){
        if(view != null && message != null){
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
        }
    }





    public InputStream getInputStreamValue(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        InputStream in = new ByteArrayInputStream(stream.toByteArray());
        return in;
    }

}

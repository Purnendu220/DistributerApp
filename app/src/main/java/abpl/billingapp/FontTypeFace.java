package abpl.billingapp;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Mobikasa on 7/25/2016.
 */
public class FontTypeFace {
    public static void setRobotoThinTypeFace(Context context, View... view) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf");
        for (View v : view) {
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(tf);
            } else if (v instanceof EditText) {
                ((EditText) v).setTypeface(tf);
            } else if (v instanceof Button) {
                ((Button) v).setTypeface(tf);
            } else if (v instanceof CheckBox) {
                ((CheckBox) v).setTypeface(tf);
            } else if (v instanceof RadioButton) {
                ((RadioButton) v).setTypeface(tf);
            }
        }
    }



    public static void setRobotoMediumTypeFace(Context context, View... view) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Roboto-Medium.ttf");
        for (View v : view) {
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(tf);
            } else if (v instanceof EditText) {
                ((EditText) v).setTypeface(tf);
            } else if (v instanceof Button) {
                ((Button) v).setTypeface(tf);
            } else if (v instanceof CheckBox) {
                ((CheckBox) v).setTypeface(tf);
            } else if (v instanceof RadioButton) {
                ((RadioButton) v).setTypeface(tf);
            }
        }
    }


}

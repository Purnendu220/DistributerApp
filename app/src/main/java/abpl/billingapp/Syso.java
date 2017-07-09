package abpl.billingapp;

import android.util.Log;


/**
 * @author This class is used for adding log in-line.
 *         Need to change "mIsDebug" value "false" when deploy.
 */
public class Syso {
//52117010264
    public static boolean mIsDebug = true;
    public static void infoFull(Object object) {
        int maxLogSize = 2000;
        String veryLongString = object.toString();
        for (int i = 0; i <= veryLongString.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i + 1) * maxLogSize;
            end = end > veryLongString.length() ? veryLongString.length() : end;
            Syso.info("1800 flowers log : ", veryLongString.substring(start, end));
        }
    }

    public static void info(String tag, Object object) {
        if (mIsDebug) {
            Log.i("1800 flowers log : " + tag, "" + object);

        }
    }

    public static void debug(Object object) {
        if (mIsDebug) {
            Log.d("1800 flowers log : ", "" + object);
        }
    }

    public static void debug(String tag, Object object) {
        if (mIsDebug) {
            Log.d("1800 flowers log : " + tag, "" + object);
        }
    }

    public static void error(Object object) {
        if (mIsDebug) {
            Syso.error("1800 flowers log : ", "" + object);
            if (object instanceof Exception) {
                ((Exception) object).printStackTrace();

            }
        }
    }



    public static void error(String tag, Object object) {
        if (mIsDebug) {
            Log.e("1800 flowers log : " + tag, "" + object);
            if (object instanceof Exception) {
                ((Exception) object).printStackTrace();
            }
        }
    }

    public static void print(Object object) {
        if (mIsDebug) {
            Syso.info("1800 flowers log : ", "" + object);
        }
    }

    public static void info(Object object) {
        print(object);
    }
}

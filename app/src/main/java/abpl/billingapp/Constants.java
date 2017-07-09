package abpl.billingapp;

/**
 * Created by Purnendu Mishra on 4/14/2017.
 */

public class Constants {


    public static String ARE_YOU_SURE="Are you sure want to exit? ";
    public static String YES="YES";
    public static String NO="NO";

    public static String READ_PHONE_STATE = "read_phone_state";
    public static final int REQUEST_ENABLE = 1;
    public static final long NUMBEROFDAY=25569;
    public static final  String BASE_DATE="01 01 1970";

    public static final  String CHECK_INTERNET="Check Internet";
    public static final String GO_TO_SETTINGS="Go To Setting";

    public interface FABRIC_EVENTS{
        String EVENT_GET_SAVED_DATA_MAIN_SCREEN="Screen Main Saved Data";
        String EVENT_GET_DATA_MAIN_SCREEN="Screen Main Api Data";

        String EVENT_GET_SAVED_DATA_RESULT_SCREEN="Screen Result Saved Data";
        String EVENT_GET_DATA_MAIN_RESULT="Screen Result Api Data";

        String EVENT_GET_ALL_DATES="Screen All Date Api Data";
        String EVENT_GET_API_FAIL_EVENT="Api Fail";

        String SCREEN_MAIN="Screen Main";
        String SCREEN_ALLDATES="Screen All Date";
        String SCREEN_ALLDATES_RESULT="Screen Result";

        String DATE_CLIKED = "Date Clicked";
        String NO_INTERNET = "No Internet";
        String FCM_REGISTERED = "Fcm Registered";
        String FCM_ID="Fcm Id";
    }

}

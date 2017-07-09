package abpl.billingapp;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@SuppressLint("SimpleDateFormat")
public class DateUtils {

    public final static String DATE_FORMAT_1 = "MMM dd, yyyy";
    public final static String DATE_FORMAT_2 = "MM/dd";
    public final static String DATE_FORMAT_3 = "MMM dd, yyyy hh:mm aa";
    public final static String DATE_FORMAT_4 = "MMMM dd, yyyy hh:mm aa";
    public final static String DATE_FORMAT_5 = "yyyy-MM-dd hh:mm:ss";
    public final static String DATE_FORMAT_6 = "yyyy-M-d";
    public final static String DATE_FORMAT_7 = "dd-MM-yyyy HH:mm:ss";
    public final static String DATE_FORMAT_8 = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_FORMAT_9 = "MM/dd/yy";
    public final static String DATE_FORMAT_10 = "yyyy-MM-dd";
    public final static String DATE_FORMAT_11 = "dd MM yyyy";


    public final static String PATTERN_FORMAT_WEEK = "EE";
    public final static String PATTERN_FORMAT_MONTH = "MMM";
    public final static String FORMAT1 = "hh:mm aa";
    public final static String FORMAT2 = "EEE MMM dd, yyyy hh:mm aa";
    public final static String FORMAT3 = "HH:mm:ss";

    private static List<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>() {
        {
            add(new SimpleDateFormat("MM/dd/yy"));
            add(new SimpleDateFormat("ddMMMyyyy"));
        }    };

    public static String getDateTime(long milliseconds, String outPutFormat) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(milliseconds);

        SimpleDateFormat sdf = new SimpleDateFormat(outPutFormat, Locale.getDefault());
        String outPutDate = sdf.format(cal.getTime());

        return outPutDate;
    }

    public static String getDateTime(Calendar cal, String outPutFormat) {

        SimpleDateFormat sdf = new SimpleDateFormat(outPutFormat, Locale.getDefault());
        String outPutDate = sdf.format(cal.getTime());

        return outPutDate;
    }


    public static long getTimeInMillis(String dateTime, String inputFormat) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(inputFormat, Locale.getDefault());
            Date date = sdf.parse(dateTime);
            return date.getTime();
        } catch (ParseException e) {
            Syso.error(e.getMessage());
            return 0;
        }
    }
public static long getNumberOfDays(String date){
    SimpleDateFormat myFormat = new SimpleDateFormat(DATE_FORMAT_11);
    try {
        Date date1 = myFormat.parse(Constants.BASE_DATE);
        Date date2 = myFormat.parse(date);
        long diff = date2.getTime() - date1.getTime();
        System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        return Constants.NUMBEROFDAY + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    } catch (ParseException e) {
        e.printStackTrace();
        return 0;
    }

}
public static  String getdateFromnumberofDays(String str){
    try{


    long days=Long.parseLong(str)-Constants.NUMBEROFDAY;
    SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_11);
    Calendar c = Calendar.getInstance();
    try {
        c.setTime(df.parse(Constants.BASE_DATE));
    } catch (ParseException e) {
        e.printStackTrace();
    }
    c.add(Calendar.DATE, (int) (long)days);
    String formattedDate = df.format(c.getTime());
    return formattedDate;
    }catch (Exception e){
        e.printStackTrace();
return null;
    }
}
    public static  String getdateFromnumberofDays(Long str){
        try{


            long days=str-Constants.NUMBEROFDAY;
            SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_11);
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(df.parse(Constants.BASE_DATE));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, (int) (long)days);
            String formattedDate = df.format(c.getTime());
            return formattedDate;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
public static String getCurrentDate(){
    Calendar c = Calendar.getInstance();
     c.add(Calendar.DATE, -1);
    System.out.println("Current time => " + c.getTime());
    SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_11);
    String formattedDate = df.format(c.getTime());
    return formattedDate;
}

    public static String getBestDay(long inputTimeStamp, String outPutFormat) {

        Calendar calInput = Calendar.getInstance();
        calInput.setTime(new Date(inputTimeStamp));

        Calendar calDevice = Calendar.getInstance();
        calDevice.setTimeInMillis(System.currentTimeMillis());

        int monthInput = calInput.get(Calendar.MONTH);
        int monthDevice = calDevice.get(Calendar.MONTH);

        int dayInput = calInput.get(Calendar.DAY_OF_MONTH);
        int dayDevice = calDevice.get(Calendar.DAY_OF_MONTH);

        String outPutDay = "";

        if (monthInput == monthDevice && dayInput == dayDevice) {
            outPutDay = "Today";
            return outPutDay;
        } else if (monthInput == monthDevice && dayInput + 1 == dayDevice) {
            outPutDay = "Yesterday";
            return outPutDay;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(outPutFormat, Locale.getDefault());
            outPutDay = sdf.format(calInput.getTime());
            return outPutDay;
        }
    }

    public static int getDaysDifference(long startTimeStamp, long endTimeStamp) {

        Date startDate = new Date(startTimeStamp);
        Date endDate = new Date(endTimeStamp);

        Syso.debug("startDate = ", startDate);
        Syso.debug("endDate = ", endDate);

        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();

        startCal.setTimeInMillis(startTimeStamp);
        endCal.setTimeInMillis(endTimeStamp);

        int diff = endCal.get(Calendar.DAY_OF_YEAR) - startCal.get(Calendar.DAY_OF_YEAR);
        diff = diff + (startCal.getMaximum(Calendar.DAY_OF_YEAR) * (endCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR)));

        return diff;
    }

    public static String getTimeinAMPM(String time) {
        String str = "";
        String timeArr[] = time.split(":");
        if (Integer.parseInt(timeArr[0]) > 11) {
            if (Integer.parseInt(timeArr[0]) > 12) {
                int h = Integer.parseInt(timeArr[0]) - 12;
                str = StringUtils.pad(h) + ":" + StringUtils.pad(Integer.parseInt(timeArr[1])) + " PM";
            } else
                str = timeArr[0] + ":" + timeArr[1] + " PM";
        } else
            str = StringUtils.pad(Integer.parseInt(timeArr[0])) + ":" + StringUtils.pad(Integer.parseInt(timeArr[1])) + " AM";
        return str;
    }

    public static String getFormattedDate(String date) {
        if (date.equalsIgnoreCase("-")) {
            return date;
        }
        String dmyarr[];
        int year = 0, month = 0, day = 0;
        if (!date.contains("-")) {
            dmyarr = date.split(" ");
            SimpleDateFormat fmt = new SimpleDateFormat("dd MMM yyyy");
            try {
                Date date123 = fmt.parse(date);
                month = date123.getMonth() + 1;
            } catch (ParseException e) {
                Syso.error(e);
            }
            year = Integer.parseInt(dmyarr[2]);
            day = Integer.parseInt(dmyarr[0]);

        } else {
            dmyarr = date.split("-");
            year = Integer.parseInt(dmyarr[0]);
            month = Integer.parseInt(dmyarr[1]);
            day = Integer.parseInt(dmyarr[2]);
        }

        // Assuming that you already have this.

        try {
            // First convert to Date. This is one of the many ways.
            String dateString = String.format("%d-%d-%d", year, month, day);
            Date parsedDate = new SimpleDateFormat(DATE_FORMAT_6).parse(dateString);

            // Then get the day of week from the Date based on specific locale.
            String dayOfWeek = new SimpleDateFormat(PATTERN_FORMAT_WEEK, Locale.ENGLISH).format(parsedDate);
            // Then get the month of year from the Date based on specific locale.
            String monthOfYear = new SimpleDateFormat(PATTERN_FORMAT_MONTH, Locale.ENGLISH).format(parsedDate);
            // return date format like : Thu Dec 04, 2014
            return monthOfYear + " " + StringUtils.pad(day) + ", " + year;
        } catch (Exception e) {
            Syso.error(e);
            return date;
        }

    }

    public static String getWeekMonthDateFormattedDate(String date) {
        if (date.equalsIgnoreCase("-")) {
            return date;
        }
        String dmyarr[];
        int year = 0, month = 0, day = 0;
        if (!date.contains("-")) {
            dmyarr = date.split(" ");
            SimpleDateFormat fmt = new SimpleDateFormat("dd MMM yyyy");
            try {
                Date date123 = fmt.parse(date);
                month = date123.getMonth() + 1;
            } catch (ParseException e) {
                Syso.error(e);
            }
            year = Integer.parseInt(dmyarr[2]);
            day = Integer.parseInt(dmyarr[0]);

        } else {
            dmyarr = date.split("-");
            year = Integer.parseInt(dmyarr[0]);
            month = Integer.parseInt(dmyarr[1]);
            day = Integer.parseInt(dmyarr[2]);
        }

        // Assuming that you already have this.

        try {
            // First convert to Date. This is one of the many ways.
            String dateString = String.format("%d-%d-%d", year, month, day);
            Date parsedDate = new SimpleDateFormat(DATE_FORMAT_6).parse(dateString);

            // Then get the day of week from the Date based on specific locale.
            String dayOfWeek = new SimpleDateFormat(PATTERN_FORMAT_WEEK, Locale.ENGLISH).format(parsedDate);
            // Then get the month of year from the Date based on specific locale.
            String monthOfYear = new SimpleDateFormat(PATTERN_FORMAT_MONTH, Locale.ENGLISH).format(parsedDate);
            // return date format like : Thu Dec 04, 2014
            return dayOfWeek + ", " + monthOfYear + " " + StringUtils.pad(day) + getsuffixforDay(day);
        } catch (Exception e) {
            Syso.error(e);
            return date;
        }

    }

    public static String getFormattedDateMMDDYY(String date) {
        String dmyarr[] = date.split("-");
        // Assuming that you already have this.
        int year = Integer.parseInt(dmyarr[0]);
        int month = Integer.parseInt(dmyarr[1]);
        int day = Integer.parseInt(dmyarr[2]);
        String yr = year + "";
        return month + "/" + day + "/" + yr.substring(2);

    }

    public static String getFormattedDateWithSlash(String date) {
        try {
            Syso.print("date " + date);
            String dmyarr[] = date.split("-");
            // Assuming that you already have this.
            int year = Integer.parseInt(dmyarr[0]);
            int month = Integer.parseInt(dmyarr[1]);
            int day = Integer.parseInt(dmyarr[2]);
            Syso.print("year " + year + " month " + month + " day " + day);
            String Year = (year + "").substring(2, 4);
            return day + "/" + month + "/" + Year;
        } catch (Exception e) {
            Syso.error(e);
            return date;
        }
    }

    public static String getFormattedDate(String date, String seperator) {
        String dmyarr[] = date.split(seperator);
        if (dmyarr.length > 2) {
            // Assuming that you already have this.
            int year = Integer.parseInt(dmyarr[0]);
            int month = Integer.parseInt(dmyarr[1]);
            int day = Integer.parseInt(dmyarr[2]);
            try {
                // First convert to Date. This is one of the many ways.
                String dateString = String.format("%d-%d-%d", year, month, day);
                Date parsedDate = new SimpleDateFormat(DATE_FORMAT_6).parse(dateString);

                // Then get the month of year from the Date based on specific locale.
                String monthOfYear = new SimpleDateFormat(PATTERN_FORMAT_MONTH, Locale.ENGLISH).format(parsedDate);
                // return date format like : Thu Dec 04, 2014
                return monthOfYear + " " + StringUtils.pad(day) + ", " + year;
            } catch (Exception e) {
                Syso.error(e);
                return date;
            }
        } else {
            // Assuming that you already have this.
            int month = Integer.parseInt(dmyarr[0]);
            int day = Integer.parseInt(dmyarr[1]);
            try {
                String monthOfYear[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
                return monthOfYear[month - 1] + " " + StringUtils.pad(day);
            } catch (Exception e) {
                Syso.error(e);
                return date;
            }
        }


    }

    public static String getESTTime() {
        Calendar currentdate = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT_7);
        TimeZone obj = TimeZone.getTimeZone("EST");
        formatter.setTimeZone(obj);
        //11-12-2014 01:46:40
        return "" + formatter.format(currentdate.getTime()).split(" ")[1];
    }

    public static String getUTCDateTime() {
        Calendar currentdate = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT_8);
        TimeZone obj = TimeZone.getTimeZone("UTC");
        formatter.setTimeZone(obj);
        //2014-12-05 01:46:40
        return "" + formatter.format(currentdate.getTime());
    }

    public static String getUTCDateTime(int mins) {
        Calendar currentdate = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT_8);
        TimeZone obj = TimeZone.getTimeZone("UTC");
        formatter.setTimeZone(obj);

        int mHour = currentdate.get(Calendar.HOUR_OF_DAY);
        int mMins = currentdate.get(Calendar.MINUTE);
        int mSecs = currentdate.get(Calendar.SECOND);
        mMins += mins;
        if (mMins > 59) {
            mHour++;
            if (mHour > 23)
                mHour = 0;
            mMins = mMins - 60;
        }
        currentdate.set(currentdate.get(Calendar.YEAR), currentdate.get(Calendar.MONTH), currentdate.get(Calendar.DAY_OF_MONTH), mHour, mMins, mSecs);
        return "" + formatter.format(currentdate.getTime());
        //		return StringUtils.pad(mHour)+":"+StringUtils.pad(mMins)+":"+StringUtils.pad(mSecs);

    }

    public static String getDateDifference(String date) {
        SimpleDateFormat myFormat = new SimpleDateFormat(DATE_FORMAT_10);


        String dmyarr[];
        int Eventmonth = 0, Eventday = 0;
        if (!date.contains("-")) {
            dmyarr = date.split(" ");
            SimpleDateFormat fmt = new SimpleDateFormat("dd MMM yyyy");
            try {
                Date date123 = fmt.parse(date);
                Eventmonth = date123.getMonth() + 1;
            } catch (ParseException e) {
                Syso.error(e);
            }
            Eventday = Integer.parseInt(dmyarr[0]);

        } else {
            dmyarr = date.split("-");
            Eventmonth = Integer.parseInt(dmyarr[1]);
            Eventday = Integer.parseInt(dmyarr[2]);
        }

        Calendar today = Calendar.getInstance();

        String fromDate = today.get(Calendar.YEAR) + "-" + (today.get(Calendar.MONTH) + 1) + "-" + today.get(Calendar.DAY_OF_MONTH);
        String toDate = today.get(Calendar.YEAR) + "-" + Eventmonth + "-" + Eventday;
        int diff = 0;
        try {

            Date date1 = myFormat.parse(toDate);
            Date date2 = myFormat.parse(fromDate);
            diff = Integer.parseInt(TimeUnit.DAYS.convert(date1.getTime() - date2.getTime(), TimeUnit.MILLISECONDS) + "");
            if (diff < 0) {
                toDate = (today.get(Calendar.YEAR) + 1) + "-" + Eventmonth + "-" + Eventday;
                date1 = myFormat.parse(toDate);
                date2 = myFormat.parse(fromDate);
                diff = Integer.parseInt(TimeUnit.DAYS.convert(date1.getTime() - date2.getTime(), TimeUnit.MILLISECONDS) + "");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return diff + "";
    }

    public static String getsuffixforDay(int day) {
        if (day == 1 || day == 21 || day == 31)
            return "st";
        else if (day == 2 || day == 22)
            return "nd";
        else if (day == 3 || day == 23)
            return "rd";
        else
            return "th";
    }

    // IT WILL RETURN 7 DAYS WITH WEEK N DAY
    // E.G. : Thursday, Apr 30th
    public static String getWeekDaysWithDateForCurrentWeek(int i) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEEE, MMM d");
        c.add(Calendar.DAY_OF_YEAR, i);
        String date = df.format(c.getTime()) + getsuffixforDay(c.get(Calendar.DAY_OF_MONTH));
        return date;
    }

    // IT WILL RETURN 7 DAYS WITH WEEK N DAY
    // E.G. : Thu, Apr 30th
    public static String getWeekDaysWithDateForCurrentWeek2(int i) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEE, MMM d");
        c.add(Calendar.DAY_OF_YEAR, i);
        String date = df.format(c.getTime()) + getsuffixforDay(c.get(Calendar.DAY_OF_MONTH));
        return date;
    }

    public static String getWeekDaysWithDateForCurrentWeek3(String d) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("EEE, MMM d");
            SimpleDateFormat df2 = new SimpleDateFormat("dd-MMM-yy");
            Date dd = df2.parse(d);
            String date = df.format(dd);
            return date;
        } catch (Exception e) {
            Syso.error(e);
            return d;
        }
    }

    public static String getFormattedUTCDateTime() {
        Calendar currentdate = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        TimeZone obj = TimeZone.getTimeZone("UTC");
        formatter.setTimeZone(obj);
        String str = formatter.format(currentdate.getTime()) + "";
        //	  Syso.print("aaaaaaaaaaaaaaaa "+formatter.format(currentdate.getTime()));
        if (str.endsWith("+0000"))
            str = str.substring(0, str.length() - 5) + "Z";
        //	  Syso.print("aaaaaaaaaaaaaaaa "+str.substring(0,str.length()-5)+"Z");
        //	  Syso.print("aaaaaaaaaaaaaaaa 2015-05-21T07:06:10.028Z");

        return "" + str;
    }


    public static String getFormatedDeliveryDate(String d) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
            SimpleDateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
            Date dd = df.parse(d);
            String date = df2.format(dd);
            return date;
        } catch (Exception e) {
            Syso.error(e);
            return d;
        }
    }

    public static Date getFormatedDeliveryDateForAppBoy(String d) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
            Date dd = df.parse(d);
            return dd;
        } catch (Exception e) {
            Syso.error(e);
            return null;
        }
    }

    public static String getFormatedExpDateForAppBoy(String d) {
        if (!"Not a member yet".equalsIgnoreCase(d)) {
            try {
                SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy");
                //			2014-03-18
                SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
                Date dd = df.parse(d);
                String date = df2.format(dd);
                return date;
            } catch (Exception e) {
                Syso.error(e);
                return null;
            }
        }
        return null;
    }

    public static String getFormatedExpDate(String d) {
        try {
            //			2018-04-28, 0418
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat df2 = new SimpleDateFormat("MMyy");
            Date dd = df.parse(d);
            String date = df2.format(dd);
            return date;
        } catch (Exception e) {
            Syso.error(e);
            return d;
        }
    }

    public static String getFormatedDate(String d, String inFormate, String outFormate) {
        try {
            //			2018-04-28, 0418
            SimpleDateFormat df = new SimpleDateFormat(inFormate);
            SimpleDateFormat df2 = new SimpleDateFormat(outFormate);
            Date dd = df.parse(d);
            String date = df2.format(dd);
            return date;
        } catch (Exception e) {
            Syso.error(e);
            return d;
        }
    }

    public static boolean isDateExpire(String date, String formate) {
        try {
            if (!TextUtils.isEmpty(date)) {
                SimpleDateFormat df = new SimpleDateFormat(formate);
                Date dd = df.parse(date);
                Date currentDate = new Date();
                if (dd.after(currentDate))
                    return false;
                else
                    return true;
            }
        } catch (Exception e) {
            Syso.error(e);
        }
        return false;
    }

    public static boolean isDateExpired(Date date, String formate) {
        try {
            if (date != null) {

                Date currentDate = new Date();
                if (date.after(currentDate))
                    return false;
                else
                    return true;
            }
        } catch (Exception e) {
            Syso.error(e);
        }
        return false;
    }

    public static Date convertToDate(String input) {
        Date date = null;
        if (null == input) {
            return null;
        }
        for (SimpleDateFormat format : dateFormats) {
            try {
                // format.setLenient(false);
                date = format.parse(input);
            } catch (ParseException e) {
                //Shhh.. try other formats
            }
            if (date != null) {
                break;
            }
        }

        return date;
    }


    public static String convertDateToString(Date date) {
        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            // Using DateFormat format method we can create a string
            // representation of a date with the defined format.
            String reportDate = df.format(date);
            // Print what date is today!
            return reportDate;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getDateDifferenceNew(String date) {
        SimpleDateFormat myFormat = new SimpleDateFormat(DATE_FORMAT_10);


        String dmyarr[];
        int Eventmonth = 0, Eventday = 0;
        if (!date.contains("-")) {
            dmyarr = date.split(" ");
            SimpleDateFormat fmt = new SimpleDateFormat("dd MMM yyyy");
            try {
                Date date123 = fmt.parse(date);
                Eventmonth = date123.getMonth() + 1;
            } catch (ParseException e) {
                Syso.error(e);
            }
            Eventday = Integer.parseInt(dmyarr[0]);

        } else {
            dmyarr = date.split("-");
            Eventmonth = Integer.parseInt(dmyarr[1]);
            Eventday = Integer.parseInt(dmyarr[0]);
        }

        Calendar today = Calendar.getInstance();

        String fromDate = today.get(Calendar.YEAR) + "-" + (today.get(Calendar.MONTH) + 1) + "-" + today.get(Calendar.DAY_OF_MONTH);
        String toDate = today.get(Calendar.YEAR) + "-" + Eventmonth + "-" + Eventday;
        int diff = 0;
        try {

            Date date1 = myFormat.parse(toDate);
            Date date2 = myFormat.parse(fromDate);
            diff = Integer.parseInt(TimeUnit.DAYS.convert(date1.getTime() - date2.getTime(), TimeUnit.MILLISECONDS) + "");
            if (diff < 0) {
                toDate = (today.get(Calendar.YEAR) + 1) + "-" + Eventmonth + "-" + Eventday;
                date1 = myFormat.parse(toDate);
                date2 = myFormat.parse(fromDate);
                diff = Integer.parseInt(TimeUnit.DAYS.convert(date1.getTime() - date2.getTime(), TimeUnit.MILLISECONDS) + "");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return diff + "";
    }

    public static long getCurrentTimeStamp() {
        long timeStampValue = 0;
        Date date = new Date();
        if (date != null) {
            Timestamp timestamp = new Timestamp(date.getTime());
            timeStampValue = timestamp.getTime();

        }
        return timeStampValue;
    }

    public static long getHourDifference(long startTimeStamp, long endTimeStamp) {
        long diff = startTimeStamp - endTimeStamp;
        long diffSeconds = diff / 1000;
        long diffMinutes = diff / (60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return diffHours;
    }


}

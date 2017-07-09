package abpl.billingapp;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Patterns;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static String KEY_PASS_19 = "Sekrit";
    public static String GUEST_EG="gmail.";
    public static String GUEST_PC="c0";
    public static boolean isEmpty(String data) {

        Syso.info(data);
        if (TextUtils.isEmpty(data)) {
            return true;
        } else {
            if (data == null)
                return true;
            String inputData = data.trim();
            if (inputData.equalsIgnoreCase("null")) {
                return true;
            } else if (inputData.equalsIgnoreCase("\"\"")) {
                return true;
            }
            else if (inputData.equalsIgnoreCase("")) {
                return true;
            }
            else if (inputData.length()==0) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method will Check Email Validation.</br>
     *
     * @param email - This is input email into <b>String</b> format.
     * @return <b>true</b> if Email is valid.
     */
    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;

        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
//	
//	public static boolean isEmailValid(String email) {
//		Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
//		Boolean validation = emailPattern.matcher(email).matches();
//		return validation;
//	}


    /**
     * ^                 # start-of-string
     * (?=.*[0-9])       # a digit must occur at least once
     * (?=.*[a-z])       # a lower case letter must occur at least once
     * (?=.*[A-Z])       # an upper case letter must occur at least once
     * (?=.*[@#$%^&+=])  # a special character must occur at least once
     * .{6,}             # anything, at least 6 places though
     * $                 # end-of-string
     *
     * @param password
     * @return
     */
    public static boolean isPasswordValid(String password) {
//		Pattern emailPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{6,}$");
        Pattern emailPattern = Pattern.compile("^(?=.*[0-9])(?=.*[A-z]).{6,}$");
        Boolean validation = emailPattern.matcher(password).matches();
        return validation;
    }

    public static String FirstLetterInUpperCase(String word) {

        String result = "";
        if (word.length() > 0) {
            char c = word.charAt(0);
            String splitedString = word.substring(1, word.length());
            result = Character.toUpperCase(c) + splitedString;
        }
        return result;
    }

    public static String FirstLetterInLowerCase(String word) {

        String result = "";
        if (word.length() > 0) {
            char c = word.charAt(0);
            String splitedString = word.substring(1, word.length());
            result = Character.toLowerCase(c) + splitedString;
        }
        return result;
    }


    public static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    public static int getIntValue(String value) {

        if (value == null) {
            return 0;
        }

        try {
            int iValue = Integer.parseInt(value);
            return iValue;
        } catch (Exception e) {
            return 0;
        }
    }

    public static Double getDoubleValue(String value) {

        if (value == null) {
            return 0.0;
        }

        try {
            Double iValue = Double.valueOf(value);
            return iValue;
        } catch (Exception e) {
            return 0.0;
        }
    }

    public static float getFloatValue(String value) {

        if (value == null) {
            return 0.0f;
        }

        try {
            float iValue = Float.valueOf(value);
            return iValue;
        } catch (Exception e) {
            Syso.error(e);
            try {
                String value2 = value.replace(",", ".");
                float iValue = Float.valueOf(value2);
                return iValue;
            } catch (Exception ee) {
                Syso.error(ee);
            }
            return 0.0f;
        }
    }

    public static String getIntValueFromFloat(String value) {

        try {
            int iValue = Float.valueOf(value).intValue();
            return String.valueOf(iValue);
        } catch (Exception e) {
            return "0";
        }
    }

    public static String getRandomImageName() {

        StringBuffer sb = new StringBuffer();
        sb.append(UUID.randomUUID().toString());
        sb.append("_a_");
        sb.append(System.currentTimeMillis());
        sb.append(".png");
        return sb.toString();
    }

    public static String getStringValue(boolean value) {
        String sValue = (value) ? "1" : "0";
        return sValue;
    }

    public static String generateMD5Hash(String str) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance(MD5);
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            Syso.print("========== signature : " + hexString.toString());
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            Syso.error(e);
        }
        return "";
    }



    public static String getBase64EncodedString(String client_id, String client_secret) {
        // encode data on your side using BASE64
        String authorizationString = "Basic " + Base64.encodeToString(
                (client_id + ":" + client_secret).getBytes(),
                Base64.NO_WRAP);
        Syso.print("============ BASE64 string : " + authorizationString);
        return authorizationString;
    }


    public static boolean isValidPhoneNumber(String target) {
        if (target == null || StringUtils.isEmpty(target)) {
            return false;
        } else {
            return Patterns.PHONE.matcher(target).matches();
        }
    }


    public static byte[] getSerializedObject(Serializable s) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(s);
        } catch (IOException e) {
            return null;
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
            }
        }
        byte[] result = baos.toByteArray();
        return result;
    }

    public static Object readSerializedObject(byte[] in) {
        Object result = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(in);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(bais);
            result = ois.readObject();
        } catch (Exception e) {
            result = null;
        } finally {
            try {
                ois.close();
            } catch (Throwable e) {
            }
        }
        return result;
    }

    public static String getFormatedNum(double price) {
        try {
            return String.format("%.2f", price);
//			String value =new DecimalFormat("##.##").format(price);
//			if(!value.contains(".")){
//				return value+".00";
//			}else
//				return value;
        } catch (Exception exception) {
            return String.valueOf(price);
        }
    }


    public static String getFormatedNum(String price) {
        String value = price;
        try {
            double dd = Double.parseDouble(price);
            value = String.format("%.2f", dd);
        } catch (Exception exception) {
            Syso.error(exception);
        }
        return value;
    }
    public static String getMonthCapital(String date)
    {
        String newDate=date;
        if(!TextUtils.isEmpty(date))
        {
            String splitArr[]=date.split("-");
            if(splitArr.length>=3)
            {
                splitArr[1]=splitArr[1].toUpperCase();
                newDate=splitArr[0]+"-"+splitArr[1]+"-"+splitArr[2];
            }
        }
        return newDate;
    }
    public static String getCreditCardType(String CreditCardNumber) {
        if (CreditCardNumber.startsWith("4"))
            return "VISA";
        else if (CreditCardNumber.startsWith("51") || CreditCardNumber.startsWith("52") || CreditCardNumber.startsWith("53") || CreditCardNumber.startsWith("54") || CreditCardNumber.startsWith("55"))
            return "Master Card";
        else if (CreditCardNumber.startsWith("34") || CreditCardNumber.startsWith("37"))
            return "AMEX";
        else if (CreditCardNumber.startsWith("6011"))
            return "Discover";
        else
            return "invalid";
    }

    public static String getCreditCardTypeForAuthorizeCC(String cardType) {
        return cardType.equalsIgnoreCase("DS") ? "DI" : cardType; // This was requested in version 5.3 of App to handle API changes by 18F Team
    }
    public static String getCreditCardValue(String type) {
        if (type.equalsIgnoreCase("VISA"))
            return "VI";
        else if (type.equalsIgnoreCase("Master Card")  || type.equalsIgnoreCase("MasterCard"))
            return "MC";
        else if (type.equalsIgnoreCase("AMEX"))
            return "AX";
        else if (type.equalsIgnoreCase("Discover"))
            return "DS";// according to new doc Discover should be DS wehen we are adding payment card
        else
            return "";
    }

    public static String getCardType(String type) {
        /*
        * Rohit
        * We have implement the null check for type if card type is null then we will show empty in string
        * */

        if(!TextUtils.isEmpty(type)) {
            if (type.equalsIgnoreCase("VI") || type.equalsIgnoreCase("VISA"))
                return "VISA";
            else if (type.equalsIgnoreCase("MC") || type.equalsIgnoreCase("Master Card") || type.equalsIgnoreCase("MasterCard"))
                return "MasterCard";
            else if (type.equalsIgnoreCase("AX") || type.equalsIgnoreCase("AMEX"))
                return "AMEX";
            else if (type.equalsIgnoreCase("DI") || type.equalsIgnoreCase("Discover"))
                return "Discover";
            else
                return type;
        }else{
            return "";
        }
    }

    public static String getZipWithSpace(String zip) {
        try {
            zip = zip.replaceAll(" ", "");
            if (zip.length() > 3) {
                StringBuilder b = new StringBuilder(zip);
                b.insert(3, " ");
                String str = b.toString();
                Syso.info("123456789 >>>> " + str);
                return str;
            }
        } catch (Exception e) {
            Syso.error(e);
        }
        return zip;
    }

    public static String replaceSpecialCharacters(String value) {
        if (value != null) {
            value = value.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("'", "&apos;").replaceAll("\"", "&quot;");
        }
        return value;
    }


    public static int checkEmptyInteger(String data) {

        Syso.info(data);
        if (TextUtils.isEmpty(data)) {
            return 0;
        } else {
            if (data == null)
                return 0;
            String inputData = data.trim();
            if (inputData.equalsIgnoreCase("null")) {
                return 0;
            } else if (inputData.equalsIgnoreCase("\"\"")) {
                return 0;
            }
        }
        return 1;
    }

   public static String getTwoDigitInDecimal(double value){
       return new DecimalFormat("#.##").format(value);
   }


    public static String getSubString(String val, int maxLength) {
        if (!StringUtils.isEmpty(val)) {
            if (val.length() > maxLength) {
                return val.substring(0, maxLength);
            }
        }
        return val;
    }
    public static String convertStringToCaps(String value){
        if(!StringUtils.isEmpty(value)) {
            return value.toUpperCase();
        }else{
            return "";
        }
    }
}

package abpl.billingapp;

import com.google.gson.Gson;

/**
 * Created by Mobikasa on 1/4/2017.
 */
public class GsonManager {



    public static String toJSON(Object obj){
        Gson gson=new Gson();
        String json=gson.toJson(obj);
        return json;
    }

    public static Object fromJSON(String data, Class className ){
        Gson gson=new Gson();
        Object value= gson.fromJson(data,className);
        return value;
    }
}

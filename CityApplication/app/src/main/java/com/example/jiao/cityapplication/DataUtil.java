package com.example.jiao.cityapplication;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by jiao3 on 2018/2/28.
 */

public class DataUtil {

    public  static void updateHistoryCityName(Context paramContext, LinkedList<String> cityList,String numTag,String itemTag) {
        SharedPreferences sharedPreferences = paramContext.getSharedPreferences("historyCityList", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(numTag, cityList.size());
        for (int i = 0; i < cityList.size(); i++)
        {
            editor.putString(itemTag+i, cityList.get(i));
        }
        editor.commit();
    }

    public  static LinkedList<String> getHistoryCityName(Context paramContext,String numTag,String itemTag) {
        LinkedList<String> citysList = new LinkedList<String>();
        SharedPreferences sharedPreferences = paramContext.getSharedPreferences("historyCityList", Context.MODE_PRIVATE);
        int cityNums = sharedPreferences.getInt(numTag, 0);
        for (int i = 0; i < cityNums; i++)
        {
            String cityItem = sharedPreferences.getString(itemTag+i, null);
            citysList.add(cityItem);
        }
        return citysList;
    }

    public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz)
    {
        Gson gson=new Gson();
        Type type = new TypeToken<ArrayList<JsonObject>>()
        {}.getType();
        ArrayList<JsonObject> jsonObjects = gson.fromJson(json, type);
        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects)
        {
            arrayList.add(gson.fromJson(jsonObject, clazz));
        }
        return arrayList;
    }


    public static <T> T jsonToArrayList(String json, TypeToken<T> type) {
        Gson gson = new GsonBuilder().create();
        T result = gson.fromJson(json, type.getType());
        return result;
    }
}

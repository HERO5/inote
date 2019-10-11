package com.mrl.inote.controller;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class GsonUtil {

    public static String objectToJson(Object obj){

        String json;
//        Gson gson = new GsonBuilder()
//                .setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
//                .create();
        Gson gson = new Gson();
        json = gson.toJson(obj);
        return json;

    }

    public static Object jsonToObject(String json, Class<?> cls){

        Gson gson = new Gson();
        Object obj;
        obj = gson.fromJson(json, cls);
        return obj;

    }

    public static <T> List<T> jsonsToObjects(String jsons, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            Gson gson = new Gson();
            JsonArray arry = new JsonParser().parse(jsons).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                list.add(gson.fromJson(jsonElement, cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

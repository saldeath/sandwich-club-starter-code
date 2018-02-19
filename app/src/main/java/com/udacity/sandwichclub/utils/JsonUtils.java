package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject rootJSON = new JSONObject(json);
            JSONObject name =  rootJSON.getJSONObject("name");

            String mainName = jsonToString(name, "mainName");
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");

            List<String> alsoKnownList = jsonToList(alsoKnownAs);

            String placeOfOrigin = jsonToString(rootJSON, "placeOfOrigin");
            String description = jsonToString(rootJSON, "description");
            String image = jsonToString(rootJSON, "image");

            JSONArray ingredientsArray = rootJSON.getJSONArray("ingredients");
            List<String> ingredients = jsonToList(ingredientsArray);

            return new Sandwich(mainName, alsoKnownList, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> jsonToList(JSONArray array){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++){
            try {
                list.add(array.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    private static String jsonToString(JSONObject object, String attribute){
        String value = null;
        try {
            value =  object.getString(attribute);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return value;
    }
}

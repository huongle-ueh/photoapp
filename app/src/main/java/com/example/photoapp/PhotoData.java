package com.example.photoapp;

import android.content.Context;
import android.util.Log;

import androidx.annotation.VisibleForTesting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class PhotoData {

    public static ArrayList<Photo> loadPhotosFromJSON(Context context){
        ArrayList<Photo> photos = new ArrayList<>();

        try{
            InputStream inputStream = context.getAssets().open("PhotoData.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String source_photo, title_photo, description_photo;
            int id;

            String json;
            json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                id = jsonObject.getInt("id");
                source_photo = jsonObject.getString("source_photo");
                title_photo = jsonObject.getString("title_photo");
                description_photo = jsonObject.getString("description_photo");
                photos.add(new Photo(id, source_photo, title_photo, description_photo));
            }

        }catch (Exception e){
            Log.e("TAG", "loadPhotosFromJSON: error" + e);
        }

        return photos;
    }

    public static Photo getPhotoFromID (int id, Context context){
        ArrayList<Photo> phs = loadPhotosFromJSON(context);
        for (int i = 0; i < phs.size(); i++){
            if (phs.get(i).getId() == id){
                return phs.get(i);
            }
        }
        return null;
    }
}

package com.novytes.fuzzz.app.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.util.Log;
import com.google.gson.Gson;
import com.novytes.fuzzz.app.R;
import com.novytes.fuzzz.app.models.TargetInfo;

/**
 * Created by res on 21/10/14.
 */
public class PreferenceHelper {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor preferencesEditor;

    public PreferenceHelper(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(context.getResources().getString(R.string.preference_file),
                                                    Context.MODE_PRIVATE);
        preferencesEditor = preferences.edit();

    }

    public void writeInt(String key, int value){
        preferencesEditor.putInt(key, value);
        preferencesEditor.commit();
    }

    public void writeString(String key, String value){
        preferencesEditor.putString(key, value);
        preferencesEditor.commit();
    }

    public void writeTarget(String key, TargetInfo info){

        Gson gson = new Gson();
        String json = gson.toJson(info);
        preferencesEditor.putString(key, json);
        preferencesEditor.commit();

    }


    public int getInt(String key){
        return preferences.getInt(key, -1);
    }

    public String getString(String key){
        return preferences.getString(key, "");
    }

    public TargetInfo getTarget(String key){
        Gson gson = new Gson();
        String json = preferences.getString(key,"");
        TargetInfo info = gson.fromJson(json, TargetInfo.class);
        return info;
    }


}

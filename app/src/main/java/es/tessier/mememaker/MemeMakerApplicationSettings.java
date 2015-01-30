package es.tessier.mememaker;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Shader;
import android.preference.PreferenceManager;

import java.util.Map;
import java.util.Set;

import es.tessier.mememaker.utils.StorageType;

/**
 * Created by Evan Anger on 8/13/14.
 */
public class MemeMakerApplicationSettings {
    SharedPreferences mSharedPreferences;
    final static String KEY_STORAGE = "Storage";

    public MemeMakerApplicationSettings(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

    }

    public String getStoragePreference(){

        return mSharedPreferences.getString(KEY_STORAGE, StorageType.INTERNAL);
    }
    public void setSharedPreference(String storageType){

        mSharedPreferences
                .edit()
                .putString(KEY_STORAGE,StorageType.INTERNAL)
                .apply();

    }



}

package com.example.androiddiplom.database;

import android.content.Context;

import androidx.room.Room;

import com.example.androiddiplom.key.Keystore;
import com.example.androiddiplom.key.SimpleKeystore;

import dagger.Module;
import dagger.Provides;

@Module
public class StorageModule {
    private final Context mContext;
    private static final String DATA_BASE_NAME = "note_database";
    private static final String PREF_USER_PIN = "pref_user_pin";

    public StorageModule(Context context) {
        mContext = context;
    }

    @Provides
    public NotesDataBase getStorage() {
        return Room.databaseBuilder(mContext, NotesDataBase.class, DATA_BASE_NAME).build();
    }

    @Provides
    public Keystore getKeystore() {
        return new SimpleKeystore(mContext.getSharedPreferences(PREF_USER_PIN, Context.MODE_PRIVATE));
    }
}
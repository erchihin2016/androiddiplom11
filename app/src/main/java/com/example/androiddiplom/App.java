package com.example.androiddiplom;

import android.app.Application;

import com.example.androiddiplom.database.DaggerStorageComponent;
import com.example.androiddiplom.database.StorageComponent;
import com.example.androiddiplom.database.StorageModule;

public class App extends Application {

    private static StorageComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerStorageComponent
                .builder()
                .storageModule(new StorageModule(this))
                .build();
    }


    public static StorageComponent getComponent() {
        return component;
    }
}

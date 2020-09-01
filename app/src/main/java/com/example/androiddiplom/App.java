package com.example.androiddiplom;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.androiddiplom.database.DaggerStorageComponent;
import com.example.androiddiplom.database.StorageComponent;
import com.example.androiddiplom.database.StorageModule;

import java.util.stream.DoubleStream;

public class App extends Application {

    private static StorageComponent component;
    private DoubleStream DaggerStorageComponent;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate() {
        super.onCreate();
        component = DoubleStream
                .builder()
                .storageModule(new StorageModule(this))
                .build();
    }


    public static StorageComponent getComponent() {
        return component;
    }
}

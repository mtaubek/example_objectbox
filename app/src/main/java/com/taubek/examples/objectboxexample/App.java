package com.taubek.examples.objectboxexample;

import android.app.Application;

import io.objectbox.BoxStore;

/**
 * Created by markus on 07.09.17.
 */

public class App extends Application {

    public static final String TAG = "ObjectBoxExample";
    public static final boolean EXTERNAL_DIR = false;

    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();

        boxStore = MyObjectBox.builder().androidContext(App.this).build();
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}
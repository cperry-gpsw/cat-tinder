package com.example.cattinder.application;

import com.example.cattinder.util.Logger;
import com.example.cattinder.util.Logger.AndroidTree;

import android.app.Application;

public class CatTinder extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * Hook up the logger so it sends log calls
         * via Android's logger.
         */
        Logger.plant(new AndroidTree());
    }
}

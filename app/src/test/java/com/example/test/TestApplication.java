package com.example.test;

import org.junit.Ignore;

import android.app.Application;

/**
 * By default Robolectric uses the Application class
 * specified in the the AndroidManifest.
 *
 * Use this for tests that don't need the real Application
 * class specified there to run during your tests.
 */
@Ignore
public class TestApplication extends Application {

}

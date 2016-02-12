package com.example.test;

import com.example.cattinder.BuildConfig;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowContentResolver;
import org.robolectric.shadows.ShadowPreferenceManager;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Base class for Robolectric based tests.
 * This should ONLY contain common configuration. If
 * you would like to override, do that in your test class.
 *
 * See: http://robolectric.org/configuring/
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, application = TestApplication.class, sdk = 21)
public abstract class RobolectricTest {

    public Context getApplicationContext() {
        return RuntimeEnvironment.application;
    }

    public ContentResolver getContentResolver() {
        return getApplicationContext().getContentResolver();
    }

    public SharedPreferences getSharedPreferences() {
        return ShadowPreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

    public void registerContentProvider(String authority, ContentProvider contentProvider) {
        ShadowContentResolver.registerProvider(authority, contentProvider);
    }
}

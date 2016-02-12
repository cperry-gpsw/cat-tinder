package com.example.test;

import org.junit.Before;
import org.junit.Ignore;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

import android.app.Activity;
import android.content.Intent;

/**
 * Used for testing an Activity on the JVM.
 * Guarantees that the Activity is constructed before each test method is run.
 *
 * Hint: you can then hook up whatever dependencies you wish before
 * the Activity lifecycle is executed.
 *
 * To go through the Activity lifecycle and "show" your Activity call:
 *
 *          activityController.setup()
 *
 * this will create -> start -> resume the Activity and make it "visible".
 *
 *
 * @param <T>
 */
@Ignore
public abstract class ActivityTestCase<T extends Activity> extends RobolectricTest {
    private final Class<T> activityClass;
    protected ActivityController<T> activityController;
    protected T activity;

    public ActivityTestCase(Class<T> activityClass) {
        this.activityClass = activityClass;
    }

    // Optional override to build the Activity with an Intent
    protected Intent setupIntent() {
        return null;
    }

    @Before
    public void setupActivity() {
        Intent intent = setupIntent();
        if (intent != null) {
            activityController = Robolectric.buildActivity(activityClass).withIntent(intent);
        }
        else {
            activityController = Robolectric.buildActivity(activityClass);
        }

        activity = activityController.get();
    }
}

package com.example.cattinder.activity;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.test.ActivityInstrumentationTestCase2;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.cattinder.matchers.ActionbarMatchers.actionBarWithTitle;
import static com.example.cattinder.matchers.AdapterViewMatchers.withAdaptedData;
import static org.hamcrest.Matchers.not;

/**
 *
 * If you'd like to test your Activity running on the device you can do that here.
 * This is set up to use Espresso.
 *
 * Espresso docs advise to turn off all system animations to avoid test flakiness.
 * You can either do this with the device you are testing with (Developer Options -> Drawing),
 * or if you want to use a test runner that does it for you in code, you can find
 * that here:
 *
 * https://code.google.com/p/android-test-kit/wiki/DisablingAnimations
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

  public MainActivityTest() {
    super(MainActivity.class);
  }

  @Override protected void setUp() throws Exception {
    super.setUp();
    getActivity();
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // TODO: Write tests
  //////////////////////////////////////////////////////////////////////////////////////////////////


}

package com.cperry.cattinder.activity;

import android.test.ActivityInstrumentationTestCase2;

import com.cperry.cattinder.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.cperry.cattinder.matchers.ActionbarMatchers.actionBarWithTitle;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

  public MainActivityTest() {
    super(MainActivity.class);
  }

  @Override protected void setUp() throws Exception {
    super.setUp();
    getActivity();
  }

  public void test_SetsCorrectTitle() {
    onView(actionBarWithTitle("Meow =^..^=")).check(matches(isDisplayed()));
  }

  public void test_ShowsKittyStack() {
    onView(withId(R.id.kittyStack)).check(matches(isDisplayed()));
  }
}

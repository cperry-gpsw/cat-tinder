package com.cperry.cattinder.activity;

import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static com.cperry.cattinder.matchers.ActionbarMatchers.actionBarWithTitle;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

  public MainActivityTest() {
    super(MainActivity.class);
  }

  @Override protected void setUp() throws Exception {
    super.setUp();
    getActivity();
  }

  public void testSetsCorrectTitle() {
    onView(actionBarWithTitle("Meow =^..^=")).check(matches(isDisplayed()));
  }


}

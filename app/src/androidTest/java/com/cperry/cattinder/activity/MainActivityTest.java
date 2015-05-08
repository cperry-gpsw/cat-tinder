package com.cperry.cattinder.activity;

import android.support.test.espresso.ViewAction;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.cperry.cattinder.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.cperry.cattinder.matchers.ActionbarMatchers.actionBarWithTitle;
import static com.cperry.cattinder.matchers.AdapterViewMatchers.hasNumItems;
import static com.cperry.cattinder.matchers.AdapterViewMatchers.withAdapter;
import static org.hamcrest.Matchers.allOf;

/**
 * Espresso docs advise to turn off all system animations to avoid test flakiness, so
 * I did this with the device I was testing with.
 *
 * If instead you want to use a test runner that does it for you in code, you can find
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
  // TESTS
  //////////////////////////////////////////////////////////////////////////////////////////////////

  public void test_showsCorrectTitle() {
    onView(actionBarWithTitle("Meow =^..^=")).check(matches(isDisplayed()));
  }

  public void test_swipeCatLeft_shouldRemoveCat() {
    swipeCats_shouldRemoveCats(swipeLeft());
  }

  public void test_swipeCatRight_shouldRemoveCat() {
    swipeCats_shouldRemoveCats(swipeRight());
  }

  public void test_pressingNoButton_shouldRemoveCat() {
    pressButton_shouldRemoveCats(R.id.noButton);
  }

  public void test_pressingYesButton_shouldRemoveCat() {
    pressButton_shouldRemoveCats(R.id.yesButton);
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // SUPPORT CODE
  //////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   * Tried to make this code more concise, but the timing in Espresso
   * is not being friendly, and the tests would fail because there's no
   * way to make Espresso wait for the Views to finish animating before
   * performing the check.
   *
   * @param viewAction The swipe action to perform
   */
  void swipeCats_shouldRemoveCats(ViewAction viewAction) {
    // Remove one cat

    onView(allOf(withId(R.id.kittyStack), hasNumItems(3)))
      .perform(viewAction);

    onView(allOf(withId(R.id.kittyStack), hasNumItems(2)))
      .check(matches(hasNumItems(2)));

    // Remove one more cat

    onView(allOf(withId(R.id.kittyStack), hasNumItems(2)))
      .perform(viewAction);

    onView(allOf(withId(R.id.kittyStack), hasNumItems(1)))
      .check(matches(hasNumItems(1)));
  }

  void pressButton_shouldRemoveCats(int buttonId) {
    onView(allOf(withId(R.id.kittyStack), hasNumItems(3)));

    onView(allOf(withId(buttonId), isClickable()))
      .perform(click());

    onView(allOf(withId(R.id.kittyStack), hasNumItems(2)))
      .check(matches(hasNumItems(2)));
  }
}

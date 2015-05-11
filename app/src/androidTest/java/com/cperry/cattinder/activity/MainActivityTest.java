package com.cperry.cattinder.activity;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.test.ActivityInstrumentationTestCase2;

import com.cperry.cattinder.R;
import com.cperry.cattinder.data.Cats;

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
import static com.cperry.cattinder.matchers.ActionbarMatchers.actionBarWithTitle;
import static com.cperry.cattinder.matchers.AdapterViewMatchers.withAdaptedData;
import static org.hamcrest.Matchers.not;

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

  public void test_swipeCatLeft_cyclesThroughCats() {
    swipingACat_paginatesThroughCats(swipeLeft());
  }

  public void test_swipeCatRight_cyclesThroughCats() {
    swipingACat_paginatesThroughCats(swipeRight());
  }

  public void test_swipeCat_removesItFromTheAdapter() {
    onView(withText("Grumpy Cat 1")).perform(swipeLeft());
    onView(withText("Pirate Cat 2")).perform(swipeRight());

    onView(withText("Ceiling Cat 3")).check(matches(isDisplayed()));

    onView(withId(R.id.kittyStack)).check(doesNotContain("Grumpy Cat 1"));
    onView(withId(R.id.kittyStack)).check(doesNotContain("Pirate Cat 2"));
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // SUPPORT CODE
  //////////////////////////////////////////////////////////////////////////////////////////////////

  private ViewAssertion doesNotContain(String snippet) {
    return matches(not(withAdaptedData(withSnippet(snippet))));
  }

  private Matcher<Object> withSnippet(String snippet) {
    return new TypeSafeMatcher<Object>() {
      @Override public boolean matchesSafely(Object o) {
        if (!(o instanceof Cats.Cat)) {
          return false;
        }

        return ((Cats.Cat)o).getSnippet().equals(snippet);
      }

      @Override public void describeTo(Description description) {
        description.appendText("with snippet: " + snippet);
      }
    };
  }

  /**
   * Pages increment by 10, and each mocked dataset is only 3 items.
   * Note the number tacked onto the description, this indicates the page
   * index. See {@link com.cperry.cattinder.api.ServiceFactoryImpl} for details.
   */
  private void swipingACat_paginatesThroughCats(ViewAction swipeDirection) {
    // Page 1
    onView(withText("Grumpy Cat 1")).perform(swipeDirection);
    onView(withText("Pirate Cat 2")).perform(swipeDirection);
    onView(withText("Ceiling Cat 3")).perform(swipeDirection);

    // Page 2
    onView(withText("Grumpy Cat 11")).perform(swipeDirection);
    onView(withText("Pirate Cat 12")).perform(swipeDirection);
    onView(withText("Ceiling Cat 13")).perform(swipeDirection);

    // Page 3
    onView(withText("Grumpy Cat 21")).perform(swipeDirection);
    onView(withText("Pirate Cat 22")).perform(swipeDirection);
    onView(withText("Ceiling Cat 23")).perform(swipeDirection);
  }

}

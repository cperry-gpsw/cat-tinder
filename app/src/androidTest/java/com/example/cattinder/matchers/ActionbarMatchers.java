package com.example.cattinder.matchers;

import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;

public class ActionbarMatchers {

  /**
   * Verifies there is an ActionBar present with the corresponding title text.
   */
  public static Matcher<View> actionBarWithTitle(String text) {
    return allOf(isDescendantOfA(withResourceName("android:id/action_bar_container")),
      withText(text));
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Private Helper Methods
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  static Matcher<View> withResourceName(String resourceName) {
    return withResourceName(is(resourceName));
  }

  static Matcher<View> withResourceName(final Matcher<String> resourceNameMatcher) {
    return new TypeSafeMatcher<View>() {
      @Override
      public void describeTo(Description description) {
        description.appendText("with resource name: ");
        resourceNameMatcher.describeTo(description);
      }

      @Override
      public boolean matchesSafely(View view) {
        int id = view.getId();
        return id != View.NO_ID && id != 0 && view.getResources() != null
          && resourceNameMatcher.matches(view.getResources().getResourceName(id));
      }
    };
  }
}

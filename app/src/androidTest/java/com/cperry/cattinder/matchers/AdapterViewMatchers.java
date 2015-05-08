package com.cperry.cattinder.matchers;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class AdapterViewMatchers {

  /**
   * Like {@link #withAdapter()} but adds the additional
   * criteria that the attached adapter has a specific item count.
   */
  public static Matcher<View> hasNumItems(int itemCount) {
    return new TypeSafeMatcher<View>() {

      @Override public boolean matchesSafely(View view) {
        boolean isAdapterView = view instanceof AdapterView;
        if (!isAdapterView) {
          return false;
        }

        Adapter adapter = ((AdapterView) view).getAdapter();
        return adapter != null && adapter.getCount() == itemCount;
      }

      @Override public void describeTo(Description description) {
        description.appendText("with item count: " + itemCount);
      }
    };
  }

  /**
   * Simply checks that a View is an AdapterView
   * and that it has an adapter attached to it.
   */
  public static Matcher<View> withAdapter() {
    return new TypeSafeMatcher<View>() {

      @Override public boolean matchesSafely(View view) {
        boolean isAdapterView = view instanceof AdapterView;
        if (!isAdapterView) {
          return false;
        }

        Adapter adapter = ((AdapterView) view).getAdapter();
        return adapter != null;
      }

      @Override public void describeTo(Description description) {
        description.appendText("with items: ");
      }
    };
  }
}

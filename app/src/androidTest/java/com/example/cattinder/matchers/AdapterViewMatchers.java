package com.example.cattinder.matchers;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.android.support.test.deps.guava.base.Predicate;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class AdapterViewMatchers {

  /**
   * Like {@link #withAdapter()} but adds the additional
   * criteria that the attached adapter has specific data items.
   */
  public static Matcher<View> withAdaptedData(final Matcher<Object> dataMatcher) {
    return new TypeSafeMatcher<View>() {

      @Override
      public void describeTo(Description description) {
        description.appendText("with adapted data: ");
        dataMatcher.describeTo(description);
      }

      @Override
      public boolean matchesSafely(View view) {
        if (!(view instanceof AdapterView)) {
          return false;
        }
        @SuppressWarnings("rawtypes")
        Adapter adapter = ((AdapterView) view).getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
          if (dataMatcher.matches(adapter.getItem(i))) {
            return true;
          }
        }
        return false;
      }
    };
  }

  /**
   * Like {@link #withAdapter()} but adds the additional
   * criteria that the attached adapter has a specific item count.
   */
  public static Matcher<View> hasNumItems(int itemCount) {
    return withAdapter(adapter -> adapter.getCount() == itemCount);
  }

  /**
   * Checks that a View is an AdapterView
   * and that it has an adapter that contains at least one item.
   */
  public static Matcher<View> withAdaptedData() {
    return withAdapter(adapter -> adapter.getCount() > 0);
  }

  /**
   * Simply checks that a View is an AdapterView
   * and that it has an adapter attached to it.
   */
  public static Matcher<View> withAdapter() {
    return withAdapter(adapter -> true);
  }

  static Matcher<View> withAdapter(Predicate<Adapter> predicate) {
    return new TypeSafeMatcher<View>() {

      @Override public boolean matchesSafely(View view) {
        boolean isAdapterView = view instanceof AdapterView;
        if (!isAdapterView) {
          return false;
        }

        Adapter adapter = ((AdapterView) view).getAdapter();
        return adapter != null && predicate.apply(adapter);
      }

      @Override public void describeTo(Description description) {
        description.appendText("with class name: ");
      }
    };
  }
}

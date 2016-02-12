package com.example.test;

import org.junit.After;
import org.junit.Ignore;
import org.robolectric.Robolectric;
import org.robolectric.util.ActivityController;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;

@Ignore
public abstract class FragmentTestCase<T extends Fragment> extends RobolectricTest {

    private static final String FRAGMENT_TAG = "fragment";

    private ActivityController<? extends Activity> mController;
    private Activity mActivity;
    private T mFragment;

    /**
     * Adds the fragment to a new blank activity, thereby fully
     * initializing its view.
     */
    public void startFragment(Class<? extends Activity> activityClass, T fragment) {
        mFragment = fragment;
        mController = Robolectric.buildActivity(activityClass);
        mActivity = mController.create()
                               .start()
                               .resume()
                               .visible()
                               .get();
        FragmentManager manager = mActivity.getFragmentManager();
        manager.beginTransaction()
               .add(fragment, FRAGMENT_TAG)
               .commit();
    }

    @After
    public void destroyFragment() {
        if (mFragment != null) {
            FragmentManager manager = mActivity.getFragmentManager();
            manager.beginTransaction()
                   .remove(mFragment)
                   .commit();
            mFragment = null;
            mActivity = null;
        }
    }

    public void pauseAndResumeFragment() {
        mController.pause()
                   .resume();
    }

    @SuppressWarnings("unchecked")
    public T recreateFragment() {
        mActivity.recreate();
        // Recreating the activity creates a new instance of the
        // fragment which we need to retrieve by tag.
        mFragment = (T) mActivity.getFragmentManager()
                                 .findFragmentByTag(FRAGMENT_TAG);
        return mFragment;
    }


}

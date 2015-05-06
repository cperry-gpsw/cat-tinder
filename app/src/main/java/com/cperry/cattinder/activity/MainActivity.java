package com.cperry.cattinder.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;

import com.cperry.cattinder.R;
import com.cperry.cattinder.fragment.HotOrNotFragment;

import java.util.logging.Logger;

import timber.log.Timber;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getActionBar().setTitle("Meow =^..^=");
    Timber.plant(new Timber.DebugTree());

    showFragment(new HotOrNotFragment());
  }

  void showFragment(Fragment fragment) {
    getFragmentManager()
      .beginTransaction()
      .replace(R.id.container, fragment)
      .commit();
  }
}

package com.cperry.cattinder.activity;

import android.app.Activity;
import android.os.Bundle;

import com.cperry.cattinder.R;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getActionBar().setTitle("Meow =^..^=");

  }
}
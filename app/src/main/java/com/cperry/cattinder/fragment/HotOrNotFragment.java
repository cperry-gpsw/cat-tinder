package com.cperry.cattinder.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cperry.cattinder.R;

public class HotOrNotFragment extends Fragment {

  @Nullable @Override public View onCreateView(
    LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_hotornot, container, false);
  }
}

package com.cperry.cattinder.fragment;

import android.app.Fragment;
import android.view.View;

public class BaseFragment extends Fragment {

  @SuppressWarnings("all")
  public <T extends View> T findViewById(int id) {
    return (T) getView().findViewById(id);
  }

}

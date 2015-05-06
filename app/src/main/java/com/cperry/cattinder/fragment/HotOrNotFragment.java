package com.cperry.cattinder.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cperry.cattinder.R;

/**
 * Key: AIzaSyAZmvW6DecHAvtLCiqYQzmhKCNnsOYxtgo
 * Search Engine ID: 005351716643766109453:uusxxukdams
 *
 * https://www.googleapis.com/customsearch/v1?key=AIzaSyAZmvW6DecHAvtLCiqYQzmhKCNnsOYxtgo&cx=005351716643766109453:uusxxukdams&q=cat&searchType=image
 */
public class HotOrNotFragment extends Fragment {

  @Nullable @Override public View onCreateView(
    LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_hotornot, container, false);
  }
}

package com.cperry.cattinder.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cperry.cattinder.R;
import com.cperry.cattinder.data.Cats.Cat;
import com.cperry.cattinder.util.CollectionUtil;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.squareup.picasso.Picasso;

import java.util.List;

import timber.log.Timber;

/**
 * Key: AIzaSyAZmvW6DecHAvtLCiqYQzmhKCNnsOYxtgo
 * Search Engine ID: 005351716643766109453:uusxxukdams
 *
 * https://www.googleapis.com/customsearch/v1?key=AIzaSyAZmvW6DecHAvtLCiqYQzmhKCNnsOYxtgo&cx=005351716643766109453:uusxxukdams&q=cat&searchType=image
 */
public class HotOrNotFragment extends BaseFragment {

  @Nullable @Override public View onCreateView(
    LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_hotornot, container, false);
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    List<Cat> cats = CollectionUtil.newList(
      new Cat(
        "http://truestorieswithgill.com/wp-content/uploads/2013/09/20130915-190532.jpg",
        "Grumpy Cat"
      ),
      new Cat(
        "https://fbcdn-sphotos-g-a.akamaihd.net/hphotos-ak-xaf1/v/t1" +
          ".0-9/304276_294451973899431_1913235174_n.jpg?oh=2b4d96f7f49d45b572902cfd346c78a7&oe=55D6655C&__gda__=1438927245_cf2c5b0a5881ae9029812edef6e4c32b",
        "Pirate Cat"
      ),
      new Cat(
        "http://static.giantbomb.com/uploads/scale_small/4/45471/1137644-ceiling_cat_900.jpg.png",
        "Ceiling Cat"
      )
    );

    KittyAdapter adapter = new KittyAdapter(
      LayoutInflater.from(getActivity()),
      Picasso.with(getActivity()),
      cats
    );

    SwipeFlingAdapterView stackView = findViewById(R.id.kittyStack);
    stackView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
      @Override public void removeFirstObjectInAdapter() {
        cats.add(cats.remove(0));
        adapter.notifyDataSetChanged();
      }

      @Override public void onLeftCardExit(Object o) {
        Timber.d("onLeftCardExit: " + o);
      }

      @Override public void onRightCardExit(Object o) {
        Timber.d("onRightCardExit: " + o);
      }

      @Override public void onAdapterAboutToEmpty(int i) {

      }

      @Override public void onScroll(float v) {
        // Negative values means left, positive means right
        Timber.d("onScroll: " + v);
        View view = stackView.getSelectedView();
        if (v < 0) {
          view.findViewById(R.id.no).setAlpha(v * -0.8f);
        } else if (v > 0) {
          view.findViewById(R.id.yes).setAlpha(v * 0.8f);
        } else {
          view.findViewById(R.id.no).setAlpha(0);
          view.findViewById(R.id.yes).setAlpha(0);
        }
      }
    });

    stackView.setAdapter(adapter);
  }

  static class KittyAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final Picasso picasso;
    private final List<Cat> cats;

    KittyAdapter(LayoutInflater inflater, Picasso picasso, List<Cat> cats) {
      this.inflater = inflater;
      this.picasso = picasso;
      this.cats = cats;
    }

    @Override public int getCount() {
      return cats.size();
    }

    @Override public Cat getItem(int position) {
      return cats.get(position);
    }

    @Override public long getItemId(int position) {
      return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
      View view = convertView;
      if (view == null) {
        view = inflater.inflate(R.layout.kitty_cat, parent, false);
      }

      Cat cat = getItem(position);

      int size = (int) dipsToPixels(view.getContext(), 190f);
      picasso.load(cat.getLink())
        .resize(size, size)
        .centerCrop()
        .noFade()
        .into((ImageView) view.findViewById(R.id.image));

      ((TextView) view.findViewById(R.id.snippet)).setText(cat.getSnippet());

      return view;
    }

    float dipsToPixels(Context context, float dipValue) {
      DisplayMetrics metrics = context.getResources().getDisplayMetrics();
      return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }
  }
}

package com.cperry.cattinder.adapter;

import android.os.AsyncTask;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class PaginationAdapter<T> extends BaseAdapter {
  private final List<T> items = new ArrayList<>();
  private final Paginator<List<T>> paginator;

  public PaginationAdapter(Paginator<List<T>> paginator) {
    this.paginator = paginator;
  }

  public void loadNextPage() {
    new AsyncTask<Void, Void, List<T>>() {

      @Override protected List<T> doInBackground(Void... params) {
        return paginator.getNextPage();
      }

      @Override protected void onPostExecute(List<T> nextPage) {
        items.addAll(nextPage);
        notifyDataSetChanged();
      }
    }.execute();
  }

  public void removeItem(int position) {
    if (position < getCount()) {
      items.remove(position);
      notifyDataSetChanged();
    }
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  //  BaseAdapter methods
  //////////////////////////////////////////////////////////////////////////////////////////////////

  @Override public int getCount() {
    return items.size();
  }

  @Override public T getItem(int position) {
    return items.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

}

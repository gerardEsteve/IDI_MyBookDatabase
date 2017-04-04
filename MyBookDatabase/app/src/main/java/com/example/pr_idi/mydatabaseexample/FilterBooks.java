package com.example.pr_idi.mydatabaseexample;

import android.widget.Filter;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by gerard on 19/12/2016.
 */
public class FilterBooks extends Filter {

    private final Adaptador mAdapter;
    List<Book> LB;
    ArrayList<Book> LBfiltered;

    public FilterBooks(Adaptador mAdapter, List<Book> l) {
        this.mAdapter = mAdapter;
        LB = l;
        LBfiltered = new ArrayList<>();
    }


    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        LBfiltered.clear();
        final FilterResults results = new FilterResults();

        if (constraint.length() == 0) {
            LBfiltered.addAll(LB);
        } else {
            for (final Book b : LB) {

                if (b.getTitle().toLowerCase().contains(constraint.toString().toLowerCase())) {
                    LBfiltered.add(b);
                }
            }
        }
        results.values = LBfiltered;
        results.count = LBfiltered.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
   //     mAdapter.customerList.clear();
        mAdapter.ListaBooks.clear();
        mAdapter.ListaBooks.addAll( (List<Book>) results.values);
        mAdapter.notifyDataSetChanged();
    }
}

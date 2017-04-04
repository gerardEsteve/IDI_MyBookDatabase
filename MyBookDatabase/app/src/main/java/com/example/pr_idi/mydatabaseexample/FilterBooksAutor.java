package com.example.pr_idi.mydatabaseexample;

import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by DrakksiatH on 04/01/2017.
 */

public class FilterBooksAutor extends Filter {
    private final ArrayBookAdapter mAdapter;
    List<Book> LB;
    ArrayList<Book> LBfiltered;

    public FilterBooksAutor(ArrayBookAdapter mAdapter, List<Book> l) {
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

                if (b.getAuthor().toLowerCase().contains(constraint.toString().toLowerCase())) {
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
        mAdapter.ListaBooks.clear();
        mAdapter.ListaBooks.addAll( (List<Book>) results.values);
        mAdapter.clear();
        mAdapter.addAll(mAdapter.ListaBooks);
        mAdapter.sort(new Comparator<Book>() {
            @Override
            public int compare(Book lhs, Book rhs) {
                return lhs.getTitle().compareTo(rhs.getTitle());

            }
        });
        mAdapter.notifyDataSetChanged();
    }
}

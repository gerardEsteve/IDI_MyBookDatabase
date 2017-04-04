package com.example.pr_idi.mydatabaseexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DrakksiatH on 07/01/2017.
 */

public class ArrayBookAdapter extends ArrayAdapter<Book> {
    List<Book> ListaBooks;
    List<Book> ListaBooksBona;

    public ArrayBookAdapter(Context context, int resource) {
        super(context, resource);
    }
    public FilterBooksAutor getFiltrePerAutor(List<Book> lista){
        return new FilterBooksAutor(this,lista);
    }

    public ArrayBookAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        ListaBooks = new ArrayList<>(objects);
        ListaBooksBona =new ArrayList<>(objects);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new FilterBooksAutor(this,ListaBooksBona);
    }

    @Override
    public void add(Book object) {
        super.add(object);
        ListaBooks.add(object);
        ListaBooksBona.add( object);
    }
}

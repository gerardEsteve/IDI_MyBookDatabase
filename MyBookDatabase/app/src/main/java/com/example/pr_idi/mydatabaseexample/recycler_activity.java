package com.example.pr_idi.mydatabaseexample;

import android.app.FragmentManager;
import android.net.Uri;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import android.app.DialogFragment;
import android.app.FragmentTransaction;

import android.support.design.widget.FloatingActionButton;
import android.view.View;

public class recycler_activity extends ActionBarActivity /*AppCompatActivity*/ implements DeleteInterface, frag_add_llibre.OnFragmentInteractionListener{
    Adaptador adapt;
    BookData BD;
    List<Book> listaBook;

    RecyclerView rv;

    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_recycler);

        rv = (RecyclerView) findViewById(R.id.RV);
        LinearLayoutManager LLM = new LinearLayoutManager(this);
        rv.setLayoutManager(LLM);

        BD = new BookData(this);
        BD.open();
        listaBook = BD.getAllBooksCategoria();

        adapt = new Adaptador(listaBook,this,this);
        rv.setAdapter(adapt);


        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // pop up de about
             /*   FragmentTransaction frag = getFragmentManager().beginTransaction();
                DialogFragment dialogFragment = frag_about.newInstance();
                dialogFragment.show(frag, "New book");*/

                // pop up de Help
                /*
                FragmentTransaction frag = getFragmentManager().beginTransaction();
                DialogFragment dialogFragment = frag_help.newInstance();
                dialogFragment.show(frag, "New book");

                */

                // pop up de ADD llibre

                FragmentTransaction frag = getFragmentManager().beginTransaction();
                DialogFragment dialogFragment = frag_add_llibre.newInstance(fab);
                dialogFragment.show(frag, "New book");

           //     listaBook.add(book);
           //     adapt = new Adaptador(listaBook,recycler_activity.this);
           //     rv.setAdapter(adapt);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recycler_activity, menu);

        fab.show();


        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Cerca per Titol...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

             return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapt.getFilter().filter(newText);
                return false;
            }
        });



        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
     //  int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        fab.show();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void bookDeleted(Book b) {
        listaBook.remove(b);

        adapt = new Adaptador(listaBook,recycler_activity.this,this);
        rv.setAdapter(adapt);
        BD.deleteBook(b);

    }

    @Override
    protected void onResume() {
        BD.open();
        super.onResume();
    }

    // Life cycle methods. Check whether it is necessary to reimplement them

    @Override
    protected void onPause() {
        BD.close();
        super.onPause();
    }

    @Override
    public void onFragmentInteraction(String titol, String autor, String categoria, String valoracioP, int any, String editorial ) {

        fab.show();

        String sAny = String.valueOf(any);

        Book book = BD.createBook(titol,autor,editorial,sAny,categoria,valoracioP);

          //   listaBook.add(book);
        listaBook = BD.getAllBooksCategoria();

             adapt = new Adaptador(listaBook,recycler_activity.this,this);
             rv.setAdapter(adapt);

    }
}

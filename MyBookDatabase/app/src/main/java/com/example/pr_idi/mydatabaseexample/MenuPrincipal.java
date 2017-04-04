package com.example.pr_idi.mydatabaseexample;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class MenuPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, frag_add_llibre.OnFragmentInteractionListener, frag_valoracio.OnFragmentInteractionListener {

    private ArrayBookAdapter adapter;
    private BookData bookData;
    private ListView llistaLlibres;
    private int posicio;
    List<Book> values;
    String valoracioP;
    String autor;
    String titol;
    int any;
    String editorial;
    String categoria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        llistaLlibres = (ListView) findViewById(R.id.lista2);


        bookData = new BookData(this);
        bookData.open();

        List<Book> values = bookData.getAllBooks();


        Collections.sort(values,new Comparator<Book>() {
            @Override
            public int compare(Book lhs, Book rhs) {
                return lhs.getTitle().compareTo(rhs.getTitle());

            }
        });


        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        adapter = new ArrayBookAdapter(this,
                android.R.layout.simple_list_item_1,new ArrayList<Book>());


        llistaLlibres.setAdapter(adapter);
        for (Book book : values) {
            adapter.add(book);
        }
        adapter.sort(new Comparator<Book>() {
            @Override
            public int compare(Book lhs, Book rhs) {
                return lhs.getTitle().compareTo(rhs.getTitle());

            }
        });
        adapter.notifyDataSetChanged();




        llistaLlibres.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View  view, int position, long id)
            {
                posicio=position;
                FragmentTransaction frag = getFragmentManager().beginTransaction();
                DialogFragment dialogFragment = frag_valoracio.newInstance();
                dialogFragment.show(frag, "New book");
            }
        });



    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);

        final MenuItem searchItem = menu.findItem(R.id.autor_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Cerca per Autor...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
  //      int id = item.getItemId();

        //noinspection SimplifiableIfStatement
  /*      if (id == R.id.action_settings) {
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // pop up de ADD llibre
            FragmentTransaction frag = getFragmentManager().beginTransaction();
            DialogFragment dialogFragment = frag_add_llibre.newInstance();
            dialogFragment.show(frag, "New book");



        } else if (id == R.id.nav_gallery) {
            Intent jumper = new Intent(MenuPrincipal.this,recycler_activity.class);
            startActivity(jumper);


        }
        /*else if (id == R.id.nav_slideshow) {

        }

        else if (id == R.id.nav_manage) {}
        */else if (id == R.id.nav_share) {
            // pop up de Help
            FragmentTransaction frag2 = getFragmentManager().beginTransaction();
            DialogFragment dialogFragment2 = frag_help.newInstance();
            dialogFragment2.show(frag2, "New book");

        } else if (id == R.id.nav_send) {
            // pop up de about
            FragmentTransaction frag = getFragmentManager().beginTransaction();
            DialogFragment dialogFragment = frag_about.newInstance();
            dialogFragment.show(frag, "New book");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        //ArrayAdapter<Book> adapter = (ArrayAdapter<Book>) llistaLlibres.getAdapter();
        Book book;
        switch (view.getId()) {

           /* case R.id.add2:
                String[] newBook = new String[] { "Miguel Strogoff", "Jules Verne", "Ulysses", "James Joyce", "Don Quijote", "Miguel de Cervantes", "Metamorphosis", "Kafka" };
                int nextInt = new Random().nextInt(4);
                // save the new book to the database
                bookData.open();
                book = bookData.createBook(newBook[nextInt*2], newBook[nextInt*2 + 1],"","","","");

                // After I get the book data, I add it to the adapter
                adapter.add(book);
                adapter.sort(new Comparator<Book>() {
                    @Override
                    public int compare(Book lhs, Book rhs) {
                        return lhs.getTitle().compareTo(rhs.getTitle());

                    }
                });
                break;
            */
            /*case R.id.delete2:
                if (llistaLlibres.getAdapter().getCount() > 0) {
                    book = (Book) llistaLlibres.getAdapter().getItem(0);
                    bookData.deleteBook(book);
                    adapter.remove(book);
                }
                break;
            */
            /*case R.id.jump2:
                Intent jumper = new Intent(MenuPrincipal.this,recycler_activity.class);
                startActivity(jumper);
                break;*/
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        bookData.open();
        values = bookData.getAllBooks();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        adapter = new ArrayBookAdapter(this,
                android.R.layout.simple_list_item_1, values);
        adapter.sort(new Comparator<Book>() {
            @Override
            public int compare(Book lhs, Book rhs) {
                return lhs.getTitle().compareTo(rhs.getTitle());

            }
        });
        llistaLlibres.setAdapter(adapter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        bookData.close();
        super.onPause();
    }

    @Override
    public void onFragmentInteraction(String titol, String autor, String categoria, String valoracioP, int any, String editorial) {
        String sAny = String.valueOf(any);

        Book book = bookData.createBook(titol,autor,editorial,sAny,categoria,valoracioP);

        //   listaBook.add(book);
        values = bookData.getAllBooks();
        adapter = new ArrayBookAdapter(this,
                android.R.layout.simple_list_item_1, values);
        adapter.sort(new Comparator<Book>() {
            @Override
            public int compare(Book lhs, Book rhs) {
                return lhs.getTitle().compareTo(rhs.getTitle());

            }
        });
        llistaLlibres.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction2(String valoracioP) {
        Book aux = (Book) llistaLlibres.getItemAtPosition(posicio);
/*
        autor=aux.getAuthor();
        titol=aux.getTitle();
        any=aux.getYear();
        String sAny = String.valueOf(any);
        editorial=aux.getPublisher();
        categoria=aux.getCategory();
        Book book = bookData.createBook(titol,autor,editorial,sAny,categoria,valoracioP);

        bookData.deleteBook(aux);

*/
        bookData.editBook(aux,valoracioP);
        aux.setPersonal_evaluation(valoracioP);
        adapter.notifyDataSetChanged();
}
}

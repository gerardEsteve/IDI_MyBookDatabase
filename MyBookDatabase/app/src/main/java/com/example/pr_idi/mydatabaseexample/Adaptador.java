package com.example.pr_idi.mydatabaseexample;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerard on 18/11/2016.
 */
public class Adaptador extends RecyclerView.Adapter<Adaptador.BookViewHolder> implements Filterable, frag_confirm_delete.OnFragmentInteractionListener /*implements View.OnClickListener*/ {

    List<Book> ListaBooks;
    List<Book> ListaBooksBona;
    DeleteInterface deleteInterface;
    int x;
    Book b;
    Activity activity;
    BookViewHolder holder2;

    public Adaptador(List<Book> listaBooks, DeleteInterface del, Activity a) {
        ListaBooks = new ArrayList<>(listaBooks);
        ListaBooksBona =new ArrayList<>(listaBooks);
        deleteInterface = del;
        activity = a;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prueba,parent,false);
        BookViewHolder BVH = new BookViewHolder(v);
        return BVH;
    }

    @Override
    public void onBindViewHolder(final BookViewHolder holder, int position) {

       if (ListaBooks.get(position).getAuthor() != null){
            holder.autBook.setText(ListaBooks.get(position).getAuthor());
        }
        if(ListaBooks.get(position).getTitle() != null){
            holder.titBook.setText(ListaBooks.get(position).getTitle());

        }
        String s = String.valueOf(ListaBooks.get(position).getYear());

        holder.yearBook.setText(s);

        if (ListaBooks.get(position).getPublisher() != null){
            holder.publiBook.setText(ListaBooks.get(position).getPublisher());
        }
        if(ListaBooks.get(position).getCategory() != null){
            holder.cateBook.setText(ListaBooks.get(position).getCategory());
        }
       if (ListaBooks.get(position).getPersonal_evaluation() != null){
           holder.persBook.setText(ListaBooks.get(position).getPersonal_evaluation());

       }



        holder.but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // mostrar el dialog de confirm delete

                x = holder.getAdapterPosition();
                b = ListaBooks.get(x);


                FragmentTransaction frag =  activity.getFragmentManager().beginTransaction();
                DialogFragment dialogFragment = frag_confirm_delete.newInstance(Adaptador.this);
                dialogFragment.show(frag, "Delete");

                holder2=holder;

                holder2.but.setEnabled(false);


            }
        });
    }

    @Override
    public int getItemCount() {
        return ListaBooks.size();
    }

    @Override
    public Filter getFilter() {
        return new FilterBooks(this,ListaBooksBona);
    }


    public void addLlibre(Book b){
        ListaBooksBona.add(b);

        notifyItemInserted(ListaBooksBona.size()-1);
    }

    @Override
    public void onFragmentInteraction(Boolean esborra) {
        if (esborra == true ){
            deleteInterface.bookDeleted(b);
            ListaBooks.remove(b);
            notifyItemRemoved(x);
        }
        else {
            holder2.but.setEnabled(true);
        }
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder{
        Button but;
        TextView autBook;
        TextView titBook;
        TextView yearBook;
        TextView publiBook;
        TextView cateBook;
        TextView persBook;

    public BookViewHolder(View itemView) {
        super(itemView);

        titBook = (TextView) itemView.findViewById(R.id.titleBook);              // Titol
        yearBook = (TextView) itemView.findViewById(R.id.yearBook);              // Any Publicacio
        publiBook = (TextView) itemView.findViewById(R.id.publisherBook);        // Editorial
        cateBook= (TextView) itemView.findViewById(R.id.categoryBook);           // Categoria
        persBook = (TextView) itemView.findViewById(R.id.personalBook);          // Valoracio
        autBook = (TextView) itemView.findViewById(R.id.AuthB);                  // Autor
        but = (Button) itemView.findViewById(R.id.button);
    }
}

}

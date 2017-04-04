package com.example.pr_idi.mydatabaseexample;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frag_add_llibre.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frag_add_llibre#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frag_add_llibre extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  /*  private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
*/
    // TODO: Rename and change types of parameters
  /*  private String mParam1;
    private String mParam2;
*/
    private OnFragmentInteractionListener mListener;
    String Categoria;
    String Valoracio;
    private static int fabint;
   static FloatingActionButton f;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * param param1 Parameter 1.
     * param param2 Parameter 2.
     * @return A new instance of fragment frag_add_llibre.
     */
    // TODO: Rename and change types and number of parameters
    public static frag_add_llibre newInstance(FloatingActionButton fab) {
        frag_add_llibre fragment = new frag_add_llibre();
        fabint = 0;
        f = fab;


      /*  Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    public static frag_add_llibre newInstance() {
        frag_add_llibre fragment = new frag_add_llibre();
        fabint = 1;
        return fragment;
    }

    public frag_add_llibre() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
          //  mParam1 = getArguments().getString(ARG_PARAM1);
           // mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag_add_llibre, container, false);

        final EditText editTextAutor = (EditText)v.findViewById(R.id.editAutor);
        final EditText editTextTitol = (EditText)v.findViewById(R.id.editTitol);
        final EditText editTextYear = (EditText)v.findViewById(R.id.editYear);
        final EditText editTextPublisher = (EditText)v.findViewById(R.id.editPublisher);
        Button cancel = (Button) v.findViewById(R.id.cancelButton);
        cancel.setTextColor(Color.WHITE);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if(fabint==0)f.show();
            }
        });

        String [] valuesCategoria =
                {"Acció","Fantasia","Biografia","Històric","Infantil","Poesia","Terror",};
        String [] valuesValoracio =
                {"Molt bo","Bo","Regular","Dolent","Molt dolent",};

        Spinner spinnerCat = (Spinner)v.findViewById(R.id.spinner);
        Spinner spinnerVal = (Spinner)v.findViewById(R.id.spinner2);

        spinnerCat.setAdapter(new ArrayAdapter<String>(this.getActivity(), R.layout.custom_spinner, valuesCategoria));
        spinnerVal.setAdapter(new ArrayAdapter<String>(this.getActivity(), R.layout.custom_spinner, valuesValoracio));

        spinnerCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // hacer que el campo con valor categoria prenda por valor el item seleccionado.
                Categoria = parent.getItemAtPosition(position).toString(); // en s esta el valor.

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerVal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                // hacer que el campo con valor valoracion prenda por valor el item seleccionado.
                Valoracio = parent.getItemAtPosition(position).toString(); // en s esta el valor.

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button acceptar = (Button) v.findViewById(R.id.acceptButton);
        acceptar.setTextColor(Color.WHITE);
        acceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String autor = editTextAutor.getText().toString();
                String titol = editTextTitol.getText().toString();
                String s = editTextYear.getText().toString().trim();
                String publisher = editTextPublisher.getText().toString();
                if( !autor.trim().equals("") && !titol.trim().equals("") && !s.equals("") && !publisher.trim().equals("")){
                    int year = Integer.parseInt(s);

                    mListener.onFragmentInteraction(titol,autor,Categoria,Valoracio,year,publisher);///////////////////////////////////////////////////////////////////
                    dismiss();

                }
               else{
                    FragmentTransaction frag = getFragmentManager().beginTransaction();
                    DialogFragment dialogFragment = frag_error.newInstance();
                    dialogFragment.show(frag, "Error");
                }

                //String titol, String autor, String categoria, String valoracioP, int any, String editorial
            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String titol, String autor, String categoria, String valoracioP, int any, String editorial) {
        if (mListener != null) {
            mListener.onFragmentInteraction(titol,autor,categoria,valoracioP,any,editorial);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String titol, String autor, String categoria, String valoracioP, int any, String editorial);
    }

}

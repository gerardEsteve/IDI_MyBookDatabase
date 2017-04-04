package com.example.pr_idi.mydatabaseexample;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link frag_valoracio.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link frag_valoracio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frag_valoracio extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;
    String valoracioP;

    private OnFragmentInteractionListener mListener;

    public frag_valoracio() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
    // * @param param1 Parameter 1.//* @param param2 Parameter 2.
     * @return A new instance of fragment frag_valoracio.
     */
    // TODO: Rename and change types and number of parameters
    public static frag_valoracio newInstance() {
        frag_valoracio fragment = new frag_valoracio();
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        */
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           // mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag_valoracio, container, false);
        String [] valuesValoracio =
                {"Molt bo","Bo","Regular","Dolent","Molt dolent",};
        Spinner spinnerVal = (Spinner)v.findViewById(R.id.spinner2);
        spinnerVal.setAdapter(new ArrayAdapter<String>(this.getActivity(), R.layout.custom_spinner, valuesValoracio));
        spinnerVal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // hacer que el campo con valor valoracion prenda por valor el item seleccionado.
                valoracioP = parent.getItemAtPosition(position).toString(); // en s esta el valor.
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
                    mListener.onFragmentInteraction2(valoracioP);///////////////////////////////////////////////////////////////////
                    dismiss();
            }
        });





        //**************VALORACIO TEXT*****************
        //return inflater.inflate(R.layout.fragment_frag_valoracio, container, false);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String titol, String autor, String categoria, String valoracioP, int any, String editorial) {
        if (mListener != null) {
            mListener.onFragmentInteraction2(valoracioP);
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction2(String valoracioP);
    }
}

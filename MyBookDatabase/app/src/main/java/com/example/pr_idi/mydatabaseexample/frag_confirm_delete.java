package com.example.pr_idi.mydatabaseexample;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class frag_confirm_delete extends DialogFragment  {


    private static OnFragmentInteractionListener mListener;


    public static frag_confirm_delete newInstance(OnFragmentInteractionListener onF) {
        frag_confirm_delete fragment = new frag_confirm_delete();
        mListener = onF;

        return fragment;
    }

    public frag_confirm_delete() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_frag_confirm_delete, container, false);
        Button ok = (Button) v.findViewById(R.id.acceptB);
        ok.setTextColor(Color.WHITE);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(true);
                dismiss();
            }
        });

        Button can = (Button) v.findViewById(R.id.canB);
        can.setTextColor(Color.WHITE);

        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(false);
                dismiss();
            }
        });

        return v;
    }

    public void onButtonPressed(Boolean b) {
        if (mListener != null) {
            mListener.onFragmentInteraction(b);
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Boolean esborra);
    }



}

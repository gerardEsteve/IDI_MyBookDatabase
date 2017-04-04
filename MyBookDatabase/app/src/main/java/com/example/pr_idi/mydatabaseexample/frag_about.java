package com.example.pr_idi.mydatabaseexample;

import android.app.Activity;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class frag_about extends DialogFragment implements View.OnClickListener {

    public static frag_about newInstance() {
        frag_about fragment = new frag_about();

        return fragment;
    }

    public frag_about() {
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
        View v = inflater.inflate(R.layout.fragment_frag_about, container, false);


        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        TextView text = (TextView) v.findViewById(R.id.aboutText);
        text.setText(R.string.about);

        Button close = (Button) v.findViewById(R.id.closeAbout);
        close.setOnClickListener(this);

        return v;
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.closeAbout) {
            dismiss();
        }
    }
}

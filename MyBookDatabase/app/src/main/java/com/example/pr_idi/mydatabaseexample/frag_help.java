package com.example.pr_idi.mydatabaseexample;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class frag_help extends android.app.DialogFragment implements View.OnClickListener {

    myExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public static frag_help newInstance() {
        frag_help fragment = new frag_help();
        return fragment;
    }

    public frag_help() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_help, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        expListView = (ExpandableListView) v.findViewById(R.id.expandableListView);

        prepareListData();

        listAdapter = new myExpandableListAdapter(getActivity(),listDataHeader,listDataChild);
        expListView.setAdapter(listAdapter);

        Button close = (Button) v.findViewById(R.id.button2);

        close.setOnClickListener(this);

        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button2) {
            dismiss();
        }
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        listDataHeader.add("Editar Llibreria");
        List<String> edit = new ArrayList<>();
        edit.add("Fent click a Editar Llibreria anirem a la recycler view del bloc A");
        edit.add("Tindrem una visualització dels llibres segons la categoria");
        edit.add("Podrem afegir llibres mitjançant el FAB button o eliminar-los fent click a l'icona 'X'");


        listDataChild.put(listDataHeader.get(0), edit);

        listDataHeader.add("Afegir Llibre");
        List<String> addL = new ArrayList<>();
        addL.add("-   Clicant el FAB button d'abaix a la dreta en el Bloc A");
        addL.add("-   Clicant sobre afegir llibre al Menu de l'aplicació");
        addL.add("En totes dues opcions sortirà un Pop Up per introduir la informació del llibre");
        addL.add("Si no introduim tots els valors de forma correcta sortirà un missatge d'error");
        addL.add("Si cliquem a cancel·lar perdrem tota la informació introduida");

        listDataChild.put(listDataHeader.get(1), addL);

        listDataHeader.add("Eliminar llibre");
        List<String> delete = new ArrayList<>();
        delete.add("-   Clicant sobre l'icona 'X' a la llista d'elements");
        delete.add("Surt un missatge de confirmació i a l'acceptar s'elimina el llibre seleccionat");
        listDataChild.put(listDataHeader.get(2), delete);

        listDataHeader.add("Canvi de valoració personal");
        List<String> valP = new ArrayList<>();
        valP.add("-   Clicant sobre el llibre de la llista en el BlocB");
        valP.add("Surt un Pop Up amb un Spinner per a canviar la valoració del llibre seleccionat");
        listDataChild.put(listDataHeader.get(3), valP);

        listDataHeader.add("Visualització de titols per autor");
        List<String> vA = new ArrayList<>();
        vA.add("Al escriure el nom d'un autor de forma correcta al SearchView del BlocB obtindrem una llista dels titols d'aquest autor ");
        listDataChild.put(listDataHeader.get(4), vA);

        listDataHeader.add("Visualització de llibres ordenats per títol");
        List<String> vS = new ArrayList<>();
        vS.add("Al bloc B, es mostren els llibres ordenats pel títol");
        listDataChild.put(listDataHeader.get(5), vS);

        listDataHeader.add("Visualització dels llibres per categoria");
        List<String> vC = new ArrayList<>();
        vC.add("Es mostren els llibres ordenats per la seva categoria");

        listDataChild.put(listDataHeader.get(6), vC);

        listDataHeader.add("Cerca per titol i eliminar");
        List<String> cercaT = new ArrayList<>();
        cercaT.add("Al escriure un text a la SearchView del BlocA, l'aplicació buscarà llibres tals que el seu titol contingui el text escrit");
        cercaT.add("Els llibres que vagin sortint d'aquesta cerca també poden ser eliminats mitjançant l'icona 'X'.");
        listDataChild.put(listDataHeader.get(7), cercaT);

    }

}

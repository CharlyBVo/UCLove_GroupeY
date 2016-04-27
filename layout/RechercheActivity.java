package com.example.charles_henry.testquimarche;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class RechercheActivity extends AppCompatActivity {
    RechercheFiltres filtre;
    String LogUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        final RechercheFiltresManager RechercheManager = new RechercheFiltresManager(this);
        LogUtil = getIntent().getStringExtra("LogUtil");
        RechercheManager.open();
        filtre = RechercheManager.getRechercheFiltres(LogUtil);
        RechercheManager.close();

        /*ADAPTERS*/

        ArrayAdapter<CharSequence> adapterGenre = ArrayAdapter.createFromResource(this, R.array.ChoixGenre, android.R.layout.simple_spinner_item);
        adapterGenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapterAge = ArrayAdapter.createFromResource(this, R.array.ChoixAge, android.R.layout.simple_spinner_item);
        adapterGenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapterLangue = ArrayAdapter.createFromResource(this, R.array.ChoixLangue, android.R.layout.simple_spinner_item);
        adapterGenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapterCheveux = ArrayAdapter.createFromResource(this, R.array.ChoixCheveux, android.R.layout.simple_spinner_item);
        adapterGenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapterYeux = ArrayAdapter.createFromResource(this, R.array.ChoixYeux, android.R.layout.simple_spinner_item);
        adapterGenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapterOrientation = ArrayAdapter.createFromResource(this, R.array.ChoixOrientation, android.R.layout.simple_spinner_item);
        adapterGenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        Spinner spinnerGenre = (Spinner) findViewById(R.id.spinnerGenre);
        spinnerGenre.setAdapter(adapterGenre);
        spinnerGenre.setSelection(filtre.getPref_Genre());
        spinnerGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtre.setPref_Genre(position);
                RechercheManager.open();
                RechercheManager.modPreference(filtre);
                RechercheManager.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spinnerAge = (Spinner) findViewById(R.id.spinnerAge);
        spinnerAge.setAdapter(adapterAge);
        spinnerAge.setSelection(filtre.getPref_Age());
        spinnerAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtre.setPref_Age(position);
                RechercheManager.open();
                RechercheManager.modPreference(filtre);
                RechercheManager.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spinnerLangue = (Spinner) findViewById(R.id.spinnerLangue);
        spinnerLangue.setAdapter(adapterLangue);
        spinnerLangue.setSelection(filtre.getPref_Langue());
        spinnerLangue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtre.setPref_Langue(position);
                RechercheManager.open();
                RechercheManager.modPreference(filtre);
                RechercheManager.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        Spinner spinnerCheveux = (Spinner) findViewById(R.id.spinnerCheveux);
        spinnerCheveux.setAdapter(adapterCheveux);
        spinnerCheveux.setSelection(filtre.getPref_Cheveux());
        spinnerCheveux.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtre.setPref_Cheveux(position);
                RechercheManager.open();
                RechercheManager.modPreference(filtre);
                RechercheManager.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spinnerYeux = (Spinner) findViewById(R.id.spinnerYeux);
        spinnerYeux.setAdapter(adapterYeux);
        spinnerYeux.setSelection(filtre.getPref_Yeux());
        spinnerYeux.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtre.setPref_Yeux(position);
                RechercheManager.open();
                RechercheManager.modPreference(filtre);
                RechercheManager.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner spinnerOrientation = (Spinner) findViewById(R.id.spinnerOrientation);
        spinnerOrientation.setAdapter(adapterOrientation);
        spinnerOrientation.setSelection(filtre.getPref_Orientation());
        spinnerOrientation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtre.setPref_Orientation(position);
                RechercheManager.open();
                RechercheManager.modPreference(filtre);
                RechercheManager.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Toast.makeText(RechercheActivity.this,"Filtre_Langue= "+filtre.getPref_Langue(), Toast.LENGTH_SHORT).show();



    }
}

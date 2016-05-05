package com.example.charles_henry.testquimarche;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
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
        Resources res =getResources();
        final ArrayAdapter<String> adapterGenre = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, res.getStringArray(R.array.ChoixGenre));
        adapterGenre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       // final ArrayAdapter<String> adapterAge = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, res.getStringArray(R.array.ChoixAge));
        //adapterAge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> adapterLangue = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, res.getStringArray(R.array.ChoixLangue));
        adapterLangue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> adapterCheveux = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, res.getStringArray(R.array.CheveuxCouleur));
        adapterCheveux.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> adapterYeux = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, res.getStringArray(R.array.YeuxCouleur));
        adapterYeux.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> adapterOrientation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, res.getStringArray(R.array.ChoixOrientation));
        adapterOrientation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



        Spinner spinnerGenre = (Spinner) findViewById(R.id.spinnerGenre);
        spinnerGenre.setAdapter(adapterGenre);
        spinnerGenre.setSelection(RechercheFiltresManager.ConversionGenre(filtre.getPref_Genre()));
        spinnerGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtre.setPref_Genre(adapterGenre.getItem(position));
                RechercheManager.open();
                RechercheManager.modPreference(filtre);
                RechercheManager.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
/*
        Spinner spinnerAge = (Spinner) findViewById(R.id.spinnerAge);
        spinnerAge.setAdapter(adapterAge);
        spinnerAge.setSelection(RechercheFiltresManager.ConversionOrientation(filtre.getPref_Age()));
        spinnerAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtre.setPref_Age(adapterAge.getItem(position));
                RechercheManager.open();
                RechercheManager.modPreference(filtre);
                RechercheManager.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
*/
        Spinner spinnerLangue = (Spinner) findViewById(R.id.spinnerLangue);
        spinnerLangue.setAdapter(adapterLangue);
        spinnerLangue.setSelection(RechercheFiltresManager.ConversionLangue(filtre.getPref_Langue()));
        spinnerLangue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtre.setPref_Langue(adapterLangue.getItem(position));
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
        spinnerCheveux.setSelection(RechercheFiltresManager.ConversionCheveux(filtre.getPref_Cheveux()));
        spinnerCheveux.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtre.setPref_Cheveux(adapterCheveux.getItem(position));
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
        spinnerYeux.setSelection(RechercheFiltresManager.ConversionYeux(filtre.getPref_Yeux()));
        spinnerYeux.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtre.setPref_Yeux(adapterYeux.getItem(position));
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
        spinnerOrientation.setSelection(RechercheFiltresManager.ConversionOrientation(filtre.getPref_Orientation()));
        spinnerOrientation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filtre.setPref_Orientation(adapterOrientation.getItem(position));
                RechercheManager.open();
                RechercheManager.modPreference(filtre);
                RechercheManager.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        ImageButton ImageButton = (ImageButton) findViewById(R.id.ImageButton);
        ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(RechercheActivity.this,PersonnesTrouvesActivity.class);
                test.putExtra("LogUtil",LogUtil);
                startActivity(test);
            }
        });


    }
}

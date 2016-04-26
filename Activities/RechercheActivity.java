package com.example.charles_henry.testquimarche;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ChoixGenre, android.R.layout.simple_spinner_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinner10);
        spinner.setAdapter(adapter);
        spinner.setSelection(filtre.getPref_Genre());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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








    }
}

package com.example.charles_henry.testquimarche;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class PreferenceActivity extends AppCompatActivity {
    String LogUtil;
    PreferenceVisibilite pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        final PreferenceVisibiliteManager prefManager = new PreferenceVisibiliteManager(this);
        LogUtil = getIntent().getStringExtra("LogUtil");
        prefManager.open();
        pref= prefManager.getPreferenceVisibilite(LogUtil);
        prefManager.close();
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.VisibilityOffer, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setSelection(pref.getParametre_nom());
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pref.setParametre_nom(position);
                prefManager.open();
                prefManager.modPreference(pref);
                prefManager.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setAdapter(adapter);
        spinner2.setSelection(pref.getParametre_photo());
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pref.setParametre_photo(position);
                prefManager.open();
                prefManager.modPreference(pref);
                prefManager.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner3.setAdapter(adapter);
        spinner3.setSelection(pref.getParametre_ville());
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pref.setParametre_ville(position);
                prefManager.open();
                prefManager.modPreference(pref);
                prefManager.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
        spinner4.setAdapter(adapter);
        spinner4.setSelection(pref.getParametre_telephone());
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pref.setParametre_telephone(position);
                prefManager.open();
                prefManager.modPreference(pref);
                prefManager.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinner5 = (Spinner) findViewById(R.id.spinner5);
        spinner5.setAdapter(adapter);
        spinner5.setSelection(pref.getParametre_mail());
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pref.setParametre_mail(position);
                prefManager.open();
                prefManager.modPreference(pref);
                prefManager.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinner6 = (Spinner) findViewById(R.id.spinner6);
        spinner6.setAdapter(adapter);
        spinner6.setSelection(pref.getParametre_orientation());
        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pref.setParametre_orientation(position);
                prefManager.open();
                prefManager.modPreference(pref);
                prefManager.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinner7 = (Spinner) findViewById(R.id.spinner7);
        spinner7.setAdapter(adapter);
        spinner7.setSelection(pref.getParametre_yeux());
        spinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pref.setParametre_yeux(position);
                prefManager.open();
                prefManager.modPreference(pref);
                prefManager.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinner8 = (Spinner) findViewById(R.id.spinner8);
        spinner8.setAdapter(adapter);
        spinner8.setSelection(pref.getParametre_cheveux());
        spinner8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pref.setParametre_cheveux(position);
                prefManager.open();
                prefManager.modPreference(pref);
                prefManager.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner spinner9 = (Spinner) findViewById(R.id.spinner9);
        spinner9.setAdapter(adapter);
        spinner9.setSelection(pref.getParametre_disponibilite());
        spinner9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pref.setParametre_disponibilite(position);
                prefManager.open();
                prefManager.modPreference(pref);
                prefManager.close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}

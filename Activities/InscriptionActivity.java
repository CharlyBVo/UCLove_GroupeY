package com.example.charles_henry.testquimarche;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by charles-henry on 25-04-16.
 */
public class InscriptionActivity extends Activity {
    public String LogUtil;
    Utilisateur a;
    UtilisateurManager utilm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription1);
        EditText EnterLogin = (EditText) findViewById(R.id.editText12);
        EditText EnterPassword = (EditText) findViewById(R.id.editText13);
        EditText EnterConfirmPassword = (EditText) findViewById(R.id.editText14);
        EditText EnterPrenom = (EditText) findViewById(R.id.editText11);
        EditText EnterNom = (EditText) findViewById(R.id.editText6);
        EditText EnterDateDeNaissance = (EditText) findViewById(R.id.editText7);
        EditText EnterTelephone = (EditText) findViewById(R.id.editText8);
        EditText EnterEmail = (EditText) findViewById(R.id.editText9);
        EditText EnterVille = (EditText) findViewById(R.id.editText10);

        Spinner Cheveux1 = (Spinner) findViewById(R.id.spinner);
        Spinner Cheveux2 = (Spinner) findViewById(R.id.spinner2);
        Spinner yeux = (Spinner) findViewById(R.id.spinner3);
        Button butInscr = (Button) findViewById(R.id.button10);






    }
}

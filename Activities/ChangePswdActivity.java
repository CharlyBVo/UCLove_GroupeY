package com.example.charles_henry.testquimarche;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by charles-henry on 25-04-16.
 */
public class ChangePswdActivity  extends Activity {
    public String LogUtil;
    Utilisateur a;
    UtilisateurManager utilm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changerpsw);
        final TextView oldPswd = (TextView) findViewById(R.id.editText25);
        final TextView newPswd1 = (TextView) findViewById(R.id.editText26);
        final TextView newPswd2 = (TextView) findViewById(R.id.editText27);
        Button changePswd = (Button) findViewById(R.id.button16);
        LogUtil = getIntent().getStringExtra("LogUtil");
        utilm= new UtilisateurManager(this);
        utilm.open();
        a= utilm.getMainUtilisateur(LogUtil);
        utilm.close();
        changePswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oldPswd.getText().toString().equals(a.getMotDePasse()))
                {if(newPswd1.getText().toString().length()>5&&newPswd1.getText().toString().equals(newPswd2.getText().toString()))
                {
                    a.setMotDePasse(newPswd1.getText().toString());
                    utilm.open();
                    utilm.modMDP(a);
                    utilm.close();
                    Toast.makeText(ChangePswdActivity.this, "Mot de passe changé", Toast.LENGTH_SHORT).show();
                }}
                else {
                    Toast.makeText(ChangePswdActivity.this, "Mot de passe incorrect, veuillez réessayer :/", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}

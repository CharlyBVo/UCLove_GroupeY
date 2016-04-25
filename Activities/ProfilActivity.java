package com.example.charles_henry.testquimarche;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by charles-henry on 25-04-16.
 */
public class ProfilActivity  extends Activity {
    public String LogUtil;
    Utilisateur a;
    UtilisateurManager utilm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monprofil1);
        Button ChangePswd = (Button) findViewById(R.id.button13);
        Button ChangePref = (Button) findViewById(R.id.button15);
        TextView Pseudo = (TextView) findViewById(R.id.textView36);
        LogUtil = getIntent().getStringExtra("LogUtil");
        Pseudo.setText(LogUtil);

        ChangePswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test =new Intent(ProfilActivity.this,ChangePswdActivity.class);
                test.putExtra("LogUtil",LogUtil);
                startActivity(test);
            }
        });
        ChangePref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test =new Intent(ProfilActivity.this,PreferenceActivity.class);
                test.putExtra("LogUtil",LogUtil);
                startActivity(test);
            }
        });

    }
}

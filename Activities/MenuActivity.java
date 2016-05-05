package com.example.charles_henry.testquimarche;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;

/**
 * Created by charles-henry on 03-04-16.
 */
public class MenuActivity extends Activity {
    public String LogUtil;
    Utilisateur a;
    UtilisateurManager utilm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil= getIntent().getStringExtra("LogUtil");
        utilm= new UtilisateurManager(this);
        utilm.open();
        a= utilm.getMainUtilisateur(LogUtil);
        utilm.close();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button butProfil = (Button) findViewById(R.id.button3);
        Button butFriends =(Button) findViewById(R.id.button4);
        Button butRequest = (Button) findViewById(R.id.button9);
        Button butHisto = (Button) findViewById(R.id.button8);
        Button butRencontre= (Button) findViewById(R.id.button5);
        Button butMessage = (Button) findViewById(R.id.button6);
        Button butRes = (Button)findViewById(R.id.button7);

        butProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(MenuActivity.this,ProfilActivity.class);
                test.putExtra("LogUtil",LogUtil);
                startActivity(test);
            }
        });
        butFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(MenuActivity.this,FriendListActivity.class);
                test.putExtra("LogUtil",LogUtil);
                startActivity(test);
            }
        });
        butRencontre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        butRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(MenuActivity.this,RequestActivity.class);
                test.putExtra("LogUtil",LogUtil);
                startActivity(test);
            }
        });
        butHisto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(MenuActivity.this,HistoricActivity.class);
                test.putExtra("LogUtil",LogUtil);
                startActivity(test);
            }
        });
        butMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(MenuActivity.this,MessageActivity.class);
                test.putExtra("LogUtil",LogUtil);
                startActivity(test);
            }
        });
        butRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(MenuActivity.this,RechercheActivity.class);
                test.putExtra("LogUtil",LogUtil);
                startActivity(test);
            }
        });

    }

}

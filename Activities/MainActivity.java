package com.example.charles_henry.testquimarche;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Utilisateur MainUser ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button butConnect = (Button) findViewById(R.id.button);
        final EditText txtLog = (EditText) findViewById(R.id.textLogin);
        final EditText txtPswd = (EditText) findViewById(R.id.textPassword);
        Button butInscr = (Button) findViewById(R.id.button2);

        //To Manage BDD
        final UtilisateurManager TestManagUtil =new UtilisateurManager(this);
        //Utilisateur Test=new Utilisateur("Moi","12345","Beber","Gerard","LLN",new Date(1999,3,22),"Bleu","chauve","0475669488","Francais",null,"M",null,"Bi");




        butConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestManagUtil.open();
                String log = txtLog.getText().toString();
                String pswd= txtPswd.getText().toString();
                MainUser= TestManagUtil.getMainUtilisateur(log);
                if(MainUser!=null&&pswd.compareTo(MainUser.getMotDePasse())==0)
                {
                    Intent intent = new Intent(MainActivity.this,MenuActivity.class);
                    TestManagUtil.close();
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Login ou mot de passe incorrect, veuillez réessayer :/",Toast.LENGTH_SHORT).show();
                }
                TestManagUtil.close();
            }
        });
        butInscr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Cette option n'est pas encore disponible",Toast.LENGTH_SHORT).show();

            }
        });
    }
}

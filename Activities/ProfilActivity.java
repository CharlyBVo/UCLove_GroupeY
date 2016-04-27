package com.example.charles_henry.testquimarche;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by charles-henry on 25-04-16.
 */
public class ProfilActivity  extends Activity {
    public String LogUtil;
    Utilisateur a;
    UtilisateurManager utilm;

    private static final String TABLE_NAME = "Utilisateur";
    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public RencontreManager(Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
    }

    public void open()
    {
        //on ouvre la table en lecture/�criture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'acc�s � la BDD
        db.close();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monprofil1);
        Button ChangePswd = (Button) findViewById(R.id.button13);
        Button ChangePref = (Button) findViewById(R.id.button15);
        Button Changer = (Button) findViewById(R.id.button14);
        TextView Pseudo = (TextView) findViewById(R.id.textView36);
        EditText Prenom = (EditText) findViewById(R.id.editText17);
        EditText Nom = (EditText) findViewById(R.id.editText18);
        EditText DateDeNaissance = (EditText) findViewById(R.id.editText19);
        EditText Telephone = (EditText) findViewById(R.id.editText20);
        EditText Email = (EditText) findViewById(R.id.editText21);
        EditText Ville = (EditText) findViewById(R.id.editText22);
        //RadioGroup Sexe = (RadioGroup)findViewById(R.id.editText17);
        Spinner Cheveux1 = (Spinner) findViewById(R.id.spinner4);
        Spinner Cheveux2 = (Spinner) findViewById(R.id.spinner5);
        Spinner Yeux = (Spinner) findViewById(R.id.spinner6);


        LogUtil = getIntent().getStringExtra("LogUtil");
        Pseudo.setText(LogUtil);
        utilm = new UtilisateurManager(this);
        utilm.open();
        a=utilm.getMainUtilisateur(LogUtil);
        utilm.close();

        ChangePswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(ProfilActivity.this, ChangePswdActivity.class);
                test.putExtra("LogUtil", LogUtil);
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

        Changer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            ContentValues values = new ContentValues();
            values.put("Prenom",Prenom.getText().toString());
            values.put("Nom", Nom.getText().toString());
            values.put("Date_de_naissance", DateDeNaissance.getText().toString());
            values.put("Telephone", Telephone.getText().toString());
            values.put("Mail",Email.getText().toString());
            values.put("Ville",Ville.getText().toString());
            values.put("Cheveux",Cheveux1.getText().toString() +" "+ Cheveux2.getText().toString());
            values.put("Yeux",Yeux.getText().toString());

            long result=db.insert(TABLE_NAME,null,values);
            // insert() retourne l'id du nouvel enregistrement insere, ou -1 en cas d'erreur
            if(result == -1){
                Toast.makeText(ProfilActivity.this, "Erreur, veuillez réessayer :/", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(ProfilActivity.this, "Informations bien changé", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

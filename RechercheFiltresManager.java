package com.example.charles_henry.testquimarche;

/**
 * Created by Maxime on 25/04/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;


public class RechercheFiltresManager
{

    public static final String TABLE_NAME = "Filtre";
    public static final String KEY_LOGIN_FILTRES="Login";

    public static final String KEY_PREF_GENRE="Filtre_genre";
    public static final String KEY_PREF_AGE="Filtre_age";
    public static final String KEY_PREF_LANGUE="Filtre_langue";
    public static final String KEY_PREF_CHEVEUX="Filtre_cheveux";
    public static final String KEY_PREF_YEUX="Filtre_yeux";
    public static final String KEY_PREF_VILLE="Filtre_ville";
    public static final String KEY_PREF_ORIENTATION="Filtre_Orientation";



    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;




    public RechercheFiltresManager(Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
    }
    public RechercheFiltresManager()
    {}

    public void open()
    {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'accès à la BDD
        db.close();
    }

    public long addRechercheFiltres(RechercheFiltres pref) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN_FILTRES,pref.getLogin());
        values.put(KEY_PREF_GENRE,pref.getPref_Genre());
        values.put(KEY_PREF_AGE,pref.getPref_Age());
        values.put(KEY_PREF_LANGUE,pref.getPref_Langue());
        values.put(KEY_PREF_CHEVEUX,pref.getPref_Cheveux());
        values.put(KEY_PREF_YEUX,pref.getPref_Yeux());
        values.put(KEY_PREF_VILLE,pref.getPref_Ville());
        values.put(KEY_PREF_ORIENTATION,pref.getPref_Orientation());

        long result=db.insert(TABLE_NAME,null,values);
        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return result;
    }


    public int modPreference(RechercheFiltres pref) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_PREF_GENRE,pref.getPref_Genre());
        values.put(KEY_PREF_AGE,pref.getPref_Age());
        values.put(KEY_PREF_LANGUE,pref.getPref_Langue());
        values.put(KEY_PREF_CHEVEUX,pref.getPref_Cheveux());
        values.put(KEY_PREF_YEUX,pref.getPref_Yeux());
        values.put(KEY_PREF_VILLE,pref.getPref_Ville());
        values.put(KEY_PREF_ORIENTATION,pref.getPref_Orientation());


        String where = KEY_LOGIN_FILTRES+" = ?";
        String[] whereArgs = {pref.getLogin()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }

    public RechercheFiltres getRechercheFiltres(String logTest) {
        // Retourne les preferences de l'utilisateur dont l'id est passé en paramètre

        RechercheFiltres pref = new RechercheFiltres(logTest);

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_LOGIN_FILTRES + "= '" + logTest + "'", null);
        if (c.moveToFirst()) {

            pref.setPref_Genre(c.getString(c.getColumnIndex(KEY_PREF_GENRE)));
            pref.setPref_Age(c.getString(c.getColumnIndex(KEY_PREF_AGE)));
            pref.setPref_Langue(c.getString(c.getColumnIndex(KEY_PREF_LANGUE)));
            pref.setPref_Cheveux(c.getString(c.getColumnIndex(KEY_PREF_CHEVEUX)));
            pref.setPref_Yeux(c.getString(c.getColumnIndex(KEY_PREF_YEUX)));
            pref.setPref_Ville(c.getString(c.getColumnIndex(KEY_PREF_VILLE)));
            pref.setPref_Orientation(c.getString(c.getColumnIndex(KEY_PREF_ORIENTATION)));

            c.close();
            return pref;
        }
        else
            return null;


    }




    public ArrayList<String> getLovers(String LogUtil, RechercheFiltres Filtre) {

        ArrayList<String> Lovers = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT Login FROM Utilisateur WHERE Orientation LIKE '%"+Filtre.getPref_Orientation()+"%'"
                +"AND Cheveux LIKE'%"+Filtre.getPref_Cheveux()+"%'"
                +"AND Yeux LIKE'%"+Filtre.getPref_Yeux()+"%'"
                +"AND Langue LIKE'%"+Filtre.getPref_Langue()+"%'"
                +"AND Genre LIKE'%"+Filtre.getPref_Genre()+"%'",null);


        if (c.moveToFirst()) {
            int count= c.getCount();

            for(int i=0;i<count;i++)
            {
                Lovers.add(c.getString(c.getColumnIndex(KEY_LOGIN_FILTRES)));
                c.moveToNext();
            }
            return Lovers;
        }
        else
            return null;
        }


    public static int ConversionOrientation(String Orientation)
    {   if(Orientation!=null){
        if(Orientation.contains(""))
        {return 0;}
        if(Orientation.contains("Hétéro"))
        {return 1;}
        if(Orientation.contains("Bi"))
        {return 2;}
        if(Orientation.contains("Homo"))
        {return 3;}

        return 0;
    }
        return 0;
    }

    public static int ConversionCheveux(String Cheveux)
    {   if(Cheveux!=null){
        if(Cheveux.contains(""))
        {return 0;}
        if(Cheveux.contains("Blond"))
        {return 1;}
        if(Cheveux.contains("Chatâin"))
        {return 2;}
        if (Cheveux.contains("Roux"))
        {return 3;}
        if (Cheveux.contains("Brun"))
        {return 4;}
        if (Cheveux.contains("Noir"))
        {return 5;}
        if (Cheveux.contains("Vert"))
        {return 6;}
        if (Cheveux.contains("Bleu"))
        {return 7;}
        if (Cheveux.contains("Chauve"))
        {return 8;}

        return 0;
    }
        return 0;
    }

    public static int ConversionGenre(String Genre)
    {   if(Genre!=null){
        if(Genre.contains(""))
        {return 0;}
        if(Genre.contains("M"))
        {return 1;}
        if(Genre.contains("F"))
        {return 2;}


        return 0;
    }
        return 0;
    }
    public static int ConversionLangue(String Langue)
    {   if(Langue!=null){
        if(Langue.contains(""))
        {return 0;}
        if(Langue.contains("Anglais"))
        {return 1;}
        if(Langue.contains("Français"))
        {return 2;}

        return 0;
    }
        return 0;
    }
    public static int ConversionYeux(String Yeux)
    {   if(Yeux!=null){
        if(Yeux.contains(""))
        {return 0;}
        if(Yeux.contains("Bleu"))
        {return 1;}
        if(Yeux.contains("Brun"))
        {return 2;}
        if(Yeux.contains("Noir"))
        {return 3;}
        if (Yeux.contains("Gris"))
        {return 4;}
        if (Yeux.contains("Rouge"))
        {return 5;}
        if (Yeux.contains("Vert"))
        {return 6;}

        return 0;
    }
        return 0;
    }
}

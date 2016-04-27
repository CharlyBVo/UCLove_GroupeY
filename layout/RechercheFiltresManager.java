package com.example.charles_henry.testquimarche;

/**
 * Created by Maxime on 25/04/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


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
    public static final String KEY_PREF_ORIENTATION="Filtre_orientation";

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
            pref.setPref_Genre(c.getInt(c.getColumnIndex(KEY_PREF_GENRE)));
            pref.setPref_Age(c.getInt(c.getColumnIndex(KEY_PREF_AGE)));
            pref.setPref_Langue(c.getInt(c.getColumnIndex(KEY_PREF_LANGUE)));
            pref.setPref_Cheveux(c.getInt(c.getColumnIndex(KEY_PREF_CHEVEUX)));
            pref.setPref_Yeux(c.getInt(c.getColumnIndex(KEY_PREF_YEUX)));
            pref.setPref_Ville(c.getInt(c.getColumnIndex(KEY_PREF_VILLE)));
            pref.setPref_Orientation(c.getInt(c.getColumnIndex(KEY_PREF_ORIENTATION)));

            c.close();
            return pref;
        }
        else
            return null;


    }






}

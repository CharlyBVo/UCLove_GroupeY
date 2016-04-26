package com.example.charles_henry.testquimarche;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by charles-henry on 22-04-16.
 */
public class PreferenceVisibiliteManager {
    public static final String TABLE_NAME = "Preference_de_visibilite";
    public static final String KEY_LOGIN_PREFERENCE_DE_VISIBILITE="Login";
    public static final String KEY_PARAMETRE_YEUX_PREFERENCE_DE_VISIBILITE="Parametre_yeux";
    public static final String KEY_PARAMETRE_VILLE_PREFERENCE_DE_VISIBILITE="Parametre_ville";
    public static final String KEY_PARAMETRE_PHOTO_PREFERENCE_DE_VISIBILITE="Parametre_photo";
    public static final String KEY_PARAMETRE_MAIL_PREFERENCE_DE_VISIBILITE="Parametre_mail";
    public static final String KEY_PARAMETRE_CHEVEUX_PREFERENCE_DE_VISIBILITE="Parametre_cheveux";
    public static final String KEY_PARAMETRE_ORIENTATION_PREFERENCE_DE_VISIBILITE="Parametre_orientation";
    public static final String KEY_PARAMETRE_TELEPHONE_PREFERENCE_DE_VISIBILITE="Parametre_telephone";
    public static final String KEY_PARAMETRE_DISPONIBILITE_PREFERENCE_DE_VISIBILITE="Parametre_disponibilite";
    public static final String KEY_PARAMETRE_NOM_PREFERENCE_DE_VISIBILITE="Parametre_nom";


    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;
    public static final  String DESTRUCT_TABLE_PREFERENCE_DE_VISIBILITE="DROP TABLE "+TABLE_NAME;

    // Constructeur
    public PreferenceVisibiliteManager(Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
    }
    public PreferenceVisibiliteManager()
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

    public long addPreferenceVisibilite(PreferenceVisibilite pref) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN_PREFERENCE_DE_VISIBILITE,pref.getLogin());
        values.put(KEY_PARAMETRE_CHEVEUX_PREFERENCE_DE_VISIBILITE,pref.getParametre_cheveux());
        values.put(KEY_PARAMETRE_DISPONIBILITE_PREFERENCE_DE_VISIBILITE,pref.getParametre_disponibilite());
        values.put(KEY_PARAMETRE_MAIL_PREFERENCE_DE_VISIBILITE,pref.getParametre_mail());
        values.put(KEY_PARAMETRE_NOM_PREFERENCE_DE_VISIBILITE,pref.getParametre_nom());
        values.put(KEY_PARAMETRE_ORIENTATION_PREFERENCE_DE_VISIBILITE,pref.getParametre_orientation());
        values.put(KEY_PARAMETRE_PHOTO_PREFERENCE_DE_VISIBILITE,pref.getParametre_photo());
        values.put(KEY_PARAMETRE_TELEPHONE_PREFERENCE_DE_VISIBILITE,pref.getParametre_telephone());
        values.put(KEY_PARAMETRE_VILLE_PREFERENCE_DE_VISIBILITE, pref.getParametre_ville());
        values.put(KEY_PARAMETRE_YEUX_PREFERENCE_DE_VISIBILITE, pref.getParametre_yeux());
        long result=db.insert(TABLE_NAME,null,values);
        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return result;
    }

    public int modPreference(PreferenceVisibilite pref) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_PARAMETRE_NOM_PREFERENCE_DE_VISIBILITE, pref.getParametre_nom());
        values.put(KEY_PARAMETRE_CHEVEUX_PREFERENCE_DE_VISIBILITE,pref.getParametre_cheveux());
        values.put(KEY_PARAMETRE_DISPONIBILITE_PREFERENCE_DE_VISIBILITE,pref.getParametre_disponibilite());
        values.put(KEY_PARAMETRE_MAIL_PREFERENCE_DE_VISIBILITE,pref.getParametre_mail());
        values.put(KEY_PARAMETRE_ORIENTATION_PREFERENCE_DE_VISIBILITE,pref.getParametre_orientation());
        values.put(KEY_PARAMETRE_PHOTO_PREFERENCE_DE_VISIBILITE,pref.getParametre_photo());
        values.put(KEY_PARAMETRE_TELEPHONE_PREFERENCE_DE_VISIBILITE,pref.getParametre_telephone());
        values.put(KEY_PARAMETRE_VILLE_PREFERENCE_DE_VISIBILITE, pref.getParametre_ville());
        values.put(KEY_PARAMETRE_YEUX_PREFERENCE_DE_VISIBILITE, pref.getParametre_yeux());

        String where = KEY_LOGIN_PREFERENCE_DE_VISIBILITE+" = ?";
        String[] whereArgs = {pref.getLogin()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
     }

    public int supPreference(PreferenceVisibilite pref) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_LOGIN_PREFERENCE_DE_VISIBILITE+" = ?";
        String[] whereArgs = {pref.getLogin()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }

    public PreferenceVisibilite getPreferenceVisibilite(String logTest) {
        // Retourne les preferences de l'utilisateur dont l'id est passé en paramètre

        PreferenceVisibilite pref = new PreferenceVisibilite(logTest);

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_LOGIN_PREFERENCE_DE_VISIBILITE + "= '" + logTest + "'", null);
        if (c.moveToFirst()) {
            pref.setParametre_cheveux(c.getInt(c.getColumnIndex(KEY_PARAMETRE_CHEVEUX_PREFERENCE_DE_VISIBILITE)));
            pref.setParametre_disponibilite(c.getInt(c.getColumnIndex(KEY_PARAMETRE_DISPONIBILITE_PREFERENCE_DE_VISIBILITE)));
            pref.setParametre_mail(c.getInt(c.getColumnIndex(KEY_PARAMETRE_MAIL_PREFERENCE_DE_VISIBILITE)));
            pref.setParametre_nom(c.getInt(c.getColumnIndex(KEY_PARAMETRE_NOM_PREFERENCE_DE_VISIBILITE)));
            pref.setParametre_orientation(c.getInt(c.getColumnIndex(KEY_PARAMETRE_ORIENTATION_PREFERENCE_DE_VISIBILITE)));
            pref.setParametre_photo(c.getInt(c.getColumnIndex(KEY_PARAMETRE_PHOTO_PREFERENCE_DE_VISIBILITE)));
            pref.setParametre_telephone(c.getInt(c.getColumnIndex(KEY_PARAMETRE_TELEPHONE_PREFERENCE_DE_VISIBILITE)));
            pref.setParametre_yeux(c.getInt(c.getColumnIndex(KEY_PARAMETRE_YEUX_PREFERENCE_DE_VISIBILITE)));
            pref.setParametre_ville(c.getInt(c.getColumnIndex(KEY_PARAMETRE_VILLE_PREFERENCE_DE_VISIBILITE)));
            c.close();
            return pref;
        }
        else
            return null;


    }

}

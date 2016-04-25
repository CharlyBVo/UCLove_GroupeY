package com.example.charles_henry.testquimarche;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;

/**
 * Created by Mathieu on 25-04-16.
 */
public class RencontreManager {

    private static final String TABLE_NAME = "Rencontre";
    public static final String KEY_DATE_RENCONTRE = "Date";
    public static final String KEY_DESTINATAIRE_RENCONTRE = "Destinataire";
    public static final String KEY_EXPEDITEUR_RENCONTRE = "Expediteur";
    public static final String KEY_LIEU_RENCONTRE = "Lieu";
    public static final String KEY_STATUT_RENCONTRE = "Statut";

    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;

    // Constructeur
    public RencontreManager(Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
    }

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

    public long addRencontre(Rencontre rencontre){

        //on ajoute toutes les infos de rencontre dans la BDD
        ContentValues values = new ContentValues();
        values.put(KEY_LIEU_RENCONTRE,rencontre.getLieu());
        values.put(KEY_DESTINATAIRE_RENCONTRE, rencontre.getDestinataire());
        values.put(KEY_EXPEDITEUR_RENCONTRE, rencontre.getExpediteur());
        values.put(KEY_STATUT_RENCONTRE, rencontre.getStatut());

        //Gestion de la date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(rencontre.getDate());
        values.put(KEY_DATE_RENCONTRE,dateString);

        long result=db.insert(TABLE_NAME,null,values);

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return result;
    }

    public void accepterRencontre(Rencontre rencontre){
        rencontre.setStatut(1);
    }

    public void refuserRencontre(Rencontre rencontre){
        rencontre.setStatut(-1);
    }
}

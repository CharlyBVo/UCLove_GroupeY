package com.example.charles_henry.testquimarche;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

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
        //on ouvre la table en lecture/�criture
        db = maBaseSQLite.getWritableDatabase();
    }

    public void close()
    {
        //on ferme l'acc�s � la BDD
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

        // insert() retourne l'id du nouvel enregistrement ins�r�, ou -1 en cas d'erreur
        return result;
    }

    public void accepterRencontre(Rencontre rencontre){
        rencontre.setStatut(1);
    }

    public void refuserRencontre(Rencontre rencontre){
        rencontre.setStatut(-1);
    }

    // HISTORIQUE LIKE
    public ArrayList<Rencontre> getRencontres(String logTest) {
        // Retourne les rencontres de l'utilisateur dont l'id est passé en paramètre

        ArrayList<Rencontre> rencontreOfUser = new ArrayList<Rencontre>();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_DESTINATAIRE_RENCONTRE+ "= '" + logTest + "' or "+ KEY_EXPEDITEUR_RENCONTRE+"= '"
                +logTest+"' ;", null);
        if (c.moveToFirst()) {
            int count= c.getCount();
            Date temp =null;
            for(int i=0;i<count;i++)
            {
                String log1 =c.getString(c.getColumnIndex(KEY_EXPEDITEUR_RENCONTRE));
                String log2 =c.getString(c.getColumnIndex(KEY_DESTINATAIRE_RENCONTRE));
                String lieu = c.getString(c.getColumnIndex(KEY_LIEU_RENCONTRE));
                int statut = c.getInt(c.getColumnIndex(KEY_STATUT_RENCONTRE));
                String date = c.getString(c.getColumnIndex(KEY_DATE_RENCONTRE));
                if(date!=null)
                {
                    try {
                        temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);

                    }
                    catch (Exception e)
                    {}
                }

                rencontreOfUser.add(new Rencontre(log2,log1,temp,lieu,statut));
                c.moveToNext();
            }
            return rencontreOfUser;
        }
        else
            return null;


    }

    public ArrayList<Rencontre> getDemandesRecuesEnAttente(String logTest) {
        // Retourne les demandes en attente de l'utilisateur dont l'id est passé en paramètre

        ArrayList<Rencontre> rencontreOfUser = new ArrayList<Rencontre>();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_DESTINATAIRE_RENCONTRE+"= '"+logTest+"' and "+KEY_STATUT_RENCONTRE+"= "+0+";", null);
        if (c.moveToFirst()) {
            int count= c.getCount();
            Date temp =null;
            for(int i=0;i<count;i++)
            {
                String log1 =c.getString(c.getColumnIndex(KEY_EXPEDITEUR_RENCONTRE));
                String log2 =c.getString(c.getColumnIndex(KEY_DESTINATAIRE_RENCONTRE));
                String lieu = c.getString(c.getColumnIndex(KEY_LIEU_RENCONTRE));
                int statut = c.getInt(c.getColumnIndex(KEY_STATUT_RENCONTRE));
                String date = c.getString(c.getColumnIndex(KEY_DATE_RENCONTRE));

                if(date!=null)
                {
                    try {
                        temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);

                    }
                    catch (Exception e)
                    {}
                }

                rencontreOfUser.add(new Rencontre(log2,log1,temp,lieu,statut));
                c.moveToNext();
            }
            return rencontreOfUser;
        }
        else
            return null;
    }

    public ArrayList<Rencontre> getDemandesRecuesAcceptees(String logTest) {
        // Retourne les demandes recues de l'utilisateur dont l'id est passé en paramètre

        ArrayList<Rencontre> rencontreOfUser = new ArrayList<Rencontre>();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_DESTINATAIRE_RENCONTRE+"= '"+logTest+"' and "+KEY_STATUT_RENCONTRE+"= "+1+";", null);
        if (c.moveToFirst()) {
            int count= c.getCount();
            Date temp =null;
            for(int i=0;i<count;i++)
            {
                String log1 =c.getString(c.getColumnIndex(KEY_EXPEDITEUR_RENCONTRE));
                String log2 =c.getString(c.getColumnIndex(KEY_DESTINATAIRE_RENCONTRE));
                String lieu = c.getString(c.getColumnIndex(KEY_LIEU_RENCONTRE));
                int statut = c.getInt(c.getColumnIndex(KEY_STATUT_RENCONTRE));
                String date = c.getString(c.getColumnIndex(KEY_DATE_RENCONTRE));

                if(date!=null)
                {
                    try {
                        temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);

                    }
                    catch (Exception e)
                    {}
                }

                rencontreOfUser.add(new Rencontre(log2,log1,temp,lieu,statut));
                c.moveToNext();
            }
            return rencontreOfUser;
        }
        else
            return null;
    }

    public ArrayList<Rencontre> getDemandesRecuesRefusees(String logTest) {
        // Retourne les demandes refusées de l'utilisateur dont l'id est passé en paramètre

        ArrayList<Rencontre> rencontreOfUser = new ArrayList<Rencontre>();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_DESTINATAIRE_RENCONTRE+"= '"+logTest+"' and "+KEY_STATUT_RENCONTRE+"= "+ -1 +";", null);
        if (c.moveToFirst()) {
            int count= c.getCount();
            Date temp =null;
            for(int i=0;i<count;i++)
            {
                String log1 =c.getString(c.getColumnIndex(KEY_EXPEDITEUR_RENCONTRE));
                String log2 =c.getString(c.getColumnIndex(KEY_DESTINATAIRE_RENCONTRE));
                String lieu = c.getString(c.getColumnIndex(KEY_LIEU_RENCONTRE));
                int statut = c.getInt(c.getColumnIndex(KEY_STATUT_RENCONTRE));
                String date = c.getString(c.getColumnIndex(KEY_DATE_RENCONTRE));

                if(date!=null)
                {
                    try {
                        temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);

                    }
                    catch (Exception e)
                    {}
                }

                rencontreOfUser.add(new Rencontre(log2,log1,temp,lieu,statut));
                c.moveToNext();
            }
            return rencontreOfUser;
        }
        else
            return null;
    }

    public ArrayList<Rencontre> getDemandesEnvoyeesEnAttente(String logTest) {
        // Retourne les demandes envoyées en attente de l'utilisateur dont l'id est passé en paramètre

        ArrayList<Rencontre> rencontreOfUser = new ArrayList<Rencontre>();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_EXPEDITEUR_RENCONTRE+"= '"+logTest+"' and "+KEY_STATUT_RENCONTRE+"= "+0+";", null);
        if (c.moveToFirst()) {
            int count= c.getCount();
            Date temp =null;
            for(int i=0;i<count;i++)
            {
                String log1 =c.getString(c.getColumnIndex(KEY_EXPEDITEUR_RENCONTRE));
                String log2 =c.getString(c.getColumnIndex(KEY_DESTINATAIRE_RENCONTRE));
                String lieu = c.getString(c.getColumnIndex(KEY_LIEU_RENCONTRE));
                int statut = c.getInt(c.getColumnIndex(KEY_STATUT_RENCONTRE));
                String date = c.getString(c.getColumnIndex(KEY_DATE_RENCONTRE));

                if(date!=null)
                {
                    try {
                        temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);

                    }
                    catch (Exception e)
                    {}
                }

                rencontreOfUser.add(new Rencontre(log2,log1,temp,lieu,statut));
                c.moveToNext();
            }
            return rencontreOfUser;
        }
        else
            return null;
    }

    public ArrayList<Rencontre> getDemandesEnvoyeesAcceptees(String logTest) {
        // Retourne les demandes envoyées acceptées de l'utilisateur dont l'id est passé en paramètre

        ArrayList<Rencontre> rencontreOfUser = new ArrayList<Rencontre>();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_EXPEDITEUR_RENCONTRE+"= '"+logTest+"' and "+KEY_STATUT_RENCONTRE+"= "+1+";", null);
        if (c.moveToFirst()) {
            int count= c.getCount();
            Date temp =null;
            for(int i=0;i<count;i++)
            {
                String log1 =c.getString(c.getColumnIndex(KEY_EXPEDITEUR_RENCONTRE));
                String log2 =c.getString(c.getColumnIndex(KEY_DESTINATAIRE_RENCONTRE));
                String lieu = c.getString(c.getColumnIndex(KEY_LIEU_RENCONTRE));
                int statut = c.getInt(c.getColumnIndex(KEY_STATUT_RENCONTRE));
                String date = c.getString(c.getColumnIndex(KEY_DATE_RENCONTRE));

                if(date!=null)
                {
                    try {
                        temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);

                    }
                    catch (Exception e)
                    {}
                }

                rencontreOfUser.add(new Rencontre(log2,log1,temp,lieu,statut));
                c.moveToNext();
            }
            return rencontreOfUser;
        }
        else
            return null;
    }

    public ArrayList<Rencontre> getDemandesEnvoyeesRefusees(String logTest) {
        // Retourne les demandes envoyées refusées de l'utilisateur dont l'id est passé en paramètre

        ArrayList<Rencontre> rencontreOfUser = new ArrayList<Rencontre>();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_EXPEDITEUR_RENCONTRE+"= '"+logTest+"' and "+KEY_STATUT_RENCONTRE+"= "+ -1 +";", null);
        if (c.moveToFirst()) {
            int count= c.getCount();
            Date temp =null;
            for(int i=0;i<count;i++)
            {
                String log1 =c.getString(c.getColumnIndex(KEY_EXPEDITEUR_RENCONTRE));
                String log2 =c.getString(c.getColumnIndex(KEY_DESTINATAIRE_RENCONTRE));
                String lieu = c.getString(c.getColumnIndex(KEY_LIEU_RENCONTRE));
                int statut = c.getInt(c.getColumnIndex(KEY_STATUT_RENCONTRE));
                String date = c.getString(c.getColumnIndex(KEY_DATE_RENCONTRE));

                if(date!=null)
                {
                    try {
                        temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);

                    }
                    catch (Exception e)
                    {}
                }

                rencontreOfUser.add(new Rencontre(log2,log1,temp,lieu,statut));
                c.moveToNext();
            }
            return rencontreOfUser;
        }
        else
            return null;
    }
}

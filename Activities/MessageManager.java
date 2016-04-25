package com.example.charles_henry.testquimarche;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by charles-henry on 24-04-16.
 */
public class MessageManager {
    private static final String TABLE_NAME = "Message";
    public static final String KEY_DATE_MESSAGE="Date";
    public static final String KEY_DESTINATAIRE_MESSAGE="Destinataire";
    public static final String KEY_EXPEDITEUR_MESSAGE="Expediteur";
    public static final String KEY_CONTENU_MESSAGE="Contenu";


    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;
    public static final  String DESTRUCT_TABLE_MESSAGE="DROP TABLE "+TABLE_NAME;

    // Constructeur
    public MessageManager(Context context)
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

    public long addMessage(Message mess) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_CONTENU_MESSAGE,mess.getContenu());
        values.put(KEY_DESTINATAIRE_MESSAGE, mess.getDestinataire());
        values.put(KEY_EXPEDITEUR_MESSAGE, mess.getExpediteur());

        //Gestion de la date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(mess.getDateEnvoi());

        values.put(KEY_DATE_MESSAGE,dateString);

        long result=db.insert(TABLE_NAME,null,values);


        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return result;
    }

    /*public int modUtilisateur(Utilisateur util) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_NOM_ANIMAL, animal.getNom_animal());

        String where = KEY_ID_ANIMAL+" = ?";
        String[] whereArgs = {animal.getId_animal()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
    }*/

    /*
    public int supMessage(Message mess) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_LOGIN_UTILISATEUR+" = ?";
        String[] whereArgs = {util.getLogin()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }*/



    /*
    public Utilisateur getMainUtilisateur(String logTest) {
        // Retourne l'utilisateur dont l'id est passé en paramètre

        Utilisateur a=new Utilisateur();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_LOGIN_UTILISATEUR + "= '" + logTest + "'", null);
        //Date naissance =db.execSQL("SELECT "+KEY_DATE_NAISSANCE_UTILISATEUR+" FROM "+TABLE_NAME+" WHERE "+KEY_LOGIN_UTILISATEUR+"= '"+logTest+"'");
        if (c.moveToFirst()) {
            a.setLogin(c.getString(c.getColumnIndex(KEY_LOGIN_UTILISATEUR)));
            a.setNom(c.getString(c.getColumnIndex(KEY_NOM_UTILISATEUR)));
            a.setCheveux(c.getString(c.getColumnIndex(KEY_CHEVEUX_UTILISATEUR)));
            String dateNoConvert = c.getString(c.getColumnIndex(KEY_DATE_NAISSANCE_UTILISATEUR));
            a.setGenre(c.getString(c.getColumnIndex(KEY_GENRE_UTILISATEUR)));
            a.setPrenom(c.getString(c.getColumnIndex(KEY_PRENOM_UTILISATEUR)));
            a.setYeux(c.getString(c.getColumnIndex(KEY_YEUX_UTILISATEUR)));
            a.setMotDePasse(c.getString(c.getColumnIndex(KEY_MDP_UTILISATEUR)));
            a.setMail(c.getString(c.getColumnIndex(KEY_MAIL_UTILISATEUR)));
            a.setVille(c.getString(c.getColumnIndex(KEY_VILLE_UTILISATEUR)));
            a.setOrientation(c.getString(c.getColumnIndex(KEY_ORIENTATION_UTILISATEUR)));
            a.setTelephone(c.getString(c.getColumnIndex(KEY_TELEPHONE_UTILISATEUR)));
            //a.setPhoto(c.getType(c.getColumnIndex(KEY_PHOTO_UTILISATEUR)));
            a.setLangue(c.getString(c.getColumnIndex(KEY_LANGUE_UTILISATEUR)));
            c.close();
            if(dateNoConvert!=null)
            {
                try {
                    Date temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse(dateNoConvert);
                    a.setDateDeNaissance(temp);
                }
                catch (Exception e)
                {}
            }



            return a;
        }
        else
            return null;
    }*/

    /*public Cursor getAnimaux() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }*/

}

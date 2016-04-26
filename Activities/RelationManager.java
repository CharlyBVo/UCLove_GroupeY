package com.example.charles_henry.testquimarche;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by charles-henry on 23-04-16.
 */
public class RelationManager {
    public static final String TABLE_NAME = "Relation";
    public static final String KEY_DATE_RELATION="Date";
    public static final String KEY_LOGIN1_RELATION="Login1";
    public static final String KEY_LOGIN2_RELATION="Login2";
    public static final String KEY_TYPE_DE_RELATION_RELATION="Type_de_relation";


    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;
    public static final  String DESTRUCT_TABLE_RELATION="DROP TABLE "+TABLE_NAME;

    // Constructeur
    public RelationManager(Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
    }
    public RelationManager()
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

    public long addRelation(Relation rel) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_LOGIN1_RELATION,rel.getLogin1());
        values.put(KEY_LOGIN2_RELATION,rel.getLogin2());
        values.put(KEY_TYPE_DE_RELATION_RELATION,rel.getTypeRelation());

        //Gestion de la date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(rel.getDateRelation());

        values.put(KEY_DATE_RELATION, dateString);

        long result=db.insert(TABLE_NAME,null,values);

        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return result;
    }

    /*public int modPreference(PreferenceVisibilite pref) {
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
    }*/

    /*public int supPreference(PreferenceVisibilite pref) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_LOGIN_PREFERENCE_DE_VISIBILITE+" = ?";
        String[] whereArgs = {pref.getLogin()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }*/

    public ArrayList<Relation> getRelations(String logTest) {
        // Retourne les relations de l'utilisateur dont l'id est passé en paramètre

        ArrayList<Relation> relationsOfUser = new ArrayList<Relation>();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_LOGIN1_RELATION+ "= '" + logTest + "' or "+ KEY_LOGIN2_RELATION+"= '"
                +logTest+"' ;", null);
        if (c.moveToFirst()) {
            int count= c.getCount();
            Date temp =null;
            for(int i=0;i<count;i++)
            {
                String log1 =c.getString(c.getColumnIndex(KEY_LOGIN1_RELATION));
                String log2 =c.getString(c.getColumnIndex(KEY_LOGIN2_RELATION));
                String dateRelNo = c.getString(c.getColumnIndex(KEY_DATE_RELATION));
                String typeRel = c.getString(c.getColumnIndex(KEY_TYPE_DE_RELATION_RELATION));

                if(dateRelNo!=null)
                {
                    try {
                        temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateRelNo);

                    }
                    catch (Exception e)
                    {}
                }
                relationsOfUser.add(new Relation(log1,log2,temp,typeRel));
                c.moveToNext();
            }
            Collections.sort(relationsOfUser, new Comparator<Relation>() {
                @Override
                public int compare(Relation lhs, Relation rhs) {
                    return rhs.getDateRelation().compareTo(lhs.getDateRelation());
                }
            });
            return relationsOfUser;
        }
        else
            return null;


    }
    public static ArrayList<String> relToString(ArrayList<Relation> relationsofUser,String logUser)
    {
        // renvoie un tableau de String contenant les éléments de l'historique de relation de l'utilisateur.

        if(relationsofUser!=null){
        Relation rel=null;
        ArrayList<String> relInStrings=new ArrayList<String>();
        for(int i=0;i<relationsofUser.size();i++) {
            rel = relationsofUser.get(i);
            String typRel = rel.getTypeRelation();
            String logFriend = rel.getLogin2();
            //Gestion de la date
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String dateString = sdf.format(rel.getDateRelation());
            if (rel.getLogin2().equals(logUser)) {
                logFriend = rel.getLogin1();
            }
            if (typRel.equals("Amis")) {
                relInStrings.add(dateString+" Vous êtes devenu ami avec " + logFriend);
            } else if (typRel.equals("en attente")) {
                if (rel.getLogin1().equals(logFriend)) {
                    relInStrings.add(dateString+" "+logFriend + " vous a envoyé une demande d'amitié");
                } else
                    relInStrings.add(dateString+" Vous avez envoye une demande d'amitié à " + logFriend);
            } else if (typRel.equals("rejet")) {
                if (rel.getLogin1().equals(logFriend)) {
                    relInStrings.add(dateString+" "+logFriend + " a rejeté votre demande d'amitié");
                } else
                    relInStrings.add(dateString+" Vous avez rejeté la demande d'amitié de " + logFriend);
            } else {
                if (rel.getLogin1().equals(logFriend)) {
                    relInStrings.add(dateString+" "+logFriend + " vous a ajouté en favoris");
                } else
                    relInStrings.add(dateString+" Vous avez ajouté " + logFriend + " en favoris");
            }
        }
        return relInStrings;}
        return null;

    }
}

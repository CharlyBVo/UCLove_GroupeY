package com.example.charles_henry.testquimarche;

/**
 * Created by charles-henry on 18-04-16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UtilisateurManager {

    private static final String TABLE_NAME = "Utilisateur";
    public static final String KEY_LOGIN_UTILISATEUR="Login";
    public static final String KEY_MDP_UTILISATEUR="Mot_de_passe";
    public static final String KEY_PRENOM_UTILISATEUR="Prenom";
    public static final String KEY_NOM_UTILISATEUR="Nom";
    public static final String KEY_DATE_NAISSANCE_UTILISATEUR="Date_de_naissance";
    public static final String KEY_VILLE_UTILISATEUR="Ville";
    public static final String KEY_YEUX_UTILISATEUR="Yeux";
    public static final String KEY_TELEPHONE_UTILISATEUR="Telephone";
    public static final String KEY_CHEVEUX_UTILISATEUR="Cheveux";
    public static final String KEY_LANGUE_UTILISATEUR="Langue";
    public static final String KEY_ORIENTATION_UTILISATEUR="Orientation";
    public static final String KEY_PHOTO_UTILISATEUR="Photo";
    public static final String KEY_MAIL_UTILISATEUR="Mail";
    public static final String KEY_GENRE_UTILISATEUR="Genre";
    //NOTE IMPORTANTE DATE DE NAISSANCE EST MIS EN FACULTATIF POUR LES TESTS ! A CHANGER !!!!
    public static final String CREATE_TABLE_UTILISATEUR = "CREATE TABLE "+TABLE_NAME+" ("+KEY_LOGIN_UTILISATEUR+
            " char PRIMARY KEY  NOT NULL ,"+KEY_MDP_UTILISATEUR+" char NOT NULL ,"+KEY_PRENOM_UTILISATEUR+
            " char NOT NULL  DEFAULT (null) ,"+KEY_NOM_UTILISATEUR+" char NOT NULL ,"+KEY_DATE_NAISSANCE_UTILISATEUR+
            " char,"+KEY_VILLE_UTILISATEUR+" char NOT NULL ,"+KEY_YEUX_UTILISATEUR+" char,"+KEY_TELEPHONE_UTILISATEUR+
            " char DEFAULT (null) ,"+KEY_CHEVEUX_UTILISATEUR+" char,"+KEY_LANGUE_UTILISATEUR+" char NOT NULL  DEFAULT ('Français') ,"+
            KEY_ORIENTATION_UTILISATEUR+" char NOT NULL ,"+KEY_PHOTO_UTILISATEUR+" BLOB,"+KEY_MAIL_UTILISATEUR+" char,"+
            KEY_GENRE_UTILISATEUR+" char NOT NULL"+" );";
    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;
    public static final  String DESTRUCT_TABLE_UTILISATEUR="DROP TABLE "+TABLE_NAME;

    // Constructeur
    public UtilisateurManager(Context context)
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

    public long addUtilisateur(Utilisateur util) {
        // Ajout d'un enregistrement dans la table

        ContentValues values = new ContentValues();
        values.put(KEY_CHEVEUX_UTILISATEUR, util.getCheveux());
        values.put(KEY_GENRE_UTILISATEUR,util.getGenre());
        values.put(KEY_LANGUE_UTILISATEUR,util.getLangue());
        values.put(KEY_LOGIN_UTILISATEUR,util.getLogin());
        values.put(KEY_MAIL_UTILISATEUR,util.getMail());
        values.put(KEY_MDP_UTILISATEUR,util.getMotDePasse());
        values.put(KEY_NOM_UTILISATEUR,util.getNom());
        values.put(KEY_PRENOM_UTILISATEUR,util.getPrenom());
        values.put(KEY_VILLE_UTILISATEUR,util.getVille());
        values.put(KEY_ORIENTATION_UTILISATEUR,util.getOrientation());
        values.put(KEY_TELEPHONE_UTILISATEUR,util.getTelephone());
        //values.put(KEY_PHOTO_UTILISATEUR,null);
        values.put(KEY_YEUX_UTILISATEUR, util.getYeux());

        //Gestion de la date de naissance
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(util.getDateDeNaissance());

        values.put(KEY_DATE_NAISSANCE_UTILISATEUR, dateString);

        long result=db.insert(TABLE_NAME,null,values);
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+KEY_PHOTO_UTILISATEUR+" ='"+util.getPhoto()+"' WHERE "
                +KEY_LOGIN_UTILISATEUR+"='"+util.getLogin()+"' ;");


        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
        return result;
    }

     public int modMDP(Utilisateur util) {
        // modification d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la requête

        ContentValues values = new ContentValues();
        values.put(KEY_MDP_UTILISATEUR, util.getMotDePasse());

        String where = KEY_LOGIN_UTILISATEUR+" = ?";
        String[] whereArgs = {util.getLogin()+""};

        return db.update(TABLE_NAME, values, where, whereArgs);
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

    public int supUtilisateur(Utilisateur util) {
        // suppression d'un enregistrement
        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon

        String where = KEY_LOGIN_UTILISATEUR+" = ?";
        String[] whereArgs = {util.getLogin()+""};

        return db.delete(TABLE_NAME, where, whereArgs);
    }
    public ArrayList<String> getFriendsofUser(String log)
    {
        //retourne un tableau contenant les logins des utilisateurs amis
        RelationManager relM= new RelationManager();
        ArrayList<String> Friends = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT " + KEY_LOGIN_UTILISATEUR + " FROM " + TABLE_NAME + " U, " + relM.TABLE_NAME +
                " R WHERE ( (U." + KEY_LOGIN_UTILISATEUR + " = R." + relM.KEY_LOGIN2_RELATION + " and R." + relM.KEY_LOGIN1_RELATION + " = '" + log +
                "') or (U." + KEY_LOGIN_UTILISATEUR + "= R." + relM.KEY_LOGIN1_RELATION + " and R." + relM.KEY_LOGIN2_RELATION + "= '" + log +
                "')) and (R." + relM.KEY_TYPE_DE_RELATION_RELATION + " = 'Amis');", null);
        if(c.moveToFirst())
        {
            int count= c.getCount();
            for(int i=0;i<count;i++)
            {
                Friends.add(c.getString(c.getColumnIndex(KEY_LOGIN_UTILISATEUR)));
                c.moveToNext();
            }
            return Friends;
        }
        return null;

    }

    public Utilisateur getMainUtilisateur(String logTest) {
        // Retourne l'utilisateur dont l'id est passé en paramètre

        Utilisateur a=new Utilisateur();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_LOGIN_UTILISATEUR + "= '" + logTest + "'", null);
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
                   Date temp = new SimpleDateFormat("yyyy-MM-dd").parse(dateNoConvert);
                   // Date temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateNoConvert);
                    a.setDateDeNaissance(temp);
                }
                catch (Exception e)
                {}
            }
            PreferenceVisibiliteManager prefMan = new PreferenceVisibiliteManager();
            PreferenceVisibilite pref = new PreferenceVisibilite(logTest);

            Cursor euh = db.rawQuery("SELECT * FROM " + prefMan.TABLE_NAME + " WHERE " + prefMan.KEY_LOGIN_PREFERENCE_DE_VISIBILITE + "= '" + logTest + "'", null);
            if (euh.moveToFirst()) {
                pref.setParametre_cheveux(euh.getInt(euh.getColumnIndex(prefMan.KEY_PARAMETRE_CHEVEUX_PREFERENCE_DE_VISIBILITE)));
                pref.setParametre_disponibilite(euh.getInt(euh.getColumnIndex(prefMan.KEY_PARAMETRE_DISPONIBILITE_PREFERENCE_DE_VISIBILITE)));
                pref.setParametre_mail(euh.getInt(euh.getColumnIndex(prefMan.KEY_PARAMETRE_MAIL_PREFERENCE_DE_VISIBILITE)));
                pref.setParametre_nom(euh.getInt(euh.getColumnIndex(prefMan.KEY_PARAMETRE_NOM_PREFERENCE_DE_VISIBILITE)));
                pref.setParametre_orientation(euh.getInt(euh.getColumnIndex(prefMan.KEY_PARAMETRE_ORIENTATION_PREFERENCE_DE_VISIBILITE)));
                pref.setParametre_photo(euh.getInt(euh.getColumnIndex(prefMan.KEY_PARAMETRE_PHOTO_PREFERENCE_DE_VISIBILITE)));
                pref.setParametre_telephone(euh.getInt(euh.getColumnIndex(prefMan.KEY_PARAMETRE_TELEPHONE_PREFERENCE_DE_VISIBILITE)));
                pref.setParametre_yeux(euh.getInt(euh.getColumnIndex(prefMan.KEY_PARAMETRE_YEUX_PREFERENCE_DE_VISIBILITE)));
                pref.setParametre_ville(euh.getInt(euh.getColumnIndex(prefMan.KEY_PARAMETRE_VILLE_PREFERENCE_DE_VISIBILITE)));
                euh.close();
            }
            a.setMyVisibility(pref);


            return a;
        }
        else
            return null;

    }

    public  Utilisateur getInfoUser (String logUser,int valueOfPref)
    {
        // Retourne un Utilisateur qui contient uniquement les donnees correspondant a la valeur de preference
        // de visibilite passe en parametre (ou superieur).

        Utilisateur a = this.getMainUtilisateur(logUser);
        PreferenceVisibilite pref = a.getMyVisibility();
        if(valueOfPref!=0)
        {a.setMotDePasse(null);}
        if(pref.getParametre_orientation()<valueOfPref)
        {a.setOrientation(null);}
        if(pref.getParametre_nom()<valueOfPref)
        {a.setNom(null);}
        if(pref.getParametre_yeux()<valueOfPref)
        {a.setYeux(null);}
        if(pref.getParametre_cheveux()<valueOfPref)
        {a.setCheveux(null);}
        if(pref.getParametre_telephone()<valueOfPref)
        {a.setTelephone(null);}
        if(pref.getParametre_orientation()<valueOfPref)
        {a.setOrientation(null);}
        if(pref.getParametre_mail()<valueOfPref)
        {a.setMail(null);}
        if(pref.getParametre_photo()<valueOfPref)
        {a.setPhoto(null);}
        if(pref.getParametre_ville()<valueOfPref)
        {a.setVille(null);}
            return a;
           }

    /*public Cursor getAnimaux() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }*/

} // class UtilisateurManager

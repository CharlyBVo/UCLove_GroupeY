package com.example.charles_henry.testquimarche;

/**
 * Created by charles-henry on 19-04-16.
 */
public class PreferenceVisibilite {
    private  String Login;
    private int Parametre_yeux;
    private int Parametre_ville;
    private int Parametre_photo;
    private int Parametre_mail;
    private int Parametre_cheveux;
    private int Parametre_telephone;
    private int Parametre_disponibilite;
    private int Parametre_nom;
    private int Parametre_orientation;

    public PreferenceVisibilite(String Login)
    {
        this.Login=Login;
        this.Parametre_yeux=3;
        this.Parametre_ville=3;
        this.Parametre_photo=3;
        this.Parametre_mail=3;
        this.Parametre_cheveux=3;
        this.Parametre_telephone=3;
        this.Parametre_disponibilite=3;
        this.Parametre_nom=3;
        this.Parametre_orientation=3;

    }
    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public int getParametre_yeux() {
        return Parametre_yeux;
    }

    public void setParametre_yeux(int parametre_yeux) {
        Parametre_yeux = parametre_yeux;
    }

    public int getParametre_ville() {
        return Parametre_ville;
    }

    public void setParametre_ville(int parametre_ville) {
        Parametre_ville = parametre_ville;
    }

    public int getParametre_photo() {
        return Parametre_photo;
    }

    public void setParametre_photo(int parametre_photo) {
        Parametre_photo = parametre_photo;
    }

    public int getParametre_mail() {
        return Parametre_mail;
    }

    public void setParametre_mail(int parametre_mail) {
        Parametre_mail = parametre_mail;
    }

    public int getParametre_cheveux() {
        return Parametre_cheveux;
    }

    public void setParametre_cheveux(int parametre_cheveux) {
        Parametre_cheveux = parametre_cheveux;
    }

    public int getParametre_telephone() {
        return Parametre_telephone;
    }

    public void setParametre_telephone(int parametre_telephone) {
        Parametre_telephone = parametre_telephone;
    }

    public int getParametre_disponibilite() {
        return Parametre_disponibilite;
    }

    public void setParametre_disponibilite(int parametre_disponibilite) {
        Parametre_disponibilite = parametre_disponibilite;
    }

    public int getParametre_nom() {
        return Parametre_nom;
    }

    public void setParametre_nom(int parametre_nom) {
        Parametre_nom = parametre_nom;
    }

    public int getParametre_orientation() {
        return Parametre_orientation;
    }

    public void setParametre_orientation(int parametre_orientation) {
        Parametre_orientation = parametre_orientation;
    }
}
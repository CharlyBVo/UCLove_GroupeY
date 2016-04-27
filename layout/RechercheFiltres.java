package com.example.charles_henry.testquimarche;

/**
 * Created by Maxime on 25/04/2016.
 */
public class RechercheFiltres {

    private String Login;
    private int Pref_Genre;
    private int Pref_Age;
    private int Pref_Langue;
    private int Pref_Cheveux;
    private int Pref_Yeux;
    private int Pref_Ville;
    private int Pref_Orientation;

    public RechercheFiltres()
    {}

    public RechercheFiltres(String Login)
    {
        this.Login= Login;
        this.Pref_Genre=1;
        this.Pref_Age=1;
        this.Pref_Langue= 1;
        this.Pref_Cheveux= 1;
        this.Pref_Yeux= 1;
        this.Pref_Ville=1;
        this.Pref_Orientation=1;
    }

    public String getLogin() {return Login;}

    public void setLogin(String login) {Login = login;}

    public int getPref_Genre() {return Pref_Genre;}

    public void setPref_Genre(int pref_Genre) {Pref_Genre = pref_Genre;}

    public int getPref_Age() {return Pref_Age;}

    public void setPref_Age(int pref_Age) {Pref_Age = pref_Age;}

    public int getPref_Langue() {return Pref_Langue;}

    public void setPref_Langue(int pref_Langue) {Pref_Langue = pref_Langue;}

    public int getPref_Cheveux() {return Pref_Cheveux;}

    public void setPref_Cheveux(int pref_Cheveux) {Pref_Cheveux = pref_Cheveux;}

    public int getPref_Yeux() {return Pref_Yeux;}

    public void setPref_Yeux(int pref_Yeux) {Pref_Yeux = pref_Yeux;}

    public int getPref_Ville() {return Pref_Ville;}

    public void setPref_Ville(int pref_Ville) {Pref_Ville = pref_Ville;}

    public int getPref_Orientation() {return Pref_Orientation;}

    public void setPref_Orientation(int pref_Orientation) {Pref_Orientation = pref_Orientation;}
}

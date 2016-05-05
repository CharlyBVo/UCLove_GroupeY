package com.example.charles_henry.testquimarche;

/**
 * Created by Maxime on 25/04/2016.
 */
public class RechercheFiltres {

    private String Login;
    private String Pref_Genre;
    private String Pref_Age;
    private String Pref_Langue;
    private String Pref_Cheveux;
    private String Pref_Yeux;
    private String Pref_Ville;
    private String Pref_Orientation;


    public RechercheFiltres()
    {}

    public RechercheFiltres(String Login)
    {
        this.Login= Login;

    }

    public String getLogin() {return Login;}

    public void setLogin(String login) {Login = login;}


    public String getPref_Genre() {return Pref_Genre;}

    public void setPref_Genre(String pref_Genre) {Pref_Genre = pref_Genre;}

    public String getPref_Age() {return Pref_Age;}

    public void setPref_Age(String pref_Age) {Pref_Age = pref_Age;}

    public String getPref_Langue() {return Pref_Langue;}

    public void setPref_Langue(String pref_Langue) {Pref_Langue = pref_Langue;}

    public String getPref_Cheveux() {return Pref_Cheveux;}

    public void setPref_Cheveux(String pref_Cheveux) {Pref_Cheveux = pref_Cheveux;}

    public String getPref_Yeux() {return Pref_Yeux;}

    public void setPref_Yeux(String pref_Yeux) {Pref_Yeux = pref_Yeux;}

    public String getPref_Ville() {return Pref_Ville;}

    public void setPref_Ville(String pref_Ville) {Pref_Ville = pref_Ville;}

    public String getPref_Orientation() {return Pref_Orientation;}

    public void setPref_Orientation(String pref_Orientation) {Pref_Orientation = pref_Orientation;}
}

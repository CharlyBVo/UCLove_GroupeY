package com.example.charles_henry.testquimarche;

import java.sql.Blob;
import java.util.Date;

/**
 * Created by charles-henry on 17-04-16.
 */
public class Utilisateur {
    private String Login;
    private String MotDePasse;
    private String Nom;
    private String Prenom;
    private String Ville;
    private Date DateDeNaissance;
    private String Yeux;
    private String Cheveux;
    private String Telephone;
    private String Langue;
    private String Mail;
    private String Genre;
    private Blob Photo;
    private String Orientation;

    public  Utilisateur()
    {

    }
    public Utilisateur( String Login,String MotDePasse,String Nom,String Prenom
                        ,String Ville,Date DateDeNaissance,String Yeux,String Cheveux,String Telephone
                        ,String Langue,String Mail,String Genre,Blob Photo,String Orientation) {
        this.Login = Login;
        this.MotDePasse = MotDePasse;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Ville = Ville;
        this.DateDeNaissance = DateDeNaissance;
        this.Yeux = Yeux;
        this.Cheveux = Cheveux;
        this.Telephone = Telephone;
        this.Langue = Langue;
        this.Mail = Mail;
        this.Genre = Genre;
        this.Photo = Photo;
        this.Orientation = Orientation;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getMotDePasse() {
        return MotDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        MotDePasse = motDePasse;
    }

    public String getNom() {
        return Nom;
    }
    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public Date getDateDeNaissance() {
        return DateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        DateDeNaissance = dateDeNaissance;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getOrientation() {
        return Orientation;
    }

    public void setOrientation(String orientation) {
        Orientation = orientation;
    }

    public String getCheveux() {
        return Cheveux;
    }

    public void setCheveux(String cheveux) {
        Cheveux = cheveux;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getLangue() {
        return Langue;
    }

    public void setLangue(String langue) {
        Langue = langue;
    }

    public String getYeux() {
        return Yeux;
    }

    public void setYeux(String yeux) {
        Yeux = yeux;
    }

    public Blob getPhoto() {
        return Photo;
    }

    public void setPhoto(Blob photo) {
        Photo = photo;
    }//Classe associe a la table utilisateur de la BDD
}

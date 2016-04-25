package com.example.charles_henry.testquimarche;

import java.util.Date;

/**
 * Created by Mathieu on 25-04-16.
 */
public class Rencontre {

    private String destinataire;
    private String expediteur;
    private String lieu;
    private Date date;
    private int statut; // 0 en attente ; 1 accepte ; -1 refuse

    public Rencontre()
    {}

    public Rencontre(String destinataire, String expediteur, Date date, String lieu, int statut){
        this.destinataire=destinataire;
        this.expediteur=expediteur;
        this.date=date;
        this.lieu=lieu;
        this.statut=statut;
    }
    public Date getDate() {
        return date;
    }
    public String getLieu() {
        return lieu;
    }
    public String getDestinataire() {
        return destinataire;
    }
    public String getExpediteur() {
        return expediteur;
    }
    public int getStatut() {
        return statut;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }
    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }
    public void setStatut(int statut) {
        this.statut = statut;
    }
}

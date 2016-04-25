package com.example.charles_henry.testquimarche;

import java.util.Date;

/**
 * Created by charles-henry on 24-04-16.
 */
public class Message {
    private Date dateEnvoi;
    private String expediteur;
    private String destinataire;
    private String contenu;

    public Message()
    {}
    public Message(String expediteur,String destinataire,Date dateEnvoi,String contenu)
    {
        this.dateEnvoi=dateEnvoi;
        this.expediteur=expediteur;
        this.destinataire=destinataire;
        this.contenu=contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public void setExpediteur(String expediteur) {
        this.expediteur = expediteur;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public String getContenu() {
        return contenu;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public String getExpediteur() {
        return expediteur;
    }

    public int compareTo(Message anotherMess)
    {
        return dateEnvoi.compareTo(anotherMess.getDateEnvoi());
    }
}

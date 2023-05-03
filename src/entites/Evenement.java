/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Timestamp;

public class Evenement {

    private int id;

    private String nom;

    private String description;

    private Timestamp date;

    private String sponsor;
    private int nbPlace;

    private int nb_ticket;
    private boolean available;

    public Evenement() {
    }

    public Evenement(String nom, String description, Timestamp date, String sponsor, int nbPlace) {
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.sponsor = sponsor;
        this.nbPlace = nbPlace;
    }

    public Evenement(int id, String nom, String description, Timestamp date, String sponsor, int nbPlace) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.sponsor = sponsor;
        this.nbPlace = nbPlace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public int getNb_ticket() {
        return nb_ticket;
    }

    public void setNb_ticket(int nb_ticket) {
        this.nb_ticket = nb_ticket;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return nom;
    }

}

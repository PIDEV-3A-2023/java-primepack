/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;

/**
 *
 * @author expert
 */
public class Ordonnance {
     private int id;
      private String description,traitement;
      private Date date;
      private Rendezvous rendezVous;

    public Ordonnance(int id, String description, String traitement, Date date) {
        this.id = id;
        this.description = description;
        this.traitement = traitement;
        this.date = date;
    }
     


   

   

  

    public Rendezvous getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(Rendezvous rendezVous) {
        this.rendezVous = rendezVous;
    }

    public Ordonnance() {
    }

    public Ordonnance(int id,String description, String traitement, Date date,Rendezvous rendezVous) {
        this.id=id;
        this.description = description;
        this.traitement = traitement;
        this.date = date;
        this.rendezVous=rendezVous;
    }

    

    public Ordonnance(int id, String description, String traitement) {
        this.id = id;
        this.description = description;
        this.traitement = traitement;
    }




   
    @Override
    public String toString() {
        return "Ordonnance{" + "id=" + id + ", description=" + description + ", traitement=" + traitement + ", date=" + date + '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTraitement() {
        return traitement;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }
      
}

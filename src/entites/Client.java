/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author user
 */
public class Client extends User{
    
    private String adresse ;
    private String ville ;
    private String genre ;
    private String taille ;
    private String poids ;

    public Client() {
    }

    public Client(String adresse, String ville, String genre, String taille, String poids, String email, String password, String nom, String prenom, String dateNaissance, String telephone, String image, String roles, boolean isVerified) {
        this.adresse = adresse;
        this.ville = ville;
        this.genre = genre;
        this.taille = taille;
        this.poids = poids;
    }

    public Client(String adresse, String ville, String genre, String taille, String poids, int id, String email, String password, String nom, String prenom, String dateNaissance, String telephone, String image, String roles, boolean isVerified) {
        this.adresse = adresse;
        this.ville = ville;
        this.genre = genre;
        this.taille = taille;
        this.poids = poids;
    }
    
    
    

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    @Override
    public String toString() {
        return super.toString() +  "Client{" + "adresse=" + adresse + ", ville=" + ville + ", genre=" + genre + ", taille=" + taille + ", poids=" + poids + '}';
    }

    
}

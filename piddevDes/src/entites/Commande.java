/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author Houssem Charef
 */
public class Commande {

    private int idCommande;
    private int quantite;
    private Produit produit;
    private int idUser;

    public Commande() {
    }

    public Commande(int idPanier, int quantite, Produit produit, int idUser) {
        this.idCommande = idPanier;
        this.quantite = quantite;
        this.produit = produit;
        this.idUser = idUser;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Commande{" + "idPanier=" + idCommande + ", quantite=" + quantite + ", produit=" + produit + ", idUser=" + idUser + '}';
    }

}

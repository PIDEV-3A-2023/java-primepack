/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import entites.Post;
import entites.Comment;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author MSI
 */
public class Membre {

    private int id;
    private String nom, prenom, email, password, tel, adresse, role;
    private Date dateNaiss;
    private ArrayList<Comment> cmnts;
    private ArrayList<Post> posts;

    public Membre() {
    }

    public Membre(int id, String nom, String prenom, String email, String password, String tel, String adresse, String role, Date dateNaiss, ArrayList<Comment> cmnts, ArrayList<Post> posts) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.adresse = adresse;
        this.role = role;
        this.dateNaiss = dateNaiss;
        this.cmnts = cmnts;
        this.posts = posts;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public ArrayList<Comment> getCmnts() {
        return cmnts;
    }

    public void setCmnts(ArrayList<Comment> cmnts) {
        this.cmnts = cmnts;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Membre{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", tel=" + tel + ", adresse=" + adresse + ", role=" + role + ", dateNaiss=" + dateNaiss + ", cmnts=" + cmnts + ", posts=" + posts + '}';
    }

}

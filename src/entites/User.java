/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author Ennou
 */
public class User {

    private int id, numero;
    private String nom, prenom, email, password, adresse, role;

    public User() {
    }

    public User(int id, String nom, String prenom, String email, String password, int numero, String adresse, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.numero = numero;
        this.adresse = adresse;
        this.role = role;
    }

    public User(String nom, String prenom, String email, String password, int numero, String adresse, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.numero = numero;
        this.adresse = adresse;
        this.role = role;
    }

//    public User(String text, String text0, String text1, String text2, String text3, String text4) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + ", numero=" + numero + ", adresse=" + adresse + ", role=" + role + '}';
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

//  public void addComment(Comment c){
//        if(!this.cmnts.contains(c)){
//            this.cmnts.add(c);
//            c.setMembre(this);
//            
//        }
//    }
//    public void removeComment(Comment c){
//        if(this.cmnts.remove(c)){
//            if(c.getMembre()==this){
//                c.setMembre(null);
//            }
//        }
//        
//    }
//    
//    public void addPost(Post p){
//        if(!this.posts.contains(p)){
//            this.posts.add(p);
//            p.setMembre(this);
//            
//        }
//    }
//    
//    public void removePost(Post p){
//        if(this.posts.remove(p)){
//            if(p.getMembre()==this){
//                p.setMembre(null);
//            }
//        }
//        
//    }
}

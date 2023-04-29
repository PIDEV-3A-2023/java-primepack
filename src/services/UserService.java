/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author 
 */
public class UserService implements IUser<User>{
    
    public Connection conx;
    public UserService() {
        conx = MyDB.getInstance().getConx();
    }
    
//    private static int id ;
//    private static String email; 
//    private static String password ;
//    private static String nom ;
//    private static String prenom ;
//    private static String dateNaissance ;
//    private static String telephone ;
//    private static String image ;
//    private static String roles ;
//    
//    private String urlI = "http://127.0.0.1:8000/Client_img/";
//    
//    private static String diplome;
//    
//    private static String adresse ;
//    private static String ville ;
//    private static String genre ;
//    private static String taille ;
//    private static String poids ;
//
//    @Override
//    public String login(User t) throws SQLException {
//        String role = "";
//        try {
//
//            if (t.getEmail()!= null && t.getPassword()!= null) {
//
//                String req = "SELECT * from user";
//                PreparedStatement pst = conx.prepareStatement(req);
//
//                ResultSet rs = pst.executeQuery();
//
//                while (rs.next()) {
//                    
//                    // Créer l'URL avec les paramètres nécessaires
//                    String url = "http://127.0.0.1:8000/verifyPasswordjson?email="+t.getEmail()+"&password="+t.getPassword();
//
//                    // Établir une connexion HTTP à l'URL
//                    URL obj = new URL(url);
//                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//                    con.setRequestMethod("GET");
//
//                    // Vérifier que la réponse HTTP est OK avant de récupérer la réponse JSON
//                    if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                        String inputLine;
//                        StringBuilder response = new StringBuilder();
//                        while ((inputLine = in.readLine()) != null) {
//                            response.append(inputLine);
//                        }
//                        in.close();
//                        
//                        if (rs.getString("email").equals(t.getEmail()) && response.toString().equals("true")) {
//                            switch (rs.getString("roles")) {
//                                case "[\"ROLE_COACH\"]":
//
//                                    id = rs.getInt("id");
//                                    nom = rs.getString("nom");
//                                    prenom = rs.getString("prenom");
//                                    email = rs.getString("email");
//                                    password = rs.getString("password");
//                                    telephone = rs.getString("telephone");
//                                    roles = rs.getString("roles");
//                                    break;
//                                case "[\"ROLE_CLIENT\"]":
//                                    
//                                    String reqM = "SELECT adresse, ville, genre, taille, poids FROM client WHERE id="+rs.getInt("id");
//                                    PreparedStatement pstM = conx.prepareStatement(reqM);
//                                    ResultSet rsM = pstM.executeQuery();
//                                    
//                                    id = rs.getInt("id");
//                                    nom = rs.getString("nom");
//                                    prenom = rs.getString("prenom");
//                                    email = rs.getString("email");
//                                    password = rs.getString("password");
//                                    telephone = rs.getString("telephone");
//                                    roles = rs.getString("roles");
//                                    
//                                    while (rsM.next()) {
//                                        poids = rsM.getString("poids");
//                                        genre = rsM.getString("genre");
//                                        taille = rsM.getString("taille");
//                                        adresse = rsM.getString("adresse");
//                                        ville = rsM.getString("ville");
//                                    }
//                                    
//                                    role = rs.getString("roles");
//                                    break;
//                                case "[\"ROLE_ADMIN\"]":
//                                    id = rs.getInt("id");
//                                    nom = rs.getString("nom");
//                                    prenom = rs.getString("prenom");
//                                    email = rs.getString("email");
//                                    roles = rs.getString("roles");
//                                   
//                                    break;
//                                default:
//                                    break;
//                            }
//                        } else {
//                            System.err.println("Verifier vos donneés !");
//                        }
//                    }
//                }
//            }
//
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        } catch (IOException ex) {
//            System.err.println(ex.getMessage());
//        }
//        return role;
//    }
//
//    @Override
//    public void forgotPassword(User t) throws SQLException {
//        
//    }
//
//    public static int getId() {
//        return id;
//    }
//
//    public static void setId(int id) {
//        UserService.id = id;
//    }
//
//    public static String getEmail() {
//        return email;
//    }
//
//    public static void setEmail(String email) {
//        UserService.email = email;
//    }
//
//    public static String getPassword() {
//        return password;
//    }
//
//    public static void setPassword(String password) {
//        UserService.password = password;
//    }
//
//    public static String getNom() {
//        return nom;
//    }
//
//    public static void setNom(String nom) {
//        UserService.nom = nom;
//    }
//
//    public static String getPrenom() {
//        return prenom;
//    }
//
//    public static void setPrenom(String prenom) {
//        UserService.prenom = prenom;
//    }
//
//    public static String getDateNaissance() {
//        return dateNaissance;
//    }
//
//    public static void setDateNaissance(String dateNaissance) {
//        UserService.dateNaissance = dateNaissance;
//    }
//
//    public static String getTelephone() {
//        return telephone;
//    }
//
//    public static void setTelephone(String telephone) {
//        UserService.telephone = telephone;
//    }
//
//    public static String getImage() {
//        return image;
//    }
//
//    public static void setImage(String image) {
//        UserService.image = image;
//    }
//
//    public static String getRoles() {
//        return roles;
//    }
//
//    public static void setRoles(String roles) {
//        UserService.roles = roles;
//    }
//
//    public static String getDiplome() {
//        return diplome;
//    }
//
//    public static void setDiplome(String diplome) {
//        UserService.diplome = diplome;
//    }
//
//    public static String getAdresse() {
//        return adresse;
//    }
//
//    public static void setAdresse(String adresse) {
//        UserService.adresse = adresse;
//    }
//
//    public static String getVille() {
//        return ville;
//    }
//
//    public static void setVille(String ville) {
//        UserService.ville = ville;
//    }
//
//    public static String getGenre() {
//        return genre;
//    }
//
//    public static void setGenre(String genre) {
//        UserService.genre = genre;
//    }
//
//    public static String getTaille() {
//        return taille;
//    }
//
//    public static void setTaille(String taille) {
//        UserService.taille = taille;
//    }
//
//    public static String getPoids() {
//        return poids;
//    }
//
//    public static void setPoids(String poids) {
//        UserService.poids = poids;
//    }
//    
//    
//    
//    
     public void AjoutUser(User u) throws SQLException {
        String req = "INSERT INTO `user`(`nom`,`prenom`,`email`,`password`,`numero`,`adresse`,`role`) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setString(1, u.getNom());
        ps.setString(2, u.getPrenom());
        ps.setString(3, u.getEmail());
        ps.setString(4, u.getPassword());
        ps.setInt(5, u.getNumero());
        ps.setString(6, u.getAdresse());
        ps.setString(7, u.getRole());
        ps.executeUpdate();
        System.out.print("personne ajoutée");
    }

    public List<User> AfficherListeU() throws SQLException {
        String req = "SELECT * FROM `personne` ";
        Statement  stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        //System.out.println("rs: "+rs.toString());
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User u = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt("numero"), rs.getString(6), rs.getString(7));
            users.add(u);
        }
        return users;

    }
    public void supprimerUtilisateur(User user) {
        try {
            String requete="delete from user where id=?";
            PreparedStatement pst = conx.prepareStatement(requete);
            pst.setInt(1,user.getId());
            pst.executeUpdate();
           
            System.out.println("Utlisateur est supprimée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}
   public void modifierUtilisateur(User u) {
        try {
            String requete2="update user set cin=?,user_name=?,numero=?,email=?,adresse=? where id=?";
            PreparedStatement ps = conx.prepareStatement(requete2);
            ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPassword());
            ps.setInt(5, u.getNumero());
            ps.setString(6, u.getAdresse());
            ps.setString(7, u.getRole());
            ps.executeUpdate();
           
            System.out.println("Utlisateur est modifié");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
    }}

    @Override
    public String login(User t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void forgotPassword(User t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

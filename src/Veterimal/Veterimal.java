/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Veterimal;

import entites.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.UserService;
import utils.MyDB;

/**
 *
 * @author user
 */
public class Veterimal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        MyDB db = MyDB.getInstance();
           
        User u = new User("flora","floradoua@gmailcom","ffff", "hgbbh",253698,"rue","ADMIN");
        UserService us= new UserService();
//        try {
//            us.AjoutUser(u);
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        try {
//            System.out.println(us.AfficherListeP());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
   }
    
    
}

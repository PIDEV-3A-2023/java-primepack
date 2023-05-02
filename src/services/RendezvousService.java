/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Animal;
import entites.Rendezvous;
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
 * @author Ennou
 */
public class RendezvousService implements IRendezvous<Rendezvous>{

    public Connection conx;
    public Statement stm;
    
    
    AnimalService animalService= new AnimalService();
    public RendezvousService() {
        conx = MyDB.getInstance().getConx();
    }

    @Override
    public void ajoutRendezvous(Rendezvous r) throws SQLException{
        String req="INSERT INTO `rendez_vous`(`duree`, `date`, `animal_id`) "+" VALUES ('"+r.getDuree()+"','"+r.getDate()+"','"+r.getAnimal().getId()+"')";
        
        stm = conx.createStatement();
        stm.executeUpdate(req);
        System.out.println("rendezvous ajoutée");
    }


   
    @Override
    public List<Rendezvous> afficherListeR() throws SQLException{
        String req= "SELECT * FROM `rendez_vous`";
        stm = conx.createStatement();
        ResultSet rs = stm.executeQuery(req);
        //System.out.println("rs: "+rs.toString());
        List<Rendezvous> Rendezvouses = new ArrayList<>();
        while(rs.next()){
           Rendezvous r = new Rendezvous();
           r.setId(rs.getInt("id"));
           r.setDate(rs.getDate("date"));
           r.setDuree(rs.getInt("duree"));
           int animal_id=rs.getInt("animal_id");
           AnimalService animalService = new AnimalService();
           Animal animal = animalService.afficherA(animal_id);
           r.setAnimal(animal);
           Rendezvouses.add(r);
        }
        return Rendezvouses;
    }
    
    
    
           public Rendezvous afficherA(int id) throws SQLException{
           Rendezvous a = new Rendezvous();
            PreparedStatement ps = conx.prepareStatement(
                    "select * from rendez_vous where id = ?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                
                
           a.setId(rs.getInt("id"));
           a.setDate(rs.getDate("date"));
           
           a.setDuree(rs.getInt("duree"));
               
           int animal_idd = rs.getInt("animal_id");
           Animal animal =animalService.afficherA(animal_idd);
           a.setAnimal(animal);
            }
            ps.close();



        return a;
           
           
      
    }
    
    
    
    
}



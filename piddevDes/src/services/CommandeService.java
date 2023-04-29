/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Commande;
import entites.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

public class CommandeService implements IService<Commande> {

    private Connection con;
    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;

    public CommandeService() {
        con = DataSource.getInstance().getCnx();
    }

    @Override
    public boolean insert(Commande commande) {
        String sql = "INSERT INTO `commande`(`quantite`, `id_produit`, `id_user`) VALUES (?,?,?)";
        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, commande.getQuantite());
            ps.setInt(2, commande.getProduit().getId());
            ps.setInt(3, commande.getIdUser());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Commande commande) {
        String sql = "UPDATE `commande` SET `quantite`=?,`id_produit`=? ,`id_user`=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, commande.getQuantite());
            ps.setInt(2, commande.getProduit().getId());
            ps.setInt(3, commande.getIdUser());
            ps.setInt(4, commande.getIdCommande());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Commande commande) {
        String sql = "DELETE FROM `commande` WHERE id=?";
        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, commande.getIdCommande());
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Commande> getAll() {
        String sql = "SELECT * FROM `commande` INNER JOIN produit on produit.id = commande.id_produit;";

        List<Commande> list = new ArrayList<>();
        try {
            st = con.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                Produit produit = new Produit();

                produit.setId(rs.getInt("id"));
                produit.setImage(rs.getString("image"));
                produit.setIsavailable(rs.getBoolean("disponibilite"));
                produit.setNom(rs.getString("nom"));
                produit.setPrix(rs.getFloat("prix"));
                produit.setStock(rs.getInt("stock"));

                Commande c = new Commande();
                c.setProduit(produit);
                c.setIdUser(rs.getInt("id_user"));
                c.setQuantite(rs.getInt("quantite"));
                c.setIdCommande(rs.getInt(1));

                list.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Commande getOne(int id) {
        String sql = "SELECT * FROM `commande` INNER JOIN produit on produit.id = commande.id_produit where id=;" + id;
        Commande c = new Commande();

        try {
            st = con.createStatement();

            rs = st.executeQuery(sql);

            rs.next();
            Produit produit = new Produit();

            produit.setId(rs.getInt("id"));
            produit.setImage(rs.getString("image"));
            produit.setIsavailable(rs.getBoolean("disponibilite"));
            produit.setNom(rs.getString("nom"));
            produit.setPrix(rs.getFloat("prix"));
            produit.setStock(rs.getInt("stock"));

            c.setProduit(produit);
            c.setIdUser(rs.getInt("id_user"));
            c.setQuantite(rs.getInt("quantite"));
            c.setIdCommande(rs.getInt(1));

        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

}

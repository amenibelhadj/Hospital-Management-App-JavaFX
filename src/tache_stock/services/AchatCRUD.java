/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_stock.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import tache_stock.entities.Achat;
import tache_stock.entities.Produit;
import utils.MyConnexion;

/**
 *
 * @author hp
 */
public class AchatCRUD implements InterfaceAchat<Produit> {
    Connection conn = MyConnexion.getInstance().getConnection();

    public void ajouterProduit2(Achat a)
    {
        
            String req="INSERT INTO `achat`(`id_produit`, `id_user`, `prix`, `quantite`) VALUES (' "+ a.getId_produit()+ "',' "+ a.getId_user()+ "',' "+ a.getPrix()+ "',' "+ a.getQuantite()+ "')";
            Statement st ; 
        try {
            st=conn.createStatement() ; 
            st.executeUpdate(req) ; 
            System.out.println("ajout terminé!!!");
        }catch(SQLException ex )
        { 
            System.err.println(ex.getMessage()) ;
        } 
    }
    }
    

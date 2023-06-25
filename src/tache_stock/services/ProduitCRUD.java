/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_stock.services;

import java.sql.Connection;
import java.util.List;
import tache_stock.entities.Produit;
import utils.MyConnexion;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class ProduitCRUD implements InterfaceProduit<Produit>{
    Connection conn = MyConnexion.getInstance().getConnection();

    @Override
    public void ajouterProduit(Produit P) {
        String req="INSERT INTO `produit`(`id_produit`, `nom`, `type`, `quantite`, `prix`) VALUES (' "+ P.getId_produit()+ "',' "+ P.getNom() + "',' "+ P.getType() + "',' "+ P.getQuantite() + "',' " + P.getPrix() + "')";
        Statement st ; 
    try {
        st=conn.createStatement() ; 
        st.executeUpdate(req) ; 
        System.out.println("ajout terminé!!!");
    }catch(SQLException ex ){ 
        System.err.println(ex.getMessage()) ;} 
    }

    @Override
    public void modiferProduit(Produit P) {
        String req="UPDATE `produit` SET `nom`="
                +" '"+ P.getNom()+"',`type`="
                +" '"+ P.getType()+"',`quantite`="
                +" '"+ P.getQuantite()+"',`prix`="
                +" '"+ P.getPrix()+"' WHERE id_produit="+P.getId_produit();
        Statement st;
        try
        {
           st = conn.createStatement();
           st.executeUpdate(req);
        }
        catch(SQLException ex)
        {
            System.err.println(ex.getMessage());
        }
        
    }

    @Override
    public void supprimerProduit(int id_produit) {
        String req = "DELETE FROM produit WHERE `id_produit`='"+id_produit+"'" ;
        Statement st;
        try {
            st =conn.createStatement();
            st.executeUpdate(req);
            System.out.println("An equipment was deleted successfully!");
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List afficherProduit() {
       List <Produit> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM Produit";
            Statement st;
            st =conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Produit P = new Produit (rs.getInt("id_produit"), rs.getString("nom"),rs.getInt("type"),rs.getInt("quantite"),rs.getInt("prix"));
                list.add(P);
        
            }                
        }
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
       
        }
        return list;
        
    }
    @Override
    public List afficherProduit2() {
       List <Produit> list = new ArrayList<>();
        try {
            String req = "SELECT id_produit,nom,prix,quantite FROM Produit WHERE type=1";
            Statement st;
            st =conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                Produit P = new Produit ( rs.getInt("id_produit"),rs.getString("nom"),rs.getInt("quantite"),rs.getInt("prix"));
                list.add(P);
        
            }                
        }
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
       
        }
        return list;
        
    }
   public int rechercherProduit(String nom) {
       System.out.println("hhhhhhhhhZ");
        String req = "SELECT * FROM produit WHERE `nom`='"+nom+"'" ;
        Statement st;
        ResultSet rst;
        int B=0;
        try {
            st =conn.createStatement();
            rst=st.executeQuery(req);
            rst.last();
            int nbrRow=rst.getRow();
            if(nbrRow!=1)
            {
                System.out.println("Produit introuvable");
                B=0;
                
                
            }
            else
            {
                System.out.println("Produit trouve");
                B=1;
            }
            
            
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
        return B;
    }
    
    
    /*
     public void rechercherProduit(String nom) {
        String req = "SELECT * FROM produit WHERE `nom`='"+nom+"'" ;
        Statement st;
        ResultSet rst;
        
        try {
            st =conn.createStatement();
            rst=st.executeQuery(req);
            rst.last();
            int nbrRow=rst.getRow();
            if(nbrRow!=1)
            {
                System.out.println("Produit introuvable");
               
                
                
            }
            else
            {
                System.out.println("Produit trouve");
               
            }
            
            
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
        
    }*/
   
    }

    

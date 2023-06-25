/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_rdv_dispo.Service;


import tache_rdv_dispo.Entities.dispo;

import tache_rdv_dispo.IServices.IdispoService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnexion;

/**
 *
 * @author user
 */
public class dispoService {
     Connection cnx = utils.MyConnexion.getInstance().getConnection();
    public void ajouterD(dispo v)
    {

         try {

            Statement pst;
            pst =cnx.createStatement();
            String req = "INSERT INTO `disponibilite`(`nom_medd` ,`date`,`heure` ) "
                    + "VALUES ('"+v.getNom_medd()+"', '"+v.getDate()+"', '"+v.getHeure()+"')";
        
            
            pst.executeUpdate(req);
           
            System.out.println("ajouté");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }

    public void supprimerD(dispo r) {
               
        try {
            String requete = "DELETE FROM disponibilite WHERE id_rdv='" ;

            PreparedStatement pst = utils.MyConnexion.getInstance().getConnection()
                    .prepareStatement(requete);
            
            pst.setInt(1,r.getId_dispo());
            
            pst.executeUpdate(requete);
            System.out.println("deleted successfully!");
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }
      public void supp(int id) {
       String req = "DELETE FROM `disponibilite` WHERE `id_dispo`='"+id+"'" ;
        Statement st;
        try {
            st =cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(" deleted successfully!");
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }

    public void modifierD(dispo v) {
        
        
        try {
            
            String req="UPDATE disponibilite SET id_dispo=?, nom_medd=?,date=?,heure=? WHERE id_dispo=? ";
            PreparedStatement pst = utils.MyConnexion.getInstance().getConnection()
                    .prepareStatement(req);
            
            pst.setInt(1,v.getId_dispo());
            pst.setString(2, v.getNom_medd());
            pst.setString(3, v.getDate());
            pst.setString(4, v.getHeure());
            pst.setInt(5,v.getId_dispo());
           
            pst.executeUpdate();
            System.out.println("updated successfully!");
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }

    public List<dispo> afficherD() {
        List <dispo> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM disponibilite v  ";
            
            PreparedStatement pst;
            pst = utils.MyConnexion.getInstance().getConnection()
                    .prepareStatement(req);
            
            ResultSet rs = pst.executeQuery(req);
            while(rs.next()){
                
                dispo v = new dispo();
            v.setId_dispo(rs.getInt("id_dispo")); 
           
            v.setNom_medd(rs.getString("nom_medd"));
            v.setDate(rs.getString("date"));
            v.setHeure(rs.getString("heure"));
          
                    
             list.add(v);
            }
            
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
        return list;
       
    }

  
    
}

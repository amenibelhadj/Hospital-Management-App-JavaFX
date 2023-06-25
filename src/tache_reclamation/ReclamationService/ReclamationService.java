/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_reclamation.ReclamationService;

import tache_reclamation.Entities.Reclamation;
import tache_reclamation.Iservice.IReclamationService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnexion;

/**
 *
 * @author Koussay
 *
 */

public class ReclamationService implements IReclamationService <Reclamation> 
{
 @Override 
 public void ajouterReclamation(Reclamation r)
 {
     
     try {
            String requete= "INSERT INTO reclamation ( description,  nom,  prenom,  email)"
                    + "VALUES (?,?,?,?)";
            
          PreparedStatement pst = utils.MyConnexion.getInstance().getConnection() //envoie d'une requete a la base
                 .prepareStatement(requete);
            pst.setString(1,r.getDescription());
            pst.setString(2, r.getNom());
            pst.setString(3, r.getPrenom());
            pst.setString(4,r.getEmail());
            pst.executeUpdate();   
            System.out.println("Reclamation inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
 }
 @Override
 public void supprimerReclamation(Reclamation r)
 {
 try {
            String requete = "DELETE FROM reclamation where id=?";
            PreparedStatement pst = utils.MyConnexion.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.out.println("Reclamation supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
 
 }
  

 @Override
 public void modifierReclamation(Reclamation r)
{
 try {
            
     String requete = "UPDATE reclamation SET id=?, description=?,nom=?,prenom=?,email=? Where id =?";
            PreparedStatement pst = utils.MyConnexion.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setInt(1, r.getId());
            pst.setString(2, r.getDescription());
            pst.setString(3, r.getNom());
            pst.setString(4, r.getPrenom());
            pst.setString(5, r.getEmail()); 
            pst.setInt(6, r.getId());
            
            pst.executeUpdate();
            System.out.println("Reclamation modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    /**
     *
     * @return
     */
    @Override

    public List<Reclamation> afficherReclamations() {
   
        List<Reclamation> ReclamationsList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reclamation r ";
            Statement st = utils.MyConnexion.getInstance().getConnection()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete); //te5ou hajet f reclamation w thotohom f rs
            while(rs.next()){
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));
                r.setDescription(rs.getString("description"));
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setEmail(rs.getString("email")); 
                ReclamationsList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ReclamationsList;
         
         }




}
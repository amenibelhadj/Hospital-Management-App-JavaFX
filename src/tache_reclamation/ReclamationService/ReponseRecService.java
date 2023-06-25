/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_reclamation.ReclamationService;





import tache_reclamation.Entities.Reclamation;
import tache_reclamation.Entities.ReponseRec;
import tache_reclamation.Iservice.IReponseRecService;
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
 */
public class ReponseRecService implements IReponseRecService<ReponseRec> {
  
    @Override
    public void ajouterReponseRec(ReponseRec r)
 {
     try {
            String requete= "INSERT INTO reponse (id,nomAg,prenomAg,traitement)"
                    + "VALUES (?,?,?,?)";
            
          PreparedStatement pst = utils.MyConnexion.getInstance().getConnection()
                 .prepareStatement(requete);
            pst.setInt(1,r.getId());
          pst.setString(2,r.getNomAg());
            pst.setString(3, r.getPrenomAg());
            pst.setBoolean(4, r.isTraitement());
        
            pst.executeUpdate();   
            System.out.println("Réponse inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
 }
 }



    @Override
     public void supprimerReponseRec(ReponseRec r)
 {
 try {
            String requete = "DELETE FROM reponse where id=?";
            PreparedStatement pst = utils.MyConnexion.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.out.println("Réponse supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
 
 }
     
     
     
     
    @Override
     public void modifierReponseRec(ReponseRec r)
{
 try {
            String requete = "UPDATE reponse SET id=?,nomAg=?,prenomAg=?,traitement=?";
            PreparedStatement pst = utils.MyConnexion.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setInt(1,r.getId());
            pst.setString(2,r.getNomAg());
             pst.setString(3,r.getPrenomAg());
            pst.setBoolean(4,r.isTraitement());
            
            pst.executeUpdate();
            System.out.println("Réponse modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
     
     
     
   // @Override
    // public List<ReponseRec> afficherReponseRec() {
   
        /*List<ReponseRec> ReponseRecList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reponse r ";
            Statement st = DataSource.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete); //te5ou hajet f reclamation w thotohom f rs
            while(rs.next()){
                ReponseRec r = new ReponseRec();
                
                r.setIdRec(rs.getInt("idRec"));
                r.setMessage(rs.getString("message"));
                r.setDatecreation(rs.getString("date-creation"));
                ReponseRecList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ReponseRecList;
         
         }
}*/

     
   
    
       
    
    
    
    
    
    
    


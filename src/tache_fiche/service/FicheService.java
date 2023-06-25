
package tache_fiche.service;

import tache_fiche.entities.Fiche;
import utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 21654
 */
public class FicheService implements tache_fiche.service.IFicheService<Fiche> {
    
    
    
    @Override
    public void ajouterFiche(Fiche f) {
         try {
            String requete= "INSERT INTO `fiche` (Nom_patient,Prenom_patient,Medecin,Maladie)"
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = MyConnexion.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setString(1,f.getNom_patient());
            pst.setString(2, f.getPrenom_patient());
            pst.setString(3, f.getMedecin());
            pst.setString(4, f.getMaladie());
            pst.executeUpdate();
            System.out.println("fiche ajoutée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    

    @Override
    public void supprimerFiche(Fiche f) {
        try {
            String requete = "DELETE FROM fiche where ID_fiche=?";
            PreparedStatement pst = MyConnexion.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setInt(1, f.getId_fiche());
            pst.executeUpdate();
            System.out.println("Fiche supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
     public void suppF(int id) {
       String req = "DELETE FROM `fiche` WHERE `ID_fiche`='"+id+"'" ;
        Statement st;
        try {
            Connection cnx = MyConnexion.getInstance().getConnection();
            st =cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Fiche was deleted successfully!");
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }

   @Override
    public void modifierFiche(Fiche f) {
             
     try {
            String requete = "UPDATE fiche SET ID_fiche=?, Nom_patient=?, Prenom_patient=?, Medecin=?, Maladie=? Where ID_fiche=? ";
            PreparedStatement pst = MyConnexion.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setInt(1, f.getId_fiche());
            pst.setString(2, f.getNom_patient());
            pst.setString(3, f.getPrenom_patient());
            pst.setString(4, f.getMedecin());
            pst.setString(5, f.getMaladie());
            pst.setInt(6, f.getId_fiche());
            pst.executeUpdate();
            System.out.println("Fiche modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
    @Override
    public List<Fiche> afficherFiche() {
         List<Fiche> FichesList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM fiche ";
            PreparedStatement pst = MyConnexion.getInstance().getConnection()
                    .prepareStatement(requete);
            ResultSet rs =  pst.executeQuery(requete);
            while(rs.next()){
                Fiche f = new Fiche ();
                f.setId_fiche(rs.getInt("ID_fiche"));
                f.setNom_patient(rs.getString("Nom_patient"));
                f.setPrenom_patient(rs.getString("Prenom_patient"));
                f.setMedecin(rs.getString("Medecin"));  
                f.setMaladie(rs.getString("Maladie"));
                
                FichesList.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return FichesList;
    }

    
}

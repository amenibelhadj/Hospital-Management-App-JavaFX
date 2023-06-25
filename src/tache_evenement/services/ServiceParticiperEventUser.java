/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_evenement.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import tache_evenement.model.ReservationEvenement;
import utils.MyConnexion;

/**
 *
 * @author hama
 */
public class ServiceParticiperEventUser {
    private Connection con;
    private Statement ste;
    public ServiceParticiperEventUser() {
  MyConnexion db=new MyConnexion();
        con = db.getConnection();
    }

    public void reserver(ReservationEvenement r) {
        PreparedStatement pre = null;
        try {
           
            pre = con.prepareStatement("INSERT INTO `reservation_evenement` (  `id_event`,`nb_place`,`totale`,`mode_paiement`,`id_user`) VALUES ( ?, ?, ?, ?, ?);",Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, r.getId_event());
            pre.setInt(2, r.getNb_place());
            pre.setInt(3, r.getTotale());
            pre.setString(4, r.getMode_paiement());
            pre.setInt(5, r.getId_user());
        pre.executeUpdate();
        System.out.println("reservation ajouté");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("problem");
        }
        
        
    }

    public void AnnulerReservation(int idMemoriser, int iduser) {
        try {
            String requete = "delete from reservation_evenement where ? = id_event and ? = id_user";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, idMemoriser);
            pst.setInt(2, iduser);
            pst.executeUpdate();
            System.out.println("reservation Deleted !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }
}

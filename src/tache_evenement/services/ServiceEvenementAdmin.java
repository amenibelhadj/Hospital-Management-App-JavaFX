/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_evenement.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import tache_evenement.model.Evenement;
import utils.MyConnexion;

/**
 *
 * @author hama
 */
public class ServiceEvenementAdmin {
    public Connection con;
    private Statement ste;
    public ServiceEvenementAdmin() {
  MyConnexion db=new MyConnexion();
        con = db.getConnection();
    }

    public Evenement searchEvent(int idevent) throws SQLException, ParseException {
        String sql=null;
            sql = "select categorie,titre,pour,date_event,lieu,image,description,capacite,etat,tarif from evenement where id_event='"+idevent+"';";
            ste = con.prepareStatement(sql);
            System.out.println(ste);
            ResultSet rs = ste.executeQuery(sql);

            //Date date=new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(4));
            /*System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getString(5));
            System.out.println(rs.getString(6));
            System.out.println(rs.getString(7));
            System.out.println(rs.getString(8));*/
            Evenement e = null; 
            while(rs.next()){
                Date date=new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(4));
                e = new Evenement(rs.getString(1),rs.getString(2),rs.getString(5),rs.getString(7),date,rs.getString(6),Integer.parseInt(rs.getString(10)),Integer.parseInt(rs.getString(8)),0,rs.getString(9),rs.getString(3));
            }
            rs.close();
                return e;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_rdv_dispo.Entities;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author user
 */
public class RDV {
    private int id_rdv;
    private int id_user;
    private String prv; //PREMIERE VISITE
    private String nom_med;
    private String date;
    private String heure;

    public RDV() {
    }

    public RDV(int id_rdv, int id_user, String prv, String nom_med, String date, String heure) {
        this.id_rdv = id_rdv;
        this.id_user = id_user;
        this.prv = prv;
        this.nom_med = nom_med;
        this.date = date;
        this.heure = heure;
        
    }
    public RDV(int id_user, String prv, String nom_med, String date, String heure) {
        this.id_user = id_user;
        
        this.prv = prv;
        this.nom_med = nom_med;
        this.date = date;
        this.heure = heure;
        
    }


    public RDV(String prv, String nom_med, String date, String heure) {
        this.prv = prv;
        this.nom_med = nom_med;
        this.date = date;
        this.heure = heure;
       
    }

    public RDV(String value1, String value2, String value3, LocalDate value4, String value5) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    public RDV(Integer i, String value2, String value3, LocalDate value4, String value5) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_rdv() {
        return id_rdv;
    }

    public void setId_rdv(int id_rdv) {
        this.id_rdv = id_rdv;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    } 

    public String getPrv() {
        return prv;
    }

    public void setPrv(String prv) {
        this.prv = prv;
    }

    public String getNom_med() {
        return nom_med;
    }

    public void setNom_med(String nom_med) {
        this.nom_med = nom_med;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
    
    
   
    @Override
    public String toString() {
        return "RDV{" + "id_rdv=" + id_rdv + ", id_user=" + id_user + ", prv=" + prv + ", nom_med=" + nom_med + ", date=" + date + '}';
    }
     /*
     @Override
    public String toString() {
        return "RDV{" + "id_rdv=" + id_rdv  + ", prv=" + prv + ", nom_med=" + nom_med + ", date=" + date +", heure=" + heure +  '}';
    } */




   

    

  

    
    }

 





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_rdv_dispo.Entities;

import java.time.LocalDate;

/**
 *
 * @author user
 */
public class dispo {
    private int id_dispo;
    private int id_user;
    private String nom_medd;
    private String date;
    private String heure;

    public dispo() {
    }

    public dispo(int id_dispo, int id_user, String nom_medd, String date, String heure) {
        this.id_dispo = id_dispo;
        this.id_user = id_user;
        this.nom_medd = nom_medd;
        this.date = date;
        this.heure = heure;
    }

    public dispo(int id_dispo, String nom_medd, String date, String heure) {
        this.id_dispo = id_dispo;
        this.nom_medd = nom_medd;
        this.date = date;
        this.heure = heure;
    }

    public dispo(String nom_medd, String date, String heure) {
        this.nom_medd = nom_medd;
        this.date = date;
        this.heure = heure;
    }

    

    public int getId_dispo() {
        return id_dispo;
    }

    public void setId_dispo(int id_dispo) {
        this.id_dispo = id_dispo;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom_medd() {
        return nom_medd;
    }

    public void setNom_medd(String nom_medd) {
        this.nom_medd = nom_medd;
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
        return "dispo{" + "id_dispo=" + id_dispo + ", nom_medd=" + nom_medd + ", date=" + date + ", heure=" + heure + '}';
    }

   /* @Override
    public String toString() {
        return "dispo{" + "id_dispo=" + id_dispo + ", id_user=" + id_user + ", nom_medd=" + nom_medd + ", date=" + date + ", heure=" + heure + '}';
    } */
    
    
    
    
}


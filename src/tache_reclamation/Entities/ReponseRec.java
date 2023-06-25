/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_reclamation.Entities;

import java.time.LocalDate;

/**
 *
 * @author Koussay
 */
public class ReponseRec {
    private int id;
    private String 
    nomAg,
    prenomAg;
    boolean traitement;

    public ReponseRec(int id, String nomAg, String prenomAg, boolean traitement) {
        this.id = id;
        this.nomAg = nomAg;
        this.prenomAg = prenomAg;
        this.traitement = traitement;
    }

    
    public ReponseRec() {
    }

    public int getId() {
        return id;
    }

    public String getNomAg() {
        return nomAg;
    }

    public String getPrenomAg() {
        return prenomAg;
    }

    public boolean isTraitement() {
        return traitement;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomAg(String nomAg) {
        this.nomAg = nomAg;
    }

    public void setPrenomAg(String prenomAg) {
        this.prenomAg = prenomAg;
    }

    public void setTraitement(boolean traitement) {
        this.traitement = traitement;
    }

    @Override
    public String toString() {
        return "ReponseRec{" + "id=" + id + ", nomAg=" + nomAg + ", prenomAg=" + prenomAg + ", traitement=" + traitement + '}';
    }





}

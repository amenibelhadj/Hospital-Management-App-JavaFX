/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tache_user.entities;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Ameni
 */
public class User {
    private int id_user;
    private String nom_user;
    private String prenom_user;
    private String cin_user;
    private String email_user;
    private String role_user;
    private String mdp_user;

    public User() {
    }
    
    

    public User(int id_user, String nom_user, String prenom_user, String cin_user, String email_user, String role_user, String mdp_user) {
        this.id_user = id_user;
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.cin_user = cin_user;
        this.email_user = email_user;
        this.role_user = role_user;
        this.mdp_user = mdp_user;
    }

    public User(String nom_user, String prenom_user, String cin_user, String email_user, String role_user, String mdp_user) {
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.cin_user = cin_user;
        this.email_user = email_user;
        this.role_user = role_user;
        this.mdp_user = mdp_user;
    }

    public User(String role_user) {
        this.role_user=role_user;
    }

    public int getId_user() {
        return id_user;
    }

    public String getNom_user() {
        return nom_user;
    }

    public String getPrenom_user() {
        return prenom_user;
    }

    public String getCin_user() {
        return cin_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public String getRole_user() {
        return role_user;
    }

    public String getMdp_user() {
        return mdp_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public void setPrenom_user(String prenom_user) {
        this.prenom_user = prenom_user;
    }

    public void setCin_user(String cin_user) {
        this.cin_user = cin_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public void setRole_user(String role_user) {
        this.role_user = role_user;
    }

    public void setMdp_user(String mdp_user) {
        this.mdp_user = mdp_user;
    }

  
    
}





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_reclamation.Entities;

/**
 *
 * @author Koussay
 */
public class Reclamation {
    /**/   private int id;
           private String 
           description, 
           nom,
           prenom, 
           email;

    public Reclamation() {
    }

    public Reclamation(int id, String description, String nom, String prenom, String email) {
        this.id = id;
        this.description = description;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", description=" + description + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + '}';
    }
    
    
}

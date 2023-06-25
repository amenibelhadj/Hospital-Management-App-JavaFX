/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_stock.entities;

import java.util.Set;

/**
 *
 * @author hp
 */
public class Achat {
    private int id_achat;
    private String nom;
    private int id_produit;
    private int id_user;
    private int prix;
    private int quantite ;  

    public Achat() {
    }

    public Achat(int id_achat, int id_produit, int id_user, int prix, int quantite) {
        this.id_achat = id_achat;
        this.id_produit = id_produit;
        this.id_user = id_user;
        this.prix = prix;
        this.quantite = quantite;
    }
    public Achat(int id_produit,String nom, int id_user, int prix, int quantite) {
        this.nom=nom;
        this.id_produit = id_produit;
        this.id_user = id_user;
        this.prix = prix;
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Achat{" + "id_achat=" + id_achat + ", nom=" + nom + ", id_produit=" + id_produit + ", id_user=" + id_user + ", prix=" + prix + ", quantite=" + quantite + '}';
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public int getId_achat() {
        return id_achat;
    }

    public int getId_produit() {
        return id_produit;
    }

    public int getId_user() {
        return id_user;
    }

    public int getPrix() {
        return prix;
    }

    public void setId_achat(int id_achat) {
        this.id_achat = id_achat;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    


    
    
    
}

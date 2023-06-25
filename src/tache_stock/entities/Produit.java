/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_stock.entities;

/**
 *
 * @author hp
 */
public class Produit {
    private int id_produit;
    private String nom;
    private int type;
    private int quantite;
    private int prix;
    private int id_achat;

    public Produit() {
    }

    public Produit(int id_produit, String nom, int type, int quantite, int prix) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.type = type;
        this.quantite = quantite;
        this.prix = prix;
    }
    public Produit(int id_produit, String nom, int quantite, int prix) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
    }

    public Produit(String nom, int type, int quantite, int prix) {
        this.nom = nom;
        this.type = type;
        this.quantite = quantite;
        this.prix = prix;
    }

    public Produit(String nom, int quantite, int prix) {
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        
    }

   
    
   

    public int getId_produit() {
        return id_produit;
    }

    public String getNom() {
        return nom;
    }

    public int getType() {
        return type;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getPrix() {
        return prix;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id_produit + ", nom=" + nom + ", type=" + type + ", quantite=" + quantite + ", prix=" + prix + '}';
    }
    
    
    
}

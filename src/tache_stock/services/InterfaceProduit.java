/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_stock.services;
import java.util.List;
/**
 *
 * @author hp
 */
public interface InterfaceProduit<Produit> {
    public void ajouterProduit(Produit P);
    public void modiferProduit(Produit P);
    public void supprimerProduit(int id);
    public List afficherProduit();
    public List afficherProduit2();
    
}

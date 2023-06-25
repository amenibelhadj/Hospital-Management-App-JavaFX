/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_user.service;

import java.util.List;

/**
 *
 * @author Ameni
 */
public interface InterfaceUser <user> {
    public void ajouterUser (user u);
    public void modifierUser (user u);
    public void supprimerUser (user u);
    public List<user> afficherUser();
    /*public void archiverUser (user u);*/
    /*public void table_affiche();*/
}

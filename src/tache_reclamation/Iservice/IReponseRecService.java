/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_reclamation.Iservice;

import java.util.List;

/**
 *
 * @author Koussay
 */
public interface IReponseRecService <ReponseRec> {
    public void ajouterReponseRec(ReponseRec r);
    public void supprimerReponseRec(ReponseRec r);
    public void modifierReponseRec(ReponseRec r);
   // public List<ReponseRec> afficherReponseRec() ;
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_reclamation.Iservice;
import tache_reclamation.Entities.Reclamation;
import java.util.List;

/**
 *
 * @author Koussay
 * @param <Reclamation>
 */
public interface IReclamationService<Reclamation> {
    
    public void ajouterReclamation(Reclamation r);
    public void supprimerReclamation(Reclamation r);
    public void modifierReclamation(Reclamation r);
    public List<Reclamation> afficherReclamations();
   
}

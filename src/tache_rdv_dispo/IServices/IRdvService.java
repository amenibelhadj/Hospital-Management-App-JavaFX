/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_rdv_dispo.IServices;

import tache_rdv_dispo.Entities.RDV;
import java.util.List;



/**
 *
 * @author user
 * @param <Rdv>
 */
public interface IRdvService<Rdv> {
    public void ajouterRdv(RDV v);
    public void supprimerRdv(RDV v);
    public void modifier(RDV v);
   public List<Rdv> afficherRdvs();
    
}

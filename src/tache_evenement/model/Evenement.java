/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_evenement.model;
import java.util.Date;
import java.util.Objects;
import javafx.scene.image.ImageView;
/**
 *
 * @author user
 */

        
public class Evenement {
    private int id_event;
    private String categorie;
    private String titre;
    private String lieu;
    private String description;
    private Date date_event; 
    private String image;
    private int tarif;
    
    private int capacite;
    private int nb_reservation;
    private String etat;
    private String pour;
    
    public Evenement() {
    }

    public Evenement( String categorie, String titre, String lieu, String description, Date date_event, String image, int tarif, int capacite, int nb_reservation, String etat, String pour) {
        
        this.categorie = categorie;
        this.titre = titre;
        this.lieu = lieu;
        this.description = description;
        this.date_event = date_event;
        this.image = image;
        this.tarif = tarif;
        this.capacite = capacite;
        this.nb_reservation = nb_reservation;
        this.etat = etat;
        this.pour = pour;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id_event;
        hash = 67 * hash + Objects.hashCode(this.categorie);
        hash = 67 * hash + Objects.hashCode(this.titre);
        hash = 67 * hash + Objects.hashCode(this.lieu);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.date_event);
        hash = 67 * hash + Objects.hashCode(this.image);
        hash = 67 * hash + this.tarif;
        hash = 67 * hash + this.capacite;
        hash = 67 * hash + this.nb_reservation;
        hash = 67 * hash + Objects.hashCode(this.etat);
        hash = 67 * hash + Objects.hashCode(this.pour);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evenement other = (Evenement) obj;
        if (this.id_event != other.id_event) {
            return false;
        }
        if (this.tarif != other.tarif) {
            return false;
        }
        if (this.capacite != other.capacite) {
            return false;
        }
        if (this.nb_reservation != other.nb_reservation) {
            return false;
        }
        if (!Objects.equals(this.categorie, other.categorie)) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.lieu, other.lieu)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.pour, other.pour)) {
            return false;
        }
        if (!Objects.equals(this.date_event, other.date_event)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_event=" + id_event + ", categorie=" + categorie + ", titre=" + titre + ", lieu=" + lieu + ", description=" + description + ", date_event=" + date_event + ", image=" + image + ", tarif=" + tarif + ", capacite=" + capacite + ", nb_reservation=" + nb_reservation + ", etat=" + etat + ", pour=" + pour + '}';
    }

   
   

    public void setImage(ImageView emp0photo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_event() {
        return date_event;
    }

    public void setDate_event(Date date_event) {
        this.date_event = date_event;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTarif() {
        return tarif;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getNb_reservation() {
        return nb_reservation;
    }

    public void setNb_reservation(int nb_reservation) {
        this.nb_reservation = nb_reservation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getPour() {
        return pour;
    }

    public void setPour(String pour) {
        this.pour = pour;
    }

    
    
}
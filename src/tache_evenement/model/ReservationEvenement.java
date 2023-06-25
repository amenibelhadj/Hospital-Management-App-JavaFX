
package tache_evenement.model;

import java.util.Objects;


public class ReservationEvenement {
    private int id_reservation_evenement;
    private int id_event;
    private int nb_place;
    private int totale;
   private String mode_paiement;
    private int id_user;

    public int getId_reservation_evenement() {
        return id_reservation_evenement;
    }

    public void setId_reservation_evenement(int id_reservation_evenement) {
        this.id_reservation_evenement = id_reservation_evenement;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public int getTotale() {
        return totale;
    }

    public void setTotale(int totale) {
        this.totale = totale;
    }

    public String getMode_paiement() {
        return mode_paiement;
    }

    public void setMode_paiement(String mode_paiement) {
        this.mode_paiement = mode_paiement;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
  public ReservationEvenement( int id_event, int nb_place, int totale, String mode_paiement, int id_user) {
        
        this.id_event = id_event;
        this.nb_place = nb_place;
        this.totale = totale;
        this.mode_paiement = mode_paiement;
        this.id_user = id_user;
    }

     public ReservationEvenement() {
      }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.id_reservation_evenement;
        hash = 29 * hash + this.id_event;
        hash = 29 * hash + this.nb_place;
        hash = 29 * hash + this.totale;
        hash = 29 * hash + Objects.hashCode(this.mode_paiement);
        hash = 29 * hash + this.id_user;
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
        final ReservationEvenement other = (ReservationEvenement) obj;
        if (this.id_reservation_evenement != other.id_reservation_evenement) {
            return false;
        }
        if (this.id_event != other.id_event) {
            return false;
        }
        if (this.nb_place != other.nb_place) {
            return false;
        }
        if (this.totale != other.totale) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (!Objects.equals(this.mode_paiement, other.mode_paiement)) {
            return false;
        }
        return true;
    }

  

    @Override
    public String toString() {
        return "ReservationEvenement{" + "id_reservation_evenement=" + id_reservation_evenement + ", id_event=" + id_event + ", nb_place=" + nb_place + ", totale=" + totale + ", mode_paiement=" + mode_paiement + ", id_user=" + id_user + '}';
    }
    
    
    
    
}

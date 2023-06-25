
package tache_fiche.entities;

/**
 *
 * @author 21654
 */
public class Fiche {
        private int id_fiche;
        private String nom_patient;
        private String prenom_patient;
        private String medecin;
        private String maladie;
    
    public Fiche (int id_fiche ,String nom_patient ,String prenom_patient ,String medecin ,String maladie){
        this.id_fiche=id_fiche;
        this.nom_patient=nom_patient;
        this.prenom_patient=prenom_patient;
        this.medecin=medecin;
        this.maladie=maladie;
    }
    public Fiche ( String nom_patient, String prenom_patient ,String medecin, String maladie){
        this.nom_patient=nom_patient;
        this.prenom_patient=prenom_patient;
        this.medecin=medecin;
        this.maladie=maladie;
     
    }

    public int getId_fiche() {
        return id_fiche;
    }

    public String getNom_patient() {
        return nom_patient;
    }
      public String getPrenom_patient() {
        return prenom_patient;
    }
      public String getMedecin() {
        return medecin;
    }
        public String getMaladie() {
        return maladie;
    }
    public void setId_fiche(int id_fiche) {
        this.id_fiche = id_fiche;
    }
    public void setNom_patient(String nom_patient) {
        this.nom_patient=nom_patient;
    }
    public void setPrenom_patient(String prenom_patient) {
        this.prenom_patient=prenom_patient;
    }

    public void setMedecin(String medecin) {
        this.medecin = medecin;
    }
   public void setMaladie(String maladie) {
        this.maladie=maladie;
    }

    @Override
    public String toString() {
        return "Fiche{" + "id fiche=" + id_fiche + ", Nom_patient=" + nom_patient +
                ", Prenom_patient=" + prenom_patient +  "Medecin=" + medecin + "Maladie=" +maladie +'}';
    }

    public Fiche() {
    }


}
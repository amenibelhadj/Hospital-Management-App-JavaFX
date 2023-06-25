/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_evenement.Admininterfaces;

import com.jfoenix.controls.JFXCheckBox;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import tache_evenement.model.Evenement;
import tache_evenement.services.ServiceAjoutEvenementAdmin;

/**
 * FXML Controller class
 *
 * @author hama
 */
public class AjoutEvenementAdminController implements Initializable {

    @FXML
    private Button ModiferEventButton;
    @FXML
    private Button AjoutEventButton;
    @FXML
    private TextField NomEvenement;
    @FXML
    private TextArea DescriptionEvenement;
    @FXML
    private DatePicker DateEvenement;
    @FXML
    private ComboBox<String> Categorie;
    @FXML
    private Label TitleInterfaceAjout;
    @FXML
    private TextField LieuEvenement;
    @FXML
    private ComboBox<String> Pour;
    @FXML
    private Spinner<Integer> Tarif;
    @FXML
    private Spinner<Integer> Capacite;
    @FXML
    private ImageView ImageEvenement;
    @FXML
    private Label PathImageEvenement;
    @FXML
    private Button AnnulerImageEventBTN;
    ServiceAjoutEvenementAdmin cs = new ServiceAjoutEvenementAdmin();
    @FXML
    private Label PathImageEvenement2;
    @FXML
    private Label alertEventNom;
    @FXML
    private Label alertEventCategorie;
    @FXML
    private Label alertEventPour;
    @FXML
    private Label alertEventLieu;
    @FXML
    private Label alertEventDate;
    @FXML
    private Label alertEventDesc;
    @FXML
    private Label alertEventCapacité;
    @FXML
    private Label alertEventTarif;
    @FXML
    private Label alertEvent1;
    @FXML
    private Label alertEvent2;
    @FXML
    private Label alertEvent3;
    @FXML
    private Label alertEvent4;
    @FXML
    private Label alertEvent5;
    @FXML
    private Label alertEvent6;
    @FXML
    private Label alertEvent7;
    @FXML
    private Label alertEvent8;
    private Boolean imageChanger;
    @FXML
    private JFXCheckBox sendMail;
    /**
     * Initializes the controller class.
     */
    public Button getModiferEventButton() {
        return this.ModiferEventButton;
    }
public Button getAjoutEventButton() {
        return this.AjoutEventButton;
    }
public Label getAjouterModifierLabel() {
        return this.TitleInterfaceAjout;
    }
public void setNomEvenement(String nom) {
        this.NomEvenement.setText(nom);
    }
public void setDescription(String desc) {
         this.DescriptionEvenement.setText(desc);
    }
public void setLieu(String lieu) {
         this.LieuEvenement.setText(lieu);
    }
public void setCategorie(String categorie) {
         this.Categorie.setValue(categorie);
    }
public void setPour(String pour) {
         this.Pour.setValue(pour);
    }
public void setCapacite(int spin) {
         this.Capacite.getValueFactory().setValue(spin);
    }
public void setTarif(int spin) {
         this.Tarif.getValueFactory().setValue(spin);
    }
public void setDateEvent(LocalDate date) {
         this.DateEvenement.setValue(date);
    }
public void setPathImageEvent(String label) {
         this.PathImageEvenement.setText(label);
    }
public void setImageEvent(String extension,int id) throws FileNotFoundException {
         
         InputStream stream2 = new FileInputStream("C:/xampp/htdocs/Clinique/image evenement/0.jpg");
                                
                Image image2 = new Image(stream2);
                File folder = new File("C:/xampp/htdocs/Clinique/image evenement/"); 
                File[] listOfFiles = folder.listFiles(); 
                int existe = 0 ;
                for (File file : listOfFiles) { 
                    if (file.isFile()) { 
                        if (file.getName().equals(id+extension)){
                            InputStream stream = new FileInputStream("C:/xampp/htdocs/Clinique/image evenement/"+id+extension);
                            Image image = new Image(stream);
                            ImageEvenement.setImage(image);
                            existe=1;
                        } 
                    } 
                } 
                if (existe == 0){      
                    ImageEvenement.setImage(image2);
                }
    }
public void setAnnulerImageEvent(Button annuler) {
         this.AnnulerImageEventBTN = annuler;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imageChanger=false;
        ObservableList<String> list;
        list = FXCollections.observableArrayList("Siminaire","Formation","Compagne de don","Voyage","Compagne de sensibilisation","Consultation sur terain" );
        Categorie.setItems(list);
        ObservableList<String> list2;
        list2 = FXCollections.observableArrayList("Tous","Médecins","Médecins,épouses","Cadre médicale","Infirmiers","Infirmiers,épouses","Les agents" );
        Pour.setItems(list2);
        
        final int initialValue = 1;

        // Value factory.
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(50,200, initialValue);

        Capacite.setValueFactory(valueFactory);
        
        final int initialValue2 = 10;

        // Value factory.
        SpinnerValueFactory<Integer> valueFactory2 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000, initialValue2);

        Tarif.setValueFactory(valueFactory2);
    }    

    @FXML
    private void AjoutEventButton(ActionEvent event) throws ParseException, MessagingException {
        boolean erreur = false;
        if (NomEvenement.getText().equals("")){
            alertEvent1.setVisible(true);
            erreur=true;
        }
        else 
            alertEvent1.setVisible(false);
        boolean isMyComboBoxEmpty11 = Categorie.getSelectionModel().isEmpty();
        if(isMyComboBoxEmpty11){
            alertEvent2.setVisible(true);
            erreur=true;
        }
        else 
            alertEvent2.setVisible(false);
        boolean isMyComboBoxEmpty22 = Pour.getSelectionModel().isEmpty();
        if(isMyComboBoxEmpty22){
            alertEvent3.setVisible(true);
            erreur=true;
        }
        else 
            alertEvent3.setVisible(false);
        if (LieuEvenement.getText().equals("")){
            alertEvent4.setVisible(true);
        }
        else 
            alertEvent4.setVisible(false);
        if (DateEvenement.getValue()!=null){
            
            if (DateEvenement.getValue().compareTo(LocalDate.now())<0){
                alertEvent5.setVisible(true);
            erreur=true;
            }
            else 
                alertEvent5.setVisible(false);
        }
        else 
            alertEvent5.setVisible(true);
        
        if (DescriptionEvenement.getText().equals("")){
            alertEvent6.setVisible(true);
            erreur=true;
        }
        else 
            alertEvent6.setVisible(false);
        
        
        
        
        
        
        if (erreur == false){
            String nomEvent=NomEvenement.getText();
            String categorie=null;
            String pour=null;
            boolean isMyComboBoxEmpty = Categorie.getSelectionModel().isEmpty();
            if(isMyComboBoxEmpty)
                 categorie = "";
            else
                categorie = Categorie.getSelectionModel().getSelectedItem().toString();

            boolean isMyComboBoxEmpty2 = Categorie.getSelectionModel().isEmpty();
            if(isMyComboBoxEmpty2)
                 pour = "";
            else
                pour = Pour.getSelectionModel().getSelectedItem().toString();
            String lieu = LieuEvenement.getText();
            String dateEvent="";
            dateEvent= String.valueOf(DateEvenement.getValue());
            Date date=new SimpleDateFormat("yyyy-MM-dd").parse(dateEvent);
            String description = null;
            description = DescriptionEvenement.getText();
            String imagePath = null;
            imagePath = PathImageEvenement.getText();
            String extension = null;
            if (imagePath.equals("")){
                extension = ".jpg";
            }
            else {
                extension = imagePath.substring(imagePath.length()-4, imagePath.length());
            }
            Integer capacite = null;
            capacite = Capacite.getValue();
            Integer tarif = null;
            tarif = Tarif.getValue();
            Evenement e = new Evenement(categorie, nomEvent,lieu,description,date,extension,tarif,capacite,0,"en cours",pour);
            int id = 0;
            try {
                
                id = cs.ajouter1(e,sendMail.isSelected());
            } catch (SQLException ex) {
                Logger.getLogger(AjoutEvenementAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();

            File outputfile = new File("C:/xampp/htdocs/Clinique/image evenement/"+id+".jpg");
            try {
                BufferedImage buffer = ImageIO.read(new File(PathImageEvenement2.getText()));
                ImageIO.write(buffer, imagePath.substring(imagePath.length()-3, imagePath.length()), outputfile); // Write the Buffered Image into an output file

            } catch (IOException ex) {
                Logger.getLogger(AjoutEvenementAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
    }
    @FXML
    private void ChoisirImageEvenementBTN(ActionEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        File selected = fc.showOpenDialog(null);
        String extension = null;
        if(selected !=null )
        {
             extension= selected.getAbsolutePath().substring(selected.getAbsolutePath().length()-3,selected.getAbsolutePath().length());
            System.out.println(extension);
            if(!extension.equals( "jpg") && !extension.equals("png"))
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid picture");
        
        alert.setContentText("Invalid picture format (only jgp/png available)"); 
     
        alert.showAndWait();
        PathImageEvenement.setText("");
            }else{
                imageChanger=true;
            PathImageEvenement.setText(selected.getName());
            PathImageEvenement2.setText(selected.getAbsolutePath());
            AnnulerImageEventBTN.setVisible(true);}
        
        InputStream stream = new FileInputStream(selected.getAbsolutePath());
      Image image = new Image(stream);
        ImageEvenement.setImage(image);
        }
      
      
    }

    @FXML
    private void EffacerChampsEvenement(ActionEvent event) {
        NomEvenement.setText("");
        LieuEvenement.setText("");
        DateEvenement.setValue(null);
        DescriptionEvenement.setText("");
        Tarif.getValueFactory().setValue(10);
        Capacite.getValueFactory().setValue(50);
        PathImageEvenement.setText("");
        ImageEvenement.setImage(null);
        AnnulerImageEventBTN.setVisible(false);
        Categorie.setValue("");
        Pour.setValue("");
    }

    @FXML
    private void AnnulerImageEvenementBTN(ActionEvent event) {
        PathImageEvenement.setText("");
        ImageEvenement.setImage(null);
        AnnulerImageEventBTN.setVisible(false);
    }

    @FXML
    private void ModifierEventButton(ActionEvent event) throws ParseException {
        boolean erreur = false;
        if (NomEvenement.getText().equals("")){
            alertEvent1.setVisible(true);
            erreur=true;
        }
        else 
            alertEvent1.setVisible(false);
        boolean isMyComboBoxEmpty11 = Categorie.getSelectionModel().isEmpty();
        if(isMyComboBoxEmpty11){
            alertEvent2.setVisible(true);
            erreur=true;
        }
        else 
            alertEvent2.setVisible(false);
        boolean isMyComboBoxEmpty22 = Pour.getSelectionModel().isEmpty();
        if(isMyComboBoxEmpty22){
            alertEvent3.setVisible(true);
            erreur=true;
        }
        else 
            alertEvent3.setVisible(false);
        if (LieuEvenement.getText().equals("")){
            alertEvent4.setVisible(true);
        }
        else 
            alertEvent4.setVisible(false);
        if (DateEvenement.getValue()!=null){
            
            if (DateEvenement.getValue().compareTo(LocalDate.now())<0){
                alertEvent5.setVisible(true);
            erreur=true;
            }
            else 
                alertEvent5.setVisible(false);
        }
        else 
            alertEvent5.setVisible(true);
        
        if (DescriptionEvenement.getText().equals("")){
            alertEvent6.setVisible(true);
            erreur=true;
        }
        else 
            alertEvent6.setVisible(false);
        
        
        
        if (!erreur){
            String nomEvent=NomEvenement.getText();
            String categorie=null;
            String pour=null;
            boolean isMyComboBoxEmpty = Categorie.getSelectionModel().isEmpty();
            if(isMyComboBoxEmpty)
                 categorie = "";
            else
                categorie = Categorie.getSelectionModel().getSelectedItem().toString();

            boolean isMyComboBoxEmpty2 = Categorie.getSelectionModel().isEmpty();
            if(isMyComboBoxEmpty2)
                 pour = "";
            else
                pour = Pour.getSelectionModel().getSelectedItem().toString();
            String lieu = LieuEvenement.getText();
            String dateEvent="";
            dateEvent= String.valueOf(DateEvenement.getValue());
            Date date=new SimpleDateFormat("yyyy-MM-dd").parse(dateEvent);
            String description = null;
            description = DescriptionEvenement.getText();
            String extension = null;

            Integer capacite = null;
            capacite = Capacite.getValue();
            Integer tarif = null;
            tarif = Tarif.getValue();
            Evenement e = new Evenement(categorie, nomEvent,lieu,description,date,".jpg",tarif,capacite,0,"en cours",pour);

            String imagePath = null;
                imagePath = PathImageEvenement.getText();

            cs.Modifier1(e, EvenementAdminController.idMemoriser);
            Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                if (PathImageEvenement.getText()!=""){
                File outputfile = new File("C:/xampp/htdocs/Clinique/image evenement/"+EvenementAdminController.idMemoriser+".jpg");
                try {
                    BufferedImage buffer = ImageIO.read(new File(PathImageEvenement2.getText()));
                    ImageIO.write(buffer, "jpg", outputfile); // Write the Buffered Image into an output file

                } catch (IOException ex) {
                    Logger.getLogger(AjoutEvenementAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        //AjoutEvenementAdminController.this.initialize(null,null);
        
    }

    @FXML
    private void exitAlertEventNom(MouseEvent event) {
        alertEventNom.setVisible(false);
    }

    @FXML
    private void showAlertEventNom(MouseEvent event) {
        alertEventNom.setVisible(true);

    }

    @FXML
    private void exitAlertEventCategorie(MouseEvent event) {
        alertEventCategorie.setVisible(false);

    }

    @FXML
    private void showAlertEventCategorie(MouseEvent event) {
                alertEventCategorie.setVisible(true);

    }

    @FXML
    private void exitAlertEventPour(MouseEvent event) {
        alertEventPour.setVisible(false);
    }

    @FXML
    private void showAlertEventPour(MouseEvent event) {
        alertEventPour.setVisible(true);
    }

    @FXML
    private void exitAlertEventLieu(MouseEvent event) {
        alertEventLieu.setVisible(false);
    }

    @FXML
    private void showAlertEventLieu(MouseEvent event) {
        alertEventLieu.setVisible(true);
    }

    @FXML
    private void exitAlertEventDate(MouseEvent event) {
        alertEventDate.setVisible(false);
    }

    @FXML
    private void showAlertEventDate(MouseEvent event) {
        alertEventDate.setVisible(true);
    }

    @FXML
    private void exitAlertEventDesc(MouseEvent event) {
        alertEventDesc.setVisible(false);
    }

    @FXML
    private void showAlertEventDesc(MouseEvent event) {
        alertEventDesc.setVisible(true);
    }

    @FXML
    private void exitAlertEventCapacité(MouseEvent event) {
        alertEventCapacité.setVisible(false);
    }

    @FXML
    private void showAlertEventCapacité(MouseEvent event) {
        alertEventCapacité.setVisible(true);
    }

    @FXML
    private void exitAlertEventTarif(MouseEvent event) {
        alertEventTarif.setVisible(false);
    }

    @FXML
    private void showAlertEventTarif(MouseEvent event) {
        alertEventTarif.setVisible(true);
    }
    
}

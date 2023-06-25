/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_fiche.gui;

import tache_fiche.entities.Fiche;
import tache_fiche.service.FicheService;
import utils.MyConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author 21654
 */
public class AjouterFicheController implements Initializable {

    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_med;
    @FXML
    private TextField txt_mal;
    @FXML
    private Button btn_confirmer_ajout;
    @FXML
    private Button btn_home1;
    @FXML
    private Button btnClose;
    Connection mc;
    PreparedStatement ste;
    ObservableList<Fiche>fichelist;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     

    @FXML
    private void ajouterF(MouseEvent event) {
         String nom , prenom , med,mal;
        nom=txt_nom.getText();
        prenom=txt_prenom.getText();
        med=txt_med.getText();
        mal=txt_mal.getText();
        if (nom.isEmpty() | prenom.isEmpty() | med.isEmpty() | mal.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setContentText("Veillez remplir les champs !");
                alert.showAndWait();
        }
        else{
            Fiche f7 = new Fiche(nom , prenom ,med ,mal);
            FicheService fss = new FicheService ();
            fss.ajouterFiche(f7);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setContentText("Fiche Ajoutée avec succes!");
                alert.showAndWait();
        }
        
    }

    @FXML
    private void goHome1(MouseEvent event) {
            try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IFiche.fxml"));
    Parent root = (Parent) fxmlLoader.load();
    Stage stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.initStyle(StageStyle.UNDECORATED);
    stage.setTitle("Home");
    stage.setScene(new Scene(root));  
    stage.show();
           }
        
        catch (IOException ex) {
            Logger.getLogger(IFicheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    @FXML
    private void on_btnClose_clicked(MouseEvent event) {
    Stage stage = (Stage) btnClose.getScene().getWindow();
    stage.close(); //element.getScene().getWindow().setWidth(element.getScene().getWidth() + 0.001);

    }

  
}

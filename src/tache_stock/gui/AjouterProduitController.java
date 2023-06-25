/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_stock.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tache_stock.entities.Produit;
import tache_stock.services.ProduitCRUD;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AjouterProduitController implements Initializable {
     private Stage stage ; 
    private Stage scene ; 
    private Stage root ;
    ResultSet rs;
    
    
    
    @FXML
    private TextField chercher_input;
    @FXML
    private TextField nom_input;
    @FXML
    private MenuButton  type_MB;
    @FXML
    private TextField quantite_input;
    @FXML
    private TextField prix_input;
    @FXML
    private Button ajouter_button;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        type_MB.getItems().clear();
        MenuItem m1 = new MenuItem("medicament");
        MenuItem m2 = new MenuItem("equipement");
        type_MB.getItems().add(m1);
        type_MB.getItems().add(m2);
          
          EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
              @Override
            public void handle(ActionEvent e)
            {
                type_MB.setText(((MenuItem)e.getSource()).getText() );
            }
        };
 
        // add action events to the menuitems 
        m1.setOnAction(event1);
        m2.setOnAction(event1);
        
        
        
    }  
    @FXML
    private void modifier_AFF(ActionEvent event) {

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("afficherProduit.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void fonction_ajout(ActionEvent event) {
    
            String id_produit,nom,quantite,prix,type ;
            nom=nom_input.getText();
            quantite=quantite_input.getText();
            prix=prix_input.getText();
            type=type_MB.getText();
            ProduitCRUD cc = new ProduitCRUD() ;
           /* Alert alert2 = new Alert(AlertType.INFORMATION);

                alert2.setTitle("Information Dialog");

                alert2.setHeaderText(null);

                alert2.setContentText("Attention tous les champs sont obligatoires!");

                alert2.show();*/
            //controle saisie
            if (nom_input.getText().equals("") || quantite_input.getText().equals("") || prix_input.getText().equals("") || type_MB.getText().equals("") )      
            {   Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Failed");
                alert1.setHeaderText("Attention !!");
                alert1.setContentText("Missing Informations");
                alert1.show();
             }
            
            else
                
            {
                if(cc.rechercherProduit(nom_input.getText())==1)
                {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Failed");
                alert1.setHeaderText("Attention !!");
                alert1.setContentText("Produit deja existant veuillez modifier juste la quantite");
                alert1.show();
                }
                else if (type.equals("medicament")){
                    Produit P1 = new Produit(nom,1,Integer.parseInt(quantite),Integer.parseInt(prix)); 

                   // ProduitCRUD cc = new ProduitCRUD() ;
                    cc.ajouterProduit(P1);
                    Alert alert = new Alert(AlertType.INFORMATION);

                    alert.setTitle("Information Dialog");

                    alert.setHeaderText(null);

                    alert.setContentText("Medicament inséré avec succés!");

                    alert.show(); 
                    }
                else {
            
                    Produit P1 = new Produit(nom,2,Integer.parseInt(quantite),Integer.parseInt(prix));         
                   // ProduitCRUD cc = new ProduitCRUD() ;
                    cc.ajouterProduit(P1);
                    Alert alert = new Alert(AlertType.INFORMATION);

                    alert.setTitle("Information Dialog");

                    alert.setHeaderText(null);

                    alert.setContentText("Equipement inséré avec succés!");

                    alert.show(); 

                }
                
            }
            
            
            
            
            
  
   }
   
    
}

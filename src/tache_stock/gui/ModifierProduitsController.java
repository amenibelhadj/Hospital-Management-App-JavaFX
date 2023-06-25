/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_stock.gui;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tache_stock.entities.Produit;
import tache_stock.services.ProduitCRUD;


public class ModifierProduitsController implements Initializable {
    private Stage stage;
    private Stage scene;
    private Stage root;
    
    @FXML
    private TextField modifid;

   @FXML
    private TextField modifnom;
    @FXML
    private TextField modiftype;
    @FXML
    private TextField modifquantite;
    @FXML
    private TextField modifprix;
    @FXML
    private Button buttonmodif;
    @FXML
    private Button modif_aff;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void modifierProduit(ActionEvent event) {
        int ID=0;
        int Quantite=0;
        int Prix=0;
        int Type=0;
        String nom, type , quantite , prix,id ; 
        
        id = modifid.getText();
        nom = modifnom.getText();
        type= modiftype.getText();
        quantite = modifquantite.getText();
        prix = modifprix.getText();
        ID =Integer.parseInt(id);
        Quantite =Integer.parseInt(quantite);
        Prix =Integer.parseInt(prix);
        Type =Integer.parseInt(type);
        if (Type!=1 && Type!=2 )      
            {   Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Failed");
                alert1.setHeaderText("Attention !!");
                alert1.setContentText("type not valid");
                alert1.show();
             }
        else{
        Produit P = new Produit(ID,nom,Type,Quantite,Prix);
            ProduitCRUD ps= new ProduitCRUD();
            ps.modiferProduit(P);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Update Done !!");
                alert.setContentText("You have successfully updated a product");
                alert.show();
        }
        
        
       
    }
    @FXML
    private void AFF_modifier(ActionEvent event) {

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
    
    
    
}


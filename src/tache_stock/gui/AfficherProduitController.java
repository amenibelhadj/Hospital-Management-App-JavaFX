/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_stock.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tache_stock.entities.Produit;
import tache_stock.services.ProduitCRUD;
import utils.MyConnexion;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AfficherProduitController implements Initializable {
    private Stage stage;
    private Stage scene;
    private Stage root;
    
    @FXML
    private Button buttonaff;
    @FXML
    private TableView<Produit> tableau;
    @FXML
    private TableColumn<Produit, Integer> col_id;
    @FXML
    private TableColumn<Produit, String> col_nom ;
    @FXML
    private TableColumn<Produit, Integer> col_type ;
    @FXML
    private TableColumn<Produit, Integer> col_quantite ;
    @FXML
    private TableColumn<Produit, Integer> col_prix ;
    
    ObservableList<Produit> AnnList = FXCollections.observableArrayList();
    
    @FXML
    private Button button_sup;
    
    @FXML
    private Button afficher_ajouter;
    
    
    @FXML
    private Button afficher_modifier;
    @FXML
    private Button button_med;
    @FXML
    private Button button_eq;
    @FXML
    private TextField chercher_input;
    @FXML
    private Button buttonRehercher;
    
    private MenuButton  CTYPE;
    @FXML
    private ImageView back;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        
       
        
        // TODO
    }
    
    
    
    public Integer rechercher(Produit P){
                    String nom;
                    int exist = 0;
                    Connection cnx = MyConnexion.getInstance().getConnection();
                    PreparedStatement pst;
                    ResultSet rs = null;
                    ProduitCRUD cc = new ProduitCRUD() ;
                    nom=chercher_input.getText();
                    
                   exist= cc.rechercherProduit(nom);
                   return exist;
                    
                  
    }
    @FXML
    void Affichertout(ActionEvent event) {
        ProduitCRUD ps = new ProduitCRUD();
        ArrayList<Produit> Produit;

            Produit = (ArrayList<Produit>) ps.afficherProduit();
            ObservableList obs = FXCollections.observableArrayList(Produit);
            tableau.setItems(obs);
            col_id.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
            col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));

   
     }
    @FXML
    private void ajouter_AFF(ActionEvent event) {

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("AjouterProduit.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void modifier_AFF(ActionEvent event) {

        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("modifierProduits.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void affiche_medicament(ActionEvent event) {
        AnnList.clear();
        ProduitCRUD cc = new ProduitCRUD();
        List<Produit> list = cc.afficherProduit();
        AnnList.addAll(list.stream().filter(c -> c.getType()== 1).collect(Collectors.toList()));
        tableau.setItems(AnnList);

    }

    @FXML
    private void affiche_equipement(ActionEvent event) {
        AnnList.clear();
        ProduitCRUD cc = new ProduitCRUD();
        List<Produit> list = cc.afficherProduit();
        AnnList.addAll(list.stream().filter(c -> c.getType()== 2).collect(Collectors.toList()));
        tableau.setItems(AnnList);

    }
    @FXML
  
     private void supprimerproduit(ActionEvent event) {
        tableau.getItems().removeAll(tableau.getSelectionModel().getSelectedItems());
        Produit P  = tableau.getSelectionModel().getSelectedItem();
       
        ProduitCRUD cr = new ProduitCRUD();
        cr.supprimerProduit(P.getId_produit());
    }
    @FXML 
      private void rechercherProduit(ActionEvent event)
    {
        if (chercher_input.getText().equals(""))
        {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Failed");
                alert1.setHeaderText("Attention !!");
                alert1.setContentText("SVP ENTRER QUELQUE CHOSE");
                alert1.show();
        }
        else 
        {
           AnnList.clear();
            ProduitCRUD cc = new ProduitCRUD();
            List<Produit> list = cc.afficherProduit();
            AnnList.addAll(list.stream().filter(c -> c.getNom().contains(chercher_input.getText()) ).collect(Collectors.toList()));
            tableau.setItems(AnnList);
        }       
    }

    @FXML
    private void back_tologin(MouseEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("/tache_user/gui/Authentification.fxml")); 
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                                      root.setOnMousePressed(pressEvent -> {
                        root.setOnMouseDragged(dragEvent -> {
                            stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                            stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                        });
                    });
                        Scene  scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
    }
    
     
     
     
     
     
}


    


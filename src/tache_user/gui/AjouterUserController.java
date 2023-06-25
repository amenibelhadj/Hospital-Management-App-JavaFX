/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_user.gui;

import tache_user.entities.User;
import tache_user.service.UserCRUD;
import utils.MyConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class AjouterUserController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtcin;
    @FXML
    private TextField txtemail;
    @FXML
    private ComboBox boxrole;
    @FXML
    private Button confirmer;
    @FXML
    private TextField txtmdp;
    
    Stage Stage1;
    @FXML
    private ImageView back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> listrole = FXCollections.observableArrayList("","patient","medecin","pharmacien","infirmier","agent_reclamation");
        boxrole.setValue("");
        /*boxrole.setValue("medecin");
        boxrole.setValue("pharmacien");
        boxrole.setValue("infirmier");
        boxrole.setValue("agent_reclamation");*/
        boxrole.setItems(listrole);
        
    }  
    
    public Integer rechercheUser(User u){
                    Integer exist = 0;
                    Connection cnx = MyConnexion.getInstance().getConnection();
                    PreparedStatement pst;
                    ResultSet rs = null;
                    try {
                        pst = cnx.prepareStatement("SELECT COUNT(*) AS count FROM user WHERE nom_user=?,prenom_user=?,cin_user=?,email_user=?,role_user=? ");
                        pst.setString(1, u.getNom_user());
                        pst.setString(2, u.getPrenom_user());
                        pst.setString(3, u.getCin_user());
                        pst.setString(4, u.getEmail_user());
                        pst.setString(5, u.getRole_user());
                        rs = pst.executeQuery();
                        if (rs.next()) {
                             exist=rs.getInt("count"); 
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                    return exist;
    }

    @FXML
    private void ajouterU(ActionEvent event) {
        String nom,prenom,email,role;
        String cin,mdp;
        
        nom = txtnom.getText();
        prenom = txtprenom.getText();
        email = txtemail.getText();
        role = (String) boxrole.getSelectionModel().getSelectedItem();
        cin = txtcin.getText();
        mdp = txtmdp.getText();
        
        //Contôle de saisie
        if (nom==null || prenom ==null || email==null || role==null || cin==null || mdp==null)      
        {   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Missing Informations");
            alert.show();
        }else if (email.matches("(.*)@(.*)")==false)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid email address");
            alert.show();
        }else if (cin.length()!=8 ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid CIN");
            alert.show();

        }else if (cin.matches("[0-9]*")==false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid CIN");
            alert.show();
        }else{
            //Methode Ajouter    
            User user1 = new User (nom,prenom,cin,email,role,mdp);
            
            //controle d'existance
            /*if (rechercheUser(user1)!=null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("USER ALREADY EXISTS");
            alert.show(); 
            }
            
            else {*/
            UserCRUD uc = new UserCRUD();
            uc.ajouterUser(user1);
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Welcome");
            alert.setContentText("You have successfully created your account. Please check your e-mail box to get your ID ");
            alert.show();
            
            try {
                     Parent root = FXMLLoader.load(getClass().getResource("Authentification.fxml"));
                        Stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
                                      root.setOnMousePressed(pressEvent -> {
                        root.setOnMouseDragged(dragEvent -> {
                            Stage1.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                            Stage1.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                        });
                    });
                        Scene  scene = new Scene(root);
                        Stage1.setScene(scene);
                        Stage1.show();

                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
            
            }
        } 


    @FXML
    private void back_tologin(MouseEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
                        Stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
                                      root.setOnMousePressed(pressEvent -> {
                        root.setOnMouseDragged(dragEvent -> {
                            Stage1.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                            Stage1.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                        });
                    });
                        Scene  scene = new Scene(root);
                        Stage1.setScene(scene);
                        Stage1.show();

                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
        
    }
    
    }
    
    

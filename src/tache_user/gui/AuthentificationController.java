/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_user.gui;

import tache_user.entities.User;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tache_user.service.crypterPassword;
import utils.MyConnexion;

/**
 * FXML Controller class
 *
 * @author Ameni
 */

public class AuthentificationController implements Initializable {

    @FXML
    private TextField authmdp;
    @FXML
    private Button connecter;
    
    Stage Stage1;
    @FXML
    private TextField authemail;
    @FXML
    private ImageView back;
    
    Stage stage;
    
    /**
     * Initializes the controller class.
     */
    private static int idConnecte;
    private static String specialiteConnecte;
    public static int getIdConnecter(){
        return idConnecte;
    }
    public static String getSpecialiteConnecter(){
        return specialiteConnecte;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public String role_selection(String email){
       String role="";
        Connection cnx = MyConnexion.getInstance().getConnection();
                    PreparedStatement pst;
                    ResultSet rs = null;
                    
                    try {
                        /*User u=new User(rs.getString("role_user"));*/
                        pst = cnx.prepareStatement("SELECT role_user FROM user WHERE email_user=? ");
                        /*User u = new User (rs.getInt("id_user"), rs.getString("nom_user"),rs.getString("prenom_user"),
                        rs.getString("cin_user"),rs.getString("email_user"),rs.getString("role_user"),rs.getString("mdp_user"));*/
                        pst.setString(1, email);
                        
                        role=rs.getString("role_user"); 
                      
                        
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
         return role; 
    }
    
    
    @FXML
    private void authentification(ActionEvent event) throws SQLException, IOException {
        
        int id=0;
       Connection cnx = MyConnexion.getInstance().getConnection();
       PreparedStatement ps = null;
       ResultSet rs = null;
       try {            System.out.println(authemail.getText()+authmdp.getText());
                        ps = cnx.prepareStatement("SELECT id_user,role_user FROM user WHERE email_user='"+authemail.getText()+"' AND mdp_user ='"+authmdp.getText()+"'");
                        
                        rs = ps.executeQuery();
                        
                        if (rs.next()) {
                            id = rs.getInt("id_user");
                            specialiteConnecte = rs.getString("role_user");
                            idConnecte=id;
                            if (rs.getString("role_user").equals("Administrateur")){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionUser.fxml"));
                                    Parent root = loader.load();
                                 Scene scene = new Scene(root);  
                                  Stage primaryStage = new Stage();
                                 primaryStage.setScene(scene);  
                                 primaryStage.setTitle("Gestion User");  
                                 primaryStage.centerOnScreen();  
                                 primaryStage.setResizable(false);  
                                 primaryStage.setOpacity(1);  
                                 primaryStage.show();  
                                 Node node = (Node) event.getSource();
                                    Stage stage = (Stage) node.getScene().getWindow();
                                    stage.close();
                            }
                            else if (rs.getString("role_user").equals("medecin")){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeMedecin.fxml"));
                                    Parent root = loader.load();
                                 Scene scene = new Scene(root);  
                                  Stage primaryStage = new Stage();
                                 primaryStage.setScene(scene);  
                                 primaryStage.setTitle("Welcome Medecin");  
                                 primaryStage.centerOnScreen();  
                                 primaryStage.setResizable(false);  
                                 primaryStage.setOpacity(1);  
                                 primaryStage.show();  
                                 Node node = (Node) event.getSource();
                                    Stage stage = (Stage) node.getScene().getWindow();
                                    stage.close();
                            }
                            else if (rs.getString("role_user").equals("patient")){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomePatient.fxml"));
                                    Parent root = loader.load();
                                 Scene scene = new Scene(root);  
                                  Stage primaryStage = new Stage();
                                 primaryStage.setScene(scene);  
                                 primaryStage.setTitle("Welcome patient");  
                                 primaryStage.centerOnScreen();  
                                 primaryStage.setResizable(false);  
                                 primaryStage.setOpacity(1);  
                                 primaryStage.show();  
                                 Node node = (Node) event.getSource();
                                    Stage stage = (Stage) node.getScene().getWindow();
                                    stage.close();
                            }
                            else if (rs.getString("role_user").equals("pharmacien")){
                               
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tache_stock/gui/afficherProduit.fxml"));
                                    Parent root = loader.load();
                                 Scene scene = new Scene(root);  
                                  Stage primaryStage = new Stage();
                                 primaryStage.setScene(scene);  
                                 primaryStage.setTitle("Welcome pharmacien");  
                                 primaryStage.centerOnScreen();  
                                 primaryStage.setResizable(false);  
                                 primaryStage.setOpacity(1);  
                                 primaryStage.show();  
                                 Node node = (Node) event.getSource();
                                    Stage stage = (Stage) node.getScene().getWindow();
                                    stage.close();
                            }
                            else if (rs.getString("role_user").equals("agent_reclamation")){
                               
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tache_reclamation/views/ReponseRecc.fxml"));
                                    Parent root = loader.load();
                                 Scene scene = new Scene(root);  
                                  Stage primaryStage = new Stage();
                                 primaryStage.setScene(scene);  
                                 primaryStage.setTitle("Welcome agent reclamation");  
                                 primaryStage.centerOnScreen();  
                                 primaryStage.setResizable(false);  
                                 primaryStage.setOpacity(1);  
                                 primaryStage.show();  
                                 Node node = (Node) event.getSource();
                                    Stage stage = (Stage) node.getScene().getWindow();
                                    stage.close();
                            }
                            else if (rs.getString("role_user").equals("infirmier")){
                               
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tache_rdv_dispo/gui/adminRDV.fxml"));
                                    Parent root = loader.load();
                                 Scene scene = new Scene(root);  
                                  Stage primaryStage = new Stage();
                                 primaryStage.setScene(scene);  
                                 primaryStage.setTitle("Welcome agent reclamation");  
                                 primaryStage.centerOnScreen();  
                                 primaryStage.setResizable(false);  
                                 primaryStage.setOpacity(1);  
                                 primaryStage.show();  
                                 Node node = (Node) event.getSource();
                                    Stage stage = (Stage) node.getScene().getWindow();
                                    stage.close();
                            }
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Welcome");
                            alert.setHeaderText("WELCOME TO SafeEYE");
                            alert.setContentText("You're connected");
                            alert.show();
                            
                            
                        
                        }
                        else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Failed");
                            alert.setHeaderText("Attention !!");
                            alert.setContentText("Can not connect to SafeEYE");
                            alert.show();
                        }
 
     
    }catch(SQLException e){}
       
    }

    @FXML
    private void mdp_oublie(MouseEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("ForgotMdp.fxml"));
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_user.gui;

import static com.sun.javafx.scene.control.skin.TextFieldSkin.BULLET;
import tache_user.entities.User;
import tache_user.service.UserCRUD;
import utils.MyConnexion;
import static java.awt.SystemColor.text;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class GestionUserController implements Initializable {

    @FXML
    private TextField gnom;
    @FXML
    private TextField gprenom;
    @FXML
    private TextField gcin;
    @FXML
    private TextField gemail;
    @FXML
    private ComboBox<String> boxrole2;
    @FXML
    private TextField gid;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User,String> IdColumn;
    @FXML
    private TableColumn <User,String> NomColumn;
    @FXML
    private TableColumn <User,String> PrenomColumn;
    @FXML
    private TableColumn<User,String> CinColumn;
    @FXML
    private TableColumn<User,String> EmailColumn;
    @FXML
    private TableColumn<User,String> RoleColumn;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private Button btnAjouter;
    @FXML
    private TableColumn<User, String> MdpColumn;
    @FXML
    private TextField gmdp;
    @FXML
    private ImageView back;
    
    Stage Stage1;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //combobox
        ObservableList<String> listrole = FXCollections.observableArrayList("","patient","medecin","pharmacien","infirmier","agent_reclamation");
        boxrole2.setValue("");
        boxrole2.setItems(listrole);
        //Table
        table_affiche();
        table.getSelectionModel().selectedItemProperty().addListener((value, oldValue, newValue) -> {
        if (newValue != null) {
        this.gid.setText(Integer.toString(newValue.getId_user()));
        this.gnom.setText(newValue.getNom_user());
        this.gprenom.setText(newValue.getPrenom_user());
        this.gcin.setText(newValue.getCin_user());
        this.gemail.setText(newValue.getEmail_user());
        this.boxrole2.setItems(listrole);
        this.gmdp.setText(newValue.getEmail_user());
        }   
        });
                }
    
   
                
    public void table_affiche(){
        Connection cnx = MyConnexion.getInstance().getConnection();
        ObservableList<User> users = FXCollections.observableArrayList();
    
        try {
            String req = "SELECT * FROM `user`";
            Statement st;
            st =cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            {
                while(rs.next()){
                User u = new User();
                u.setId_user(rs.getInt("id_user"));
                u.setNom_user(rs.getString("nom_user"));
                u.setPrenom_user(rs.getString("prenom_user"));
                u.setCin_user(rs.getString("cin_user"));
                u.setEmail_user(rs.getString("email_user"));
                u.setRole_user(rs.getString("role_user"));
                u.setMdp_user(rs.getString("mdp_user"));
                users.add(u);}
            }   
            
            IdColumn.setCellValueFactory(new PropertyValueFactory<User,String>("id_user"));
            NomColumn.setCellValueFactory(new PropertyValueFactory<User,String>("nom_user"));
            PrenomColumn.setCellValueFactory(new PropertyValueFactory<User,String>("prenom_user"));
            CinColumn.setCellValueFactory(new PropertyValueFactory<User,String>("cin_user"));
            EmailColumn.setCellValueFactory(new PropertyValueFactory<User,String>("email_user"));
            RoleColumn.setCellValueFactory(new PropertyValueFactory<User,String>("role_user"));
            MdpColumn.setCellValueFactory(new PropertyValueFactory<User,String>("mdp_user"));
            table.setItems(users);
            
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
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
    public Integer rechercheID(User u){
                    Integer exist = 0;
                    Connection cnx = MyConnexion.getInstance().getConnection();
                    PreparedStatement pst;
                    ResultSet rs = null;
                    try {
                        pst = cnx.prepareStatement("SELECT COUNT(*) AS count FROM user WHERE id_user=? ");
                        pst.setInt(1, u.getId_user());
                      
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
    private void AjouterU(ActionEvent event) {
        String nom,prenom,email,role,mdp;
        String cin;
        
        nom = gnom.getText();
        prenom = gprenom.getText();
        email = gemail.getText();
        role = (String) boxrole2.getSelectionModel().getSelectedItem();
        cin = gcin.getText();
        mdp = gmdp.getText();
        
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
            table_affiche();
            }
         
    }


    @FXML
    private void ModifierU(ActionEvent event) {
        int ID=0;
        String nom,prenom,email,role,id;
        String cin,mdp;
        
        id = gid.getText();
        nom = gnom.getText();
        prenom = gprenom.getText();
        email = gemail.getText();
        role = (String) boxrole2.getSelectionModel().getSelectedItem();
        cin = gcin.getText();
        mdp = gmdp.getText();
        ID =Integer.parseInt(id);
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
        
            User user1 = new User (ID,nom,prenom,cin,email,role,mdp);
            if (rechercheUser(user1)==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed");
                alert.setHeaderText("Attention !!");
                alert.setContentText("USER DOES NOT EXISTS");
                alert.show(); 
            }
            
            else {
                UserCRUD uc = new UserCRUD();
                uc.modifierUser(user1);
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Update Done !!");
                alert.setContentText("You have successfully updated an account");
                alert.show();
                table_affiche();
            }
    
        } 
    }
    

    @FXML
    private void SupprimerU(ActionEvent event) {
        int ID=0;
        String nom,prenom,email,role,id;
        String cin,mdp;
        
        
        id = gid.getText();
        nom = gnom.getText();
        prenom = gprenom.getText();
        email = gemail.getText();
        role = (String) boxrole2.getSelectionModel().getSelectedItem();
        cin = gcin.getText();
        mdp = gmdp.getText();
        ID = Integer.parseInt(id);
        
        User user2 = new User (ID,nom,prenom,cin,email,role,mdp);
            if (rechercheUser(user2)==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed");
                alert.setHeaderText("Attention !!");
                alert.setContentText("USER DOES NOT EXISTS");
                alert.show(); 
            }
            else {
                UserCRUD uc = new UserCRUD();
                uc.supprimerUser(user2);
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Removal Done !!");
                alert.setContentText("You have successfully deleted an account ");
                alert.show();
                table_affiche();
            }
        }

    @FXML
    private void getSelected(MouseEvent event) {
        User clicked = table.getSelectionModel().getSelectedItem();
        gid.setText(String.valueOf(clicked.getId_user()));
    }

    @FXML
    private void back_tologin(MouseEvent event) {
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

    @FXML
    private void GoToRendezVous(ActionEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("/tache_rdv_dispo/gui/adminRDV.fxml"));
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
    private void GoToEvenement(ActionEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("/tache_evenement/Admininterfaces/evenementAdmin.fxml"));
                        Stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
                                      
                        Scene  scene = new Scene(root);
                        Stage1.setScene(scene);
                        Stage1.show();

                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
    
    }
    
    }
    
    
    



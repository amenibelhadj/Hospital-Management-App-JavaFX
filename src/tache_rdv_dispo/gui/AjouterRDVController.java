/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_rdv_dispo.gui;

import tache_rdv_dispo.API.SendSMS;
import static tache_rdv_dispo.API.SendSMS.SendSMS;
import tache_rdv_dispo.Entities.RDV;
import tache_rdv_dispo.Service.RdvService;
import tache_rdv_dispo.Entities.dispo;
import tache_rdv_dispo.Service.dispoService;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import utils.MyConnexion;


/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterRDVController implements Initializable {

    @FXML
    private Button valider;
    @FXML
    private RadioButton rboui;
    @FXML
    private RadioButton rbnon;
  
    
    ObservableList<RDV>rdvList;

    @FXML
    private Button afficher;
  
    @FXML
    private Button supprimer;
    
    Connection mc;
    PreparedStatement ste;
    @FXML
    private TextArea idtxt;
    @FXML
    private Button modifier;
    @FXML
    private TableView<RDV> tabb;
    @FXML
    private TableColumn<RDV, Integer> idrdv;
    @FXML
    private TableColumn<RDV, Integer> iduser;
    @FXML
    private TableColumn<RDV, String> prv;
    @FXML
    private TableColumn<RDV, String> nom;
    @FXML
    private TableColumn<RDV, String> datee;
    @FXML
    private TableColumn<RDV, String> heuree;
    @FXML
    private TableView<dispo> dispo_tab;
    @FXML
    private TableColumn<dispo, Integer> clid1;
    @FXML
    private TableColumn<dispo, String> clnom1;
    @FXML
    private TableColumn<dispo, String> cldate1;
    @FXML
    private TableColumn<dispo, String> clheure1;
    @FXML
    private TextField idusertxt;
    @FXML
    private TextField nommed_txt;
    @FXML
    private TextField daterdv_txt;
    @FXML
    private TextField heurerdv_txt;
    
     ObservableList<RDV> ListRDV = FXCollections.observableArrayList();
    @FXML
    private Button aff;
    @FXML
    private TextField iddispo;
    @FXML
    private ImageView back;
    
    //ObservableList<RDV> liste = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
       
       
    }    

    @FXML
    
    
    private void ajouterR(ActionEvent event) {
       String prv = null, nom, datee,heure, user;
        int id1;
       
       
       if (rboui.isSelected()) {
            prv = rboui.getText();
        }
        if (rbnon.isSelected()) {
            prv = rbnon.getText();
        }
        
        nom= nommed_txt.getText();
        datee= daterdv_txt.getText();  
        heure =heurerdv_txt.getText();
        user=idusertxt.getText();
        id1=tache_user.gui.AuthentificationController.getIdConnecter(); 
        
         if (  prv.isEmpty() || nom.isEmpty() || datee.isEmpty()|| heure.isEmpty()){
      
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Veuillez remplir tous les champs!"); 
             alert.showAndWait();          
         }
         else{

        RDV r1;
        r1 = new RDV(id1,prv,nom,datee,heure);
        RdvService r= new RdvService();
        r.ajouterRdv(r1);   
        
        String nomm="", date="", heuree="";
        int i;
        i=Integer.valueOf(iddispo.getText());

        dispo r2;
        r2 = new dispo(i,nomm,datee,heure);
        dispoService d= new dispoService();
        d.supp(i);
        
        
        JOptionPane.showMessageDialog(null, "Rdv ajouté" );
        SendSMS sm = new SendSMS();
       sm.SendSMS(r1);
        
        afficherdispo();
      
        nommed_txt.setText("");
        daterdv_txt.setText("");
        heurerdv_txt.setText(null); 
        rboui.setSelected(false);
        rbnon.setSelected(false);
        
         
         }
        
           
}

    @FXML
    private void afficher() {
        int i;
        i=tache_user.gui.AuthentificationController.getIdConnecter(); 
        
       RdvService sp= new RdvService();
       List RDV = (List) sp.afficherRdvs();
       ObservableList et = FXCollections.observableArrayList(RDV);
  
        idrdv.setCellValueFactory(new PropertyValueFactory<RDV, Integer>("id_rdv"));
        iduser.setCellValueFactory(new PropertyValueFactory<RDV, Integer>("id_user"));
        prv.setCellValueFactory(new PropertyValueFactory<RDV, String>("prv"));
        nom.setCellValueFactory(new PropertyValueFactory<RDV, String>("nom_med"));
        datee.setCellValueFactory(new PropertyValueFactory<RDV, String>("date"));
        heuree.setCellValueFactory(new PropertyValueFactory<RDV, String>("heure"));
        
        tabb.setItems(et);
        
          
        ListRDV.clear();
        
        RdvService cc= new RdvService();
        List<RDV> list = cc.afficherRdvs();
        
        ListRDV.addAll(list.stream().filter(c -> c.getId_user()==i).collect(Collectors.toList()));
        tabb.setItems(ListRDV);
        
    }

  

    @FXML
    private void supprimerRDV(MouseEvent event) throws SQLException {
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("vous êtes sûr de supprimer");
            alert.setContentText("Confirmation..!");

        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
            
         String prv="" , nom="", datee="", heure="";
                 int i;
        
         i=Integer.valueOf(idtxt.getText());
        
         
        RDV r1;
        r1 = new RDV(i,prv,nom,datee,heure);
        RdvService r= new RdvService();
        r.supp(i);
            JOptionPane.showMessageDialog(null, "Rdv supprimé" );
            afficher();
           
             }

    }

    @FXML
    private void modifierRDV(MouseEvent event) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("vous êtes sûr de modifier");
            alert.setContentText("Confirmation..!");
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){ 
        try{
        
             
             String id = idtxt.getText();
             int value1 = Integer.parseInt(id);
             int value2 = tache_user.gui.AuthentificationController.getIdConnecter();
             String value3="";
             if (rboui.isSelected()) {value3+= rboui.getText();}
             if (rbnon.isSelected()) { value3+= rbnon.getText();}
             String value4 = nommed_txt.getText();
             String value5 = daterdv_txt.getText();
             String value6 = heurerdv_txt.getText();
            
         RdvService rc = new RdvService( );
             RDV r= new RDV(value1,value2,value3,value4,value5,value6);
             rc.modifier(r);   
       
                /* mc=DataSource.getinstance().getCnx();
             String sql = "update rdv set id_rdv = '"+value1+"', id_user = '"+value2+"',prv = '"+value3+"', nom_med = '"+value4+"', date = '"+value5+"', heure = '"+value6+"'  where id_rdv ='"+value1+"' ";
             ste=mc.prepareStatement(sql);
             ste.execute();  */
            
             
             JOptionPane.showMessageDialog(null, "rdv modifié");
             
              nommed_txt.setText("");
        daterdv_txt.setText("");
        heurerdv_txt.setText(null); 
        rboui.setSelected(false);
        rbnon.setSelected(false);
             afficher();
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);

        } 
               }
    }
    


 

    @FXML
    private void select(MouseEvent event) {
         RDV clicked = tabb.getSelectionModel().getSelectedItem();
        idtxt.setText(String.valueOf(clicked.getId_rdv()));
    }

    @FXML
    private void selectrdv(MouseEvent event) {
            tache_rdv_dispo.Entities.dispo clicked = dispo_tab.getSelectionModel().getSelectedItem();
          iddispo.setText(String.valueOf(clicked.getId_dispo()));
         nommed_txt.setText(String.valueOf(clicked.getNom_medd()));
        daterdv_txt.setText(String.valueOf(clicked.getDate()));
        heurerdv_txt.setText(String.valueOf(clicked.getHeure()));
        
    }

    @FXML
    private void afficherdispo() {
                   dispoService sp= new dispoService();
        List dispo = (List) sp.afficherD();
        
        
        ObservableList t = FXCollections.observableArrayList(dispo);
        
        clid1.setCellValueFactory(new PropertyValueFactory<dispo, Integer>("id_dispo"));
        
        clnom1.setCellValueFactory(new PropertyValueFactory<dispo, String>("nom_medd"));
        cldate1.setCellValueFactory(new PropertyValueFactory<dispo, String>("date"));
        clheure1.setCellValueFactory(new PropertyValueFactory<dispo, String>("heure"));
        
        dispo_tab.setItems(t);
    }

    Stage stage;
    @FXML
        private void back_tologin(MouseEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("/tache_user/gui/WelcomePatient.fxml")); 
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

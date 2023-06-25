/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_reclamation.views;


import java.io.IOException;
import tache_reclamation.Entities.Reclamation;
import tache_reclamation.Entities.ReponseRec;
import tache_reclamation.ReclamationService.ReclamationService;
import tache_reclamation.ReclamationService.ReponseRecService;
import static java.lang.Boolean.parseBoolean;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import utils.MyConnexion;

/**
 * FXML Controller class
 *
 * @author Koussay
 */
public class ReponseReccController implements Initializable {

    private TextArea nomRep;
    @FXML
    private Button ajouterRep;
    @FXML
    private TableView<ReponseRec> tableRep;
    
    @FXML
    private Button supprimerRep;
    @FXML
    private Button modifierRep;
    @FXML
    private TextArea idtxt;
    
    
    ObservableList<ReponseRec>recList;
    
     Connection mc;
    PreparedStatement ste;
    @FXML
    private TextArea nomAg;
    @FXML
    private TableColumn<ReponseRec, String> NomAg;
    @FXML
    private TableColumn<ReponseRec, String> prenomAg;
    @FXML
    private TableColumn<ReponseRec, Boolean> traitement;
    @FXML
    private TextArea Trait;
    @FXML
    private TextArea PrenomAg;
    @FXML
    private Button Retourbtn;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       afficherReponseRec();
       }    

    
    
     void afficherReponseRec() {

  mc=utils.MyConnexion.getInstance().getConnection();
        recList = FXCollections.observableArrayList();
       
        
        String sql="select * from reponse";
        try {
            ste=mc.prepareStatement(sql); 
            ResultSet rs=ste.executeQuery(); 
            while(rs.next()){
                ReponseRec r = new ReponseRec();
                r.setId(rs.getInt("id"));
                r.setNomAg(rs.getString("nomAg"));
                r.setPrenomAg(rs.getString("prenomAg"));
                r.setTraitement(rs.getBoolean("traitement"));
                recList.add(r); 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       // idtab.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()));
        NomAg.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNomAg()));
        prenomAg.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenomAg()));
        traitement.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().isTraitement()));
        
       
        tableRep.setItems(recList);
        
    }
    
    
    
 
    
    
    @FXML
    private void addRec(MouseEvent event) throws SQLException {
        
        String i = idtxt.getText();
        String nom = nomAg.getText();
        String prenom = PrenomAg.getText();
        String trait = Trait.getText();
        
      
         if (nom.isEmpty() || prenom.isEmpty()|| trait.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Donnees non disponible!!");
             alert.showAndWait();          
         }
         else{     
             ReponseRec r=new ReponseRec(Integer.parseInt(i),nom,prenom,parseBoolean(trait));
             ReponseRecService rc = new ReponseRecService();
             
             ResultSet rs=ste.executeQuery();        
             rc.ajouterReponseRec(r);
            
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             
             alert.setContentText("Reponse Ajoutée avec succes!");
                alert.showAndWait();
                
           
 
           nomAg.setText(null);
          PrenomAg.setText(null);
          Trait.setText(null);
          idtxt.setText(null);
         }
         refresh();
    }
    


public void refresh(){
        
         recList.clear();
         
          mc=utils.MyConnexion.getInstance().getConnection();

        recList = FXCollections.observableArrayList();
        
        String sql="select * from reponse";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next())
            {
                
                ReponseRec e = new ReponseRec();
                
                
                e.setNomAg(rs.getString("nomAg"));
                e.setPrenomAg(rs.getString("prenomAg"));
                e.setTraitement(rs.getBoolean("traitement"));
              
                recList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         tableRep.setItems(recList);   
    }

    @FXML
    private void getSelected(MouseEvent event) {
         ReponseRec clicked = tableRep.getSelectionModel().getSelectedItem();
         nomAg.setText(String.valueOf(clicked.getNomAg()));
        PrenomAg.setText(String.valueOf(clicked.getPrenomAg()));
        Trait.setText(String.valueOf(clicked.isTraitement())); 
        //idtxt.setText(String.valueOf(clicked.getId()));
    }

    @FXML
    private void deleteRep(MouseEvent event) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
            
            String value1 = idtxt.getText();
            
             String nom = nomAg.getText();
        String prenom = PrenomAg.getText();
        String trait = Trait.getText();
        

        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
            
             ReponseRec r= new ReponseRec(Integer.parseInt(value1),nom,prenom,parseBoolean(trait));
             ReponseRecService rc = new ReponseRecService();
             rc.supprimerReponseRec(r);
            JOptionPane.showMessageDialog(null, "Reponse supprimée" );
        
            refresh();
             }
        else{

          nomAg.setText(null);
          prenomAg.setText(null);
          Trait.setText(null);
          }
    }

    @FXML
    private void updateRep(MouseEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
       
            String value1 = idtxt.getText();
             String value2 = nomAg.getText();
             String value3 = PrenomAg.getText();
             String value4 = Trait.getText();
            
            
             Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){ 
        
            try{
        
             ReponseRecService rc = new ReponseRecService();
             ReponseRec r= new ReponseRec(Integer.parseInt(value1),value2,value3,parseBoolean(value4));
             rc.modifierReponseRec(r);
            JOptionPane.showMessageDialog(null, "reponse modifié");
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);

        }
        refresh();
               }
        else{

              nomAg.setText(null);
              PrenomAg.setText(null);
              Trait.setText(null);
              
              
            

        }
        refresh();
        
        
        
    }

    Stage stage;
    @FXML
    private void retour(MouseEvent event) {
        
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

 /*   @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void deleteRep(MouseEvent event) {
    }

    @FXML
    private void updateRep(MouseEvent event) {
    }
*/
    
    


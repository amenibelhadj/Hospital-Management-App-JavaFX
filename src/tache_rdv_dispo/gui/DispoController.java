/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_rdv_dispo.gui;

import java.io.IOException;
import tache_rdv_dispo.Entities.RDV;
import tache_rdv_dispo.Entities.dispo;
import tache_rdv_dispo.Service.RdvService;
import tache_rdv_dispo.Service.dispoService;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
public class DispoController implements Initializable {

    @FXML
    private TextArea idtxt;
    @FXML
    private DatePicker dateid;
    @FXML
    private ComboBox<String> comboheure;
    @FXML
    private Button valider;
    @FXML
    private Button afficher;
    @FXML
    private Button modifier;
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private TableView<dispo> tab;
    @FXML
    private TableColumn<dispo, Integer> clid;
    @FXML
    private TableColumn<dispo, String> clnom;
    @FXML
    private TableColumn<dispo,String > cldate;
    @FXML
    private TableColumn<dispo, String> clheure;
    
      ObservableList<String> list = FXCollections.observableArrayList("Koussay","Mohamed","Ameni","Maryem","Khairy");
      ObservableList<String> liste = FXCollections.observableArrayList("09:00","10:00","11:00","13:00","14:00","15:00");
      
      Connection mc;
    PreparedStatement ste;
    @FXML
    private ImageView back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combobox.setItems(list);
        comboheure.setItems(liste);
        
      
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        String nom="", datee="", heure="";
        nom+= combobox.getValue();
        datee+= dateid.getValue();  
        heure += comboheure.getValue();
  if ( nom==""|| datee.isEmpty()|| heure.isEmpty()){
        
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Veuillez remplir tous les champs!"); 
             alert.showAndWait();          
         }
  else{
        dispo r1;
        r1 = new dispo(nom,datee,heure);
        dispoService r= new dispoService();
        r.ajouterD(r1);
        
                JOptionPane.showMessageDialog(null, "ajouté" );
       
        combobox.setValue("");
        comboheure.setValue("");
        dateid.setValue(null);
        afficher();
    }}

    @FXML
    private void afficher() {
        
                 dispoService sp= new dispoService();
        List dispo = (List) sp.afficherD();
        
        
        ObservableList et = FXCollections.observableArrayList(dispo);
        
        clid.setCellValueFactory(new PropertyValueFactory<dispo, Integer>("id_dispo"));
        
        clnom.setCellValueFactory(new PropertyValueFactory<dispo, String>("nom_medd"));
        cldate.setCellValueFactory(new PropertyValueFactory<dispo, String>("date"));
        clheure.setCellValueFactory(new PropertyValueFactory<dispo, String>("heure"));
        
        tab.setItems(et);
    }

    @FXML
    private void getselected(MouseEvent event) {
        dispo clicked = tab.getSelectionModel().getSelectedItem(); 
    
        idtxt.setText(String.valueOf(clicked.getId_dispo()));
    }

    @FXML
    private void modifier(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("vous êtes sûr de modifier");
            alert.setContentText("Confirmation..!");
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){ 
        try{
  
             String value1 = idtxt.getText();                   
             String value3 = combobox.getValue();
             String value4=dateid.getValue().toString();
             String value5 = comboheure.getValue();           
             int i=Integer.parseInt(value1);

             dispoService rc = new dispoService();
             dispo r= new dispo(i,value3,value4,value5);
             rc.modifierD(r); 
             
            JOptionPane.showMessageDialog(null, " modifié");
                   
       
        combobox.setValue("select name");
        comboheure.setValue("");
        dateid.setValue(null);
        afficher();
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);
        } 
     
               }

    }

    @FXML
    private void supprimer(MouseEvent event) throws SQLException {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("vous êtes sûr de supprimer");
            alert.setContentText("Confirmation..!");

        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
            
        String nom="", datee="", heure="";
        int i;
        
        i=Integer.valueOf(idtxt.getText());

        dispo r1;
        r1 = new dispo(i,nom,datee,heure);
        dispoService r= new dispoService();
        r.supp(i);
            
            JOptionPane.showMessageDialog(null, " supprimer" );
            afficher();
             }
    }
    Stage stage;
    @FXML
        private void back_tologin(MouseEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("/tache_rdv_dispo/gui/adminRDV.fxml")); 
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

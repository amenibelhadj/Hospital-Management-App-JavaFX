/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_reclamation.views;

import tache_reclamation.API.sendSMS;
import tache_reclamation.Entities.Reclamation;
import tache_reclamation.Iservice.IReclamationService;
import tache_reclamation.ReclamationService.ReclamationService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import utils.MyConnexion;

/**
 * FXML Controller class
 *
 * @author Koussay
 */
public class RecInterfaceController implements Initializable {

    @FXML
    private TextArea prenomRec;
    @FXML
    private TextArea nomRec;
    @FXML
    private TableColumn<Reclamation, String> nom;
    @FXML
    private TableColumn<Reclamation, String> premon;
    @FXML
    private TableColumn<Reclamation, String> email;
    @FXML
    private TableColumn<Reclamation, String> desc;
    @FXML
    private TextArea emailRec;
    @FXML
    private TextArea descRec;
    @FXML
    private Button ajouterRec;
    @FXML
    private Button supprimerRec;
    @FXML
    private Button modifierRec;
    @FXML
    private TableView<Reclamation> tableRec;
    
      ObservableList<Reclamation>recList;
    
     Connection mc;
    PreparedStatement ste;
    @FXML
    private TextArea idtxt;
    @FXML
    private TableColumn<?, ?> id;
   
    @FXML
    private Button pdfRec;
    @FXML
    private TextArea recherche;
    @FXML
    private TableColumn<?, ?> userId;
    @FXML
    private Button Retourbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         afficherReclamation();
    }  
    void afficherReclamation(){
            mc=utils.MyConnexion.getInstance().getConnection();
        recList = FXCollections.observableArrayList();
       
        
        String sql="select * from reclamation";
        try {
            ste=mc.prepareStatement(sql); //preparer requeete
            ResultSet rs=ste.executeQuery();//exec lel req mte3ek 
            while(rs.next()){
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));//tekhou mel base w tseti fel instance mb3ed el instance bsh thotha fi lista w tajoputiha or taffich or update
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setEmail(rs.getString("email"));
                r.setDescription(rs.getString("description"));
                recList.add(r); 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        nom.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));
        premon.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrenom()));
        email.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        desc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));
       
        tableRec.setItems(recList);
        search();
        
    
        
    }
//tjiblek les champs eli clikcet alihom fel tab w thothom fel labels
    @FXML
    private void getSelected(MouseEvent event) {
          Reclamation clicked = tableRec.getSelectionModel().getSelectedItem();
         nomRec.setText(String.valueOf(clicked.getNom()));
        prenomRec.setText(String.valueOf(clicked.getPrenom()));
        emailRec.setText(String.valueOf(clicked.getEmail()));
        descRec.setText(String.valueOf(clicked.getDescription()));
        idtxt.setText(String.valueOf(clicked.getId()));
    }

    @FXML
    private void addRec(MouseEvent event) throws SQLException {
        
        String nom = nomRec.getText();
        String prenom = prenomRec.getText(); // bch te5ou text mawjoud f label w thotou f variable
        String email = emailRec.getText();
        String description = descRec.getText();
        
     
         if (nom.isEmpty() || prenom.isEmpty()|| email.isEmpty()|| description.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Donnees non disponible!!"); // controle de saisie
             alert.showAndWait();          
         }
         else{     
             Reclamation r=new Reclamation(1,description,nom,prenom,email);
             ReclamationService rc = new ReclamationService();
             
             ResultSet rs=ste.executeQuery();        
             rc.ajouterReclamation(r);
             sendSMS sm = new sendSMS();
             sendSMS.sendSMS(r);
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             
             alert.setContentText("Reclamation Ajoutée avec succes!");
                alert.showAndWait();
                
           
 
           nomRec.setText(null);
          prenomRec.setText(null);
          emailRec.setText(null);
          descRec.setText(null);
         }
         refresh();
    }

    @FXML
    private void deleteRec(MouseEvent event) throws SQLException {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
            
            String value1 = idtxt.getText();
            
             String nom = nomRec.getText();
        String prenom = prenomRec.getText(); // bch te5ou text mawjoud f label w thotou f variable
        String email = emailRec.getText();
        String description = descRec.getText();

        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
            
             Reclamation r= new Reclamation(Integer.parseInt(value1),description,nom,prenom,email);
             ReclamationService rc = new ReclamationService();
             rc.supprimerReclamation(r);
            JOptionPane.showMessageDialog(null, "Reclamation supprimer" );
        
            refresh();
             }
        else{

          nomRec.setText(null);
          prenomRec.setText(null);
          emailRec.setText(null);
          descRec.setText(null);

        }
    }

    @FXML
    private void updateRec(MouseEvent event)  {
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
       
            String value1 = idtxt.getText();
             String value2 = nomRec.getText();
             String value3 = prenomRec.getText();
             String value4 = emailRec.getText();
             String value5 = descRec.getText();
            
             Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){ 
        
            try{
        
             ReclamationService rc = new ReclamationService( );
             Reclamation r= new Reclamation(Integer.parseInt(value1),value5,value2,value3,value4);
             rc.modifierReclamation(r);
            JOptionPane.showMessageDialog(null, "reclamation modifié");
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);

        }
        refresh();
               }
        else{

              nomRec.setText(null);
              prenomRec.setText(null);
              emailRec.setText(null);
              descRec.setText(null);
              
            ;

        }
        refresh();
    }
    
    public void refresh(){
        
         recList.clear();
       
          
          mc=utils.MyConnexion.getInstance().getConnection();

        recList = FXCollections.observableArrayList();
        
        String sql="select * from reclamation";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Reclamation e = new Reclamation();
                e.setNom(rs.getString("nom"));
                e.setPrenom(rs.getString("prenom"));
                e.setEmail(rs.getString("email"));
                e.setDescription(rs.getString("description"));
                recList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         tableRec.setItems(recList);   
    }

    @FXML
    private void generationpdf(MouseEvent event) throws SQLException, FileNotFoundException, DocumentException, IOException {
        String sql = "SELECT * from reclamation";
    ste=mc.prepareStatement(sql);
    ResultSet rs=ste.executeQuery();

    Document doc = new Document();
    PdfWriter.getInstance(doc, new FileOutputStream("./ListeDesReclamations.pdf"));

    doc.open();
   
    doc.add(new Paragraph("   "));
    doc.add(new Paragraph(" *************************************** Liste Des Reclamations *************************************** "));
    doc.add(new Paragraph("   "));

    PdfPTable table = new PdfPTable(4);
    table.setWidthPercentage(50);
    PdfPCell cell;

    cell = new PdfPCell(new Phrase("Nom", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
   
    cell = new PdfPCell(new Phrase("Prenom", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
    
    cell = new PdfPCell(new Phrase("email", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
    
    
    cell = new PdfPCell(new Phrase("description", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);

    while (rs.next()) {

        Reclamation e = new Reclamation();
      
        e.setNom(rs.getString("nom"));
        e.setPrenom(rs.getString("prenom"));
       e.setEmail(rs.getString("email"));
       e.setDescription(rs.getString("description"));
       
      
        cell = new PdfPCell(new Phrase(e.getNom(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(e.getPrenom(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(e.getEmail(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        
        cell = new PdfPCell(new Phrase(e.getDescription(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    doc.add(table);
    doc.close();
    Desktop.getDesktop().open(new File("./ListeDesReclamations.pdf"));

    }
    private void search() {      
        
        FilteredList<Reclamation>filteredData = new FilteredList<>(recList, b->true);
        recherche.textProperty().addListener((observable, oldValue, newValue)->{
           
            filteredData.setPredicate(reclamation->{
               
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
               
                String lowerCaseFilter = newValue.toLowerCase();
                 
                  if(String.valueOf(reclamation.getId()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                  else if(String.valueOf(reclamation.getNom()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }else{
                return false;
                }
            });          
        });
        SortedList<Reclamation>sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableRec.comparatorProperty());
        tableRec.setItems(sortedData);
    }
Stage stage;
    @FXML
    private void retour(MouseEvent event) {
        
         try {
         if (tache_user.gui.AuthentificationController.getSpecialiteConnecter().equals("medecin")){
                     Parent root = FXMLLoader.load(getClass().getResource("/tache_user/gui/WelcomeMedecin.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                                      root.setOnMousePressed(pressEvent -> {
                        root.setOnMouseDragged(dragEvent -> {
                            stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                            stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                        });
                    });
                        Scene  scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();}
         else if (tache_user.gui.AuthentificationController.getSpecialiteConnecter().equals("patient")){
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
         }
                        

                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
    }
}



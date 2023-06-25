/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_rdv_dispo.gui;

import tache_rdv_dispo.Entities.RDV;
import tache_rdv_dispo.Service.RdvService;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;
import com.itextpdf.text.DocumentException;


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
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class AdminRDVController implements Initializable {

    @FXML
    private TableView<RDV> tab_rdv;
    @FXML
    private TableColumn<RDV, Integer> id;
    @FXML
    private TableColumn<RDV, Integer> idpatient;
    @FXML
    private TableColumn<RDV, String> idprv;
    @FXML
    private TableColumn<RDV, String> idnommed;
    @FXML
    private TableColumn<RDV, String> iddate;
    @FXML
    private TableColumn<RDV, String> idheure;
    @FXML
    private Button af;
    @FXML
    private Button a;
    @FXML
    private TextField id_patient;
    @FXML
    private TextField idtxt;
    
      ObservableList<RDV> ListRDV = FXCollections.observableArrayList();
    @FXML
    private Button supp;
    @FXML
    private Button pdf;

    Connection mc  ;
    @FXML
    private ImageView back;
    @FXML
    private Button af1;
    @FXML
    private Button af11;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void getselect(MouseEvent event) {
          RDV clicked = tab_rdv.getSelectionModel().getSelectedItem();
        idtxt.setText(String.valueOf(clicked.getId_rdv()));
    }

    @FXML
    private void afficher_rdv() {
  
        
       RdvService sp= new RdvService();
       List RDV = (List) sp.afficherRdvs();
       ObservableList et = FXCollections.observableArrayList(RDV);
  
        id.setCellValueFactory(new PropertyValueFactory<RDV, Integer>("id_rdv"));
        idpatient.setCellValueFactory(new PropertyValueFactory<RDV, Integer>("id_user"));
        idprv.setCellValueFactory(new PropertyValueFactory<RDV, String>("prv"));
        idnommed.setCellValueFactory(new PropertyValueFactory<RDV, String>("nom_med"));
        iddate.setCellValueFactory(new PropertyValueFactory<RDV, String>("date"));
        idheure.setCellValueFactory(new PropertyValueFactory<RDV, String>("heure"));
        
        tab_rdv.setItems(et);
    }

    @FXML
    private void recherche_rdv(ActionEvent event) {
        String user;
        int i;
         user=id_patient.getText();
        i=Integer.parseInt(user); 
        
         ListRDV.clear();
        
        RdvService cc= new RdvService();
        List<RDV> list = cc.afficherRdvs();
        
        ListRDV.addAll(list.stream().filter(c -> c.getId_user()==i).collect(Collectors.toList()));
        tab_rdv.setItems(ListRDV);
         
        
       
     
       
    }

    @FXML
    private void supp(ActionEvent event) {
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
            afficher_rdv() ;
    }
    }

    @FXML
    private void generationpdf(MouseEvent event) throws SQLException, IOException, DocumentException {
        String sql = "SELECT * from rdv";
        
      mc=utils.MyConnexion.getInstance().getConnection();
        PreparedStatement ste;   
    ste=mc.prepareStatement(sql);
    ResultSet rs=ste.executeQuery();
    Document doc;
        doc = new Document();
    PdfWriter.getInstance(doc,new FileOutputStream("./ListeRDV.pdf"));

    doc.open();
   
    doc.add(new Paragraph("   "));
    doc.add(new Paragraph(" ************************************* Liste Des Rendez-Vous ************************************* "));
    doc.add(new Paragraph("   "));

    PdfPTable table = new PdfPTable(5);
    table.setWidthPercentage(80);
    PdfPCell cell;

  
   
    cell = new PdfPCell(new Phrase("id_user", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
    
    cell = new PdfPCell(new Phrase("prv", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
    
    
    cell = new PdfPCell(new Phrase("nom_med", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell);
    
    cell = new PdfPCell(new Phrase("date", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell);
    
    cell = new PdfPCell(new Phrase("heure", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);

    while (rs.next()) {

        RDV e = new RDV();
        
       
        e.setId_user(rs.getInt("id_user"));
         e.setPrv(rs.getString("prv"));
        e.setNom_med(rs.getString("nom_med"));
       e.setDate(rs.getString("date"));
       e.setHeure(rs.getString("heure"));
       
      
     
        
        cell = new PdfPCell(new Phrase(String.valueOf(e.getId_user()), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(e.getPrv(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        
        cell = new PdfPCell(new Phrase(e.getNom_med(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(e.getDate(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(e.getHeure(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    doc.add(table);
    doc.close();
    Desktop.getDesktop().open(new File("./ListeRDV.pdf"));
    }

    Stage stage;
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

    @FXML
    private void GoToDisponibilite(ActionEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("/tache_rdv_dispo/gui/dispo.fxml")); 
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

    @FXML
    private void GoToEvenement(ActionEvent event) {
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("/tache_evenement/UserInterfaces/EvenementUser.fxml")); 
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

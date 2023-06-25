/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_evenement.UserInterfaces;

import tache_evenement.Admininterfaces.AjoutEvenementAdminController;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import tache_evenement.model.Evenement;
import tache_evenement.model.ReservationEvenement;
import tache_evenement.services.ServiceAjoutEvenementAdmin;
import tache_evenement.services.ServiceParticiperEventUser;
import utils.MyConnexion;

/**
 * FXML Controller class
 *
 * @author hama
 */
public class ParticiperEventUserController implements Initializable {

    @FXML
    private ComboBox<String> modePaiment;
    @FXML
    private Spinner<Integer> placeEvent;
    @FXML
    private Button ReserverEventButton;
    @FXML
    private Label dateEvent;
    @FXML
    private Label tarifEvent;
    @FXML
    private Label totalPayer;
    ServiceParticiperEventUser cs = new ServiceParticiperEventUser();
    /**
     * Initializes the controller class.
     */
    
public void setDate(LocalDate date) {
        this.dateEvent.setText(date.toString());
    }
public void setTarif(String tarif) {
         this.tarifEvent.setText(tarif);
    }
public void setTotal(String total) {
         this.totalPayer.setText(total);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list;
        list = FXCollections.observableArrayList("En ligne","Sur place");
        modePaiment.setItems(list);
        
        final int initialValue = 1;

        // Value factory.
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10, initialValue);

        placeEvent.setValueFactory(valueFactory);
        
        modePaiment.setValue("En ligne");
    }    

    @FXML
    private void ReserverEventButton(ActionEvent event) throws DocumentException, SQLException {
        EvenementUserController pdc = new EvenementUserController();
        int total = Integer.parseInt(totalPayer.getText());
        String mode = modePaiment.getValue();
        int place = placeEvent.getValue();
        
        int idEvent = pdc.getIdMemoriser();
        ReservationEvenement r = new ReservationEvenement(idEvent, place,total,mode,tache_user.gui.AuthentificationController.getIdConnecter());
        toPDF(r);
        cs.reserver(r);
        
    }
    
    private void toPDF(ReservationEvenement r) throws DocumentException, SQLException {
        String nomUser=null;
        String prenomUser=null;
        
        Connection con;
        Statement ste;
        MyConnexion db=new MyConnexion();
        con = db.getConnection();
            String sql=null;
            sql = "select nom_user,prenom_user from user where id_user="+tache_user.gui.AuthentificationController.getIdConnecter();
            ste = con.prepareStatement(sql);
            System.out.println(ste);
            ResultSet rs = ste.executeQuery(sql);

            while(rs.next()){
                nomUser=rs.getString("nom_user");
                prenomUser=rs.getString("prenom_user");
            }
            rs.close();
        
        String titre=null;
        String tarif=null;
        
        Connection con2;
        Statement ste2;
        con2 = db.getConnection();
            String sql2=null;
            sql2 = "select tarif,titre from evenement where id_event="+EvenementUserController.idMemoriser;
            ste2 = con.prepareStatement(sql2);
            System.out.println(ste2);
            ResultSet rs2 = ste2.executeQuery(sql2);

            while(rs2.next()){
                titre=rs2.getString("titre");
                tarif=rs2.getString("tarif");
            }
            rs2.close();
            
            
            
            try {
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream("Reservation"+r.getId_event()+".pdf"));
                
                doc.open();
                
                String img = "C:\\Users\\hama\\Documents\\NetBeansProjects\\Clinique\\src\\imgEvent\\Eye Center Logo Template Design.jpg";
                com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(img);
                doc.add(image);
                //Test PDF
                PdfPTable table = new PdfPTable(2);

                table.setWidthPercentage(100);
                table.setSpacingBefore(0f);
                table.setSpacingAfter(0f);

                // first row
                PdfPCell cell = new PdfPCell(new Phrase("Reservation"));
                cell.setColspan(5);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(5.0f);
                cell.setBackgroundColor(new BaseColor(140, 221, 8));
                table.addCell(cell);
                table.addCell("Id user");
                table.addCell(String.valueOf(tache_user.gui.AuthentificationController.getIdConnecter()));
                table.addCell("Nom user");
                table.addCell(nomUser+" "+prenomUser);
                table.addCell("Spécialité user");
                table.addCell(String.valueOf(tache_user.gui.AuthentificationController.getSpecialiteConnecter()));
                table.addCell("Id événement");
                table.addCell(String.valueOf(EvenementUserController.idMemoriser));
                table.addCell("Titre événement");
                table.addCell(titre);
                table.addCell("Tarif événement");
                table.addCell(tarif);
                table.addCell("Nombre de place Réservé ");
                table.addCell(String.valueOf(r.getNb_place()));
                table.addCell("Total a payer");
                table.addCell(String.valueOf(r.getTotale()));
                table.addCell("Mode de paiment");
                table.addCell(String.valueOf(r.getMode_paiement()));
               
                
                
                doc.add(table); 
                doc.close();
                               Desktop.getDesktop().open(new File("C:\\Users\\hama\\Documents\\NetBeansProjects\\Projet_PI\\Reservation"+r.getId_event()+".pdf"));

                System.out.println("done");
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        

    }

    @FXML
    private void CalculerTotalAPayer(MouseEvent event) {
        totalPayer.setText(String.valueOf(Integer.parseInt(tarifEvent.getText())*placeEvent.getValue()));
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_stock.gui;

import com.itextpdf.text.BaseColor;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tache_stock.entities.Achat;
import tache_stock.entities.Produit;
import tache_stock.services.AchatCRUD;
import tache_stock.services.ProduitCRUD;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class PanierController implements Initializable {
    
    
   private static List<Achat> panier=new ArrayList();
    
    
    

    @FXML
    private TableView<Achat> tabPanier2;
    @FXML
    private TableColumn<Produit, String> med2;
    @FXML
    private TableColumn<Produit, Integer> QD2 ;
    @FXML
    private TableColumn<Produit, Integer> P2 ;

   
    
    
    
    @FXML
    private Button BAFF;
    @FXML
    private TableView<Produit> tabPanier1;
    @FXML
    private TableColumn<Produit, String> med;
    @FXML
    private TableColumn<Produit, Integer> QD ;
    @FXML
    private TableColumn<Produit, Integer> P ;
   
    @FXML
    private Button ButtonSomme;
    @FXML
    private Button add;
   
    @FXML
    private Button ButtonImprimer;
    @FXML
    private Button affPanier;
    @FXML
    private TextField affSom;
    @FXML
    private Spinner<Integer> quantite;
    @FXML
    private TableColumn<?, ?> id1;
    @FXML
    private ImageView back;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id1.setCellValueFactory(new PropertyValueFactory<>("id"));
        med.setCellValueFactory(new PropertyValueFactory<>("nom"));
        QD.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        P.setCellValueFactory(new PropertyValueFactory<>("prix"));
        final int initialValue = 1;

        // Value factory.
        SpinnerValueFactory<Integer> valueFactory = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1,40, initialValue);

        quantite.setValueFactory(valueFactory);
    }  
    @FXML
    void afficheDispo(ActionEvent event) {
        ProduitCRUD ps = new ProduitCRUD();
        ArrayList<Produit> Produit;
List<Produit> Produit2 ;
            Produit2 = ps.afficherProduit2();
            ObservableList<Produit> obs = FXCollections.observableArrayList(Produit2);
            tabPanier1.setItems(obs);
            id1.setCellValueFactory(new PropertyValueFactory<>("id_produit"));
            med.setCellValueFactory(new PropertyValueFactory<>("nom"));
            QD.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            P.setCellValueFactory(new PropertyValueFactory<>("prix"));

            
            
     }
    
    /*
    @FXML
    private void affichePanier(ActionEvent event) {
        tabPanier2.getItems().removeAll(tabPanier2.getSelectionModel().getSelectedItems());
        Produit P  = (Produit) tabPanier2.getSelectionModel().getSelectedItem();
       
        ProduitCRUD cr = new ProduitCRUD();
        ArrayList<Produit> Produit;
        Produit = (ArrayList<Produit>) cr.afficherProduit2();
      //  cr.afficherProduit2();
   

    */
   
private int sum=0;
    @FXML
    private void somme(ActionEvent event) throws DocumentException, SQLException, IOException {
        
   /* for (Integer value : tabPanier2.getItems()) 
        sum = sum + value; */
AchatCRUD a = new AchatCRUD();
        for (int i = 0; i < panier.size(); i++) {
            sum = sum + panier.get(i).getPrix()*panier.get(i).getQuantite();
            
            a.ajouterProduit2(panier.get(i));
        }
        affSom.setText(String.valueOf(sum));
        
    }

    @FXML
    private void addMedicament(ActionEvent event) {
         Produit p = tabPanier1.getSelectionModel().getSelectedItem();
         System.out.println(p.toString());
        Achat ac=new Achat(p.getId_produit(),p.getNom(),1,p.getPrix(),quantite.getValue());
        System.out.println(ac.toString());
        panier.add(ac);
//        AchatCRUD a = new AchatCRUD();
//        a.ajouterProduit2(ac);
    }

    @FXML
    private void afficherPanier(ActionEvent event) {
        AchatCRUD ac = new AchatCRUD();
            ObservableList<Achat> obs = FXCollections.observableArrayList(panier);
            tabPanier2.setItems(obs);
            med2.setCellValueFactory(new PropertyValueFactory<>("nom"));
            QD2.setCellValueFactory(new PropertyValueFactory<>("quantite"));
            P2.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        
    }
    Connection mc  ;
private void generationpdf(int sum) throws SQLException, IOException, DocumentException {
       
    Document doc;
        doc = new Document();
    PdfWriter.getInstance(doc,new FileOutputStream("./ListeAchat.pdf"));

    doc.open();
   
    doc.add(new Paragraph("   "));
    doc.add(new Paragraph(" ************************************* Liste Des Achat ************************************* "));
    doc.add(new Paragraph("   "));

    PdfPTable table = new PdfPTable(4);
    table.setWidthPercentage(80);
    PdfPCell cell;

  
   
    cell = new PdfPCell(new Phrase("id_user", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
    
    cell = new PdfPCell(new Phrase("nom medicament", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
    
    
    cell = new PdfPCell(new Phrase("quantite", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell);
    
    cell = new PdfPCell(new Phrase("prix", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell);
    

    for (int i = 0; i < panier.size(); i++) {
        
       
      
     
        
        cell = new PdfPCell(new Phrase(String.valueOf(panier.get(i).getId_user()), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(String.valueOf(panier.get(i).getNom()), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(String.valueOf(panier.get(i).getQuantite()), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(String.valueOf(panier.get(i).getPrix()), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        
        
    }
        cell = new PdfPCell(new Phrase("-----", FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("-----", FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        
        cell = new PdfPCell(new Phrase("-----", FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(String.valueOf(sum), FontFactory.getFont("Comic Sans MS", 12,BaseColor.RED)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    doc.add(table);
    doc.close();
    Desktop.getDesktop().open(new File("./ListeAchat.pdf"));
    }
    @FXML
    private void imprimer(ActionEvent event) throws SQLException, IOException, DocumentException {
        generationpdf(sum);
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
    
    
    


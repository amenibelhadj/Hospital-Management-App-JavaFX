/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_evenement.UserInterfaces;

import tache_evenement.Admininterfaces.AjoutEvenementAdminController;
import tache_evenement.Admininterfaces.EvenementAdminController;
import static tache_evenement.Admininterfaces.EvenementAdminController.idMemoriser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tache_evenement.model.Evenement;
import tache_evenement.services.ServiceAjoutEvenementAdmin;
import tache_evenement.services.ServiceEvenementAdmin;
import tache_evenement.services.ServiceEvenementUser;
import tache_evenement.services.ServiceParticiperEventUser;

/**
 * FXML Controller class
 *
 * @author hama
 */
public class EvenementUserController implements Initializable {

    @FXML
    private JFXComboBox<String> comboPage;
    @FXML
    private JFXButton RtoureB;
    @FXML
    private Pane pane111;
    @FXML
    private Label id11;
    @FXML
    private Button btnSigin;
    @FXML
    private Button btnSigin11;
    @FXML
    private Label id111;
    @FXML
    private Label id1111;
    @FXML
    private Label id11111;
    @FXML
    private Label id112;
    @FXML
    private Pane paneDetailEvent;
    @FXML
    private ImageView imageEventDetail;
    @FXML
    private Label nomEventDetail;
    @FXML
    private Label descEventDetail;
    @FXML
    private Label dateEventDetail;
    @FXML
    private Label nbReservationEventDetail;
    @FXML
    private Label prixEventDetail;
    @FXML
    private Label capaciteEventDetail;
    @FXML
    private Label pourEventDetail;
    @FXML
    private Label categorieEventDetail;
    @FXML
    private Label lieuEventDetail1;
    @FXML
    private TextArea rechercherEntryEvent;
    @FXML
    private Pane pane0;
    @FXML
    private ImageView imageEventAff0;
    @FXML
    private Label newEvent1;
    @FXML
    private Label idevent1;
    @FXML
    private DatePicker dateEventFiltrer;
    @FXML
    private Pane pane2;
    @FXML
    private ImageView imageEventAff2;
    @FXML
    private Label newEvent2;
    @FXML
    private Label idevent2;
    @FXML
    private Pane pane3;
    @FXML
    private ImageView imageEventAff3;
    @FXML
    private Label newEvent3;
    @FXML
    private Label idevent3;
    @FXML
    private Pane pane4;
    @FXML
    private ImageView imageEventAff4;
    @FXML
    private Label newEvent4;
    @FXML
    private Label idevent4;
    @FXML
    private Pane pane5;
    @FXML
    private ImageView imageEventAff5;
    @FXML
    private Label id111111;
    @FXML
    private Label newEvent5;
    @FXML
    private Label idevent5;
    @FXML
    private Pane pane6;
    @FXML
    private ImageView imageEventAff6;
    @FXML
    private Label id1111111;
    @FXML
    private Label newEvent6;
    @FXML
    private Label idevent6;
    @FXML
    private Pane pane7;
    @FXML
    private ImageView imageEventAff7;
    @FXML
    private Label id11111111;
    @FXML
    private Label newEvent7;
    @FXML
    private Label idevent7;
    @FXML
    private ToggleGroup Etat;
    @FXML
    private Pane pane1;
    @FXML
    private ImageView imageEventAff1;
    @FXML
    private Label newEvent11;
    @FXML
    private Label idevent11;
    @FXML
    private ToggleGroup event;
    public static int idMemoriser=0;
    ServiceEvenementUser eas = new ServiceEvenementUser();
    private String filterEtat ="desc";
    @FXML
    private JFXButton participerEventBTN;
    @FXML
    private JFXButton cancelParticipationBTN;
    
    
   
    @FXML
    private Label VousAvez;
    @FXML
    private Label nbPlace;
    @FXML
    private JFXRadioButton tousLesEvenements;
    @FXML
    private JFXRadioButton mesParticipation;
    
    /**
     * Initializes the controller class.
     */
    public int getIdMemoriser(){
        return this.idMemoriser;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        paneDetailEvent.setVisible(false);
        remplirComboPage();
        try {
            affichageEvenement();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EvenementUserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(EvenementUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
public void remplirComboPage(){
        try {
            ServiceEvenementAdmin p=new ServiceEvenementAdmin();
            Connection conn = p.con;
            PreparedStatement ste;
            String sql = "select count(*) from evenement ";
            ste = conn.prepareStatement(sql);
            ResultSet rs = ste.executeQuery(sql);
            ObservableList<String> list=FXCollections.observableArrayList();  
            while(rs.next()){
                if (Integer.parseInt(rs.getString(1))%8==0){
                    for (int i=1;i<Integer.parseInt(rs.getString(1))/8+1;i++)
                    {
                        list.add(Integer.toString(i));
                    }
                }
                else {
                    for (int i=1;i<Integer.parseInt(rs.getString(1))/8+2;i++)
                    {
                        
                        list.add(Integer.toString(i));
                    }
                }
            }
            comboPage.setItems(list);
            rs.close();
            comboPage.setValue("1");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void affichageEvenement() throws FileNotFoundException, ParseException{
        try {
            List<Pane> panes=new ArrayList<>();
            panes.add(pane0);panes.add(pane1);panes.add(pane2);panes.add(pane3);panes.add(pane4);panes.add(pane5);panes.add(pane6);panes.add(pane7);
            List<ImageView> imageEvent=new ArrayList<>();
            imageEvent.add(imageEventAff0);imageEvent.add(imageEventAff1);imageEvent.add(imageEventAff2);imageEvent.add(imageEventAff3);imageEvent.add(imageEventAff4);imageEvent.add(imageEventAff5);imageEvent.add(imageEventAff6);imageEvent.add(imageEventAff7);
            
            
            for (Pane p : panes){
                p.setVisible(false);
            }
            ServiceEvenementAdmin p=new ServiceEvenementAdmin();
            Connection conn = p.con;
            PreparedStatement ste;
            int a=(Integer.parseInt(comboPage.getValue())-1)*8;
            int b=Integer.parseInt(comboPage.getValue())*8;
            System.out.println("a="+a+"     b="+b);
            String sql=null;
            sql = "select categorie,titre,pour,date_event,id_event,image,date_ajout from evenement order by date_ajout "+filterEtat+" limit "+a+","+b;
            ste = conn.prepareStatement(sql);
            System.out.println(ste);
            ResultSet rs = ste.executeQuery(sql);
            int i=0;
            while(rs.next()){
                panes.get(i).setVisible(true);
                InputStream stream2 = new FileInputStream("C:/xampp/htdocs/Clinique/image evenement/0.jpg");
                                
                Image image2 = new Image(stream2);
                File folder = new File("C:/xampp/htdocs/Clinique/image evenement/"); 
                File[] listOfFiles = folder.listFiles(); 
                int existe = 0 ;
                for (File file : listOfFiles) { 
                    if (file.isFile()) { 
                        if (file.getName().equals(rs.getString(5)+rs.getString(6))){
                            InputStream stream = new FileInputStream("C:/xampp/htdocs/Clinique/image evenement/"+rs.getString(5)+rs.getString(6));
                            Image image = new Image(stream);
                            imageEvent.get(i).setImage(image);
                            existe=1;
                        } 
                    } 
                } 
                if (existe == 0){      
                    imageEvent.get(i).setImage(image2);
                }

                
                ((Label)panes.get(i).getChildren().get(1)).setText(rs.getString(2));
                ((Label)panes.get(i).getChildren().get(2)).setText(rs.getString(4));
                ((Label)panes.get(i).getChildren().get(3)).setText(rs.getString(1));
                ((Label)panes.get(i).getChildren().get(4)).setText(rs.getString(3));
                ((Label)panes.get(i).getChildren().get(7)).setText(rs.getString(5));
                
                Date dateAjout=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(7)); 
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date in = new Date();
                LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
                ZonedDateTime zdt = ldt.minusDays(1).atZone(ZoneId.systemDefault());
                Date output = Date.from(zdt.toInstant());
                System.out.println(dateAjout.compareTo(output));
                if (dateAjout.compareTo(output)>0){
                    ((Label)panes.get(i).getChildren().get(6)).setVisible(true);
                }
                else {
                    ((Label)panes.get(i).getChildren().get(6)).setVisible(false);
                }
                
                i++;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenementAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void MouseChange(MouseEvent event) {
    }


    @FXML
    private void nextPage(ActionEvent event) throws FileNotFoundException, ParseException {
        affichageEvenement();
    }

    @FXML
    private void RtoureB(ActionEvent event) {
    }
Stage Stage1;
    @FXML
    private void btnSiginAction(ActionEvent event) {
        try {
            if (tache_user.gui.AuthentificationController.getSpecialiteConnecter().equals("medecin")){
                     Parent root = FXMLLoader.load(getClass().getResource("/tache_user/gui/WelcomeMedecin.fxml"));
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
            }
            else if (tache_user.gui.AuthentificationController.getSpecialiteConnecter().equals("infirmier")){
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
            }
                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
        
    }

    
    private void AffichageDetail(int id) throws SQLException, FileNotFoundException{
        
            ServiceEvenementUser p=new ServiceEvenementUser();
            Connection conn = p.con;
            PreparedStatement ste;
            PreparedStatement ste2;
            
            String sql=null;
            sql = "select categorie,titre,pour,date_event,lieu,image,description,capacite,tarif,id_event from evenement where id_event="+id;
            ste = conn.prepareStatement(sql);
            System.out.println(ste);
            ResultSet rs = ste.executeQuery(sql);
            
            
            int i=0;
            while(rs.next()){
                
                InputStream stream2 = new FileInputStream("C:/xampp/htdocs/Clinique/image evenement/0.jpg");
                                
                Image image2 = new Image(stream2);
                File folder = new File("C:/xampp/htdocs/Clinique/image evenement/"); 
                File[] listOfFiles = folder.listFiles(); 
                int existe = 0 ;
                for (File file : listOfFiles) { 
                    if (file.isFile()) { 
                        System.out.println(file.getName());
                        if (file.getName().equals(rs.getString(10)+".jpg")){
                            InputStream stream = new FileInputStream("C:/xampp/htdocs/Clinique/image evenement/"+rs.getString(10)+".jpg");
                            Image image = new Image(stream);
                            imageEventDetail.setImage(image);
                            existe=1;
                        } 
                    } 
                } 
                if (existe == 0){      
                    imageEventDetail.setImage(image2);
                }
                ((Label)paneDetailEvent.getChildren().get(1)).setText(rs.getString(2));
                ((Label)paneDetailEvent.getChildren().get(7)).setText(rs.getString(4));
                ((Label)paneDetailEvent.getChildren().get(16)).setText(rs.getString(1));
                ((Label)paneDetailEvent.getChildren().get(14)).setText(rs.getString(3));
                ((Label)paneDetailEvent.getChildren().get(12)).setText(rs.getString(9));
                ((Label)paneDetailEvent.getChildren().get(13)).setText(rs.getString(8));
                ((Label)paneDetailEvent.getChildren().get(4)).setText(rs.getString(7));
                ((Label)paneDetailEvent.getChildren().get(18)).setText(rs.getString(5));
                
                i++;
            }
            rs.close();
            String sql2=null;
            sql2 = "select nb_place from reservation_evenement where id_event="+id+" and id_user="+tache_user.gui.AuthentificationController.getIdConnecter() ;
            ste2 = conn.prepareStatement(sql2);
            System.out.println(ste2);
            ResultSet rs2 = ste.executeQuery(sql2);
            int reserve = 0;
            while(rs2.next()){
                     participerEventBTN.setVisible(false);
                     cancelParticipationBTN.setVisible(true);
                     VousAvez.setVisible(true);
                     nbPlace.setVisible(true);
                     nbPlace.setText(rs2.getString(1));
                     reserve = 1;
                 }
            if (reserve == 0){
                    participerEventBTN.setVisible(true);
                     cancelParticipationBTN.setVisible(false);
                     VousAvez.setVisible(false);
                     nbPlace.setText("");
                     nbPlace.setVisible(false);
            }
            rs2.close();
            
}
    @FXML
    private void pane0Clicked(MouseEvent event) throws SQLException, FileNotFoundException {
        idMemoriser = Integer.parseInt(((Label)pane0.getChildren().get(7)).getText());
        DropShadow ds = new DropShadow();
        ds.setBlurType(BlurType.THREE_PASS_BOX);
        ds.setWidth(95.86);
        ds.setRadius(43.92);
        ds.setHeight(81.83);
        ds.setSpread(0.33);
        Color c = Color.rgb(87, 165, 207);  
        ds.setColor(c);
        pane0.setEffect(ds);
        pane1.setEffect(null);
        pane2.setEffect(null);
        pane3.setEffect(null);
        pane4.setEffect(null);
        pane5.setEffect(null);
        pane6.setEffect(null);
        pane7.setEffect(null);
        pane1.setScaleX(1.0);
        pane1.setScaleY(1.0);
        pane2.setScaleX(1.0);
        pane2.setScaleY(1.0);
        pane3.setScaleX(1.0);
        pane3.setScaleY(1.0);
        pane4.setScaleX(1.0);
        pane4.setScaleY(1.0);
        pane5.setScaleX(1.0);
        pane5.setScaleY(1.0);
        pane6.setScaleX(1.0);
        pane6.setScaleY(1.0);
        pane7.setScaleX(1.0);
        pane7.setScaleY(1.0);
        paneDetailEvent.setVisible(true);
        
        AffichageDetail(Integer.parseInt(((Label)pane0.getChildren().get(7)).getText()));
    }
    @FXML   
    private void pane1Clicked(MouseEvent event) throws SQLException, FileNotFoundException {
        //showDetailEvent(Integer.parseInt(idevent1.getText()));
        idMemoriser = Integer.parseInt(((Label)pane1.getChildren().get(7)).getText());
        DropShadow ds = new DropShadow();
        ds.setBlurType(BlurType.THREE_PASS_BOX);
        ds.setWidth(95.86);
        ds.setRadius(43.92);
        ds.setHeight(81.83);
        ds.setSpread(0.33);
        Color c = Color.rgb(87, 165, 207);  
        ds.setColor(c);
        pane0.setEffect(null);
        pane1.setEffect(ds);
        pane2.setEffect(null);
        pane3.setEffect(null);
        pane4.setEffect(null);
        pane5.setEffect(null);
        pane6.setEffect(null);
        pane7.setEffect(null);
        pane0.setScaleX(1.0);
        pane0.setScaleY(1.0);
        pane2.setScaleX(1.0);
        pane2.setScaleY(1.0);
        pane3.setScaleX(1.0);
        pane3.setScaleY(1.0);
        pane4.setScaleX(1.0);
        pane4.setScaleY(1.0);
        pane5.setScaleX(1.0);
        pane5.setScaleY(1.0);
        pane6.setScaleX(1.0);
        pane6.setScaleY(1.0);
        pane7.setScaleX(1.0);
        pane7.setScaleY(1.0);
        paneDetailEvent.setVisible(true);
        
        AffichageDetail(Integer.parseInt(((Label)pane1.getChildren().get(7)).getText()));
        
    }

    @FXML
    private void pane2Clicked(MouseEvent event) throws SQLException, FileNotFoundException {
        //showDetailEvent(Integer.parseInt(idevent2.getText()));
        idMemoriser = Integer.parseInt(((Label)pane2.getChildren().get(7)).getText());
        DropShadow ds = new DropShadow();
        ds.setBlurType(BlurType.THREE_PASS_BOX);
        ds.setWidth(95.86);
        ds.setRadius(43.92);
        ds.setHeight(81.83);
        ds.setSpread(0.33);
        Color c = Color.rgb(87, 165, 207);  
        ds.setColor(c);
        pane0.setEffect(null);
        pane1.setEffect(null);
        pane2.setEffect(ds);
        pane3.setEffect(null);
        pane4.setEffect(null);
        pane5.setEffect(null);
        pane6.setEffect(null);
        pane7.setEffect(null);
        pane0.setScaleX(1.0);
        pane0.setScaleY(1.0);
        pane1.setScaleX(1.0);
        pane1.setScaleY(1.0);
        pane3.setScaleX(1.0);
        pane3.setScaleY(1.0);
        pane4.setScaleX(1.0);
        pane4.setScaleY(1.0);
        pane5.setScaleX(1.0);
        pane5.setScaleY(1.0);
        pane6.setScaleX(1.0);
        pane6.setScaleY(1.0);
        pane7.setScaleX(1.0);
        pane7.setScaleY(1.0);
        paneDetailEvent.setVisible(true);
        AffichageDetail(Integer.parseInt(((Label)pane2.getChildren().get(7)).getText()));
    }

    @FXML
    private void pane3Clicked(MouseEvent event) throws SQLException, FileNotFoundException {
        //showDetailEvent(Integer.parseInt(idevent3.getText()));
                idMemoriser = Integer.parseInt(((Label)pane3.getChildren().get(7)).getText());
DropShadow ds = new DropShadow();
        ds.setBlurType(BlurType.THREE_PASS_BOX);
        ds.setWidth(95.86);
        ds.setRadius(43.92);
        ds.setHeight(81.83);
        ds.setSpread(0.33);
        Color c = Color.rgb(87, 165, 207);  
        ds.setColor(c);
        pane0.setEffect(null);
        pane3.setEffect(ds);
        pane1.setEffect(null);
        pane2.setEffect(null);
        pane4.setEffect(null);
        pane5.setEffect(null);
        pane6.setEffect(null);
        pane7.setEffect(null);
        pane0.setScaleX(1.0);
        pane0.setScaleY(1.0);
        pane1.setScaleX(1.0);
        pane1.setScaleY(1.0);
        pane2.setScaleX(1.0);
        pane2.setScaleY(1.0);
        pane4.setScaleX(1.0);
        pane4.setScaleY(1.0);
        pane5.setScaleX(1.0);
        pane5.setScaleY(1.0);
        pane6.setScaleX(1.0);
        pane6.setScaleY(1.0);
        pane7.setScaleX(1.0);
        pane7.setScaleY(1.0);
        paneDetailEvent.setVisible(true);
        AffichageDetail(Integer.parseInt(((Label)pane3.getChildren().get(7)).getText()));
    }

    @FXML
    private void pane4Clicked(MouseEvent event) throws SQLException, FileNotFoundException {
        //showDetailEvent(Integer.parseInt(idevent4.getText()));
                idMemoriser = Integer.parseInt(((Label)pane4.getChildren().get(7)).getText());
DropShadow ds = new DropShadow();
        ds.setBlurType(BlurType.THREE_PASS_BOX);
        ds.setWidth(95.86);
        ds.setRadius(43.92);
        ds.setHeight(81.83);
        ds.setSpread(0.33);
        Color c = Color.rgb(87, 165, 207);  
        ds.setColor(c);
        pane0.setEffect(null);
        pane4.setEffect(ds);
        pane1.setEffect(null);
        pane2.setEffect(null);
        pane3.setEffect(null);
        pane5.setEffect(null);
        pane6.setEffect(null);
        pane7.setEffect(null);
        pane0.setScaleX(1.0);
        pane0.setScaleY(1.0);
        pane1.setScaleX(1.0);
        pane1.setScaleY(1.0);
        pane2.setScaleX(1.0);
        pane2.setScaleY(1.0);
        pane3.setScaleX(1.0);
        pane3.setScaleY(1.0);
        pane5.setScaleX(1.0);
        pane5.setScaleY(1.0);
        pane6.setScaleX(1.0);
        pane6.setScaleY(1.0);
        pane7.setScaleX(1.0);
        pane7.setScaleY(1.0);
        paneDetailEvent.setVisible(true);
        AffichageDetail(Integer.parseInt(((Label)pane4.getChildren().get(7)).getText()));
    }

    @FXML
    private void pane5Clicked(MouseEvent event) throws SQLException, FileNotFoundException {
        //showDetailEvent(Integer.parseInt(idevent5.getText()));
                idMemoriser = Integer.parseInt(((Label)pane5.getChildren().get(7)).getText());
DropShadow ds = new DropShadow();
        ds.setBlurType(BlurType.THREE_PASS_BOX);
        ds.setWidth(95.86);
        ds.setRadius(43.92);
        ds.setHeight(81.83);
        ds.setSpread(0.33);
        Color c = Color.rgb(87, 165, 207);  
        ds.setColor(c);
        pane0.setEffect(null);
        pane5.setEffect(ds);
        pane1.setEffect(null);
        pane2.setEffect(null);
        pane3.setEffect(null);
        pane4.setEffect(null);
        pane6.setEffect(null);
        pane7.setEffect(null);
        pane0.setScaleX(1.0);
        pane0.setScaleY(1.0);
        pane1.setScaleX(1.0);
        pane1.setScaleY(1.0);
        pane2.setScaleX(1.0);
        pane2.setScaleY(1.0);
        pane3.setScaleX(1.0);
        pane3.setScaleY(1.0);
        pane4.setScaleX(1.0);
        pane4.setScaleY(1.0);
        pane6.setScaleX(1.0);
        pane6.setScaleY(1.0);
        pane7.setScaleX(1.0);
        pane7.setScaleY(1.0);
        paneDetailEvent.setVisible(true);
        AffichageDetail(Integer.parseInt(((Label)pane5.getChildren().get(7)).getText()));
    }

    @FXML
    private void pane6Clicked(MouseEvent event) throws SQLException, FileNotFoundException {
        //showDetailEvent(Integer.parseInt(idevent6.getText()));
                idMemoriser = Integer.parseInt(((Label)pane6.getChildren().get(7)).getText());
DropShadow ds = new DropShadow();
        ds.setBlurType(BlurType.THREE_PASS_BOX);
        ds.setWidth(95.86);
        ds.setRadius(43.92);
        ds.setHeight(81.83);
        ds.setSpread(0.33);
        Color c = Color.rgb(87, 165, 207);  
        ds.setColor(c);
        pane0.setEffect(null);
        pane6.setEffect(ds);
        pane1.setEffect(null);
        pane2.setEffect(null);
        pane3.setEffect(null);
        pane4.setEffect(null);
        pane5.setEffect(null);
        pane7.setEffect(null);
        pane0.setScaleX(1.0);
        pane0.setScaleY(1.0);
        pane1.setScaleX(1.0);
        pane1.setScaleY(1.0);
        pane2.setScaleX(1.0);
        pane2.setScaleY(1.0);
        pane3.setScaleX(1.0);
        pane3.setScaleY(1.0);
        pane4.setScaleX(1.0);
        pane4.setScaleY(1.0);
        pane5.setScaleX(1.0);
        pane5.setScaleY(1.0);
        pane7.setScaleX(1.0);
        pane7.setScaleY(1.0);
        paneDetailEvent.setVisible(true);
        AffichageDetail(Integer.parseInt(((Label)pane6.getChildren().get(7)).getText()));
    }

    @FXML
    private void pane7Clicked(MouseEvent event) throws SQLException, FileNotFoundException {
        //showDetailEvent(Integer.parseInt(idevent7.getText()));
                idMemoriser = Integer.parseInt(((Label)pane7.getChildren().get(7)).getText());
DropShadow ds = new DropShadow();
        ds.setBlurType(BlurType.THREE_PASS_BOX);
        ds.setWidth(95.86);
        ds.setRadius(43.92);
        ds.setHeight(81.83);
        ds.setSpread(0.33);
        Color c = Color.rgb(87, 165, 207);  
        ds.setColor(c);
        pane0.setEffect(null);
        pane7.setEffect(ds);
        pane1.setEffect(null);
        pane2.setEffect(null);
        pane3.setEffect(null);
        pane4.setEffect(null);
        pane5.setEffect(null);
        pane6.setEffect(null);
        pane0.setScaleX(1.0);
        pane0.setScaleY(1.0);
        pane1.setScaleX(1.0);
        pane1.setScaleY(1.0);
        pane2.setScaleX(1.0);
        pane2.setScaleY(1.0);
        pane3.setScaleX(1.0);
        pane3.setScaleY(1.0);
        pane4.setScaleX(1.0);
        pane4.setScaleY(1.0);
        pane5.setScaleX(1.0);
        pane5.setScaleY(1.0);
        pane6.setScaleX(1.0);
        pane6.setScaleY(1.0);
        paneDetailEvent.setVisible(true);
        AffichageDetail(Integer.parseInt(((Label)pane7.getChildren().get(7)).getText()));
    }
    
    private void showDetailEvent (int idevent){
        /*try {
            Evenement e = eas.searchEvent(idevent);
            System.out.println(e.toString());
        } catch (SQLException ex) {
            Logger.getLogger(EvenementAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(EvenementAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    @FXML
    private void InfoProduit(MouseEvent event) {
    }


    @FXML
    private void ActualiserEventBTN(ActionEvent event) throws FileNotFoundException, ParseException {
        tousLesEvenements.setSelected(true);
        remplirComboPage();
        affichageEvenement();
        dateEventFiltrer.setValue(null);
        rechercherEntryEvent.setText("");
        pane0.setEffect(null);
        pane1.setEffect(null);
                
        pane2.setEffect(null);
        pane3.setEffect(null);
        pane4.setEffect(null);
        pane5.setEffect(null);
        pane6.setEffect(null);
        pane7.setEffect(null);
        pane0.setScaleX(1.0);
        pane0.setScaleY(1.0);
        pane1.setScaleX(1.0);
        pane1.setScaleY(1.0);
        pane2.setScaleX(1.0);
        pane2.setScaleY(1.0);
        pane3.setScaleX(1.0);
        pane3.setScaleY(1.0);
        pane4.setScaleX(1.0);
        pane4.setScaleY(1.0);
        pane5.setScaleX(1.0);
        pane5.setScaleY(1.0);
        pane6.setScaleX(1.0);
        pane6.setScaleY(1.0);
        pane7.setScaleX(1.0);
        pane7.setScaleY(1.0);
        paneDetailEvent.setVisible(false);
    }

    @FXML
    private void sizePaneChange(MouseEvent event) {
        ((Pane)event.getSource()).setScaleX(1.07);
        ((Pane)event.getSource()).setScaleY(1.07);
    }

    @FXML
    private void sizePaneChangeExited(MouseEvent event) {
        
        if (((Pane)event.getSource()).getEffect()==null){
            ((Pane)event.getSource()).setScaleX(1.0);
            ((Pane)event.getSource()).setScaleY(1.0);
        }
    }

    @FXML
    private void filterNewest(ActionEvent event) throws ParseException {
        pane0.setEffect(null);
        pane1.setEffect(null);
        pane2.setEffect(null);
        pane3.setEffect(null);
        pane4.setEffect(null);
        pane5.setEffect(null);
        pane6.setEffect(null);
        pane7.setEffect(null);
        pane0.setScaleX(1.0);
        pane0.setScaleY(1.0);
        pane1.setScaleX(1.0);
        pane1.setScaleY(1.0);
        pane2.setScaleX(1.0);
        pane2.setScaleY(1.0);
        pane3.setScaleX(1.0);
        pane3.setScaleY(1.0);
        pane4.setScaleX(1.0);
        pane4.setScaleY(1.0);
        pane5.setScaleX(1.0);
        pane5.setScaleY(1.0);
        pane6.setScaleX(1.0);
        pane6.setScaleY(1.0);
        pane7.setScaleX(1.0);
        pane7.setScaleY(1.0);
        paneDetailEvent.setVisible(false);
        filterEtat="desc";
        remplirComboPage();
        try {
            affichageEvenement();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EvenementUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void filterOldest(ActionEvent event) throws ParseException {
        pane0.setEffect(null);
        pane1.setEffect(null);
        pane2.setEffect(null);
        pane3.setEffect(null);
        pane4.setEffect(null);
        pane5.setEffect(null);
        pane6.setEffect(null);
        pane7.setEffect(null);
        pane0.setScaleX(1.0);
        pane0.setScaleY(1.0);
        pane1.setScaleX(1.0);
        pane1.setScaleY(1.0);
        pane2.setScaleX(1.0);
        pane2.setScaleY(1.0);
        pane3.setScaleX(1.0);
        pane3.setScaleY(1.0);
        pane4.setScaleX(1.0);
        pane4.setScaleY(1.0);
        pane5.setScaleX(1.0);
        pane5.setScaleY(1.0);
        pane6.setScaleX(1.0);
        pane6.setScaleY(1.0);
        pane7.setScaleX(1.0);
        pane7.setScaleY(1.0);
        paneDetailEvent.setVisible(false);
        filterEtat="";
        remplirComboPage();
        try {
            affichageEvenement();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EvenementUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void FilterEventParDate(ActionEvent event) throws SQLException, FileNotFoundException, ParseException {
        
        if (rechercherEntryEvent.getText()==""){
            affichage_filter_date(dateEventFiltrer.getValue());
        }
        else
            affichage_Filtrer_NomDate_Event(rechercherEntryEvent.getText() ,dateEventFiltrer.getValue());
    }
    public void affichage_filter_date(LocalDate dateF) throws SQLException, FileNotFoundException, ParseException{
        try {
            ServiceEvenementAdmin p=new ServiceEvenementAdmin();
            Connection conn = p.con;
            PreparedStatement ste;
            String sql = "select count(*) from evenement where date_event='"+dateF+"'";
            ste = conn.prepareStatement(sql);
            ResultSet rs = ste.executeQuery(sql);
            ObservableList<String> list=FXCollections.observableArrayList();  
            while(rs.next()){
                if (Integer.parseInt(rs.getString(1))%8==0){
                    for (int i=1;i<Integer.parseInt(rs.getString(1))/8+1;i++)
                    {
                        list.add(Integer.toString(i));
                    }
                }
                else {
                    for (int i=1;i<Integer.parseInt(rs.getString(1))/8+2;i++)
                    {
                        
                        list.add(Integer.toString(i));
                    }
                }
            }
            comboPage.setItems(list);
            rs.close();
            comboPage.setValue("1");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Pane> panes=new ArrayList<>();
            panes.add(pane0);panes.add(pane1);panes.add(pane2);panes.add(pane3);panes.add(pane4);panes.add(pane5);panes.add(pane6);panes.add(pane7);
            List<ImageView> imageEvent=new ArrayList<>();
            imageEvent.add(imageEventAff0);imageEvent.add(imageEventAff1);imageEvent.add(imageEventAff2);imageEvent.add(imageEventAff3);imageEvent.add(imageEventAff4);imageEvent.add(imageEventAff5);imageEvent.add(imageEventAff6);imageEvent.add(imageEventAff7);
            
            
            for (Pane p : panes){
                p.setVisible(false);
            }
        ServiceEvenementAdmin p=new ServiceEvenementAdmin();
            Connection conn = p.con;
            PreparedStatement ste;
            
            String sql=null;
            sql = "select categorie,titre,pour,date_event,lieu,image,description,capacite,tarif,id_event,date_ajout from evenement where date_event='"+dateF+"'";
            ste = conn.prepareStatement(sql);
            System.out.println(ste);
            ResultSet rs = ste.executeQuery(sql);
            int i=0;
            System.out.println("5555555555555");
            while(rs.next()){
                panes.get(i).setVisible(true);
                InputStream stream2 = new FileInputStream("C:/xampp/htdocs/Clinique/image evenement/0.jpg");
                                
                Image image2 = new Image(stream2);
                File folder = new File("C:/xampp/htdocs/Clinique/image evenement/"); 
                File[] listOfFiles = folder.listFiles(); 
                int existe = 0 ;
                for (File file : listOfFiles) { 
                    if (file.isFile()) { 
                        if (file.getName().equals(rs.getString(5)+rs.getString(6))){
                            InputStream stream = new FileInputStream("C:/xampp/htdocs/Clinique/image evenement/"+rs.getString(5)+rs.getString(6));
                            Image image = new Image(stream);
                            imageEvent.get(i).setImage(image);
                            existe=1;
                        } 
                    } 
                } 
                if (existe == 0){      
                    imageEvent.get(i).setImage(image2);
                }

                
                ((Label)panes.get(i).getChildren().get(1)).setText(rs.getString(2));
                ((Label)panes.get(i).getChildren().get(2)).setText(rs.getString(4));
                ((Label)panes.get(i).getChildren().get(3)).setText(rs.getString(1));
                ((Label)panes.get(i).getChildren().get(4)).setText(rs.getString(3));
                ((Label)panes.get(i).getChildren().get(7)).setText(rs.getString(10));
                
                Date dateAjout=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(11)); 
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date in = new Date();
                LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
                ZonedDateTime zdt = ldt.minusDays(1).atZone(ZoneId.systemDefault());
                Date output = Date.from(zdt.toInstant());
                System.out.println(dateAjout.compareTo(output));
                if (dateAjout.compareTo(output)>0){
                    ((Label)panes.get(i).getChildren().get(6)).setVisible(true);
                }
                else {
                    ((Label)panes.get(i).getChildren().get(6)).setVisible(false);
                }
                
                i++;
            }
            rs.close();
            
    }

    @FXML
    private void RechercherEventNom(MouseEvent event) throws SQLException, FileNotFoundException, ParseException {
        if (dateEventFiltrer.getValue()!=null)
            affichage_Filtrer_NomDate_Event(rechercherEntryEvent.getText(),dateEventFiltrer.getValue());
        else 
            affichage_Rechercher_Event(rechercherEntryEvent.getText());
    }
    public void affichage_Rechercher_Event(String nom) throws SQLException, FileNotFoundException, ParseException{
        try {
            ServiceEvenementAdmin p=new ServiceEvenementAdmin();
            Connection conn = p.con;
            PreparedStatement ste;
            String sql = "select count(*) from evenement where titre like '%"+nom+"%'";
            ste = conn.prepareStatement(sql);
            ResultSet rs = ste.executeQuery(sql);
            ObservableList<String> list=FXCollections.observableArrayList();  
            while(rs.next()){
                if (Integer.parseInt(rs.getString(1))%8==0){
                    for (int i=1;i<Integer.parseInt(rs.getString(1))/8+1;i++)
                    {
                        list.add(Integer.toString(i));
                    }
                }
                else {
                    for (int i=1;i<Integer.parseInt(rs.getString(1))/8+2;i++)
                    {
                        
                        list.add(Integer.toString(i));
                    }
                }
            }
            comboPage.setItems(list);
            rs.close();
            comboPage.setValue("1");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Pane> panes=new ArrayList<>();
            panes.add(pane0);panes.add(pane1);panes.add(pane2);panes.add(pane3);panes.add(pane4);panes.add(pane5);panes.add(pane6);panes.add(pane7);
            List<ImageView> imageEvent=new ArrayList<>();
            imageEvent.add(imageEventAff0);imageEvent.add(imageEventAff1);imageEvent.add(imageEventAff2);imageEvent.add(imageEventAff3);imageEvent.add(imageEventAff4);imageEvent.add(imageEventAff5);imageEvent.add(imageEventAff6);imageEvent.add(imageEventAff7);
            
            
            for (Pane p : panes){
                p.setVisible(false);
            }
            ServiceEvenementAdmin p=new ServiceEvenementAdmin();
            Connection conn = p.con;
            PreparedStatement ste;
            
            String sql=null;
            sql = "select categorie,titre,pour,date_event,lieu,image,description,capacite,tarif,id_event,date_ajout from evenement where titre like '%"+nom+"%'";
            ste = conn.prepareStatement(sql);
            System.out.println(ste);
            ResultSet rs = ste.executeQuery(sql);
            int i=0;
            while(rs.next()){
                panes.get(i).setVisible(true);
                InputStream stream2 = new FileInputStream("C:/xampp/htdocs/Clinique/image evenement/0.jpg");
                                
                Image image2 = new Image(stream2);
                File folder = new File("C:/xampp/htdocs/Clinique/image evenement/"); 
                File[] listOfFiles = folder.listFiles(); 
                int existe = 0 ;
                for (File file : listOfFiles) { 
                    if (file.isFile()) { 
                        if (file.getName().equals(rs.getString(5)+rs.getString(6))){
                            InputStream stream = new FileInputStream("C:/xampp/htdocs/Clinique/image evenement/"+rs.getString(5)+rs.getString(6));
                            Image image = new Image(stream);
                            imageEvent.get(i).setImage(image);
                            existe=1;
                        } 
                    } 
                } 
                if (existe == 0){      
                    imageEvent.get(i).setImage(image2);
                }

                
                ((Label)panes.get(i).getChildren().get(1)).setText(rs.getString(2));
                ((Label)panes.get(i).getChildren().get(2)).setText(rs.getString(4));
                ((Label)panes.get(i).getChildren().get(3)).setText(rs.getString(1));
                ((Label)panes.get(i).getChildren().get(4)).setText(rs.getString(3));
                ((Label)panes.get(i).getChildren().get(7)).setText(rs.getString(10));
                
                Date dateAjout=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(11)); 
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date in = new Date();
                LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
                ZonedDateTime zdt = ldt.minusDays(1).atZone(ZoneId.systemDefault());
                Date output = Date.from(zdt.toInstant());
                System.out.println(dateAjout.compareTo(output));
                if (dateAjout.compareTo(output)>0){
                    ((Label)panes.get(i).getChildren().get(6)).setVisible(true);
                }
                else {
                    ((Label)panes.get(i).getChildren().get(6)).setVisible(false);
                }
                
                i++;
            }
            rs.close();
            
    }
    public void affichage_Filtrer_NomDate_Event(String nom ,LocalDate dateF) throws SQLException, FileNotFoundException, ParseException{
        try {
            ServiceEvenementAdmin p=new ServiceEvenementAdmin();
            Connection conn = p.con;
            PreparedStatement ste;
            String sql = "select count(*) from evenement where titre like '%"+nom+"%' and date_event='"+dateF+"'";
            ste = conn.prepareStatement(sql);
            ResultSet rs = ste.executeQuery(sql);
            ObservableList<String> list=FXCollections.observableArrayList();  
            while(rs.next()){
                if (Integer.parseInt(rs.getString(1))%8==0){
                    for (int i=1;i<Integer.parseInt(rs.getString(1))/8+1;i++)
                    {
                        list.add(Integer.toString(i));
                    }
                }
                else {
                    for (int i=1;i<Integer.parseInt(rs.getString(1))/8+2;i++)
                    {
                        
                        list.add(Integer.toString(i));
                    }
                }
            }
            comboPage.setItems(list);
            rs.close();
            comboPage.setValue("1");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Pane> panes=new ArrayList<>();
            panes.add(pane0);panes.add(pane1);panes.add(pane2);panes.add(pane3);panes.add(pane4);panes.add(pane5);panes.add(pane6);panes.add(pane7);
            List<ImageView> imageEvent=new ArrayList<>();
            imageEvent.add(imageEventAff0);imageEvent.add(imageEventAff1);imageEvent.add(imageEventAff2);imageEvent.add(imageEventAff3);imageEvent.add(imageEventAff4);imageEvent.add(imageEventAff5);imageEvent.add(imageEventAff6);imageEvent.add(imageEventAff7);
            
            
            for (Pane p : panes){
                p.setVisible(false);
            }
        ServiceEvenementAdmin p=new ServiceEvenementAdmin();
            Connection conn = p.con;
            PreparedStatement ste;
            
            String sql=null;
            sql = "select categorie,titre,pour,date_event,lieu,image,description,capacite,tarif,id_event,date_ajout from evenement where titre like '%"+nom+"%' and date_event='"+dateF+"'";
            ste = conn.prepareStatement(sql);
            System.out.println(ste);
            ResultSet rs = ste.executeQuery(sql);
            int i=0;
            while(rs.next()){
                panes.get(i).setVisible(true);
                InputStream stream2 = new FileInputStream("C:/xampp/htdocs/Clinique/image evenement/0.jpg");
                                
                Image image2 = new Image(stream2);
                File folder = new File("C:/xampp/htdocs/Clinique/image evenement/"); 
                File[] listOfFiles = folder.listFiles(); 
                int existe = 0 ;
                for (File file : listOfFiles) { 
                    if (file.isFile()) { 
                        if (file.getName().equals(rs.getString(5)+rs.getString(6))){
                            InputStream stream = new FileInputStream("C:/xampp/htdocs/Clinique/image evenement/"+rs.getString(5)+rs.getString(6));
                            Image image = new Image(stream);
                            imageEvent.get(i).setImage(image);
                            existe=1;
                        } 
                    } 
                } 
                if (existe == 0){      
                    imageEvent.get(i).setImage(image2);
                }

                
                ((Label)panes.get(i).getChildren().get(1)).setText(rs.getString(2));
                ((Label)panes.get(i).getChildren().get(2)).setText(rs.getString(4));
                ((Label)panes.get(i).getChildren().get(3)).setText(rs.getString(1));
                ((Label)panes.get(i).getChildren().get(4)).setText(rs.getString(3));
                ((Label)panes.get(i).getChildren().get(7)).setText(rs.getString(10));
                
                Date dateAjout=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(11)); 
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date in = new Date();
                LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
                ZonedDateTime zdt = ldt.minusDays(1).atZone(ZoneId.systemDefault());
                Date output = Date.from(zdt.toInstant());
                System.out.println(dateAjout.compareTo(output));
                if (dateAjout.compareTo(output)>0){
                    ((Label)panes.get(i).getChildren().get(6)).setVisible(true);
                }
                else {
                    ((Label)panes.get(i).getChildren().get(6)).setVisible(false);
                }
                
                i++;
            }
            rs.close();
            
    }

    @FXML
    private void OpenParticiperEventInterface(ActionEvent event) throws IOException {
        /*paneDetailEvent.setVisible(false);
        pane0.setScaleX(1.0);
        pane0.setScaleY(1.0);
        pane1.setScaleX(1.0);
        pane1.setScaleY(1.0);
        pane2.setScaleX(1.0);
        pane2.setScaleY(1.0);
        pane3.setScaleX(1.0);
        pane3.setScaleY(1.0);
        pane4.setScaleX(1.0);
        pane4.setScaleY(1.0);
        pane5.setScaleX(1.0);
        pane5.setScaleY(1.0);
        pane6.setScaleX(1.0);
        pane6.setScaleY(1.0);
        pane7.setScaleX(1.0);
        pane7.setScaleY(1.0);
        pane0.setEffect(null);
        pane1.setEffect(null);
        pane2.setEffect(null);
        pane3.setEffect(null);
        pane4.setEffect(null);
        pane5.setEffect(null);
        pane6.setEffect(null);
        pane7.setEffect(null);*/
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ParticiperEventUser.fxml"));
        
        Parent root = loader.load();
        Scene scene = new Scene(root,587, 506);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);  
     primaryStage.setTitle("Réservez un événement");  
     primaryStage.centerOnScreen();
     primaryStage.setResizable(false);
     primaryStage.setOpacity(1);
     ParticiperEventUserController pdc = loader.getController();
     Evenement e = null;
        try {
            e = eas.searchEvent(idMemoriser);
            System.out.println(e.getDate_event().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        } catch (SQLException ex) {
            Logger.getLogger(EvenementAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(EvenementAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pdc.setDate(e.getDate_event().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        pdc.setTarif(String.valueOf(e.getTarif()));
        pdc.setTotal(String.valueOf(e.getTarif()));
       primaryStage.show();
        
    }

    @FXML
    private void CancelParticipationBTN(ActionEvent event) throws SQLException, FileNotFoundException {
        ServiceParticiperEventUser cs = new ServiceParticiperEventUser();
            if(idMemoriser==0)
            {
                Alert alert1 = new Alert(Alert.AlertType.WARNING);
               alert1.setTitle("Alerte");
               alert1.setHeaderText("Séléctionner un événement");
               alert1.setContentText("Séléctionner un événement à supprimer puis cliquez sur supprimer");
               
               alert1.showAndWait(); 
            }
            else
            {
                
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confimer annulation");
            alert.setHeaderText("Voulez vous annuler cette réservation ?");
            alert.setContentText("Confirmez la annulation");
            Optional<ButtonType> btn = alert.showAndWait();
            if(btn.get()==ButtonType.OK)
            {
                
                
                cs.AnnulerReservation(idMemoriser,tache_user.gui.AuthentificationController.getIdConnecter());
                AffichageDetail(idMemoriser);
                
            }
            else {alert.close();}
            }
    }

    @FXML
    private void AffichageTous(ActionEvent event) throws FileNotFoundException, ParseException {
        remplirComboPage();
        affichageEvenement();
        dateEventFiltrer.setValue(null);
        rechercherEntryEvent.setText("");
        pane0.setEffect(null);
        pane1.setEffect(null);
                
        pane2.setEffect(null);
        pane3.setEffect(null);
        pane4.setEffect(null);
        pane5.setEffect(null);
        pane6.setEffect(null);
        pane7.setEffect(null);
        pane0.setScaleX(1.0);
        pane0.setScaleY(1.0);
        pane1.setScaleX(1.0);
        pane1.setScaleY(1.0);
        pane2.setScaleX(1.0);
        pane2.setScaleY(1.0);
        pane3.setScaleX(1.0);
        pane3.setScaleY(1.0);
        pane4.setScaleX(1.0);
        pane4.setScaleY(1.0);
        pane5.setScaleX(1.0);
        pane5.setScaleY(1.0);
        pane6.setScaleX(1.0);
        pane6.setScaleY(1.0);
        pane7.setScaleX(1.0);
        pane7.setScaleY(1.0);
        paneDetailEvent.setVisible(false);
    }

    @FXML
    private void AffichageMesParticipation(ActionEvent event) throws FileNotFoundException, ParseException {
        try {
            ServiceEvenementAdmin p=new ServiceEvenementAdmin();
            Connection conn = p.con;
            PreparedStatement ste;
            String sql = "select count(*) from reservation_evenement r join evenement e on e.id_event = r.id_event where r.id_user ="+tache_user.gui.AuthentificationController.getIdConnecter()+" ";
            ste = conn.prepareStatement(sql);
            ResultSet rs = ste.executeQuery(sql);
            ObservableList<String> list=FXCollections.observableArrayList();  
            while(rs.next()){
                if (Integer.parseInt(rs.getString(1))%8==0){
                    for (int i=1;i<Integer.parseInt(rs.getString(1))/8+1;i++)
                    {
                        list.add(Integer.toString(i));
                    }
                }
                else {
                    for (int i=1;i<Integer.parseInt(rs.getString(1))/8+2;i++)
                    {
                        
                        list.add(Integer.toString(i));
                    }
                }
            }
            comboPage.setItems(list);
            rs.close();
            comboPage.setValue("1");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            List<Pane> panes=new ArrayList<>();
            panes.add(pane0);panes.add(pane1);panes.add(pane2);panes.add(pane3);panes.add(pane4);panes.add(pane5);panes.add(pane6);panes.add(pane7);
            List<ImageView> imageEvent=new ArrayList<>();
            imageEvent.add(imageEventAff0);imageEvent.add(imageEventAff1);imageEvent.add(imageEventAff2);imageEvent.add(imageEventAff3);imageEvent.add(imageEventAff4);imageEvent.add(imageEventAff5);imageEvent.add(imageEventAff6);imageEvent.add(imageEventAff7);
            
            
            for (Pane p : panes){
                p.setVisible(false);
            }
            ServiceEvenementAdmin p=new ServiceEvenementAdmin();
            Connection conn = p.con;
            PreparedStatement ste;
            int a=(Integer.parseInt(comboPage.getValue())-1)*8;
            int b=Integer.parseInt(comboPage.getValue())*8;
            System.out.println("a="+a+"     b="+b);
            String sql=null;
            sql = "select e.categorie,e.titre,e.pour,e.date_event,e.id_event,e.image,e.date_ajout from reservation_evenement r join evenement e on e.id_event = r.id_event where r.id_user ="+tache_user.gui.AuthentificationController.getIdConnecter()+" order by e.date_ajout "+filterEtat+" limit "+a+","+b;
            ste = conn.prepareStatement(sql);
            System.out.println(ste);
            ResultSet rs = ste.executeQuery(sql);
            int i=0;
            while(rs.next()){
                panes.get(i).setVisible(true);
                InputStream stream2 = new FileInputStream("C:/xampp/htdocs/Clinique/image evenement/0.jpg");
                                
                Image image2 = new Image(stream2);
                File folder = new File("C:/xampp/htdocs/Clinique/image evenement/"); 
                File[] listOfFiles = folder.listFiles(); 
                int existe = 0 ;
                for (File file : listOfFiles) { 
                    if (file.isFile()) { 
                        if (file.getName().equals(rs.getString(5)+rs.getString(6))){
                            InputStream stream = new FileInputStream("C:/xampp/htdocs/Clinique/image evenement/"+rs.getString(5)+rs.getString(6));
                            Image image = new Image(stream);
                            imageEvent.get(i).setImage(image);
                            existe=1;
                        } 
                    } 
                } 
                if (existe == 0){      
                    imageEvent.get(i).setImage(image2);
                }

                
                ((Label)panes.get(i).getChildren().get(1)).setText(rs.getString(2));
                ((Label)panes.get(i).getChildren().get(2)).setText(rs.getString(4));
                ((Label)panes.get(i).getChildren().get(3)).setText(rs.getString(1));
                ((Label)panes.get(i).getChildren().get(4)).setText(rs.getString(3));
                ((Label)panes.get(i).getChildren().get(7)).setText(rs.getString(5));
                
                Date dateAjout=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString(7)); 
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date in = new Date();
                LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
                ZonedDateTime zdt = ldt.minusDays(1).atZone(ZoneId.systemDefault());
                Date output = Date.from(zdt.toInstant());
                System.out.println(dateAjout.compareTo(output));
                if (dateAjout.compareTo(output)>0){
                    ((Label)panes.get(i).getChildren().get(6)).setVisible(true);
                }
                else {
                    ((Label)panes.get(i).getChildren().get(6)).setVisible(false);
                }
                
                i++;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenementAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

}
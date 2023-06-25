/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_user.gui;

import tache_user.entities.User;
import tache_user.service.UserCRUD;
import utils.MyConnexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class NewPwdController implements Initializable {

    @FXML
    private Button newpasswordbutton;
    
    Connection cnx = MyConnexion.getInstance().getConnection();
    @FXML
    private TextField txtnew;
    @FXML
    private TextField txtconfirm;
    @FXML
    private TextField txtemail1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void ModifierMdp(String email, String pwd) {
     try{
        String req = "UPDATE user SET mdp_user=? WHERE email_user=?";
        PreparedStatement pst = cnx.prepareStatement(req);
            
            pst.setString(1, pwd);
            pst.setString(2, email);
            pst.executeUpdate();
            System.out.println("The password was updated successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @FXML
    private void NewPwd(ActionEvent event) {
        String new_pwd, confirm_pwd, email;
        new_pwd=txtnew.getText();
        confirm_pwd=txtconfirm.getText();
        email=txtemail1.getText();
        
        
        if (new_pwd==null || confirm_pwd ==null)      
        {   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Missing Informations");
            alert.show();
        }
        else if (new_pwd.equals(confirm_pwd)==false)      
        {   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter the same password to confirm");
            alert.show();
        }else {
            ModifierMdp(email,new_pwd);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DONE");
            alert.setHeaderText("PASSWORD UPDATED");
            alert.setContentText("Reconnect with your new password");
            alert.show();
        }
        
    }

   
    
    
    
    
}

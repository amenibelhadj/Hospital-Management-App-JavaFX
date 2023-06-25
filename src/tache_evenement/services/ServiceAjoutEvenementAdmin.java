/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_evenement.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnexion;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import tache_evenement.model.Evenement;
/**
 *
 * @author hama
 */
public class ServiceAjoutEvenementAdmin {
    private Connection con;
    private Statement ste;

    public ServiceAjoutEvenementAdmin() {
  MyConnexion db=new MyConnexion();
        con = db.getConnection();
    }

    public int ajouter1(Evenement e ,boolean sendMail) throws SQLException, MessagingException {
        PreparedStatement pre = null;
        try {
           
            pre = con.prepareStatement("INSERT INTO `evenement` (  `categorie`,`titre`,`description`,`lieu`,`date_event`,`image`,`tarif`,`capacite`,`nb_reservation`,`etat`,`pour`) VALUES ( ?, ?, ?, ?, ?, ?,?, ?, ?,?,?);",Statement.RETURN_GENERATED_KEYS);
            pre.setString(1, e.getCategorie());
            pre.setString(2, e.getTitre());
            pre.setString(3, e.getDescription());
            pre.setString(4, e.getLieu());
            pre.setDate(5, new java.sql.Date(e.getDate_event().getTime()));
            pre.setString(6, e.getImage());
            pre.setFloat(7, e.getTarif());
            pre.setInt(8, e.getCapacite());
            pre.setInt(9, e.getNb_reservation());
            pre.setString(10, "en cours");
            pre.setString(11, e.getPour());
            /*SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            System.out.println(formatter.format(date));
            pre.setDate(12, formatter.format(date));*/
    pre.executeUpdate();
    
    if (sendMail){
        
        
        Connection con;
        Statement ste;
        MyConnexion db=new MyConnexion();
        con = db.getConnection();
            String sql=null;
            sql = "select email_user ,nom_user,prenom_user from user where role_user='medecin' or role_user='infirmier'";
            ste = con.prepareStatement(sql);
            System.out.println(ste);
            ResultSet rs = ste.executeQuery(sql);

            while(rs.next()){
                envoyer(rs.getString("email_user"),rs.getString("nom_user")+" "+rs.getString("prenom_user"),e);
            }
            rs.close();
    }
        System.out.println("evenement ajouté");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("problem");
        }
        try (ResultSet generatedKeys =pre.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                System.out.println(generatedKeys.getInt(1));
                return generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
        
        
    }

    public void Modifier1(Evenement e,int id) {
PreparedStatement pre;
        try {
           
            String requete = "update evenement set categorie=?,titre=?,description=?,lieu=?,date_event=?,image=?,tarif=?,capacite=?,pour=? where ? = id_event";
            pre = con.prepareStatement(requete);
            pre.setString(1, e.getCategorie());
            pre.setString(2, e.getTitre());
            pre.setString(3, e.getDescription());
            pre.setString(4, e.getLieu());
            pre.setDate(5, new java.sql.Date(e.getDate_event().getTime()));
            pre.setString(6,e.getImage());
            pre.setFloat(7,e.getTarif());
            
            pre.setInt(8, e.getCapacite());
            pre.setString(9,e.getPour());
            pre.setInt(10,id);
            

    pre.executeUpdate();
        System.out.println("evenement modifié");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("problem");
        }    }

    public void SupprimerEvenement(int idMemoriser) {
        try {
            String requete = "delete from evenement where ? = id_event";
            PreparedStatement pst = con.prepareStatement(requete);
            pst.setInt(1, idMemoriser);
            pst.executeUpdate();
            System.out.println("Evenement Deleted !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void envoyer(String destinataire,String nomUser, Evenement e) throws MessagingException {
            
            String username = "chebbi.mohamed1@esprit.tn";
            String password ="213JMT4559";
            System.out.println("Entrain d'envoyer un email de vérification !! ");
            // Etape 1 : Création de la session
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.port","587");
            
            Session session = Session.getInstance(props,new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);}
            });
            
            Message message = prepareMessage(session,username,destinataire,e,nomUser);
            Transport.send(message);
            System.out.println("Message envoyé !!");
}
        
    

    private static Message prepareMessage(Session session, String username,String destinataire, Evenement e,String nomUser) throws MessagingException {
        
        try { 
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(destinataire));
            message.setSubject("Nouveau evenement !!");
            
            message.setText("Chers "+nomUser+", \nTitre evenement : "+e.getTitre()+"\nCategorie : "+e.getCategorie()+"\nLieu : "+e.getLieu()+"\nDédier pour : "+e.getPour()+"\nDate événement : "+e.getDate_event()+"\nDescription : "+e.getDescription()+"\nTarif : Seulement "+e.getTarif()+" Dinar\n");
            return message;
        } catch (AddressException ex) {
            Logger.getLogger(ServiceAjoutEvenementAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}

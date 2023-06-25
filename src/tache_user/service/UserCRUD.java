/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_user.service;

import tache_user.entities.User;
import utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ameni
 */
public class UserCRUD implements InterfaceUser <User> {
     Connection cnx = MyConnexion.getInstance().getConnection();

    @Override
    public void ajouterUser(User u) {
        try{
            String req ="INSERT INTO user (nom_user, prenom_user, cin_user, email_user, role_user, mdp_user) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            
            pst.setString(1, u.getNom_user());
            pst.setString(2, u.getPrenom_user());
            pst.setString(3, u.getCin_user());
            pst.setString(4, u.getEmail_user());
            pst.setString(5, u.getRole_user());
            pst.setString(6, u.getMdp_user());
            
            
            pst.executeUpdate();
            System.out.println("A user was added successfully!");
            
        }catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifierUser(User u) {
        try{
        String req = "UPDATE user SET nom_user=?,prenom_user=?,cin_user=?,email_user=?,role_user=?,mdp_user=? WHERE id_user=?";
        PreparedStatement pst = cnx.prepareStatement(req);
            
            pst.setString(1, u.getNom_user());
            pst.setString(2, u.getPrenom_user());
            pst.setString(3, u.getCin_user());
            pst.setString(4, u.getEmail_user());
            pst.setString(5, u.getRole_user());
            pst.setString(6, u.getMdp_user());
            pst.setInt(7, u.getId_user());
            
            pst.executeUpdate();
            System.out.println("A user was updated successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerUser(User u) {
        try{
            String req = "DELETE FROM user WHERE id_user=?" ;
            Statement st;
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, u.getId_user());
            pst.executeUpdate();
            System.out.println("A user was deleted successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<User> afficherUser() {
        List <User> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `user`";
            Statement st;
            st =cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                User u = new User (rs.getInt("id_user"), rs.getString("nom_user"),rs.getString("prenom_user"),
                        rs.getString("cin_user"),rs.getString("email_user"),rs.getString("role_user"),rs.getString("mdp_user"));
                list.add(u);
            }
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
    
    /*public void table_affiche(){
        Connection cnx = MyConnexion.getInstance().getConnection();
        ObservableList<User> users = FXCollections.observableArrayList();
    
        try {
            String req = "SELECT * FROM `user`";
            Statement st;
            st =cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            {
                while(rs.next()){
                User u = new User();
                u.setId_user(rs.getInt("id_user"));
                u.setNom_user(rs.getString("nom_user"));
                u.setPrenom_user(rs.getString("prenom_user"));
                u.setCin_user(rs.getString("cin_user"));
                u.setEmail_user(rs.getString("email_user"));
                u.setRole_user(rs.getString("role_user"));
                users.add(u);}
            }   
            
            IdColumn.setCellValueFactory(new PropertyValueFactory<User,String>("id_user"));
            NomColumn.setCellValueFactory(new PropertyValueFactory<User,String>("nom_user"));
            PrenomColumn.setCellValueFactory(new PropertyValueFactory<User,String>("prenom_user"));
            CinColumn.setCellValueFactory(new PropertyValueFactory<User,String>("cin_user"));
            EmailColumn.setCellValueFactory(new PropertyValueFactory<User,String>("email_user"));
            RoleColumn.setCellValueFactory(new PropertyValueFactory<User,String>("role_user"));
            table.setItems(users);
            
        }
        catch (SQLException ex){
            System.err.println(ex.getMessage());
        }
    }*/
}
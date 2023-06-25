/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hama
 */
public class MyConnexion {
    String url = "jdbc:mysql://localhost:3306/projet_pi";
    final static String USERNAME="root";
    final static String PWD="";
    //att
    private Connection cnx;
    //singleton#1
    static MyConnexion instance= null;
    
    public MyConnexion(){
        try { 
            cnx = DriverManager.getConnection(url,USERNAME,PWD);
            System.out.println("Connexion etablie !! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public static MyConnexion getInstance(){
        if (instance==null)
            instance = new MyConnexion ();
        return instance;
    }
    public Connection getConnection(){
        return cnx;
    
    }
}

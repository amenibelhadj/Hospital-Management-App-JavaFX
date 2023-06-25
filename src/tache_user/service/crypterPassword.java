/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_user.service;

import utils.MyConnexion;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Ameni
 */
public class crypterPassword {
    
    public Connection cnx;
    private Statement ste;
    public crypterPassword() {
	cnx = MyConnexion.getInstance().getConnection();
        MyConnexion db=new MyConnexion();
        cnx = db.getConnection();
        
        
   
    }
    
    public String crypterPassword(String password) {
	String hashValue = "";
	try {

	    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	    messageDigest.update(password.getBytes());
	    byte[] digestedBytes = messageDigest.digest();
	    hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();

	} catch (NoSuchAlgorithmException e) {
	}

	return hashValue;
    }
    
}

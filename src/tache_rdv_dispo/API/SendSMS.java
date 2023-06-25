/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tache_rdv_dispo.API;


import tache_rdv_dispo.Entities.RDV;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author user
 */
 public class SendSMS {

    public static final String ACCOUNT_SID = System.getenv("AC5a499c1cd5135a3bcd8027d026387af4");
    public static final String AUTH_TOKEN = System.getenv("3b372fff79edc62a8ad2428f0c3c8cc6");

    public static void SendSMS(RDV r) {
        Twilio.init("AC5a499c1cd5135a3bcd8027d026387af4", "3b372fff79edc62a8ad2428f0c3c8cc6");
        Message message = Message.creator(new PhoneNumber("+21654017791"),
                new PhoneNumber("+13464895283"),
                "RDV ajouté: 1ere visite: "+r.getPrv()+" Nom_medecin: "+r.getNom_med()+" Date: "+r.getDate()+" heure: "+r.getHeure()).create();


        System.out.println(message.getSid());
    }

}

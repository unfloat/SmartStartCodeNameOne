/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Note;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceNote {

    public void createNote(Note ta) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/foobar10/web/app_dev.php/note/notes/newNote?priority="+ta.getPriority()+"&noteText="+ta.getNoteText()+"&noteName="+ta.getNoteName();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }

   
    
    public ArrayList<Note> getListNotes(String json) throws ParseException {
        ArrayList<Note> notes = new ArrayList<>();
        System.out.println("JSON*************\n" + json);
        try {

            JSONParser j = new JSONParser();

            Map<String, Object> chambres = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) chambres.get("root");

            for (Map<String, Object> obj : list) {
                Note n = new Note(); //id, json, status);

                float id = Float.parseFloat(obj.get("id").toString());         
                n.setId((int) id);
                String noteName= obj.get("noteName").toString();
                n.setNoteName(noteName);
                String noteText= obj.get("noteText").toString();
                n.setNoteText(noteText);
                String priority= obj.get("priority").toString();
                n.setPriority(priority);
                
                notes.add(n);
            }
        } catch (IOException ex) {
        }
        System.out.println(notes);
        return notes;

    }

}

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
import com.mycompany.Entite.Bookmark;
import com.mycompany.Entite.Job;
import com.mycompany.Entite.Project;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class BookmarkService {

    public void addBookmark(Job j) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/mySmartStartSymphony/web/app_dev.php/api/bookmark/add/1/1";
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueue(con);
        System.out.println("yes!");
    }

    public ArrayList<Bookmark> displayBookmarks() {
        ArrayList<Bookmark> listBookmark = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setHttpMethod("GET");
        con.setUrl("http://localhost/mySmartStartSymphony/web/app_dev.php/api/bookmark/all/1");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonParser = new JSONParser();

                try {
                    Map<String, Object> bookmarks = jsonParser.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) bookmarks.get("root");
                    //List projectData = (List) bids.get("project");
                    //Parcourir la liste des tâches Json
                    for (Map<String, Object> obj : list) {
                        //Création des tâches et récupération de leurs données
                        Bookmark bookmark = new Bookmark();
                        float id = Float.parseFloat(obj.get("id").toString());

                        Map<String, Double> date = (Map<String, Double>) obj.get("dateAdded");
                        long l = (long) (date.get("timestamp") * 1000);
                        long t = (long) l * 10000;
                        Date bookmarkDate = new Date(l);
                        Map<String, Object> project = (Map<String, Object>) obj.get("project");
                        Project bookmarkProject = new Project();
                        String projectName = (String) project.get("projectName");

//                        float projectMinBudget = Float.parseFloat(project.get("minBudget").toString());
//                        float projectMaxBudget = Float.parseFloat(project.get("maxBudget").toString());
                        bookmark.setId((int) id);
                        bookmark.setDateAdded(bookmarkDate);
                        bookmarkProject.setProjectName(projectName);

                        bookmark.setProject(bookmarkProject);

                        listBookmark.add(bookmark);
                        //System.out.println(bid.getProject().toString());
                    }

                } catch (IOException ex) {
                }

            }
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(con);

        return listBookmark;
    }



    public void updateBookmark(Bookmark bookmark, int x) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/mySmartStartSymphony/web/app_dev.php/api/bookmark/edit/1";
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueue(con);
        System.out.println("yes!");
    }

    public void deleteBookmark(int x) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/mySmartStartSymphony/web/app_dev.php/api/bookmark/delete/" + x;
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueue(con);
        System.out.println("yes!");
    }
}

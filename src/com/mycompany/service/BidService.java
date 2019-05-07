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
import com.mycompany.Entite.Bid;
import com.mycompany.Entite.Project;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class BidService {

    public void addBid(Bid bid) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/mySmartStartSymphony/web/app_dev.php/api/bid/place/" + bid.getDeliveryTime() + "/"
                + bid.getMinimalRate() + "/1/1";
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueue(con);
        System.out.println("yes!");
    }

    public ArrayList<Bid> displayBids() {
        ArrayList<Bid> listBids = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setHttpMethod("GET");
        con.setUrl("http://localhost/mySmartStartSymphony/web/app_dev.php/api/bid/all/1");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonParser = new JSONParser();

                try {
                    Map<String, Object> bids = jsonParser.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) bids.get("root");
                    //List projectData = (List) bids.get("project");
                    //Parcourir la liste des tâches Json
                    for (Map<String, Object> obj : list) {
                        //Création des tâches et récupération de leurs données
                        Bid bid = new Bid();
                        float id = Float.parseFloat(obj.get("id").toString());
                        float deliveryTime = Float.parseFloat(obj.get("deliveryTime").toString());
                        float minimalRate = Float.parseFloat(obj.get("minimalRate").toString());

                        Map<String, Object> project = (Map<String, Object>) obj.get("project");
                        Project bidsProject = new Project();
                            String projectName = (String) project.get("projectName");

                            float projectMinBudget = Float.parseFloat(project.get("minBudget").toString());
                            float projectMaxBudget = Float.parseFloat(project.get("maxBudget").toString());

                        bid.setId((int) id);
                        bid.setDeliveryTime((int) deliveryTime);
                        bid.setMinimalRate((int) minimalRate);
                        bidsProject.setProjectName(projectName);
                        bidsProject.setMinBudget((int) projectMinBudget);
                        bidsProject.setMaxBudget((int) projectMaxBudget);
                        bid.setProject(bidsProject);
                        

                        listBids.add(bid);
                        //System.out.println(bid.getProject().toString());
                    }
                    
                } catch (IOException ex) {
                }

            }
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(con);

        return listBids;
    }

    public void updateBid(Bid bid, int x) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/mySmartStartSymphony/web/app_dev.php/api/bid/edit/"
                + x + "/"
                + bid.getDeliveryTime() + "/"
                + bid.getMinimalRate();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueue(con);
        System.out.println("yes!");
    }

    public void deleteBid(int x) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/mySmartStartSymphony/web/app_dev.php/api/bid/delete/"
                + x;
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueue(con);
        System.out.println("yes!");
    }
}

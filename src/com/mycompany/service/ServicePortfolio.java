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
import com.mycompany.Entite.Portfolio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author asus
 */
public class ServicePortfolio {

public void addPortfolio(Portfolio p) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/FooBar/web/app_dev.php/portfolio/new?" + "skills=" + p.getSkills() + "&description=" + p.getDescription()+ "&previousExperiences=" + p.getPreviousExperiences();
        con.setUrl(Url);

         con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueue(con);
        System.out.println("yes!");
    }

    public ArrayList<Portfolio> getList2() {
        ArrayList<Portfolio> listPort = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/FooBar/web/app_dev.php/portfolio/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> jobs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(jobs);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) jobs.get("root");
                    for (Map<String, Object> obj : list) {
                        Portfolio p=new Portfolio();
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        p.setId((int) id);
                        //job.setEmployer_id((int) obj.get("employer"));
                        p.setDescription(obj.get("description").toString());
                        p.setSkills(obj.get("skills").toString());
                        p.setPreviousExperiences(obj.get("previousExperiences").toString());
                       

                        listPort.add(p);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPort;
    }
    
    
    public void updateJob(Portfolio p,int x) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/FooBar/web/app_dev.php/portfolio/update/"+x+"skills=" + p.getSkills() + "&description=" + p.getDescription()+ "&previousExperiences=" + p.getPreviousExperiences();
        con.setUrl(Url);

         con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueue(con);
        System.out.println("yes!");
    }
    
    
    
    

}


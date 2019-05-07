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
import com.mycompany.Entite.Job;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class ServiceJob {

    public void addJob(Job j) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/FooBar/web/app_dev.php/api/new?" + "title=" + j.getTitre() + "&type=" + j.getType()+ "&location=" + j.getLocation() + "&maxSalary=" + j.getMaxSal() + "&minSalary=" + j.getMinSal() + "&description=" + j.getDescription();
        con.setUrl(Url);

         con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueue(con);
        System.out.println("yes!");
    }

    public ArrayList<Job> getList2() {
        ArrayList<Job> listJobs = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/FooBar/web/app_dev.php/api/all");
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
                        Job job = new Job();
                        float id = Float.parseFloat(obj.get("id").toString());
                        float minSal=Float.parseFloat(obj.get("minSalary").toString());
                        float maxSal=Float.parseFloat(obj.get("maxSalary").toString());
                        job.setId((int) id);
                        //job.setEmployer_id((int) obj.get("employer"));
                        job.setTitre(obj.get("title").toString());
                        job.setType(obj.get("type").toString());
                        job.setLocation(obj.get("location").toString());
                        job.setMinSal((double) minSal);
                        job.setMaxSal((double) maxSal);
                        job.setDescription(obj.get("description").toString());

                        listJobs.add(job);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listJobs;
    }
    
    
    public void updateJob(Job j,int x) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/FooBar/web/app_dev.php/api/update/"+x+"?title=" + j.getTitre() + "&type=" + j.getType()+ "&location=" + j.getLocation() + "&maxSalary=" + j.getMaxSal() + "&minSalary=" + j.getMinSal() + "&description=" + j.getDescription();
        con.setUrl(Url);

         con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueue(con);
        System.out.println("yes!");
    }
    
    
    public void deleteJob(int x) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/FooBar/web/app_dev.php/api/remove/"+x;
        con.setUrl(Url);

         con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueue(con);
        System.out.println("yes!");
    }
    
    
    

}

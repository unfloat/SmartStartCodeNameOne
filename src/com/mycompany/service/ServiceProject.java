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
import com.mycompany.Entite.Project;
import com.mycompany.Entite.ProjectF;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class ServiceProject {

    public void createProject(ProjectF ta) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/foobar10/web/app_dev.php/task/projects/newProject?projectName=" + ta.getProjectName() + "&projectLocation=" + ta.getProjectLocation() + "&minBudget=" + ta.getMinBudget() + "&maxBudget=" + ta.getMaxBudget() + "&projectDescription=" + ta.getProjectDescription() + "&address=fatma@gmail.com&idSkill=4&idCategory=2&projectEndDay=" + ta.getProjectEndDay();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }

    public ArrayList<Project> getListProjects(String json) throws ParseException {
        ArrayList<Project> projects = new ArrayList<>();
        try {

            JSONParser j = new JSONParser();

            Map<String, Object> chambres = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) chambres.get("root");

            for (Map<String, Object> obj : list) {
                Project p = new Project();

                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int) id);
                String projectName = obj.get("projectName").toString();
                p.setProjectName(projectName);
                String projectDescription = obj.get("projectDescription").toString();
                p.setProjectDescription(projectDescription);
                p.setProjectLocation(obj.get("projectLocation").toString());
                float projectMinBudget = Float.parseFloat(obj.get("minBudget").toString());
                float projectMaxBudget = Float.parseFloat(obj.get("maxBudget").toString());
                p.setMinBudget((int) projectMinBudget);
                p.setMaxBudget((int) projectMaxBudget);

                projects.add(p);
            }
        } catch (IOException ex) {
        }
        System.out.println(projects);
        return projects;

    }

    public ArrayList<Project> parseListProjectJson(String json) {

        ArrayList<Project> listProjects = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                Project p = new Project();
//                float id = Float.parseFloat(obj.get("id").toString());         
//                p.setId((int) id);
                String projectName = obj.get("projectName").toString();
                p.setProjectName(projectName);
                String projectDescription = obj.get("projectDescription").toString();
                p.setProjectDescription(projectDescription);
                p.setProjectLocation(obj.get("projectLocation").toString());
                Double MinBudget = Double.parseDouble(obj.get("minBudget").toString());
                Double MaxBudget = Double.parseDouble(obj.get("maxBudget").toString());
                //p.setMaxBudget(MaxBudget);
                //p.setMinBudget(MinBudget);

                listProjects.add(p);
            }

        } catch (IOException ex) {
        }

        System.out.println(listProjects);
        return listProjects;

    }

    ArrayList<Project> al = new ArrayList<>();
//    String a;

    public ArrayList<Project> searchProjects(String description) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/mySmartStartSymfony/web/app_dev.php/bid/project/description/" + description);// création de l'URL

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceProject ser = new ServiceProject();
//                String str = new String(con.getResponseData());

                al = ser.parseListProjectJson(new String(con.getResponseData()));

                System.out.print(al);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(con);
        return al;

    }

}

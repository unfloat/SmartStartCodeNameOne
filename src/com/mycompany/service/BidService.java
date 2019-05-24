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
import com.mycompany.Entite.FosUser;
import com.mycompany.Entite.Project;
import com.mycompany.Entite.ProjectF;
import java.io.IOException;
import java.util.ArrayList;
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

    public Project getBidById(int project_id) {
        ConnectionRequest con = new ConnectionRequest();
        Project project = new Project();

        con.setHttpMethod("GET");
        con.setUrl("http://localhost/mySmartStartSymphony/web/app_dev.php/api/bid/project/" + project_id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonParser = new JSONParser();

                try {
                    Map<String, Object> projects = jsonParser.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) projects.get("root");
                    //List projectData = (List) bids.get("project");
                    //Parcourir la liste des tâches Json
                    for (Map<String, Object> obj : list) {
                        //Création des tâches et récupération de leurs données
                        System.out.println("here" + obj.toString() + "\n" + list.toString());
                        float id = Float.parseFloat(obj.get("id").toString());

                        project.setId((int) id);
                        project.setProjectName(obj.get("projectName").toString());
                        String projectDescription = obj.get("projectDescription").toString();
                        project.setProjectDescription(projectDescription);
                        project.setProjectLocation(obj.get("projectLocation").toString());
                        Double MinBudget = Double.parseDouble(obj.get("minBudget").toString());
                        Double MaxBudget = Double.parseDouble(obj.get("maxBudget").toString());
                        project.setProjectName(obj.get("projectName").toString());
                        System.out.println(project);

                    }

                } catch (IOException ex) {
                }

            }
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(con);

        return project;
    }

    public Project getProjectById(int project_id) {
        ConnectionRequest con = new ConnectionRequest();
        Project project = new Project();

        con.setHttpMethod("GET");
        con.setUrl("http://localhost/mySmartStartSymphony/web/app_dev.php/api/bid/project/" + project_id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonParser = new JSONParser();

                try {
                    Map<String, Object> projects = jsonParser.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) projects.get("root");
                    //List projectData = (List) bids.get("project");
                    //Parcourir la liste des tâches Json
                    for (Map<String, Object> obj : list) {
                        //Création des tâches et récupération de leurs données
                        System.out.println("here" + obj.toString() + "\n" + list.toString());
                        float id = Float.parseFloat(obj.get("id").toString());

                        project.setId((int) id);
                        project.setProjectName(obj.get("projectName").toString());
                        String projectDescription = obj.get("projectDescription").toString();
                        project.setProjectDescription(projectDescription);
                        project.setProjectLocation(obj.get("projectLocation").toString());
                        Double MinBudget = Double.parseDouble(obj.get("minBudget").toString());
                        Double MaxBudget = Double.parseDouble(obj.get("maxBudget").toString());
                        project.setProjectName(obj.get("projectName").toString());
                        System.out.println(project);

                    }

                } catch (IOException ex) {
                }

            }
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(con);

        return project;
    }
    ArrayList<ProjectF> listProjects = new ArrayList<>();

    public ArrayList<ProjectF> getProject(String projectName) {
        ConnectionRequest con = new ConnectionRequest();
        ProjectF project = new ProjectF();

        con.setHttpMethod("GET");
        con.setUrl("http://localhost/mySmartStartSymphony/web/app_dev.php/api/bid/project/" + projectName);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonParser = new JSONParser();

                try {
                    Map<String, Object> projects = jsonParser.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) projects.get("root");
                    //List projectData = (List) bids.get("project");
                    //Parcourir la liste des tâches Json
                    for (Map<String, Object> obj : list) {
                        //Création des tâches et récupération de leurs données
                        System.out.println("here" + obj.toString() + "\n" + list.toString());
                        float id = Float.parseFloat(obj.get("id").toString());

                        project.setId((int) id);
                        project.setProjectName(obj.get("projectName").toString());
                        String projectDescription = obj.get("projectDescription").toString();
                        project.setProjectDescription(projectDescription);
                        project.setProjectLocation(obj.get("projectLocation").toString());
                        Double MinBudget = Double.parseDouble(obj.get("minBudget").toString());
                        Double MaxBudget = Double.parseDouble(obj.get("maxBudget").toString());
                        project.setProjectName(obj.get("projectName").toString());
                        System.out.println(project);

                        listProjects.add(project);

                    }

                } catch (IOException ex) {
                }

            }
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(con);

        return listProjects;
    }
    int numberOfBids = 0;

    public int getNumberOfCreatedBids() {
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
                        numberOfBids++;
                    }

                } catch (IOException ex) {
                }

            }
        }
        );
        NetworkManager.getInstance()
                .addToQueueAndWait(con);

        return numberOfBids;
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

    public ArrayList<Bid> displayBidders(int project_id) {
        ArrayList<Bid> listBidders = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setHttpMethod("GET");
        con.setUrl("http://localhost/mySmartStartSymphony/web/app_dev.php/api/bid/bidders/" + project_id);
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

                        Map<String, Object> freelancer = (Map<String, Object>) obj.get("freelancer");
                        FosUser bidsFreelancer = new FosUser();
                        String freelancerName = (String) freelancer.get("projectName");

                        float projectMinBudget = Float.parseFloat(project.get("minBudget").toString());
                        float projectMaxBudget = Float.parseFloat(project.get("maxBudget").toString());

                        bid.setId((int) id);
                        bid.setDeliveryTime((int) deliveryTime);
                        bid.setMinimalRate((int) minimalRate);
                        bidsProject.setProjectName(projectName);
                        bidsProject.setMinBudget((int) projectMinBudget);
                        bidsProject.setMaxBudget((int) projectMaxBudget);
                        bidsFreelancer.setUsername(freelancerName);
                        bid.setProject(bidsProject);
                        bid.setFreelancer(bidsFreelancer);

                        listBidders.add(bid);
                        //System.out.println(bid.getProject().toString());
                    }

                } catch (IOException ex) {
                }

            }
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(con);

        return listBidders;
    }

    public ArrayList<Project> getProjects() {
        ArrayList<Project> listProjects = new ArrayList<>();
        Project project = new Project();

        ConnectionRequest con = new ConnectionRequest();
        con.setHttpMethod("GET");
        con.setUrl("http://localhost/mySmartStartSymphony/web/app_dev.php/api/bid/projects");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonParser = new JSONParser();
                try {
                    Map<String, Object> projects = jsonParser.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) projects.get("root");
                    //List projectData = (List) bids.get("project");
                    //Parcourir la liste des tâches Json
                    for (Map<String, Object> obj : list) {
                        System.out.println(obj.toString());

                        //Création des tâches et récupération de leurs données
                        float id = Float.parseFloat(obj.get("id").toString());

                        project.setId((int) id);
                        project.setProjectName(obj.get("projectName").toString());
                        String projectDescription = obj.get("projectDescription").toString();
                        project.setProjectDescription(projectDescription);
                        project.setProjectLocation(obj.get("projectLocation").toString());
                        float MinBudget = Float.parseFloat(obj.get("minBudget").toString());
                        float MaxBudget = Float.parseFloat(obj.get("maxBudget").toString());
                        project.setProjectName(obj.get("projectName").toString());
                        project.setMinBudget((int) MinBudget);
                        project.setMaxBudget((int) MaxBudget);
                        System.out.println(project);

                        listProjects.add(project);
                        //System.out.println(bid.getProject().toString());
                    }

                } catch (IOException ex) {
                }

            }
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(con);

        return listProjects;
    }

    public void updateBid(Bid bid) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/mySmartStartSymphony/web/app_dev.php/api/bid/edit/"
                + bid.getId() + "/"
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

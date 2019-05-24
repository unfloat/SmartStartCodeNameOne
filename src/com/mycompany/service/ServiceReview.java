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
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Review;
import com.mycompany.gui.review.AffichageReview;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceReview {
    
//    Button btndel;
//    Form a;

    public void ajoutReview(Review r) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        int b = 0;
        int t = 0;
        if(r.getOnBudget()==true){b=1;}
        if(r.getOnTime()==true){t=1;}

        System.out.println(r.getOnBudget()+""+r.getOnTime()+""+r.getRating());
        String Url = "http://localhost/FooBar/web/app_dev.php/review/api/afficheFormApi?onBudget="+b+"&onTime="+t+"&rating="+r.getRating()+"&comment="+r.getComment();
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
        System.out.println("done adding");
    }
    
    public void deleteReview(int id) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/FooBar/web/app_dev.php/review/api/deleteReviewApi/"+id;
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
        System.out.println("done deleting");
    }
    public void updateReview(Review r,int x) {
        int b = 0;
        int t = 0;
        if(r.getOnBudget()==true){b=1;}
        if(r.getOnTime()==true){t=1;}
        System.out.println("updating..");
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/FooBar/web/app_dev.php/review/api/updateReviewApi/"+x+"?onBudget="+b+"&onTime="+t+"&rating="+r.getRating()+"&comment="+r.getComment();
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
        System.out.println("done updating");
    }
    ArrayList<Review> listReviews = new ArrayList<>();
    
    public ArrayList<Review> getList2() {
//        a= new Form("Review List");
//        btndel=new Button("Delete");
//        ArrayList<Review> listReviews = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/FooBar/web/app_dev.php/review/api/rateFreelancerApi");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listReviews = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> reviews = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println("map done");
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) reviews.get("root");
                    for (Map<String, Object> obj : list) {
                        Review e = new Review();
                        float id =Float.parseFloat(obj.get("id").toString());
                        float rating = Float.parseFloat(obj.get("rating").toString());
//                        float idE = Float.parseFloat(obj.get("employerReviewerId").toString());
//                        float idF = Float.parseFloat(obj.get("freelancerReviewedId").toString());
//                        float idP = Float.parseFloat(obj.get("projectId").toString());
                        boolean budget = Boolean.parseBoolean(obj.get("onBudget").toString());
                        boolean time = Boolean.parseBoolean(obj.get("onTime").toString());
                        
                        e.setId((int)id);
//                        e.setEmployerReviewerId((int)idE);
//                        e.setFreelancerReviewedId((int)idF);
//                        e.setProjectId((int)idP);
                        e.setOnBudget((boolean) budget);
                        e.setOnTime((boolean) time);
                        e.setRating((int) rating);
                        e.setComment(obj.get("comment").toString());
                        System.out.println(e);
                        listReviews.add(e);
                        //a.add(btndel);
//                        btndel.addActionListener((ev)->{
//                            deleteReview(e.getId());
//                            
//                            Affichage a=new Affichage();
//                            a.getF().show();
//                        });

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReviews;
    }

//    public ArrayList<Review> parseListReviewJson(String json) {
//
//        ArrayList<Review> listReviews = new ArrayList<>();
//
//        try {
//            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
//
//            /*
//                On doit convertir notre réponse texte en CharArray à fin de
//            permettre au JSONParser de la lire et la manipuler d'ou vient 
//            l'utilité de new CharArrayReader(json.toCharArray())
//            
//            La méthode parse json retourne une MAP<String,Object> ou String est 
//            la clé principale de notre résultat.
//            Dans notre cas la clé principale n'est pas définie cela ne veux pas
//            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
//            qui est root.
//            En fait c'est la clé de l'objet qui englobe la totalité des objets 
//                    c'est la clé définissant le tableau de tâches.
//            */
//            Map<String, Object> review = j.parseJSON(new CharArrayReader(json.toCharArray()));
//                       
//            
//            /* Ici on récupère l'objet contenant notre liste dans une liste 
//            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
//            */
//            List<Map<String, Object>> list = (List<Map<String, Object>>) review.get("root");
//
//            //Parcourir la liste des tâches Json
//            for (Map<String, Object> obj : list) {
//                //Création des tâches et récupération de leurs données
//                Review e = new Review();
//                
//                float id =Float.parseFloat(obj.get("id").toString());
//                float onBudget = Float.parseFloat(obj.get("onBudget").toString());
//                float onTime = Float.parseFloat(obj.get("onTime").toString());
//                float rating = Float.parseFloat(obj.get("rating").toString());
//                e.setId((int)id);
//                e.setOnBudget((int) onBudget);
//                e.setOnTime((int) onTime);
//                e.setRating((int) rating);
//                e.setComment(obj.get("comment").toString());
//                System.out.println(e);
//                
//                listReviews.add(e);
//
//            }
//
//        } catch (IOException ex) {
//        }
//        
//        /*
//            A ce niveau on a pu récupérer une liste des tâches à partir
//        de la base de données à travers un service web
//        
//        */
//        System.out.println(listReviews);
//        return listReviews;
//
//    }
//    
//    
//    ArrayList<Review> listReviews = new ArrayList<>();
//    
//    public ArrayList<Review> getList2(){       
//        ConnectionRequest con = new ConnectionRequest();
//        con.setUrl("http://localhost/FooBar/web/app_dev.php/review/api/rateFreelancerApi");  
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                ServiceReview ser = new ServiceReview();
//                listReviews = ser.parseListReviewJson(new String(con.getResponseData()));
//            }
//        });
//        NetworkManager.getInstance().addToQueue(con);
//        return listReviews;
//    }
//
}

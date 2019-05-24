/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.job;

import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompagny.service.ServiceJob;
import com.mycompany.Entite.Job;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author sana
 */
public class ShowJobs {
    Resources res;
    Form f ;
    ArrayList<Job> listJobs = new ArrayList<>();
    ArrayList<Job> listJobsSearch = new ArrayList<>();
       TextField search=new TextField("");
       Button sear=new Button("Find");
       ServiceJob js = new ServiceJob();
       

    public ShowJobs(){
        listJobs=js.getList2();
        Style s = UIManager.getInstance().getComponentStyle("Title");
          f = new Form("Jobs List",new BoxLayout(BoxLayout.Y_AXIS));
         FontImage icon=FontImage.createMaterial(FontImage.MATERIAL_EXIT_TO_APP, s);
        FontImage icon1=FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
        FontImage icon2=FontImage.createMaterial(FontImage.MATERIAL_SCANNER, s);
         f.getToolbar().addCommandToSideMenu("Home", icon, (e) -> Log.p("Clicked"));
         f.getToolbar().addCommandToSideMenu("Add a Job", icon1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddForm_1 up=new AddForm_1();
                up.getF().show();
            }
        });
         f.getToolbar().addCommandToSideMenu("Jobs List", icon2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowJobs up=new ShowJobs();
                up.getF().show();
            }
        });
         
       
        search=new TextField("","Search");

        search.getHintLabel().setUIID("title");
        search.getAllStyles().setAlignment(Component.LEFT);
        f.add(search);
        FontImage searchIcon=FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        f.getToolbar().addCommandToRightBar("Home", null, (ev)->{Home h = new Home();
          h.getF().show();
          });
        f.add(sear);

        sear.addActionListener((ActionEvent e)->{
            for(int i=0;i<listJobs.size();i++)
            {
                
               
               if(search.getText().toLowerCase().equals( listJobs.get(i).getTitre().substring(0, search.getText().length()).toLowerCase()))
                   listJobsSearch.add(listJobs.get(i));
            }
            SearchJob m=new SearchJob(listJobsSearch);
            m.getF().show();
        });
        for (Job j : listJobs){                                               
            try {
                System.out.println("ok!");
                add(j);
            } catch (IOException ex) {
                System.out.println("no!");
            }
        }
    }
    public void add(Job j) throws IOException {
         EncodedImage enc = EncodedImage.create("/Job.png");
         int height = Display.getInstance().convertToPixels(8f);
       int width = Display.getInstance().convertToPixels(8f);
       Button image = new Button(enc.fill(width, height));
         Container c2 = new Container(BoxLayout.x());
                 c2.add(image);
                 c2.setLeadComponent(image);
                 image.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                 
                Show1Job obi= new Show1Job(res, j);
                obi.getF().show();
                             }
         });
                
        Container c1 = new Container(BoxLayout.y());
        c2.add(c1);
        Label title = new Label(j.getTitre());
            SpanLabel description = new SpanLabel(j.getDescription());
            c1.add(title);
            c1.add(description);
            f.add(c2);
    }

        public Form getF() {
            return f;
        }

        public void setF(Form f) {
            this.f = f;
        }
   
    
   
    
    
    
    
    



}

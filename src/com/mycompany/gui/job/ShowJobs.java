/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.job;

import com.mycompany.gui.views.Home;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.service.ServiceJob;
import com.mycompany.Entite.Job;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author sana
 */
public class ShowJobs {
    Resources res;

  
    /*public ShowJobs() {
        
       
        
        f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
        ServiceJob serviceTask=new ServiceJob();
        ArrayList<Job > lis=serviceTask.getList2();
       jobs=new Form();
       Container list=new Container(BoxLayout.y());
       list.setScrollableY(true);
       for(int iter=0;iter<1;iter++)
       {
           MultiButton mb=new MultiButton("bonjour");
          // mb.setTextLine2("Location"+lis.get(iter).getLocation());
           //mb.setTextLine3("Salary"+lis.get(iter).getMinSal()+"-"+lis.get(iter).getMaxSal());
      }
       jobs.add(CENTER,list);
       jobs.show();
         
    

    /* public Component[] fetComponents(int index, int amount){
         Component[] more=new Component[amount];
         for(int iter=0;iter<amount;iter++)
         {
             int offset=index+iter;
             MultiButton mb=new MultiButton("aa");
             FontImage.setMaterialIcon(mb, FontImage.MATERIAL_PERSON);
             mb.addActionListener(al->
                ToastBar.showMessage("Clicked:"+offset, FontImage.MATERIAL_PERSON));
             more[iter]=mb;
         }
        return more;
            
        }
    public Form getJobs() {
        return jobs;
    }

    public void setJobs(Form jobs) {
        this.jobs = jobs;
    }*/

    
    
    Form f ;
    ArrayList<Job> listJobs = new ArrayList<>();
    ArrayList<Job> listJobsSearch = new ArrayList<>();
       TextField search=new TextField("");
       ServiceJob js = new ServiceJob();

    public ShowJobs(){
        Style s = UIManager.getInstance().getComponentStyle("Title");
         
        f = new Form("Toolbar",new BoxLayout(BoxLayout.Y_AXIS));
        search=new TextField("","Toolbar Search");
        search.getHintLabel().setUIID("title");
        search.getAllStyles().setAlignment(Component.LEFT);
        f.getToolbar().setTitleComponent(search);
        FontImage searchIcon=FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        f.getToolbar().addCommandToRightBar("Home", null, (ev)->{Home h = new Home(res);
          h.getF().show();
          });
        
//        search.addDataChangeListener((d,e) -> {
//            
//            String t=search.getText();
//            if(t.length()<1)
//            {
//                for(Component cmp : f.getContentPane())
//                {
//                    cmp.setHidden(false);
//                    cmp.setVisible(true);
//                }
//            }
//            else
//            {
//                t=t.toLowerCase();
//                for(Component cmp : f.getContentPane()) {
//            String val = null;
//            if(cmp instanceof Label) {
//                val = ((Label)cmp).getText();
//            } else {
//                if(cmp instanceof TextArea) {
//                    val = ((TextArea)cmp).getText();
//                } else {
//                    val = (String)cmp.getPropertyValue("text");
//                }
//            }
//            boolean show = val != null && val.toLowerCase().indexOf(t) > -1;
//            cmp.setHidden(!show); 
//            cmp.setVisible(show);
//        }
//    }
//    f.getContentPane().animateLayout(250);
//});
//f.getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
//    search.startEditingAsync(); 
//});    
                
//            for(int i=0;i<js.getList2().size();i++)
//            {
//                
//               
//               if(search.getText().equals( js.getList2().get(i).getTitre().substring(0, search.getText().length())))
//                   listJobsSearch.add(js.getList2().get(i));
//                   
//            }
//            for (Job j : listJobs){                                               
//            try {
//                System.out.println("ok!");
//                add(j);
//            } catch (IOException ex) {
//                System.out.println("no!");
//            }
//        }
            
            
             
        
        
        listJobs= js.getList2();
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
            
           // Label priceMin = new Label(""+o.getPriceMin());
            //Label priceMax = new Label(""+o.getPriceMax());
            //Label type = new Label(o.getType());
            //Label status = new Label(o.getStatus());
            //Label location = new Label(o.getLocation());
            c1.add(title);
            c1.add(description);
            
            //c1.add(priceMin);
            //c1.add(priceMax);
            //c1.add(type);
            //c1.add(status);
            //c1.add(location);
            f.add(c2);
            
    }

        public Form getF() {
            return f;
        }

        public void setF(Form f) {
            this.f = f;
        }
   
    
   
    
    
    
    
    



}

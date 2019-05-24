/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.job;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompagny.service.ServiceJob;
import com.mycompany.Entite.Job;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author sana
 */
public class SearchJob {
    Resources res;
    Form f ;
    ArrayList<Job> listJobsSearch = new ArrayList<>();
       ServiceJob js = new ServiceJob();

    public SearchJob(ArrayList<Job> j){
          f = new Form("Search By Title",new BoxLayout(BoxLayout.Y_AXIS));
        ShowJobs s=new ShowJobs();
        f.getToolbar().addCommandToRightBar("Back", null, (ev)->{ShowJobs h = new ShowJobs();
          h.getF().show();
          });
            for (Job j1 : j){                                               
            try {
                System.out.println("ok!");
                add(j1);
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

/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui.job;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.service.ServiceJob;
import com.mycompany.Entite.Job;



/**
 * The newsfeed form
 *
 * @author Shai Almog
 */
public class Show1Job {
    
   Form f;

    public Show1Job(Resources res,Job j) {
        
        f=new Form();
//     Toolbar tb = new Toolbar(true);
//      f.setToolbar(tb);
      //super.addSideMenu(res); 
      f.getToolbar().addCommandToRightBar("Back", null, (ev)->{ShowJobs h = new ShowJobs();
          h.getF().show();
          });
      Label title=new Label("Title : "+j.getTitre());
      Label type=new Label("Type : "+j.getType());
      Label location=new Label("Location : "+j.getLocation());
      Label salary=new Label("Salary : "+j.getMinSal()+"  -  "+j.getMaxSal());
      Label desc=new Label("Description : "+j.getEmployer_id());
      
      
      Container C=new Container(BoxLayout.y());
      C.add(title);
      C.add(type);
      C.add(location);
      C.add(salary);
      C.add(desc);
           
      Button delete=new Button("Delete");
      Button update=new Button("Update");
       Container cont=new Container();
          cont.add(delete);
          cont.add(update);
          C.add(cont);
          f.add(C);
           delete.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                   ServiceJob js=new ServiceJob();
                   js.deleteJob(j.getId());
                   ShowJobs a = new ShowJobs();
                   a.getF().show();
               }
           });
           
           update.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                   UpdateForm up=new UpdateForm(j);
                   up.getF().show();
               }
           });
      
       
     
      
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
   
}

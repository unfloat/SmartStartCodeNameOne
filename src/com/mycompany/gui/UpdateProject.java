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
package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Project;
import com.mycompany.service.ServiceProject;
import java.text.ParseException;
import java.util.List;

/**
 * GUI builder created Form
 *
 * @author shai
 */
public class UpdateProject extends BaseForm {

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }
    
    
    public UpdateProject(com.codename1.ui.util.Resources resourceObjectInstance, Project p1) {
        initGuiBuilderComponents(resourceObjectInstance,p1);
        
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Update Project", "Title")
                        
                )
        );
        
        installSidemenu(resourceObjectInstance);
        
        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("toolbar-profile-pic.png"), e -> {});

    }



    com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
     com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
     com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField();
     com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField();
     com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
     com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
        
     
    
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance, Project p1) {
         
        
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Sign In");
        setName("SignInForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        
        TextArea projectDescription = new TextArea("",3, 20, TextArea.ANY);
        
      
         Label projectName = new Label("Project Name");
         Label projectLocation = new Label("Project Location");
         Label projectDescriptionn = new Label("Project Description");
         Label budget = new Label("Budget");
         
        TextField ProjectMinBudget = new TextField(String.valueOf(p1.getMinBudget()), String.valueOf(p1.getMinBudget()), 20, TextField.ANY);
        TextField ProjectMaxBudget = new TextField(String.valueOf(p1.getMaxBudget()), String.valueOf(p1.getMaxBudget()), 20, TextField.ANY);
         
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Text_Field_1.setText(p1.getProjectName());
        gui_Text_Field_2.setText(p1.getProjectLocation());
        projectDescription.setText(p1.getProjectDescription());
        
        
        
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(projectName);
        gui_Component_Group_1.addComponent(gui_Text_Field_1);
        gui_Component_Group_1.addComponent(projectLocation);
        gui_Component_Group_1.addComponent(gui_Text_Field_2);
        gui_Component_Group_1.addComponent(projectDescriptionn);
        gui_Component_Group_1.addComponent(projectDescription);
        gui_Component_Group_1.addComponent(budget);
        gui_Component_Group_1.addComponent(ProjectMinBudget);
        gui_Component_Group_1.addComponent(ProjectMaxBudget);
       
        
        
        
        
        gui_Container_1.addComponent(gui_Button_2);

        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Update Project");
        gui_Button_2.setName("Button_2");
        
        addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Button_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
       
        gui_Button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                ConnectionRequest req = new ConnectionRequest();
                req.setUrl("http://localhost/foobar10/web/app_dev.php/task/projects/"
                        + p1.getId() + "/updateProject?"
                        + "projectName=" + gui_Text_Field_1.getText() 
                        + "&projectLocation=" + gui_Text_Field_2.getText()
                        + "&minBudget=" + ProjectMinBudget.getText()
                        + "&maxBudget=" + ProjectMaxBudget.getText()
                        + "&projectDescription=" + projectDescription.getText()
                       
                        );

                req.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        byte[] data = (byte[]) evt.getMetaData();
                        String s = new String(data);

                        if (!s.equals("Error")) {
                            
                            Dialog.show("Confirmation", "Project Updated", "Ok", null);
                            new ListProjects(resourceObjectInstance).show();
                            
                        } else {
                            Dialog.show("Confirmation", "Update Failed", "Ok", null);
                        }
                    }                                        
                }); 
                NetworkManager.getInstance().addToQueue(req);
                   //new ListeChambre(res).show();
            }
        });
        
        
        
        
        
    }

//-- DON'T EDIT ABOVE THIS LINE!!!
    
    
}

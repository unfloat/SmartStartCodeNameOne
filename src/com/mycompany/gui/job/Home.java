/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.job;

import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;

/**
 *
 * @author AGENT6
 */
public class Home {
    Form f;
    
    Button btnajout,btnaff;
    
    public Home(){
        Style s = UIManager.getInstance().getComponentStyle("Title");
         f = new Form("Smart Start", new BoxLayout(BoxLayout.Y_AXIS));
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

    
    
    
    
}

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
    
}


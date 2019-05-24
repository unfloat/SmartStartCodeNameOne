/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.job;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.mycompany.service.ServicePortfolio;
import com.mycompany.Entite.Portfolio;

/**
 *
 * @author sana
 */
public class addPortForm {

    Form f;
    TextField tSkills;
    TextArea tDesc;
    TextField tPrevExp;
    Label desc;
    Button btnajout;

    public addPortForm() {
        f = new Form("Add Portfolio");
        f.getToolbar().addCommandToRightBar("Home", null, (ev)->{Home h = new Home();
          h.getF().show();
          });
        tSkills = new TextField("","Skills");
        
        
        tPrevExp = new TextField("","Previous Experiences");
        
        desc=new Label("Description:");
        
        tDesc = new TextArea("");
        
        btnajout = new Button("Ajouter");
        f.add(tSkills);
        f.add(tPrevExp);
        f.add(tDesc);
        
        
        f.add(btnajout);
        btnajout.addActionListener((e) -> {
            ServicePortfolio ser = new ServicePortfolio();
            Portfolio p=new Portfolio(tSkills.getText(), tDesc.getText(), tPrevExp.getText());
            ser.addPortfolio(p);
            tSkills.setText("");
            
            tDesc.setText("");
            tPrevExp.setText("");
           
            Home up=new Home();
            up.getF().show();
            

        });
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    

   

}


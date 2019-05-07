/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.job;

import com.mycompany.gui.views.Home;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.service.ServiceJob;
import com.mycompany.Entite.Job;

/**
 *
 * @author sana
 */
public class AddForm_1 {

    Form f;
    TextField tTitle;
    TextField tDesc;
    TextField tLoc;
    TextField tType;
    TextField tMax;
    TextField tMin;
    TextField tTag;
    Button btnajout, btnaff;
    Resources res;

    public AddForm_1() {
        f = new Form("Add Job");
        f.getToolbar().addCommandToRightBar("back", null, (ev) -> {
            Home h = new Home(res);
            h.getF().show();
        });
        tTitle = new TextField();
        tType = new TextField();
        tLoc = new TextField();
        tMin = new TextField();
        tMax = new TextField();
        tDesc = new TextField();
        btnajout = new Button("Ajouter");
        btnaff = new Button("Affichage");
        f.add(tTitle);
        f.add(tType);
        f.add(tLoc);
        f.add(tMin);
        f.add(tMax);
        f.add(tDesc);

        f.add(btnajout);
        btnajout.addActionListener((e) -> {
            ServiceJob ser = new ServiceJob();
            Job j = new Job(tTitle.getText(), tType.getText(), tLoc.getText(), Double.valueOf(tMin.getText()), Double.valueOf(tMax.getText()), tDesc.getText());
            ser.addJob(j);
            tTitle.setText("");
            tTitle.setUIID("addField");
            tTitle.setHint("Job Title");
            tTitle.getHintLabel().setUIID("job Field hint");
            tType.setText("");
            tDesc.setText("");
            tLoc.setText("");
            tMax.setText("");
            tMin.setText("");
            ShowJobs up = new ShowJobs();
            up.getF().show();

        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField gettTitle() {
        return tTitle;
    }

    public void settTitle(TextField tTitle) {
        this.tTitle = tTitle;
    }

    public TextField gettDesc() {
        return tDesc;
    }

    public void settDesc(TextField tDesc) {
        this.tDesc = tDesc;
    }

    public TextField gettLoc() {
        return tLoc;
    }

    public void settLoc(TextField tLoc) {
        this.tLoc = tLoc;
    }

    public TextField gettType() {
        return tType;
    }

    public void settType(TextField tType) {
        this.tType = tType;
    }

    public TextField gettMax() {
        return tMax;
    }

    public void settMax(TextField tMax) {
        this.tMax = tMax;
    }

    public TextField gettMin() {
        return tMin;
    }

    public void settMin(TextField tMin) {
        this.tMin = tMin;
    }

    public TextField gettTag() {
        return tTag;
    }

    public void settTag(TextField tTag) {
        this.tTag = tTag;
    }

    public Button getBtnajout() {
        return btnajout;
    }

    public void setBtnajout(Button btnajout) {
        this.btnajout = btnajout;
    }

    public Button getBtnaff() {
        return btnaff;
    }

    public void setBtnaff(Button btnaff) {
        this.btnaff = btnaff;
    }

}

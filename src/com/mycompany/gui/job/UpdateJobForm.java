/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.job;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.mycompagny.service.ServiceJob;
import com.mycompany.Entite.Job;
/**
 *
 * @author sana
 */
public class UpdateJobForm {

    Form f;
    TextField tTitle;
    TextField tDesc;
    TextField tLoc;
    TextField tType;
    TextField tMax;
    TextField tMin;
    Button btnajout,btnaff;

    public UpdateJobForm(Job j) {
        f = new Form("home");
        tTitle = new TextField();
        tType = new TextField();
        tLoc = new TextField();
        tMin = new TextField();
        tMax = new TextField();
        tDesc = new TextField();
        btnajout = new Button("Save");
        btnaff=new Button("Jobs List");
        f.add(tTitle);
        f.add(tType);
        f.add(tLoc);
        f.add(tMin);
        f.add(tMax);
        f.add(tDesc);
        tTitle.setText(j.getTitre());
            tType.setText(j.getType());
            tLoc.setText(j.getLocation());
            tMin.setText(""+j.getMinSal());
            tMax.setText(""+j.getMaxSal());
            tDesc.setText(j.getDescription());
            
        
        f.add(btnajout);
        btnajout.setAlignment(4);

        btnajout.addActionListener((e) -> {
            ServiceJob ser = new ServiceJob();
            Job j1=new Job(tTitle.getText(), tType.getText(), tLoc.getText(),Double.valueOf(tMin.getText()), Double.valueOf(tMax.getText()), tDesc.getText());
            ser.updateJob(j1,j.getId());
            Dialog.show("Successfull Update",j.getTitre()+" updated successfully !", "Ok", null);
            tTitle.setText("");
            tType.setText("");           
            tLoc.setText("");
            tMax.setText("");
            tMin.setText("");
            tDesc.setText("");
            ShowJobs a = new ShowJobs();
            a.getF().show();

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

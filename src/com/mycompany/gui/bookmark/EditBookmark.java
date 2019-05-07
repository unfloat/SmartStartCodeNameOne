/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.bookmark;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.mycompany.service.ServiceJob;
import com.mycompany.Entite.Bookmark;
import com.mycompany.Entite.Job;

/**
 *
 * @author asus
 */
public class EditBookmark {

    Form f;
    TextField tTitle;
    TextField tDesc;
    TextField tLoc;
    TextField tType;
    TextField tMax;
    TextField tMin;
    Button btnajout, btnaff;

    public EditBookmark(Bookmark bookmark) {
        f = new Form("home");
        tTitle = new TextField();
        tType = new TextField();
        tLoc = new TextField();
        tMin = new TextField();
        tMax = new TextField();
        tDesc = new TextField();
        btnajout = new Button("Save");
        btnaff = new Button("Jobs List");
        f.add(tTitle);
        f.add(tType);
        f.add(tLoc);
        f.add(tMin);
        f.add(tMax);
        f.add(tDesc);
        tTitle.setText("project id");
        tType.setText(bookmark.getDateAdded().toString());

        f.add(btnajout);
        btnajout.setAlignment(4);

        btnajout.addActionListener((e) -> {
            ServiceJob ser = new ServiceJob();
            Job j1 = new Job(tTitle.getText(), tType.getText(), tLoc.getText(), Double.valueOf(tMin.getText()), Double.valueOf(tMax.getText()), tDesc.getText());
            ser.updateJob(j1, bookmark.getId());
            tTitle.setText("");
            tType.setText("");
            tLoc.setText("");
            tMax.setText("");
            tMin.setText("");
            tDesc.setText("");
            DisplayBookmarks a = new DisplayBookmarks();
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
}

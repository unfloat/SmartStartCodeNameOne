/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.bookmark;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Bookmark;
import com.mycompany.gui.job.ShowJobs;
import com.mycompany.gui.views.Home;
import com.mycompany.service.BookmarkService;

/**
 *
 * @author asus
 */
public class ShowBookmark {

    Form f;

    public ShowBookmark(Resources res, Bookmark bookmark) {

        f = new Form();
//     Toolbar tb = new Toolbar(true);
//      f.setToolbar(tb);
        //super.addSideMenu(res); 
        f.getToolbar().addCommandToRightBar("Back", null, (ev) -> {
            DisplayBookmarks displayBookmarks = new DisplayBookmarks(res);
            displayBookmarks.getF().show();
        });
        Label project = new Label("Project : " + bookmark.getProject().getProjectName());
        Label dateAdded = new Label("Date : " + bookmark.getDateAdded());

        Container C = new Container(BoxLayout.y());
        C.add(project);
        C.add(dateAdded);

        Button delete = new Button("Delete");
        Button projectDetails = new Button("Project Details");
        Container cont = new Container();
        cont.add(delete);
        cont.add(projectDetails);
        C.add(cont);
        f.add(C);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                BookmarkService bookmarkService = new BookmarkService();
                bookmarkService.deleteBookmark(bookmark.getId());
                DisplayBookmarks up = new DisplayBookmarks(res);
                up.getF().show();
            }
        });

        projectDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Home up = new Home(res);
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

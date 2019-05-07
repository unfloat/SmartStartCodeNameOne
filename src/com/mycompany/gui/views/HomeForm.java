/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.views;

import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.mycompany.gui.bid.DisplayBids;
import com.mycompany.gui.bookmark.DisplayBookmarks;
import java.util.Date;

/**
 *
 * @author esprit
 */
public class HomeForm extends SideMenuBaseForm {

    Form f;

    public HomeForm(Resources res) {

        UIBuilder ui = new UIBuilder();
        //f = ui.createContainer(res, "Home").getComponentForm();

        Label bonjour = new Label();
        Label labdate = new Label();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(); //2016/11/16 12:08:43
        bonjour.setText("Bonjour ");
        labdate.setText(" On est le " + dateFormat.format(date));
        f.getToolbar().addCommandToSideMenu("Bids", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                DisplayBids displayBids = new DisplayBids(res);
                displayBids.getF().show();
            }
        });
        f.getToolbar().addCommandToSideMenu("Bookmarks", null, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                DisplayBookmarks displayBookmarks = new DisplayBookmarks(res);
                displayBookmarks.getF().show();
            }
        });

        /*    f.getToolbar().addCommandToSideMenu("Test", null, new ActionListener() {

             @Override
            public void actionPerformed(ActionEvent evt) {
           Test t = new Test(theme,  nom);
           t.getF().show();
            }
        });*/
    }

    public Form getF() {
        return f;
    }

    public void show() {

        f.show();
    }

    @Override
    protected void showOtherForm(Resources res) {
        new DisplayBids(res).getF().show();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.views;

import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.mycompany.gui.bid.AddBid;
import com.mycompany.gui.bid.DisplayBids;
import com.mycompany.gui.bookmark.DisplayBookmarks;
import java.util.Date;

/**
 *
 * @author AGENT6
 */
public class Home {
    Form f;

    Button btnBookmarks, btnBids;

    public Home(Resources res) {
        f = new Form("Home");

        btnBookmarks = new Button("Bookmarks");
        btnBids = new Button("Bids");

        f.add(btnBookmarks);
        f.add(btnBids);

        btnBids.addActionListener((e) -> {
            DisplayBids up = new DisplayBids(res);
            up.getF().show();
        });

        
        btnBookmarks.addActionListener((e) -> {
            DisplayBookmarks up = new DisplayBookmarks(res);
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

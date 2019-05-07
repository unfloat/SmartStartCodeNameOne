/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.bid;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.service.BidService;
import com.mycompany.Entite.Bid;
import com.mycompany.gui.bid.DisplayBids;
import com.mycompany.gui.views.Home;

/**
 *
 * @author asus
 */
public class ShowBid {

    Form f;

    public ShowBid(Resources res, Bid bid) {

        f = new Form();

//     Toolbar tb = new Toolbar(true);
//      f.setToolbar(tb);
        //super.addSideMenu(res); 
        f.getToolbar().addCommandToRightBar("Back", null, (ev) -> {
            DisplayBids displayBids = new DisplayBids(res);
            displayBids.getF().show();
        });
        Label deliveryTime = new Label("Delivery Time : " + bid.getDeliveryTime());
        Label minimalRate = new Label("Minimal Rate : " + bid.getMinimalRate());

        Container C = new Container(BoxLayout.y());
        C.add(deliveryTime);
        C.add(minimalRate);

        Button delete = new Button("Delete");
        Button update = new Button("Update");
        Container cont = new Container();
        cont.add(delete);
        cont.add(update);
        C.add(cont);
        f.add(C);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                BidService bidService = new BidService();
                bidService.deleteBid(bid.getId());
                DisplayBids displayBids = new DisplayBids(res);
                displayBids.getF().show();
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                EditBid editBid = new EditBid(res, bid);
                editBid.getF().show();
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

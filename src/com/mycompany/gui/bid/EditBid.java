/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.bid;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.service.BidService;
import com.mycompany.Entite.Bid;
import com.mycompany.gui.views.Home;

/**
 *
 * @author asus
 */
public class EditBid {

    Form f;
    TextField textDeliveryTime;
    TextField textMinimalRate;

    Button btnValider;
    public EditBid(Resources res,Bid bid) {
        f = new Form("Edit Bid");
        f.getToolbar().addCommandToRightBar("back", null, (ev) -> {
            Home h = new Home(res);
            h.getF().show();
        });
        textDeliveryTime = new TextField();
        textMinimalRate = new TextField();

        btnValider = new Button("Valider");
        
        textDeliveryTime.setText("");
        textDeliveryTime.setUIID("addField");
        textDeliveryTime.setHint("Actual DeliveryTime" +String.valueOf(bid.getDeliveryTime()));

        textMinimalRate.setText("");
        textMinimalRate.setUIID("addField");
        textMinimalRate.setHint("Actual Minimal Rate" +String.valueOf(bid.getMinimalRate()));

        f.add(textDeliveryTime);
        f.add(textMinimalRate);


        f.add(btnValider);
        btnValider.setAlignment(4);

        btnValider.addActionListener((e) -> {
            BidService bidService = new BidService();
            Bid bidUpdated = new Bid(Integer.valueOf(textDeliveryTime.getText()), Integer.valueOf(textMinimalRate.getText()));
            bidService.updateBid(bidUpdated, bid.getId());
            textDeliveryTime.setText("");
            textMinimalRate.setText("");
           ShowBid showBids = new ShowBid(res,bidUpdated);
            showBids.getF().show();
            //DisplayBids displayBids = new DisplayBids();
            //displayBids.getF().show();

        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getMinimalRate() {
        return textMinimalRate;
    }

    public void settTitle(TextField tTitle) {
        this.textMinimalRate = tTitle;
    }

    public TextField gettextDeliveryTime() {
        return textDeliveryTime;
    }

    public void settextDeliveryTime(TextField tTitle) {
        this.textDeliveryTime = tTitle;
    }
}

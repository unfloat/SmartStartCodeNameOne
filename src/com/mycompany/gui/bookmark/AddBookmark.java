/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.bookmark;

import com.mycompany.gui.bid.*;
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
public class AddBookmark {

    Form f;
    TextField textDeliveryTime;
    TextField textMinimalRate;

    Button btnajout, btnaff;
        Resources res;


    public AddBookmark() {
        f = new Form("Add Bid");
        f.getToolbar().addCommandToRightBar("back", null, (ev) -> {
            Home h = new Home(res);
            h.getF().show();
        });
        textDeliveryTime = new TextField();
        textMinimalRate = new TextField();

        btnajout = new Button("Ajouter");
        btnaff = new Button("Affichage");
        textDeliveryTime.setText("");
        textDeliveryTime.setUIID("addField");
        textDeliveryTime.setHint("DeliveryTime");

        textMinimalRate.setText("");
        textMinimalRate.setUIID("addField");
        textMinimalRate.setHint("Minimal Rate");

        f.add(textDeliveryTime);
        f.add(textMinimalRate);

        f.add(btnajout);
        btnajout.addActionListener((e) -> {
            BidService bidService = new BidService();
            Bid bid = new Bid(Integer.valueOf(textDeliveryTime.getText()), Integer.valueOf(textMinimalRate.getText()));
            bidService.addBid(bid);
            textDeliveryTime.setText("");
            textDeliveryTime.setUIID("addField");
            textDeliveryTime.setHint("DeliveryTime");

            textMinimalRate.setText("");
            textMinimalRate.setUIID("addField");
            textMinimalRate.setHint("Minimal Rate");

            ShowBid showBid = new ShowBid(res,bid);
            showBid.getF().show();
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

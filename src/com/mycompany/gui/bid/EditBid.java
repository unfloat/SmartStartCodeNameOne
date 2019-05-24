/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.bid;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.service.BidService;
import com.mycompany.Entite.Bid;
import com.mycompany.gui.BaseForm;
import com.mycompany.gui.views.Home;

/**
 *
 * @author asus
 */
public class EditBid extends BaseForm {

    TextField textDeliveryTime;
    TextField textMinimalRate;

    Button btnValider;

    public EditBid(Resources res, Bid bid) {
        com.codename1.ui.Container gui_Container_111 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

        textDeliveryTime = new TextField();
        textMinimalRate = new TextField();

        btnValider = new Button("Valider");

        textDeliveryTime.setText("");
        textDeliveryTime.setUIID("addField");
        textDeliveryTime.setHint("Actual DeliveryTime" + String.valueOf(bid.getDeliveryTime()));

        textMinimalRate.setText("");
        textMinimalRate.setUIID("addField");
        textMinimalRate.setHint("Actual Minimal Rate" + String.valueOf(bid.getMinimalRate()));

        gui_Container_111.add(textDeliveryTime);
        gui_Container_111.add(textMinimalRate);

        gui_Container_111.add(btnValider);
        btnValider.setAlignment(4);

        btnValider.addActionListener((e) -> {
            BidService bidService = new BidService();
            Bid bidUpdated = new Bid();

            if (textDeliveryTime.getText().isEmpty()) {
                bidUpdated.setMinimalRate(Integer.valueOf(textMinimalRate.getText()));
                bidUpdated.setDeliveryTime(bid.getDeliveryTime());

            }
            if (textMinimalRate.getText().isEmpty()) {
                bidUpdated.setDeliveryTime(Integer.valueOf(textDeliveryTime.getText()));
                bidUpdated.setMinimalRate(bid.getMinimalRate());

            }
//            if (textMinimalRate.getText().isEmpty() && textDeliveryTime.getText().isEmpty()) {
//
//                Dialog.show("Erreur", "Veuillez remplir au moins un champs", "Ok", null );
//            }

            bidUpdated.setDeliveryTime(Integer.valueOf(textDeliveryTime.getText()));
            bidUpdated.setMinimalRate(Integer.valueOf(textMinimalRate.getText()));

            bidUpdated.setId(bid.getId());
            bidService.updateBid(bidUpdated);

            textDeliveryTime.setText("");
            textMinimalRate.setText("");
            new ListBids(res).show();

        }
        );

        addComponent(gui_Container_111);

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

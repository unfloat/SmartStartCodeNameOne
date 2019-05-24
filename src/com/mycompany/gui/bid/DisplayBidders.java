/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.bid;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Bid;
import com.mycompany.service.BidService;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class DisplayBidders {

    Resources res;
    Form form;
    ArrayList<Bid> listBidders;
    BidService bidService = new BidService();

    public DisplayBidders(Resources theme) {
        form = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
        listBidders = bidService.displayBidders(8);
        System.out.println(listBidders.toString());
        if (listBidders.isEmpty()) {
            Label message = new Label("You have no bids.\n Go ahead and create some! ");
            Container container = new Container(new BoxLayout((BoxLayout.Y_AXIS)));
            container.add(message);
            form.add(container);

        }

        Component[] listingsToAdd = new Component[listBidders.size()];
        for (int iteration = 0; iteration < listingsToAdd.length; iteration++) {
            System.out.println(listBidders.get(iteration));
            Button showBid = new Button("Details");
            Bid currentBid = listBidders.get(iteration);
                    System.out.println(currentBid.toString());

            Container c = new Container(new BoxLayout((BoxLayout.Y_AXIS)));
            //String guid = (String) currentListing.get("seq").toString();
            //Label date = new Label((String) currentBid.getProject().getPublishingDate());
            Label freelancerName = new Label("Freelancer: " + String.valueOf(currentBid.getFreelancer().getFirstName()));

            Label minimalRate = new Label("Bid minimal rate: " + String.valueOf(currentBid.getMinimalRate()));
            Label deliveryTime = new Label("Bid delivery time: " + String.valueOf(currentBid.getDeliveryTime()));
            Label projectName = new Label("Project Title: " + String.valueOf(currentBid.getProject().getProjectName()));
            Label projectMinBudget = new Label("Project Minimum Budget: " + String.valueOf(currentBid.getProject().getMinBudget()));
            Label projectMaxBudget = new Label("Project Maximum Budget: " + String.valueOf(currentBid.getProject().getMaxBudget()));
            //Label projectExpiration = new Label(String.valueOf(currentBid.getProject().getValidityPeriod()));

            c.add(minimalRate)
                    .add(deliveryTime)
                    .add(projectName)
                    .add(projectMinBudget)
                    .add(projectMaxBudget)
                    .add(freelancerName)
                    .add(showBid);
            showBid.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ShowBid or = new ShowBid(res, currentBid);
                    or.getF().show();
                }
            });
            listingsToAdd[iteration] = c;
            form.add(c);

        }

    }

    public Form getF() {
        return form;
    }

    public void setF(Form f) {
        this.form = f;
    }
}

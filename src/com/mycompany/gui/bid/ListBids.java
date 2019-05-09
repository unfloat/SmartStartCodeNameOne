/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.bid;

import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.service.BidService;
import com.mycompany.Entite.Bid;
import com.mycompany.Entite.Project;
import com.mycompany.gui.BaseForm;
import com.mycompany.gui.ListProjects;
import com.mycompany.gui.UpdateProject;
import com.mycompany.gui.views.Home;
import com.mycompany.service.ServiceProject;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author asus
 */
public class ListBids extends BaseForm {

    Resources res;
    Form form;
    ArrayList<Bid> listBids;
    BidService bidService = new BidService();

    public ListBids() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }

    public ListBids(Resources resourceObjectInstance) {
        //initGuiBuilderComponents(resourceObjectInstance);
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("List Projects", "Title")
                )
        );

        installSidemenu(resourceObjectInstance);

        listBids = bidService.displayBids();
        form.getToolbar().addCommandToRightBar("Add", null, (ev) -> {
            AddBid addBid = new AddBid(res, 1);
            addBid.getF().show();
        });
        if (listBids.isEmpty()) {
            Label message = new Label("You have no bids.\n Go ahead and create some! ");
            Container container = new Container(new BoxLayout((BoxLayout.Y_AXIS)));
            container.add(message);
            form.add(container);

        }

        Component[] listingsToAdd = new Component[listBids.size()];
        for (int iteration = 0; iteration < listingsToAdd.length; iteration++) {
            Bid currentBid = listBids.get(iteration);

            ////////////////////////////////////////////////////////////
            Button btn = new Button("Delete");
            Dimension d = new Dimension();
            d.setWidth(70);
            d.setHeight(70);
            btn.setPreferredSize(d);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    Dialog d = new Dialog();

                    if (Dialog.show("Confirmation", "Delete This Project??", "Ok", null)) {
                        BidService bidService = new BidService();
                        bidService.deleteBid(currentBid.getId());

                        bidService.displayBids().remove(currentBid);
                        refreshTheme();

                    }

                    new ListBids(resourceObjectInstance).removeAll();
                    new ListBids(resourceObjectInstance).show();

                }
            });

            Button btn2 = new Button("Edit");
            btn2.setPreferredSize(d);
            btn2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    EditBid editBid = new EditBid(resourceObjectInstance, currentBid);
                    editBid.getF().show();
                }
            });

            com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
            com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
            com.codename1.ui.Container gui_Container_4 = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
            com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

            com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
            com.codename1.ui.Label gui_Label_1_1 = new com.codename1.ui.Label();
            com.codename1.ui.Label gui_Label_2 = new com.codename1.ui.Label();
            com.codename1.ui.Label gui_Label_3 = new com.codename1.ui.Label();
            com.codename1.ui.Label gui_Label_4 = new com.codename1.ui.Label();
            com.codename1.ui.Label gui_Label_6 = new com.codename1.ui.Label();

            com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
            gui_Label_6.setShowEvenIfBlank(true);

            gui_Text_Area_1.setRows(2);
            gui_Text_Area_1.setColumns(100);
            gui_Text_Area_1.setEditable(false);

            addComponent(gui_Container_1);
            gui_Container_1.setName("Container_1");
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2);

            gui_Container_2.setName("Container_2");
            gui_Container_2.addComponent(gui_Label_1);

            gui_Label_1.setText(String.valueOf(currentBid.getProject().getMinBudget()) + " TND ");
            gui_Label_1.setUIID("SmallFontLabel");
            gui_Label_1.setName("Label_1");

            gui_Container_2.addComponent(gui_Label_1_1);

            gui_Label_1_1.setText(String.valueOf(currentBid.getProject().getMaxBudget()) + " TND");
            gui_Label_1_1.setUIID("SmallFontLabel");
            gui_Label_1_1.setName("Label_1_1");

            gui_Container_2.addComponent(gui_Label_2);

            gui_Label_2.setText(String.valueOf("Bid minimal rate: " + currentBid.getMinimalRate()));
            gui_Label_2.setUIID("SmallFontLabel");
            gui_Label_2.setName("Label_2");

            gui_Container_2.addComponent(gui_Label_2);

            gui_Label_2.setText(String.valueOf("Bid delivery time: " + currentBid.getDeliveryTime()));
            gui_Label_2.setUIID("SmallFontLabel");
            gui_Label_2.setName("Label_2");

            form.add(gui_Container_1);

            ////////////////////////////////////////////////////////////
            //Button showBid = new Button("Details");
            //Container c = new Container(new BoxLayout((BoxLayout.Y_AXIS)));
            //String guid = (String) currentListing.get("seq").toString();
            //Label date = new Label((String) currentBid.getProject().getPublishingDate());
//            Label minimalRate = new Label("Bid minimal rate: " + String.valueOf(currentBid.getMinimalRate()));
//            Label deliveryTime = new Label("Bid delivery time: " + String.valueOf(currentBid.getDeliveryTime()));
//            Label projectName = new Label("Project Title: " + String.valueOf(currentBid.getProject().getProjectName()));
//            Label projectMinBudget = new Label("Project Minimum Budget: " + String.valueOf(currentBid.getProject().getMinBudget()));
//            Label projectMaxBudget = new Label("Project Maximum Budget: " + String.valueOf(currentBid.getProject().getMaxBudget()));
//            //Label projectExpiration = new Label(String.valueOf(currentBid.getProject().getValidityPeriod()));
//
//            c.add(minimalRate)
//                    .add(deliveryTime)
//                    .add(projectName)
//                    .add(projectMinBudget)
//                    .add(projectMaxBudget)
//                    .add(showBid);
//            showBid.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//                    ShowBid or = new ShowBid(res, currentBid);
//                    or.getF().show();
//                }
//            });
//            listingsToAdd[iteration] = c;
//            form.add(listingsToAdd[iteration]);
        }

    }

    public Form getF() {
        return form;
    }

    public void setF(Form f) {
        this.form = f;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.bid;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.service.BidService;
import com.mycompany.Entite.Bid;
import com.mycompany.Entite.ProjectF;
import com.mycompany.gui.BaseForm;
import com.mycompany.gui.ListProjects;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class ListBids extends BaseForm {

    Resources res;
    ArrayList<Bid> listBids;
    BidService bidService = new BidService();
    TextField rech = new TextField();

    public ListBids() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }

    public ListBids(Resources resourceObjectInstance) {
         getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("List Bids", "Title")
                )
        );
        installSidemenu(resourceObjectInstance);

        listBids = bidService.displayBids();

        System.out.println("listBids" + listBids.toString());

        if (listBids.isEmpty()) {
            Label message = new Label("You have no bids.\n Go ahead and create some! ");
            Container container = new Container(new BoxLayout((BoxLayout.Y_AXIS)));
            container.add(message);
            Button listProjectBtn = new Button("Available Projects");
            listProjectBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new ListProjects(resourceObjectInstance).show();

                }
            });
            container.add(listProjectBtn);
            addComponent(container);

        }


        com.codename1.ui.Container gui_Container_111 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        gui_Container_111.addComponent(rech);

        //bouton recherche
        Button btnRech = new Button("Search");
        Dimension ddd = new Dimension();
        ddd.setWidth(70);
        ddd.setHeight(70);
        btnRech.setPreferredSize(ddd);
        gui_Container_111.addComponent(btnRech);
        btnRech.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                SpanLabel l1 = new SpanLabel();
                for (ProjectF p1 : bidService.getProject(rech.getText())) {
                    Label name = new Label(p1.getProjectName());
                    gui_Container_111.add(name);
                }
//                                Project ta = new Project();
//                                l1.setText(s.searchProjects(ta).toString());
//                                String a=s.searchProjects(rech.getText());
//                                System.out.println(a);
//                                l1.add(s.searchProjects(rech.getText()).toString());
                gui_Container_111.add(l1);
            }
        });

        addComponent(gui_Container_111);

        //Component[] listingsToAdd = new Component[listBids.size()];
        for (int iteration = 0; iteration < listBids.size(); iteration++) {
            Bid currentBid = listBids.get(iteration);
            System.out.println("here");

//            com.codename1.ui.Container singleFlowContainer = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
//
//            com.codename1.ui.Label label = new com.codename1.ui.Label();
//
//            com.codename1.ui.Container flowContainer = new com.codename1.ui.Container(new com.codename1.ui.layouts.FlowLayout());
            Button btn = new Button("Delete");
            Dimension d = new Dimension();
            d.setWidth(70);
            d.setHeight(70);
            btn.setPreferredSize(d);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    Dialog d = new Dialog();

                    if (Dialog.show("Confirmation", "Delete This Bid??", "Ok", null)) {
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
                    new EditBid(resourceObjectInstance, currentBid).show();

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

            gui_Label_1.setText(String.valueOf(currentBid.getProject().getMinBudget()) + " TND + ");
            gui_Label_1.setUIID("SmallFontLabel");
            gui_Label_1.setName("Label_1");

            gui_Container_2.addComponent(gui_Label_1_1);

            gui_Label_1_1.setText(String.valueOf(currentBid.getProject().getMaxBudget()) + " TND");
            gui_Label_1_1.setUIID("SmallFontLabel");
            gui_Label_1_1.setName("Label_1_1");

            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Container_4);
            gui_Container_4.setName("Container_4");
            ((com.codename1.ui.layouts.FlowLayout) gui_Container_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
            gui_Container_4.addComponent(gui_Label_4);
            gui_Label_4.setUIID("Padding2");
            gui_Label_4.setName("Label_4");
            gui_Label_4.setIcon(resourceObjectInstance.getImage("label_round.png"));
            gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3);
            gui_Container_3.setName("Container_3");
            gui_Container_3.addComponent(gui_Label_3);
            gui_Container_3.addComponent(gui_Label_2);

            gui_Container_3.addComponent(gui_Text_Area_1);
            gui_Container_3.addComponent(btn);
            gui_Container_3.addComponent(btn2);
            gui_Label_3.setText("Project "+ currentBid.getProject().getProjectName());
            gui_Label_3.setName("Label_3");
            gui_Label_2.setText(String.valueOf(currentBid.getDeliveryTime()));
            gui_Label_2.setUIID("RedLabel");
            gui_Label_2.setName("Label_2");
            gui_Text_Area_1.setText(String.valueOf(currentBid.getMinimalRate()));
            
            gui_Text_Area_1.setUIID("SmallFontLabel");
            gui_Text_Area_1.setName("Text_Area_1");
            gui_Container_2.setName("Container_2");
            gui_Container_4.setName("Container_4");
            ((com.codename1.ui.layouts.FlowLayout) gui_Container_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
            gui_Container_3.setName("Container_3");
            addComponent(gui_Label_6);
            gui_Container_1.setName("Container_1");
            gui_Label_6.setText("");
            gui_Label_6.setUIID("Separator");

        }

    }

}

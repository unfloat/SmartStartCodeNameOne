/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.bid;

import com.codename1.notifications.LocalNotification;
import com.codename1.notifications.LocalNotificationCallback;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.service.BidService;
import com.mycompany.Entite.Bid;
import com.mycompany.Entite.Project;
import com.mycompany.gui.BaseForm;

/**
 *
 * @author asus
 */
public class AddBid extends BaseForm implements LocalNotificationCallback {

    TextField textDeliveryTime;
    TextField textMinimalRate;

    Button btnAjouter;

    public AddBid(Resources resourceObjectInstance, int projectId) {
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Add Bid", "Title")
                )
        );
        installSidemenu(resourceObjectInstance);

        com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();

        textDeliveryTime = new TextField();
        textMinimalRate = new TextField();

        btnAjouter = new Button("Ajouter");
        textDeliveryTime.setText("");
        textDeliveryTime.setUIID("addField");
        textDeliveryTime.setHint("Delivery Time");

        textMinimalRate.setText("");
        textMinimalRate.setUIID("addField");
        textMinimalRate.setHint("Minimal Rate");

        gui_Container_1.addComponent(gui_Component_Group_1);

        gui_Component_Group_1.addComponent(textDeliveryTime);
        gui_Component_Group_1.addComponent(textMinimalRate);
        gui_Component_Group_1.addComponent(btnAjouter);

        //        f.add(textDeliveryTime);
        //        f.add(textMinimalRate);
        //
        //        f.add(btnajout);
        //        f.add(interval);
        btnAjouter.addActionListener((e) -> {
            BidService bidService = new BidService();
            //Project project = bidService.getProjectById(projectId);

            Bid bid = new Bid(Integer.valueOf(textDeliveryTime.getText()), Integer.valueOf(textMinimalRate.getText()), projectId);
            //bid.setProject(project);
            bidService.addBid(bid);
            textDeliveryTime.setText("");
            textMinimalRate.setText("");
            int badgeNumber = 0;
            LocalNotification localNotification = new LocalNotification();
            localNotification.setAlertBody("A freelancer put a bid on your project");
            localNotification.setAlertTitle("New bid");
            localNotification.setId("notification");
            localNotification.setBadgeNumber(badgeNumber++);
            int repeatType = LocalNotification.REPEAT_MINUTE;
           

            Display.getInstance().scheduleLocalNotification(localNotification, System.currentTimeMillis() + 10 * 1000, repeatType);
            localNotificationReceived(localNotification.getId());
//            ShowBid showBid = new ShowBid(res,bid);
//            showBid.getF().show();
        });

        addComponent(gui_Container_1);

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


    @Override
    public void localNotificationReceived(String notificationId) {
        System.out.println("notif" + notificationId);
    }
}

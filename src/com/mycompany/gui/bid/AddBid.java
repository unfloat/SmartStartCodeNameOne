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
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.service.BidService;
import com.mycompany.Entite.Bid;
import com.mycompany.Entite.Project;
import com.mycompany.gui.views.Home;

/**
 *
 * @author asus
 */
public class AddBid extends Form implements LocalNotificationCallback {

    Form f;
    TextField textDeliveryTime;
    TextField textMinimalRate;
    final ComboBox interval = new ComboBox(new Object[]{"None", "Minute", "Hour", "Day", "Week"});

    Button btnajout, btnaff;

    public AddBid(Resources res, int projectId) {
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
        textDeliveryTime.setHint("Delivery Time");

        textMinimalRate.setText("");
        textMinimalRate.setUIID("addField");
        textMinimalRate.setHint("Minimal Rate");

        f.add(textDeliveryTime);
        f.add(textMinimalRate);

        f.add(btnajout);
        f.add(interval);
        btnajout.addActionListener((e) -> {
            BidService bidService = new BidService();
            Project project = bidService.getProject(projectId);
            System.out.println(project.toString());

            Bid bid = new Bid(Integer.valueOf(textDeliveryTime.getText()), Integer.valueOf(textMinimalRate.getText()));
            bid.setProject(project);
            bidService.addBid(bid);
            textDeliveryTime.setText("");
            textMinimalRate.setText("");
            int badgeNumber = 0;
            System.out.println("gui" + bid.getProject().toString());
            LocalNotification localNotification = new LocalNotification();
            localNotification.setAlertBody("A freelancer put a bid on your project" + bid.getProject().getProjectName());
            localNotification.setAlertTitle("New bid");
            localNotification.setId("1");
            localNotification.setBadgeNumber(badgeNumber++);
            int repeatType = LocalNotification.REPEAT_NONE;
            String selRepeat = (String) interval.getModel().getItemAt(interval.getModel().getSelectedIndex());
            if ("Minute".equals(selRepeat)) {
                repeatType = LocalNotification.REPEAT_MINUTE;
            } else if ("Hour".equals(selRepeat)) {
                repeatType = LocalNotification.REPEAT_HOUR;
            } else if ("Day".equals(selRepeat)) {
                repeatType = LocalNotification.REPEAT_DAY;
            } else if ("Week".equals(selRepeat)) {
                repeatType = LocalNotification.REPEAT_WEEK;
            }

            Display.getInstance().scheduleLocalNotification(localNotification, System.currentTimeMillis() + 10 * 1000, repeatType);

//            ShowBid showBid = new ShowBid(res,bid);
//            showBid.getF().show();
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

    @Override
    public void localNotificationReceived(String notificationId) {
        System.out.println("Received local notification " + notificationId + " in callback localNotificationReceived");
    }
}

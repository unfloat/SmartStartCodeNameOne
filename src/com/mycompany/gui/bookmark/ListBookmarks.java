/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.bookmark;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.service.BookmarkService;
import com.mycompany.Entite.Bookmark;
import com.mycompany.gui.BaseForm;
import com.mycompany.gui.ListProjects;
import java.util.ArrayList;

/**
 *
 * @author asus
 *
 *
 */
public class ListBookmarks extends BaseForm {

    Resources res;
    final Form form = new Form("Favourites");

    ArrayList<Bookmark> listBookmarks = new ArrayList<>();
    ArrayList<Bookmark> listBookmarksSearch = new ArrayList<>();
    TextField search = new TextField("");
    BookmarkService bookmarkService = new BookmarkService();

    public ListBookmarks() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }

//    private void addBackToHome(Form f) {
//        // the back command maps to the physical button on devices other than iPhone and creates the back arrow appearance on iOS
//        f.setBackCommand(new Command("PropertyCross") {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                Home home = new Home(res);
//                home.getF().show();
//            }
//        });
//    }
    com.codename1.ui.Container gui_Container_111 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

    public ListBookmarks(Resources resourceObjectInstance) {

        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("List Bookmarks", "Title")
                )
        );
        installSidemenu(resourceObjectInstance);

        listBookmarks = bookmarkService.displayBookmarks();
        System.out.println(listBookmarks.toString());
        if (listBookmarks.isEmpty()) {
            Label message = new Label("You have no bookmarks.");
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
        for (int iteration = 0; iteration < listBookmarks.size(); iteration++) {
            Bookmark currentBookmark = listBookmarks.get(iteration);

            Button btnDelete = new Button("Delete");
            Dimension d = new Dimension();
            d.setWidth(70);
            d.setHeight(70);
            btnDelete.setPreferredSize(d);
            btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    Dialog d = new Dialog();

                    if (Dialog.show("Confirmation", "Delete This Bookmark??", "Ok", null)) {
                        bookmarkService.deleteBookmark(currentBookmark.getId());
                        bookmarkService.displayBookmarks().remove(currentBookmark);
                        refreshTheme();

                    }

                    new ListBookmarks(resourceObjectInstance).removeAll();
                    new ListBookmarks(resourceObjectInstance).show();

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

            gui_Label_1.setText(String.valueOf(currentBookmark.getProject().getMinBudget()) + " TND + ");
            gui_Label_1.setUIID("SmallFontLabel");
            gui_Label_1.setName("Label_1");

            gui_Container_2.addComponent(gui_Label_1_1);

            gui_Label_1_1.setText(String.valueOf(currentBookmark.getProject().getMaxBudget()) + " TND");
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

            gui_Label_2.setText(String.valueOf(currentBookmark.getProject().getProjectLocation()));
            gui_Label_2.setUIID("RedLabel");
            gui_Label_2.setName("Label_2");

            gui_Container_3.addComponent(gui_Text_Area_1);
            gui_Container_3.addComponent(btnDelete);
            gui_Label_3.setText(String.valueOf(currentBookmark.getDateAdded()));
            gui_Label_3.setName("Label_3");

            gui_Text_Area_1.setText("Project " + String.valueOf(currentBookmark.getProject().getProjectName()));
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

    public Form getF() {
        return form;
    }

}

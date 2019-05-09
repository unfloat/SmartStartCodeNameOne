
package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.mycompany.Entite.Note;
import com.mycompany.service.ServiceNote;
import java.text.ParseException;



public class ListNotes extends BaseForm {

    public ListNotes() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }
    
    
    public ListNotes(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance); 
        
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("List Notes", "Title")        
                )
        );
        
        installSidemenu(resourceObjectInstance);
        
        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("emplyer.png"), e -> {});

        FloatingActionButton fab  = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        
        RoundBorder rb = (RoundBorder)fab.getUnselectedStyle().getBorder();
        
        rb.uiid(true);
        
        fab.bindFabToContainer(getContentPane());
        
        fab.addActionListener(e -> {
            new CreateNote(resourceObjectInstance).show(); 
                
        });
    }

                        
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
       
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("InboxForm");
        setName("InboxForm");

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/foobar10/web/app_dev.php/note/notes/all"); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceNote s = new ServiceNote();
                try {
                    for (Note n1 : s.getListNotes(new String(con.getResponseData()))) {
                        
                        // DeleteButton
                        Button btn = new Button("Delete");
                        Dimension d = new Dimension();
                        d.setWidth(70);
                        d.setHeight(70);
                        btn.setPreferredSize(d);
                        btn.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                
                                Dialog d = new Dialog();
                                
                                if (Dialog.show("Confirmation", "Delete This Note ?", "Ok", null)) {
                                    ConnectionRequest req = new ConnectionRequest();
                                    
                                    req.setUrl("http://localhost/foobar10/web/app_dev.php/note/notes/"
                                            + n1.getId() + "/deleteNote");
                                    
                                    try {
                                        s.getListNotes(new String(con.getResponseData())).remove(n1);
                                        refreshTheme();
                                    } catch (ParseException ex) {
                                        
                                    }
                                    
                                    System.out.println(n1.getId());
                                    
                                    NetworkManager.getInstance().addToQueue(req);
                                    new ListNotes(resourceObjectInstance).removeAll();
                                    new ListNotes(resourceObjectInstance).show();
                                    
                                }
                            }
                        });
                        
                        
                        //UpdateButton
                        Button btn2 = new Button("Edit");
                        btn2.setPreferredSize(d);
                        btn2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                UpdateNote upd = new UpdateNote(resourceObjectInstance, n1);
                                upd.show();
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
                        
                        
                        gui_Label_6.setShowEvenIfBlank(true);
                        
                        addComponent(gui_Container_1);
                        gui_Container_1.setName("Container_1");
                        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2);
                        
                        gui_Container_2.setName("Container_2");
                        gui_Container_2.addComponent(gui_Label_1);
                        
                        gui_Label_1.setText(String.valueOf(n1.getPriority()));
                        gui_Label_1.setUIID("SmallFontLabel");
                        gui_Label_1.setName("Label_1");
                        
                        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Container_4);
                        gui_Container_4.setName("Container_4");
                        ((com.codename1.ui.layouts.FlowLayout)gui_Container_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
                        gui_Container_4.addComponent(gui_Label_4);
                        gui_Label_4.setUIID("Padding2");
                        gui_Label_4.setName("Label_4");
                        gui_Label_4.setIcon(resourceObjectInstance.getImage("label_round.png"));
                        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3);
                        gui_Container_3.setName("Container_3");
                        gui_Container_3.addComponent(gui_Label_3);
                        gui_Container_3.addComponent(gui_Label_2);
                        gui_Container_3.addComponent(btn);
                        gui_Container_3.addComponent(btn2);
                        gui_Label_3.setText(n1.getNoteName());
                        gui_Label_3.setName("Label_3");
                        gui_Label_2.setText(n1.getNoteText());
                        gui_Label_2.setUIID("RedLabel");
                        gui_Label_2.setName("Label_2");
                        gui_Container_2.setName("Container_2");
                        gui_Container_4.setName("Container_4");
                        ((com.codename1.ui.layouts.FlowLayout)gui_Container_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
                        gui_Container_3.setName("Container_3");
                        addComponent(gui_Label_6);
                        gui_Container_1.setName("Container_1");
                        gui_Label_6.setText("");
                        gui_Label_6.setUIID("Separator");
                        gui_Label_6.setName("Label_6");
                        
                    }
                } catch (ParseException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueue(con);
       
    }

}

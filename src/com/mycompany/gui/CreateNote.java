package com.mycompany.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Note;
import com.mycompany.service.ServiceNote;
import com.mycompany.service.ServiceProject;
import java.text.ParseException;
import java.util.List;


public class CreateNote extends BaseForm {

    public CreateNote() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }
    
    
    public CreateNote(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Add Note", "Title")                
                )
        );
        
        installSidemenu(resourceObjectInstance);
        
        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("toolbar-profile-pic.png"), e -> {});
        
    }


    com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    com.codename1.ui.TextField gui_Text_Field_3 = new com.codename1.ui.TextField(); 
    com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField();
    com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField();
    com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
        

    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
         
        
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Sign In");
        setName("SignInForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);

        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        
        
        Label noteName = new Label("Note Name");
        Label priority = new Label("Note Priority");
        Label noteText = new Label("Note Text");
        
        
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(noteName);
        gui_Component_Group_1.addComponent(gui_Text_Field_1);
        gui_Component_Group_1.addComponent(priority);
        gui_Component_Group_1.addComponent(gui_Text_Field_2);
        gui_Component_Group_1.addComponent(noteText);
        gui_Component_Group_1.addComponent(gui_Text_Field_3);
 

        gui_Container_1.addComponent(gui_Button_2);

        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Create Note");
        gui_Button_2.setName("Button_2");
        
        addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Button_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
       
        gui_Button_2.addActionListener((e) -> {
            ServiceNote ser = new ServiceNote();
            Note t = new Note(gui_Text_Field_2.getText(), gui_Text_Field_3.getText(), gui_Text_Field_1.getText());
            ser.createNote(t);
            if (Dialog.show("Confirmation", "Your Note was created","OK",null))
                new ListNotes(resourceObjectInstance).show();

        });
        
    }

     public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
        new ListProjects().show();
    }
}

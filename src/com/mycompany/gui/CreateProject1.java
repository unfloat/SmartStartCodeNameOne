package com.mycompany.gui;

import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.Entite.Project;
import com.mycompany.Entite.ProjectF;
import com.mycompany.service.ServiceProject;


public class CreateProject1 extends BaseForm {

    public CreateProject1() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

    @Override
    protected boolean isCurrentInbox() {
        return true;
    }
    
    
    public CreateProject1(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Add Project", "Title")                
                )
        );
        
        installSidemenu(resourceObjectInstance);
        
        getToolbar().addCommandToRightBar("", resourceObjectInstance.getImage("toolbar-profile-pic.png"), e -> {});
        
    }


    com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField();
    com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField();
    com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
        
     
    
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
         
        
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Sign In");
        setName("SignInForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        
        TextArea projectDescription = new TextArea("",3, 20, TextArea.ANY);
        
        Slider  projectMinB = new Slider();
        projectMinB.setMinValue(100);
        projectMinB.setMaxValue(1000);
        projectMinB.setEditable(true);

        projectMinB.addDataChangedListener(new DataChangedListener() {
            @Override
            public void dataChanged(int type, int index) {
                 System.out.println("vaaal" + projectMinB.getProgress());
            }
        });
       int valMin = projectMinB.getProgress();
       Label valMinLabel = new Label("");
        
        Slider  projectMaxB = new Slider();
        projectMaxB.setMinValue(100);
        projectMaxB.setMaxValue(2000);
        projectMaxB.setEditable(true);

        projectMaxB.addDataChangedListener(new DataChangedListener() {
            @Override
            public void dataChanged(int type, int index) {
                 System.out.println("vaaal" + projectMaxB.getProgress());
            }

        });
        int valMax =projectMaxB.getProgress();
       
         Picker datepiker = new Picker();
         Label projectEndDay = new Label("Deadline");
        
         Label projectName = new Label("Project Name");
         Label projectLocation = new Label("Project Location");
         Label projectDescriptionn = new Label("Project Description");
         Label budget = new Label("Budget");
         
         
         
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(projectName);
        gui_Component_Group_1.addComponent(gui_Text_Field_1);
        gui_Component_Group_1.addComponent(projectLocation);
        gui_Component_Group_1.addComponent(gui_Text_Field_2);
        gui_Component_Group_1.addComponent(projectDescriptionn);
        gui_Component_Group_1.addComponent(projectDescription);
        gui_Component_Group_1.addComponent(budget);
        gui_Component_Group_1.addComponent(projectMinB);
        gui_Component_Group_1.addComponent(projectMaxB);
        gui_Component_Group_1.addComponent(projectEndDay);
        gui_Component_Group_1.addComponent(datepiker);

        gui_Container_1.addComponent(gui_Button_2);

        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Create Project");
        gui_Button_2.setName("Button_2");
        
        addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Button_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
       
        gui_Button_2.addActionListener((e) -> {
            System.out.println(" min = "+projectMinB.getProgress() + " max = " + projectMaxB.getProgress());
            ServiceProject ser = new ServiceProject();
            ProjectF t = new ProjectF(gui_Text_Field_1.getText(), projectMinB.getProgress(), projectDescription.getText(), projectMaxB.getProgress(),gui_Text_Field_2.getText(),"2010-10-26",datepiker.getDate().toString(),"fatma@gmail.com",2,2);
            ser.createProject(t);
            if (Dialog.show("Confirmation", "Your Project was created","OK",null))
                new ListProjects(resourceObjectInstance).show();

        });
        
        
        
        
        
    }

//-- DON'T EDIT ABOVE THIS LINE!!!
    
     public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
        new ListProjects().show();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.bookmark;

import com.mycompany.gui.bid.*;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Bookmark;
import com.mycompany.gui.BaseForm;
import com.mycompany.gui.views.Home;
import com.mycompany.service.BookmarkService;

/**
 *
 * @author asus
 */
public class AddBookmark extends BaseForm {

    TextField textDeliveryTime;
    TextField textMinimalRate;

    Button btnajout, btnaff;
        Resources res;


    public AddBookmark(com.codename1.ui.util.Resources resourceObjectInstance, int projectId) {
     getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Add Project", "Title")                
                )
        ); 
        installSidemenu(resourceObjectInstance);
      
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

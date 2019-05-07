
package com.mycompany.gui.views;


import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 * Base class for the forms with common functionality
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {

    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }
    
    
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res,String s,String a,String z,String x) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("kfan.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        )
        ));
        
        tb.addMaterialCommandToSideMenu("Accueil", FontImage.MATERIAL_UPDATE, e -> new HomeForm(res).show());
        //tb.addMaterialCommandToSideMenu("Mon profil", FontImage.MATERIAL_SETTINGS, e -> new ProfileForm(res,s,a,z,x).show());
        //tb.addMaterialCommandToSideMenu("Livraisons en cours", FontImage.MATERIAL_UPDATE, e -> new LivraisonsEnCours(res,s,a,z,x).show());
        //tb.addMaterialCommandToSideMenu("Livraisons finies", FontImage.MATERIAL_ACCOUNT_BALANCE, e -> new LivraisonsFinies(res,s,a,z,x).show());
        //tb.addMaterialCommandToSideMenu("Se deconnecter", FontImage.MATERIAL_EXIT_TO_APP, e -> new SignInForm(res,s).show());


    }
}

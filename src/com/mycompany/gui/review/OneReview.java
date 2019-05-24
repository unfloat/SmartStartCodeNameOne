/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.review;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Review;
import com.mycompany.service.ServiceReview;

/**
 *
 * @author asus
 */
public class OneReview {
       Form f;

    public OneReview(Resources res,Review r) {
     
        f=new Form();
//      Toolbar tb = new Toolbar(true);
//      setToolbar(tb);
//      super.addSideMenu(res); 
      f.getToolbar().addCommandToRightBar("back", null, (ev)->{AffichageReview h=new AffichageReview();
          h.getF().showBack();
          });
      Label onBudget=new Label("was it on Budget: "+r.getOnBudget());
      Label onTime=new Label("was it on Time: "+r.getOnTime());
      Label rating=new Label("Rating : ");
      SpanLabel comment=new SpanLabel("Comment: "+r.getComment());
      Slider rate= createStarRankSlider();
      if(r.getRating()==1){rate.setProgress(1);}
        if(r.getRating()==2){rate.setProgress(2);}
        if(r.getRating()==3){rate.setProgress(3);}
        if(r.getRating()==4){rate.setProgress(4);}
        if(r.getRating()==5){rate.setProgress(5);}
      Container C=new Container(BoxLayout.y());
      C.add(onBudget);
      C.add(onTime);
      C.add(rating);
      C.add(FlowLayout.encloseCenter(rate));
      C.add(comment);
           
      Button delete=new Button("Delete");
      Button update=new Button("Update");
       Container cont=new Container();
          cont.add(delete);
          cont.add(update);
          C.add(cont);
          f.add(C);
           delete.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                   ServiceReview sp=new ServiceReview();
                   Dialog.show("Delete Review", "Review Deleted with Success ", "Continue", null);
                   sp.deleteReview(r.getId());
                   AffichageReview a=new AffichageReview();
                   a.getF().show();
               }
           });
           
           update.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                   UpdateFormReview up=new UpdateFormReview(r);
                   up.getF().show();
               }
           });
      
       
     
      
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
    }

        public Slider createStarRankSlider() {
    Slider starRank = new Slider();
    starRank.setEditable(false);
    starRank.setMinValue(0);
    starRank.setMaxValue(5);
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(10, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    return starRank;
    }
   
}

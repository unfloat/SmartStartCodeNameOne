/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.review;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
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
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Review;
import com.mycompany.service.ServiceReview;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class AffichageReview {

    Form f;
    //SpanLabel lb;
    private ServiceReview ser = new ServiceReview();
    private ArrayList<Review> reviews = ser.getList2();
    Resources res;
    
    public AffichageReview() {
        f= new Form("Reviews List",BoxLayout.y());
        //f.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeForm h=new HomeForm();
        //  h.getF().showBack();
        //  });
        EncodedImage enc;
        
        for(Review r : reviews)
        {
        try 
        {
           // enc = EncodedImage.create("/project.png");
            
            addReviewButton(r);
        }
        catch (IOException ex) 
        {
        }     
        }
        Button b= new Button("Add a new Review");
        f.add(b);
        b.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               HomeFormReview h=new HomeFormReview();
               
               h.getF().showBack();
           }
       });
    }
    
   private void addReviewButton( Review r) throws IOException {
       EncodedImage enc;
       enc = EncodedImage.create("/logo.png");
       int height = Display.getInstance().convertToPixels(8f);
       int width = Display.getInstance().convertToPixels(8f);
       Button image = new Button(enc.fill(width, height));
       image.setUIID("Label");
       image.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               OneReview or=new OneReview(res,r);
               
               or.getF().show();
           }
       });
       Container cnt = new Container(BoxLayout.x());
       cnt.add(enc);
       cnt.setLeadComponent(image);
       Container cn = new Container(BoxLayout.y());
       
//       TextArea ta = new TextArea(r.getTitle());
//    ta.setUIID("NewsTopLine");
//       ta.setEditable(false);

       SpanLabel description = new SpanLabel(r.getComment(), "Comment:");
       Label rating = new Label("Rating:");
       Slider rate= createStarRankSlider();
        if(r.getRating()==1){rate.setProgress(1);}
        if(r.getRating()==2){rate.setProgress(2);}
        if(r.getRating()==3){rate.setProgress(3);}
        if(r.getRating()==4){rate.setProgress(4);}
        if(r.getRating()==5){rate.setProgress(5);}
       SpanLabel ligne = new SpanLabel("  ");
       Container c = new Container(BoxLayout.x());
       c.add(rating);
       c.add(rate);
       cn.add(c);
       cn.add(description);
       cn.add(ligne);
       cnt.add(cn);
       f.add(cnt);
   }
     
   

//    public Form getF() {
//        return f;
//    }
//
//    public void setF(Form f) {
//        this.f = f;
//    }

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
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
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

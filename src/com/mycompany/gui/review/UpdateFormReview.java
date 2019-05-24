/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.review;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.Entite.Review;
import com.mycompany.service.ServiceReview;

/**
 *
 * @author asus
 */
public class UpdateFormReview {
    Form f;
    Slider rate;
    public UpdateFormReview(Review r) {
        f = new Form("home");
        f.getToolbar().addCommandToRightBar("back", null, (ev)->{AffichageReview h=new AffichageReview();
          h.getF().showBack();
        });
//        onBudget = new TextField("","Was it on budget?");
//        onTime = new TextField("","Was it on time?");
        ComboBox Budget = new ComboBox("Yes","No");  
        ComboBox Time = new ComboBox("Yes","No");  
        Label lbudget= new Label("Was it on Budget?");
        Label ltime= new Label  ("Was it on Time?");
        Label lcomment=new Label("Leave a Comment :");
        Label lrating= new Label("Rating :");
        //ComboBox rating = new ComboBox("1","2","3","4","5"); 
        rate= createStarRankSlider();
        TextField comment = new TextField("","Leave a comment");
        Button btnup = new Button("Update");
        Container cn= new Container(BoxLayout.y());
        if(r.getOnBudget()==true){Budget.setSelectedItem("Yes");}
        if(r.getOnBudget()==false){Budget.setSelectedItem("No");}
        if(r.getOnTime()==true){Time.setSelectedItem("Yes");}
        if(r.getOnTime()==false){Time.setSelectedItem("No");}
        System.out.println(r.getRating());
        if(r.getRating()==1){rate.setProgress(1);}
        if(r.getRating()==2){rate.setProgress(2);}
        if(r.getRating()==3){rate.setProgress(3);}
        if(r.getRating()==4){rate.setProgress(4);}
        if(r.getRating()==5){rate.setProgress(5);}
        comment.setText(r.getComment());
        cn.add(lbudget);
        cn.add(Budget);
        cn.add(ltime);
        cn.add(Time);
        
        cn.add(lrating);
        cn.add(FlowLayout.encloseCenter(rate));
        cn.add(lcomment);
        cn.add(comment);
        cn.add(btnup);
        f.add(cn);
        
        btnup.addActionListener((e) -> {
        ServiceReview sr = new ServiceReview();
        boolean budget = false;
        boolean time = false;
        int rat=0;

        if (Budget.getSelectedItem()=="Yes") {budget=true;}
        if (Time.getSelectedItem()=="Yes") {time=true;}        
//        if (onBudget.getText()=="yes"){ budget=1;}
//        if (onBudget.getText()=="no"){ budget=0;}
//        if (onTime.getText()=="yes"){ time=1;}
//        if (onTime.getText()=="no"){ budget=0;}
        if (rate.getProgress()==1){ rat=1;}
        if (rate.getProgress()==2){ rat=2;}
        if (rate.getProgress()==3){ rat=3;}
        if (rate.getProgress()==4){ rat=4;}
        if (rate.getProgress()==5){ rat=5;}
        
        Review rv = new Review(budget,time,rat,comment.getText());
            System.out.println(r.getId());
        sr.updateReview(rv,r.getId());
        AffichageReview a = new AffichageReview();
        a.getF().show();
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
    starRank.setEditable(true);
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

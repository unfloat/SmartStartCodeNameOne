/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.bookmark;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.service.BookmarkService;
import com.mycompany.Entite.Bid;
import com.mycompany.Entite.Bookmark;
import com.mycompany.Entite.Job;
import com.mycompany.gui.views.Home;
import com.mycompany.gui.job.Show1Job;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author asus
 

*/
public class DisplayBookmarks {

    Resources res;
    Form form;
    ArrayList<Bookmark> listBookmarks = new ArrayList<>();
    ArrayList<Bookmark> listBookmarksSearch = new ArrayList<>();
    TextField search = new TextField("");
    BookmarkService bookmarkService = new BookmarkService();

    public DisplayBookmarks() {
          form = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
        listBookmarks = bookmarkService.displayBookmarks();
        if (listBookmarks.isEmpty()) {
            Label message = new Label("You have no bids.\n Go ahead and create some! ");
            Container container = new Container(new BoxLayout((BoxLayout.Y_AXIS)));
            container.add(message);
            form.add(container);

        }

        Component[] listingsToAdd = new Component[listBookmarks.size()];
        for (int iteration = 0; iteration < listingsToAdd.length; iteration++) {
            Button showBookmark = new Button("Details");
            Bookmark currentBookmark = listBookmarks.get(iteration);
            Container c = new Container(new BoxLayout((BoxLayout.Y_AXIS)));
            //String guid = (String) currentListing.get("seq").toString();
            //Label date = new Label((String) currentBid.getProject().getPublishingDate());
            Label date = new Label("Date added: " + String.valueOf(currentBookmark.getDateAdded()));
            Label projectName = new Label("Project : " + String.valueOf(currentBookmark.getProject().getProjectName()));
            
            //Label projectExpiration = new Label(String.valueOf(currentBid.getProject().getValidityPeriod()));

            c.add(date)
                    .add(projectName)                    
                    .add(showBookmark);
            showBookmark.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ShowBookmark bookmark = new ShowBookmark(res, currentBookmark);
                    bookmark.getF().show();
                }
            });
            listingsToAdd[iteration] = c;
            form.add(listingsToAdd[iteration]);

        }


    }

    public void add(Bookmark bookmark) throws IOException {
        EncodedImage enc = EncodedImage.create("/Job.png");
        int height = Display.getInstance().convertToPixels(8f);
        int width = Display.getInstance().convertToPixels(8f);
        Button image = new Button(enc.fill(width, height));
        Container c2 = new Container(BoxLayout.x());
        c2.add(image);
        c2.setLeadComponent(image);
        image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                ShowBookmark obi = new ShowBookmark(res, bookmark);
                obi.getF().show();
            }
        });

        Container c1 = new Container(BoxLayout.y());
        c2.add(c1);
        Label title = new Label("project id");
        SpanLabel description = new SpanLabel(bookmark.getDateAdded().toString());

        // Label priceMin = new Label(""+o.getPriceMin());
        //Label priceMax = new Label(""+o.getPriceMax());
        //Label type = new Label(o.getType());
        //Label status = new Label(o.getStatus());
        //Label location = new Label(o.getLocation());
        c1.add(title);
        c1.add(description);

        //c1.add(priceMin);
        //c1.add(priceMax);
        //c1.add(type);
        //c1.add(status);
        //c1.add(location);
        form.add(c2);

    }

    public Form getF() {
        return form;
    }

    public void setF(Form f) {
        this.form = f;
    }
}

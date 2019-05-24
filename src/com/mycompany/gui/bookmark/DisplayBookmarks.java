/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.bookmark;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
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
import com.mycompany.gui.bid.DisplayBids;
import com.mycompany.gui.views.Home;
import com.mycompany.gui.job.Show1Job;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author asus
 *
 *
 */
public class DisplayBookmarks {

    Resources res;
    final Form form = new Form("Favourites");

    ArrayList<Bookmark> listBookmarks = new ArrayList<>();
    ArrayList<Bookmark> listBookmarksSearch = new ArrayList<>();
    TextField search = new TextField("");
    BookmarkService bookmarkService = new BookmarkService();
    /**
     * A star image used to mark whether an entry is a favorite
     */
    private Image favoriteUnsel;
    private Image favoriteSel;
    
    /**
     * Helper method to implement the back command to the main form
     * @param f the form from which we would be returning
     */
    private void addBackToHome(Form f) {
        // the back command maps to the physical button on devices other than iPhone and creates the back arrow appearance on iOS
        f.setBackCommand(new Command("PropertyCross") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Home home = new Home(res);
                home.getF().show();
            }
        });
    }

    public DisplayBookmarks(Resources res) {
        form.getToolbar().addCommandToRightBar("Back", null, (ev) -> {
            DisplayBids displayBids = new DisplayBids(res);
            displayBids.getF().show();
        });
        form.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
        favoriteSel = res.getImage("favorite_sel");
        favoriteUnsel = res.getImage("favorite_unsel");
        listBookmarks = bookmarkService.displayBookmarks();
        System.out.println(listBookmarks.toString());
        if (listBookmarks.isEmpty()) {
            form.addComponent(new SpanLabel("You have not added any bookmarks"));

        }
        Component[] listingsToAdd = new Component[listBookmarks.size()];
        for (int iteration = 0; iteration < listingsToAdd.length; iteration++) {
            Button showBookmark = new Button("Details");
            Bookmark currentBookmark = listBookmarks.get(iteration);
            Container c = new Container(new BoxLayout((BoxLayout.Y_AXIS)));
            //String guid = (String) currentListing.get("seq").toString();
            //Label date = new Label((String) currentBid.getProject().getPublishingDate());
            String date = "Date added: " + String.valueOf(currentBookmark.getDateAdded());
            String projectName = "Project : " + String.valueOf(currentBookmark.getProject().getProjectName());
            MultiButton multiButton = new MultiButton();
            multiButton.setTextLine1(projectName);
            multiButton.setTextLine2(date);
            multiButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    ShowBookmark bookmark = new ShowBookmark(res, currentBookmark);
                    bookmark.getF().show();
                }
            });
            form.addComponent(multiButton);

            //Label projectExpiration = new Label(String.valueOf(currentBid.getProject().getValidityPeriod()));
//            c.add(date)
//                    .add(projectName)
//                    .add(showBookmark);
//            showBookmark.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent evt) {
//                    ShowBookmark bookmark = new ShowBookmark(res, currentBookmark);
//                    bookmark.getF().show();
//                }
//            });
//            listingsToAdd[iteration] = c;
//            form.add(listingsToAdd[iteration]);
        }

    }

    public Form getF() {
        return form;
    }


}

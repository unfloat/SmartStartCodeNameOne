package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.bid.DisplayBidders;
import com.mycompany.gui.bid.ListBids;
import com.mycompany.gui.bookmark.ListBookmarks;
import com.mycompany.service.BidService;


public class BaseForm extends Form {
    
    BidService bidService = new BidService();
    public void installSidemenu(Resources res) {
        Image selection = res.getImage("selection-in-sidemenu.png");
        
        Image inboxImage = null;
        if(isCurrentInbox()) inboxImage = selection;

        Image trendingImage = null;
        if(isCurrentTrending()) trendingImage = selection;
        
        Image calendarImage = null;
        if(isCurrentCalendar()) calendarImage = selection;
        
        Image statsImage = null;
        if(isCurrentStats()) statsImage = selection;
        
        Button inboxButton = new Button("List Projects", inboxImage);
        inboxButton.setUIID("SideCommand");
        inboxButton.getAllStyles().setPaddingBottom(0);
        Container inbox = FlowLayout.encloseMiddle(inboxButton, 
                new Label("18", "SideCommandNumber"));
        inbox.setLeadComponent(inboxButton);
        inbox.setUIID("SideCommand");
        inboxButton.addActionListener(e -> new ListProjects().show());
        getToolbar().addComponentToSideMenu(inbox);
        
        Button inboxButton2 = new Button("List Notes", inboxImage);
        inboxButton2.setUIID("SideCommand");
        inboxButton2.getAllStyles().setPaddingBottom(0);
        Container inbox2 = FlowLayout.encloseMiddle(inboxButton2, 
                new Label("18", "SideCommandNumber"));
        inbox2.setLeadComponent(inboxButton2);
        inbox2.setUIID("SideCommand");
        inboxButton2.addActionListener(e -> new ListNotes().show());
        getToolbar().addComponentToSideMenu(inbox2);
        
        Button myBidsButton = new Button("Bids", inboxImage);
        myBidsButton.setUIID("SideCommand");
        myBidsButton.getAllStyles().setPaddingBottom(0);
        Container bidsContainer = FlowLayout.encloseMiddle(myBidsButton, 
                new Label(String.valueOf(bidService.getNumberOfCreatedBids()), "SideCommandNumber"));
        bidsContainer.setLeadComponent(myBidsButton);
        bidsContainer.setUIID("SideCommand");
        myBidsButton.addActionListener(e -> new ListBids(res).show());
        getToolbar().addComponentToSideMenu(bidsContainer);
        
        Button myBookmarksButton = new Button("Bookmarks", inboxImage);
        myBookmarksButton.setUIID("SideCommand");
        myBookmarksButton.getAllStyles().setPaddingBottom(0);
        Container bookmarksContainer = FlowLayout.encloseMiddle(myBookmarksButton, 
                new Label("18", "SideCommandNumber"));
        bookmarksContainer.setLeadComponent(myBookmarksButton);
        bookmarksContainer.setUIID("SideCommand");
        myBookmarksButton.addActionListener(e -> new ListBookmarks(res).show());
        getToolbar().addComponentToSideMenu(bookmarksContainer);
        
        
        Button myReviewsButton = new Button("Reviews", inboxImage);
        myReviewsButton.setUIID("SideCommand");
        myReviewsButton.getAllStyles().setPaddingBottom(0);
        Container reviewsContainer = FlowLayout.encloseMiddle(myReviewsButton, 
                new Label("18", "SideCommandNumber"));
        reviewsContainer.setLeadComponent(myReviewsButton);
        reviewsContainer.setUIID("SideCommand");
        myReviewsButton.addActionListener(e -> new ListBookmarks(res).show());
        getToolbar().addComponentToSideMenu(reviewsContainer);
        
        Button myJobsButton = new Button("Jobs", inboxImage);
        myJobsButton.setUIID("SideCommand");
        myJobsButton.getAllStyles().setPaddingBottom(0);
        Container jobsContainer = FlowLayout.encloseMiddle(myJobsButton, 
                new Label("18", "SideCommandNumber"));
        jobsContainer.setLeadComponent(myJobsButton);
        jobsContainer.setUIID("SideCommand");
        myJobsButton.addActionListener(e -> new ListBookmarks(res).show());
        getToolbar().addComponentToSideMenu(jobsContainer);
        
        Button myBidders = new Button("Bidders", inboxImage);
        myBidders.setUIID("SideCommand");
        myBidders.getAllStyles().setPaddingBottom(0);
        Container biddersContainer = FlowLayout.encloseMiddle(myBidders, 
                new Label("18", "SideCommandNumber"));
        biddersContainer.setLeadComponent(myBidders);
        biddersContainer.setUIID("SideCommand");
        myBidders.addActionListener(e -> new DisplayBidders(res).getF().show());
        getToolbar().addComponentToSideMenu(biddersContainer);
        
        


        getToolbar().addCommandToSideMenu("Calendar", calendarImage, e -> new CalendarForm(res).show());
        getToolbar().addCommandToSideMenu("Log Out", null, e -> new SignInForm(res).show());
        
        // spacer
        getToolbar().addComponentToSideMenu(new Label(" ", "SideCommand"));
        getToolbar().addComponentToSideMenu(new Label(res.getImage("profile_image.png"), "Container"));
        getToolbar().addComponentToSideMenu(new Label("Cloud Temple", "SideCommandNoPad"));
        getToolbar().addComponentToSideMenu(new Label("Tunisia", "SideCommandSmall"));
    }

        
    protected boolean isCurrentInbox() {
        return false;
    }
    
    protected boolean isCurrentTrending() {
        return false;
    }

    protected boolean isCurrentCalendar() {
        return false;
    }

    protected boolean isCurrentStats() {
        return false;
    }
}

package com.aaron.navigator2.views;

import com.aaron.navigator2.ui.AnimalViewer;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Tree;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

/** Main view with a menu (with declarative layout design) */
//@DesignRoot
public class MainView extends HorizontalLayout implements View {
    private static final long serialVersionUID = -3398565663865641952L;

    public static final String NAME = "MBPeT";
//    VerticalLayout menuLayout = new VerticalLayout();
    MBPeTMenu menu;
	final Tree tree;
	final LandingPageView landingPage;
//	ContentView contentView = new ContentView(tree);
	String[] animals = new String[] {"possum", "donkey", "pig", "duck", "dog", "cow", "horse", "cat", "reindeer", "penguin", "sheep", "goat", "tractor cow", "chicken", "bacon", "cheddar"};
//    Panel equalPanel = new Panel("equal panel");

    // Menu navigation button listener
    class ButtonListener implements Button.ClickListener {
        private static final long serialVersionUID = -4941184695301907995L;

        String menuitem;
        public ButtonListener(String menuitem) {
            this.menuitem = menuitem;
        }

        @Override
        public void buttonClick(ClickEvent event) {
            // Navigate to a specific state
        	UI.getCurrent()
        		.getNavigator()
    				.navigateTo(MainView.NAME + "/" + menuitem);
        	//        	navigator.navigateTo(MainView.NAME + "/" + menuitem);
        }
    }
    
    
	public MainView() {
		
		tree = new Tree("Test Cases:");
		landingPage = new LandingPageView(tree);
		
//    	setSpacing(true);
		setSizeFull();
		addStyleName("mainview");
    	
    	// add menu to main view
    	menu = new MBPeTMenu(tree);	//navigator
    	addComponent(menu);
    	setExpandRatio(menu, 1.7f);
    	
    	
    	//Landing Page
    	addComponent(landingPage);	
    	setExpandRatio(landingPage, 8.3f);    	
    	
    }        
    
    
    @Override
    public void enter(ViewChangeEvent event) {
    	// Get the user name from the session
//        String username = String.valueOf(getSession().getAttribute("user"));
//
//        // And pass it to the menu to disaply it
//        Notification.show("welcome: " + username);
        
//        if (event.getParameters() == null
//            || event.getParameters().isEmpty()) {
//          contentView.equalPanel.setContent(
//        		  new Label("Nothing to see here, " +
//        				  "just pass along."));
//            return;
//        } 
    	if (event.getParameters().equals("landingPage")
    			|| event.getParameters() == null || event.getParameters().isEmpty()) {
//            removeComponent(contentView);
        	removeComponent(getComponent(1));	//pageTemplate
            addComponent(landingPage);
            setExpandRatio(landingPage, 8.3f);
            
            markAsDirty();
            
              return;
        } else {
        	removeComponent(getComponent(1));	//contentView
        	try {
        		ContentView contentView = new ContentView(tree);
        		addComponent(contentView);        		
        		setExpandRatio(contentView, 8.3f);

                markAsDirty();

        		// update page title
        		ContentView.setPageTitle(event.getParameters());
        		
//        		contentView.equalPanel.setContent(new AnimalViewer(
//        				event.getParameters()));
        	} catch (RuntimeException e) {
        		getUI().getConnectorTracker().markAllConnectorsDirty(); 
        		getUI().getConnectorTracker().markAllClientSidesUninitialized(); 
        		getUI().getPage().reload();
        	}
        }
    }
  

}

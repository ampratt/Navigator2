package com.aaron.navigator2.views;

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
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

/** Main view with a menu (with declarative layout design) */
//@DesignRoot
public class MainView extends HorizontalLayout implements View {
    private static final long serialVersionUID = -3398565663865641952L;

    public static final String NAME = "main";
    VerticalLayout menuLayout = new VerticalLayout();
	ContentView contentView = new ContentView();
	PageTemplate pageTemplate = new PageTemplate();
	String[] animals = new String[] {"possum", "donkey", "pig", "duck", "dog", "cow", "horse", "cat", "reindeer", "penguin", "sheep", "goat", "tractor cow", "chicken", "bacon", "cheddar"};
//    Navigator navigator;
//    HorizontalLayout menuAndContentLayout = new HorizontalLayout();
//    Panel equalPanel = new Panel("equal panel");
//	VerticalLayout contentLayout = new VerticalLayout();

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
    
    
    @SuppressWarnings("serial")
	public MainView() {		//final Navigator navigator
//    	this.navigator = navigator;

//    	setSpacing(true);
    	setSizeFull();
    	addStyleName("mainview");
    	
    	// add menu to main view
    	MBPeTMenu menu = new MBPeTMenu();	//navigator
    	addComponent(menu);
    	
    	
    	//Landing Page
    	
    	
    	
    	// add content area to main view
//	    	ComponentContainer content = new CssLayout();
//	    	content.addStyleName("view-content");
//	    	content.setSizeFull();
//    	addComponent(content);
//    	setExpandRatio(content, 1.0f);
    	
//    	Component contentLayout = buildContentLayout();
//    	ContentView contentView = new ContentView();
    	addComponent(contentView);	//pageTemplate

        setExpandRatio(menu, 1.7f);
        setExpandRatio(contentView, 8.3f);
        
//    	new MBPeTNavigator(content);
 
    }        
    
    
    @Override
    public void enter(ViewChangeEvent event) {
        if (event.getParameters() == null
            || event.getParameters().isEmpty()) {
          contentView.equalPanel.setContent(
        		  new Label("Nothing to see here, " +
        				  "just pass along."));
            return;
        } else if (event.getParameters() == "landingPage") {
            contentView.equalPanel.setContent(
          		  new Label("This is the Landing Page"));
              return;
        } else {
//        	ContentView content = new ContentView();
        	contentView.equalPanel.setContent(new AnimalViewer(
                    event.getParameters()));
        }
    }

    
//    @Override
//    public void enter(ViewChangeEvent event) {
//        if (event.getParameters() == null
//            || event.getParameters().isEmpty()) {
//        	pageTemplate.setPageTitle("Nothing to see here, just pass along.");
//
//            return;
//        } else {
//        	AnimalViewer str = new AnimalViewer(event.getParameters());
//        	pageTemplate.setPageTitle(str.toString());
//        }
//    }
    
    
    
    
//    public void MenuLayout() {
//    	menuLayout.addStyleName("menu");
//    	
//		Label title = new Label("Layout Demo");
//		title.addStyleName("h1");
//		menuLayout.addComponent(title);
//        
//        // Allow going back to the start
//        logout.addClickListener(new Button.ClickListener() {
//			@Override
//			public void buttonClick(ClickEvent event) {
//				// TODO Auto-generated method stub
//				navigator.navigateTo(LoginView.NAME);					
//			}
//		});
//        menuLayout.addComponent(logout);
//
//        Panel menuPanel = new Panel();
//        VerticalLayout menuButtons = new VerticalLayout();	
//        
//        for (String s : animals) {
//        	String animal = s.substring(0, 1).toUpperCase() + s.substring(1);
//        	menuButtons.addComponent(new Button(animal,
//        			new ButtonListener(s)));
//        }
////        menuButtons.addComponent(new Button("Pig",
////        		new ButtonListener("pig")));
////        menuButtons.addComponent(new Button("Cat",
////        		new ButtonListener("cat")));
////        menuButtons.addComponent(new Button("Dog",      
////        		new ButtonListener("dog")));
////        menuButtons.addComponent(new Button("Reindeer",
////        		new ButtonListener("reindeer")));
////        menuButtons.addComponent(new Button("Penguin",
////        		new ButtonListener("penguin")));
////        menuButtons.addComponent(new Button("Sheep",
////        		new ButtonListener("sheep")));
//        
//        menuPanel.setContent(menuButtons);
//        menuLayout.addComponent(menuPanel);
//        
////        menuAndContentLayout.setSpacing(true);
////        menuAndContentLayout.setSizeFull();
////        addComponent(menuLayout);		//menuAndContentLayout
//////        menuAndContentLayout.addComponent(equalPanel);
////        setExpandRatio(menuLayout, 2);	//menuAndContentLayout
////        menuAndContentLayout.setExpandRatio(equalPanel, 3);
////        addComponent(equalPanel);
////        addComponent(logout);
////        addComponent(menuAndContentLayout);
////        addComponent(menuContent);
//               
//    }
  

}

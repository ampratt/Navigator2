package com.aaron.navigator2.views;

import com.aaron.navigator2.tabs.TabLayout;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;

public class PageTemplate extends VerticalLayout implements View {

	String pageTitle = null;
	Label h1;
	
	public PageTemplate() {
		setSizeFull();
		
		addComponent(buildTopBar());
		
		TabLayout tabs = new TabLayout();
		addComponent(tabs);
		setExpandRatio(tabs, 1);
//		addComponent(buildTabs());
	}
	
	private HorizontalLayout buildTopBar() {
		h1 = new Label();
		//h1.setValue(pageTitle);
		h1.addStyleName("h1");
		
		Button saveButton = new Button("Save settings");
		Button startButton = new Button("Start Test");
		
		HorizontalLayout header = new HorizontalLayout();
		//header.setStyleName("header-padding");
		header.setWidth("100%");
		header.setSpacing(true);
		header.addComponent(h1);
		header.addComponent(saveButton);
		header.addComponent(startButton);
		
		header.setComponentAlignment(h1, Alignment.MIDDLE_LEFT);
		header.setComponentAlignment(saveButton, Alignment.MIDDLE_RIGHT);
		header.setComponentAlignment(startButton, Alignment.MIDDLE_RIGHT);
		header.setExpandRatio(h1, 1);
		header.setExpandRatio(startButton, 0);
		header.setExpandRatio(saveButton, 2);	
		
	    saveButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				//testing purposes
				Notification.show("Your settings will be saved", Type.WARNING_MESSAGE);
			}
		});
	    startButton.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				//testing purposes
				Notification.show("This will launch the test case to the master", Type.WARNING_MESSAGE);
			}
		});		
		
		return header;
	}

	private Component buildTabs() {
		TabLayout tabs = new TabLayout();
		//tabs.getTab(1);
		    
	    return tabs;
	}

	public void setPageTitle(String title){
		pageTitle = title;
		h1.setValue(pageTitle);

	}
	public String getPageTitle() {
		return pageTitle;
	}

    @Override
    public void enter(ViewChangeEvent event) {
        if (event.getParameters() == null
            || event.getParameters().isEmpty()) {
        	setPageTitle("Nothing to see here, just pass along.");
//            equalPanel.setContent(
//                new Label("Nothing to see here, " +
//                          "just pass along."));
            return;
        } else {
        	AnimalViewer str = new AnimalViewer(event.getParameters());
            setPageTitle(str.toString());
        }
    }

	
}

package com.aaron.navigator2.views;


import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;

@SuppressWarnings("serial")
public class LandingPageView extends VerticalLayout {

	public LandingPageView() {
			setSizeFull();
		
	        Label h1 = new Label("Welcome to the MBPet tool for web application performance testing.");
	        h1.addStyleName("h1");
	        addComponent(h1);
	        
	        addComponent(buildActionButtons());
	        

	 }
	
	public HorizontalLayout buildActionButtons() {
		HorizontalLayout hc = new HorizontalLayout();
		hc.setWidth("100%");
		
		hc.addComponent(new Button("create new Test Case", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Notification.show("this will create a new test case", Type.WARNING_MESSAGE);
			}
		}));
		
		return hc;
	}

//    @Override
//    public void enter(ViewChangeEvent event) {
//        if (event.getParameters() == null
//            || event.getParameters().isEmpty()) {
//        	title.setValue("didn't get anything?");
//            equalPanel.setContent(
//                new Label("Nothing to see here, " +
//                          "just pass along."));
//            return;
//        } else {
//        	String str = event.getParameters();
////            setPageTitle(str);
//            title.setValue(str);
//            title.setCaption(str);
//            
//        	System.out.println("EVENTS PARAMS WERE: " + event.getParameters().toString());
//            equalPanel.setContent(new AnimalViewer(
//                event.getParameters()));
//        }
//    }
	 
}

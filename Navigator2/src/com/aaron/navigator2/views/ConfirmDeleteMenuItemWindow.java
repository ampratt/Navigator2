package com.aaron.navigator2.views;

import java.util.Collection;

import com.vaadin.data.Item;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

// Define a sub-window by inheritance
public class ConfirmDeleteMenuItemWindow extends Window {

	@SuppressWarnings("serial")
	public ConfirmDeleteMenuItemWindow(Object target) {
        super("Heads Up!");
        center();
        setResizable(false);
        setClosable(true);
        setModal(true);

        setContent(buildWindowContent(target));
	}
	
        private Component buildWindowContent(final Object target) {
        	// Some basic content for the window
            VerticalLayout vc = new VerticalLayout();
            vc.addComponent(new Label(target.toString() + " has child instances that will be deleted.<br />" +
										"Delete " + target.toString() + " and all its instances?<br /><br />", 
										ContentMode.HTML));
            vc.setMargin(true);
            vc.setSpacing(true);
            setContent(vc);
          
            
            // confirm deletion from menu
            Button delete = new Button("Delete");
            delete.addStyleName("danger");
//            submit.setClickShortcut(KeyCode.ENTER);
            delete.addClickListener(new ClickListener() {
                public void buttonClick(ClickEvent event) {
                	Tree tree = MBPeTMenu.tree;
                	
                	// delete all child items from parent
                	Collection children = tree.getChildren(target);
                	for (Object child : children) {
                		tree.removeItem(child);
                	}    
                	
                	// delete the parent item and navigate to landing page
                	tree.removeItem(target);
                	getUI()
    	            	.getNavigator()
    	            		.navigateTo(MainView.NAME + "/" + "landingPage");
                	close(); 
                }

            });
            
            
            // cancel changes and close window
            Button cancel = new Button("Cancel");
//            addStyleName("danger");
            cancel.addClickListener(new ClickListener() {
                public void buttonClick(ClickEvent event) {
                    close(); // Close the sub-window
                }

            });

            HorizontalLayout buttons = new HorizontalLayout();
            buttons.setWidth("100%");
            buttons.addComponent(cancel);
            buttons.addComponent(delete);
            buttons.setComponentAlignment(cancel, Alignment.MIDDLE_LEFT);
            buttons.setComponentAlignment(delete, Alignment.MIDDLE_RIGHT);
            
            vc.addComponent(buttons);     
            
            return vc;
        }     

}

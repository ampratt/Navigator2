package com.aaron.navigator2.views;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.Navigator;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

public class MBPeTMenu extends CustomComponent{

//	Navigator navigator;
	final Tree tree = new Tree("Test Cases:");
	String[] animals = new String[] {"possum", "donkey", "pig", "duck", "dog", "cow", "horse", "cat", "reindeer", "penguin", "sheep", "goat", "tractor cow", "chicken", "bacon", "cheddar"};
    
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
        	//            navigator.navigateTo(MainView.NAME + "/" + menuitem);
        }
    }
    
	public MBPeTMenu() {
		
		setCompositionRoot(buildContent());
	}
	
	
	public Component buildContent() {
	    VerticalLayout menuLayout = new VerticalLayout();
    	menuLayout.addStyleName("menu");
    	
    	menuLayout.addComponent(buildTitle());
        menuLayout.addComponent(buildUserMenu());
        menuLayout.addComponent(landingPageButton());
        menuLayout.addComponent(buildTreeMenu());
//        menuLayout.addComponent(buildMenuItems());

//        menuButtons.addComponent(new Button("Pig",
//        		new ButtonListener("pig")));
//        menuButtons.addComponent(new Button("Cat",
//        		new ButtonListener("cat")));
//        menuButtons.addComponent(new Button("Dog",      
//        		new ButtonListener("dog")));
//        menuButtons.addComponent(new Button("Reindeer",
//        		new ButtonListener("reindeer")));
//        menuButtons.addComponent(new Button("Penguin",
//        		new ButtonListener("penguin")));
//        menuButtons.addComponent(new Button("Sheep",
//        		new ButtonListener("sheep")));
        
        
//        menuAndContentLayout.setSpacing(true);
//        menuAndContentLayout.setSizeFull();
//        addComponent(menuLayout);		//menuAndContentLayout
////        menuAndContentLayout.addComponent(equalPanel);
//        setExpandRatio(menuLayout, 2);	//menuAndContentLayout
//        menuAndContentLayout.setExpandRatio(equalPanel, 3);
//        addComponent(equalPanel);
//        addComponent(logout);
//        addComponent(menuAndContentLayout);
//        addComponent(menuContent);
        return menuLayout;
               
    }


	private Component landingPageButton() {
		@SuppressWarnings("serial")
		Button button = new Button("Start page", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				UI.getCurrent()
	        		.getNavigator()
	            		.navigateTo(MainView.NAME + "/" + "landingPage");
				
			}
		});
		
		return button;
	}


	private Component buildTitle(){
		VerticalLayout l = new VerticalLayout();
		l.addStyleName("menu-title");
		
		Label title = new Label("MBPeT Design <b>Demo</b>", ContentMode.HTML);
		title.addStyleName("h3");
		title.addStyleName("menu-title");

		l.addComponent(title);
		return l;
	}
	

	@SuppressWarnings("serial")
	private Component buildUserMenu() {
		// Allow going back to the start
	    Button logout = new Button("logout");
	    logout.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				UI.getCurrent()
	                .getNavigator()
	                .navigateTo(
                		LoginView.NAME);	//DashboardViewType.REPORTS.getViewName());
//				navigator.navigateTo(LoginView.NAME);					
			}
		});
		return logout;
	}
	
	
	@SuppressWarnings("serial")
	private VerticalLayout buildTreeMenu() {
		// layout holder for menu items
		VerticalLayout vc = new VerticalLayout();
		vc.setHeight("100%");
		
		// create test case button
		Button createTestCase = new Button("+ (new test case)");
		createTestCase.addStyleName("tiny");
		vc.addComponent(createTestCase);
		
        // button listener
		createTestCase.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
	            // Create new item, set as parent, allow children (= leaf node)
	            final Object[] itemId = new Object[] {"New Item"};
	            String name = (String) itemId[0];
	            tree.addItem(itemId[0].toString());
//	            final Object itemId = tree.addItem();
//	            tree.setParent(itemId, target);
	            tree.setChildrenAllowed(itemId, true);
	 
	            // Set the name for this item (we use it as item caption)
	            final Item item = tree.getItem(itemId);
//	            final Property name = item
//	                    .getItemProperty(ExampleUtil.hw_PROPERTY_NAME);
//	            name.setValue("New Item");

	            // Allow children for the target item, and expand it
//	            tree.setChildrenAllowed(target, true);
//	            tree.expandItem(target);
			}
		});
		
		
		// TREE MENU
		   final Object[][] testCases = new Object[][]{
	    	        new Object[]{"Dashboard", "01.02.15", "03.04.15", "04.05.15", "06.07.15"},
	    	        new Object[]{"Panel", "01.02.15", "03.04.15", "04.05.15", "06.07.15"},
	    	        new Object[]{"Portal", "01.02.15", "03.04.15", "04.05.15", "06.07.15"},
	    	        new Object[]{"demo", "01.02.15", "03.04.15", "04.05.15", "06.07.15"},
	    	        new Object[]{"random", "01.02.15", "03.04.15", "04.05.15", "06.07.15"}
		        };
	    	        
	    	
	    	vc.addComponent(tree);
	//	    	tree.addStyleName("treemenu");
	    	
	    	/* Add test cases as root items in the tree. */
	    	for (int i=0; i<testCases.length; i++) {
	    	    String testCase = (String) (testCases[i][0]);
	    	    tree.addItem(testCase);
	    	    
	    	    if (testCases[i].length == 1) {
	    	        // The test case has no instances so make it a leaf.
	    	        tree.setChildrenAllowed(testCase, true);	//false
	    	    } else {
	    	        // Add children (instances) under the test cases.
	    	        for (int j=1; j<testCases[i].length; j++) {
	    	            String instance = (String) testCases[i][j];
	    	            
	    	            // Add the item as a regular item.
	    	            tree.addItem(instance);
	    	            
	    	            // Set it to be a child.
	    	            tree.setParent(instance, testCase);
	    	            
	    	            // Make the instance look like leaves.
	    	            tree.setChildrenAllowed(instance, false);
	    	        }
	
	    	        // Expand the subtree.
	//	    	        tree.expandItemsRecursively(testCase);
	    	    }
	    	}
	    	
	    	
	        // Contents from a (prefilled example) hierarchical container:
	//	        tree.setContainerDataSource(ExampleUtil.getHardwareContainer());
	 
	        // Add actions (context menu)
	//	        sample.addActionHandler(this);
	 
	        // Cause valueChange immediately when the user selects
	        tree.setImmediate(true);
	 
	        // Set tree to show the 'name' property as caption for items
	//	        sample.setItemCaptionPropertyId(ExampleUtil.hw_PROPERTY_NAME);
	//	        tree.setItemCaptionMode(ItemCaptionMode.PROPERTY);
	 
	        // Expand whole tree
	//	        for (final Object id : tree.rootItemIds()) {
	//	            tree.expandItemsRecursively(id);
	//	        }
	 
	        tree.addItemClickListener(new ItemClickListener() {
				@Override
				public void itemClick(ItemClickEvent event) {
					// TODO Auto-generated method stub
	            	String selected = (String) event.getItemId();
	            	String path = selected;
	            	System.out.println("this is the current ITEM selection: " + selected.toString());
					if (!tree.isRoot(event.getItemId())) {
	//						Object parent = tree.getParent(event.getItemId());
						String parent = (String) tree.getParent(event.getItemId());
		            	System.out.println("Parent is: " + parent);
		            	path = parent+ "/" + selected;
					}
					
					// navigate to corresponding item
					getUI()
		         		.getNavigator()
		         			.navigateTo(MainView.NAME + "/" + 
		     							path);
					// update page title
//					ContentView.setPageTitle(path);
	
	//					Notification.show("Value changed: ",
	//	    	                path,	//event.getItem()),
	//	    	                Type.HUMANIZED_MESSAGE); 
				}
			});
	        
//	        tree.addValueChangeListener(new ValueChangeListener(){
//	            public void valueChange(ValueChangeEvent event){
//	            	// get parent and child
//	            	Object current = event.getProperty();
//	            	System.out.println("this is the current PROPERTY	 selection: " + event.getProperty().toString());
//	            	if(!tree.isRoot(event.getProperty())){
//	            		Object parent = tree.getParent(event.getProperty());
////		            	System.out.println("Parent is: " + tree.getParent(event.getProperty()).toString());
//
//	            	}
//	            }
//         }
//	        );
	    	
			return vc;
		}
	
	private Component buildMenuItems() {
        Panel panel = new Panel();
        VerticalLayout menuButtons = new VerticalLayout();	
        
        for (String s : animals) {
        	String animal = s.substring(0, 1).toUpperCase() + s.substring(1);
        	menuButtons.addComponent(new Button(animal,
        			new ButtonListener(s)));
        }
        panel.setContent(menuButtons);
        return panel;
	}
	

}

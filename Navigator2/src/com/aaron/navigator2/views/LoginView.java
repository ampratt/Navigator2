package com.aaron.navigator2.views;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

/** A start view for navigating to the main view */
public class LoginView extends VerticalLayout  implements View {
    private static final long serialVersionUID = -3398565663865641952L;

    public static final String NAME = "";
    
    public LoginView() {
    	setSpacing(true);
    	setSizeFull();
    	this.addStyleName("login-background-grey");
//    	addComponent(buildContent());
    	
        Component loginForm = buildLoginForm();
        addComponent(loginForm);
        setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);

        // welcome notification
        Notification notification = new Notification(
                "Welcome to Dashboard Demo");
        notification
                .setDescription("<span>This application is not real, it only demonstrates an application built with the <a href=\"https://vaadin.com\">Vaadin framework</a>.</span> <span>No username or password is required, just click the <b>Sign In</b> button to continue.</span>");
        notification.setHtmlContentAllowed(true);
        notification.setStyleName("tray dark small closable login-help");
        notification.setPosition(Position.BOTTOM_CENTER);
        notification.setDelayMsec(20000);
//        notification.show(Page.getCurrent());
    }        
    
    private Component buildContent() {
    	setSizeFull();

        Component loginForm = buildLoginForm();
        addComponent(loginForm);
        setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);

        Notification notification = new Notification(
                "Welcome to the MBPeT Design Demo");
        notification
                .setDescription("<span>This application is not real, it only demonstrates an application built with the <a href=\"https://vaadin.com\">Vaadin framework</a>.</span> <span>No username or password is required, just click the <b>Sign In</b> button to continue.</span>");
        notification.setHtmlContentAllowed(true);
        notification.setStyleName("tray dark small closable login-help");
        notification.setPosition(Position.BOTTOM_CENTER);
        notification.setDelayMsec(20000);
        notification.show(Page.getCurrent());
    	
    	
//        Button button = new Button("Login",
//                new Button.ClickListener() {
//            private static final long serialVersionUID = -1809072471885383781L;
//            @Override
//            public void buttonClick(ClickEvent event) {
//            	UI.getCurrent()
//        			.getNavigator()
//            			.navigateTo(MainView.NAME);
////                navigator.navigateTo(MainView.NAME);
//            }
//        });
//        addComponent(button);
////        setComponentAlignment(button, Alignment.MIDDLE_CENTER);
//        
//        
//        Button registerButton = new Button("create user account",
//                new Button.ClickListener() {
//					@Override
//					public void buttonClick(ClickEvent event) {
//						// TODO Auto-generated method stub
//						UI.getCurrent()
//	        			.getNavigator()
//	            			.navigateTo(RegistrationView.NAME);
////						navigator.navigateTo(RegistrationView.NAME);
//					}
//				});
//   		addComponent(registerButton);
//        setComponentAlignment(registerButton, Alignment.MIDDLE_CENTER);
		return null;
	}
    
    private Component buildLoginForm() {
        final VerticalLayout loginPanel = new VerticalLayout();
        loginPanel.setSizeUndefined();
        loginPanel.setSpacing(true);
        loginPanel.addStyleName("login-panel");

        loginPanel.addComponent(buildLabels());
        loginPanel.addComponent(buildFields());
        loginPanel.addComponent(buildRegistrationFields());
//        loginPanel.addComponent(new CheckBox("Remember me", true));
        return loginPanel;
    }
    


	private Component buildLabels() {
        HorizontalLayout labels = new HorizontalLayout();	//CssLayout
        labels.addStyleName("labels");
        labels.setWidth("100%");

        Label welcome = new Label("Welcome");
        welcome.setSizeUndefined();
        welcome.addStyleName(ValoTheme.LABEL_H4);
        welcome.addStyleName(ValoTheme.LABEL_COLORED);
        labels.addComponent(welcome);
        labels.setComponentAlignment(welcome, Alignment.TOP_LEFT);

        Label title = new Label("MBPeT Dashboard");
        title.setSizeUndefined();
        title.addStyleName(ValoTheme.LABEL_H3);
        title.addStyleName(ValoTheme.LABEL_LIGHT);
        labels.addComponent(title);
        labels.setComponentAlignment(title, Alignment.TOP_RIGHT);
        
        return labels;
    }
    

	private Component buildFields() {
        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.addStyleName("fields");

        final TextField username = new TextField("Username");
        username.setIcon(FontAwesome.USER);
        username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        final PasswordField password = new PasswordField("Password");
        password.setIcon(FontAwesome.LOCK);
        password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        final Button signin = new Button("Sign In");
        signin.addStyleName(ValoTheme.BUTTON_PRIMARY);
        signin.setClickShortcut(KeyCode.ENTER);
        signin.focus();

        fields.addComponents(username, password, signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

        signin.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
            	UI.getCurrent()
    			.getNavigator()
        			.navigateTo(MainView.NAME+ "/" + "landingPage");
//                DashboardEventBus.post(new UserLoginRequestedEvent(username
//                        .getValue(), password.getValue()));
            }
        });
        		
        return fields;
    }


    private Component buildRegistrationFields() {
        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.addStyleName("fields");
        fields.setWidth("100%");
        
        Button registerButton = new Button("Create account",
        		new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						// TODO Auto-generated method stub
						UI.getCurrent()
	        			.getNavigator()
	            			.navigateTo(RegistrationView.NAME);
//						navigator.navigateTo(RegistrationView.NAME);
					}
				});
        registerButton.addStyleName("link");
        fields.addComponent(registerButton);
        fields.setComponentAlignment(registerButton, Alignment.MIDDLE_RIGHT);
 		
 		return fields;
	}
    
    

	@Override
    public void enter(ViewChangeEvent event) {
        Notification.show("Welcome to the MBPeT design demo", 
        			Notification.Type.TRAY_NOTIFICATION);
    }
}

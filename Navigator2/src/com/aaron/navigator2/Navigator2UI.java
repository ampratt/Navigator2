package com.aaron.navigator2;

import javax.servlet.annotation.WebServlet;

import com.aaron.navigator2.views.LoginView;
import com.aaron.navigator2.views.MainView;
import com.aaron.navigator2.views.RegistrationView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("navigator2")
public class Navigator2UI extends UI {

    Navigator  navigator;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Navigator2UI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Navigation2 Example");
        
        // Create a navigator to control the views
        navigator = new Navigator(this, this);
        
        // Create and register the views
        navigator.addView("", new LoginView());	//navigator
        navigator.addView(RegistrationView.NAME, new RegistrationView(navigator));
        navigator.addView(MainView.NAME, new MainView());	//navigator
    }

}
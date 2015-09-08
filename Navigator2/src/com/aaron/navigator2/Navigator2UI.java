package com.aaron.navigator2;

import java.util.logging.Level;

import javax.servlet.annotation.WebServlet;

import com.aaron.navigator2.views.LoginView;
import com.aaron.navigator2.views.MainView;
import com.aaron.navigator2.views.RegistrationView;
import com.aaron.simplelogin.SimpleLoginMainView;
import com.aaron.simplelogin.SimpleLoginView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ClientConnector;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ConnectorTracker;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("navigator2")
public class Navigator2UI extends UI {
	private static final long serialVersionUID = -4150121950677547344L;
	
	public static final String PERSISTENCE_UNIT = "Navigator2";
//	Navigator  navigator;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Navigator2UI.class)
	public static class Servlet extends VaadinServlet {
		private static final long serialVersionUID = 304105821396553550L;
	}

	@Override
    protected void init(VaadinRequest request) {           
        addDetachListener(new DetachListener() {
			private static final long serialVersionUID = -7633078219228421931L;

			@Override
            public void detach(DetachEvent event) {
                releaseResources();
            }
        });
        
        getPage().setTitle("Navigation2 Example");
        
		//
        // Create a new instance of the navigator. The navigator will attach
        // itself automatically to this view.
        //
        new Navigator(this, this);
        
        // Create and register the views

        getNavigator().addView(LoginView.NAME, new LoginView());	//("", new LoginView());
        getNavigator().addView(MainView.NAME, new MainView());	//navigator
        getNavigator().addView(RegistrationView.NAME, new RegistrationView());
        
        //
        // We use a view change handler to ensure the user is always redirected
        // to the login view if the user is not logged in.
        //
        getNavigator().addViewChangeListener(new ViewChangeListener() {
            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {

                // Check if a user has logged in
                boolean isLoggedIn = getSession().getAttribute("user") != null;
                boolean isLoginView = event.getNewView() instanceof LoginView;

                if (!isLoggedIn && !isLoginView) {
                    // Redirect to login view always if a user has not yet
                    // logged in
                    getNavigator().navigateTo(LoginView.NAME);
                    return false;

                } else if (isLoggedIn && isLoginView) {
                    // If someone tries to access to login view while logged in,
                    // then cancel
                    return false;
                }

                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {

            }
        });
    }
	
    private void releaseResources() {
        // Redirect this page immediately
        getPage().setLocation("/");	//"/MBPeT"
        
        // Close the session
        getSession().close();
    }
	
	  private ConnectorTracker tracker;

	  @Override
	  public ConnectorTracker getConnectorTracker() {
	    if (this.tracker == null) {
	      this.tracker =  new ConnectorTracker(this) {
			private static final long serialVersionUID = -2456104393122612400L;

			@Override
	        public void registerConnector(ClientConnector connector) {
	          try {
	            super.registerConnector(connector);
	          } catch (RuntimeException e) {
	            getLogger().log(Level.SEVERE, "FAILED CONNECTOR: {0}", connector.getClass().getSimpleName());
	            System.out.println("FAILED CONNECTOR: {" + connector.getConnectorId() + "} " 
	            					+ connector.getClass().getSimpleName());
	            throw e;
	          }
	        }

	      };
	    }

	    return tracker;
	  }

}
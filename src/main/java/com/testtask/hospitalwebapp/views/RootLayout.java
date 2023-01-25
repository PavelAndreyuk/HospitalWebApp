package com.testtask.hospitalwebapp.views;

import com.testtask.hospitalwebapp.services.SessionService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

public class RootLayout extends AppLayout implements RouterLayout {

    private final SessionService sessionService;

    private final Span titleSpan = new Span();

    public RootLayout(SessionService sessionService) {
        this.sessionService = sessionService;
        initMenu();
        initUserNameSpan();
    }

    private void initMenu() {
        DrawerToggle toggle = new DrawerToggle();
        VerticalLayout tabs = new VerticalLayout();

        tabs.add(initRouterLink("Home", HomePage.class));
        tabs.add(initRouterLink("Patients", PatientsPage.class));
        tabs.add(initRouterLink("Doctors", DoctorsPage.class));
        tabs.add(initRouterLink("Recipes", RecipesPage.class));

        addToDrawer(tabs);
        addToNavbar(toggle);
    }

    private RouterLink initRouterLink(String caption, Class<? extends Component> target) {
        RouterLink link = new RouterLink(caption, target);
        link.addClassName("router-link");
        return link;
    }

    private void initUserNameSpan() {
        HorizontalLayout hl = new HorizontalLayout();
        hl.setAlignItems(FlexComponent.Alignment.CENTER);

        Span userNameSpan = null;
        if (sessionService.getUser() != null) {
            userNameSpan = new Span(sessionService.getUser().getName());
            userNameSpan.addClassName("userNameSpan");
        }

        createLogoutContextMenu(userNameSpan);

        HorizontalLayout wrapper = new HorizontalLayout(userNameSpan);
        wrapper.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        wrapper.setAlignItems(FlexComponent.Alignment.CENTER);

        titleSpan.addClassName("titleSpan");

        hl.setWidthFull();
        hl.expand(wrapper);
        hl.add(titleSpan, wrapper);
        hl.addClassName("appBar");
        addToNavbar(hl);
    }

    private void createLogoutContextMenu(Span userName) {
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.addItem("Logout", event -> logout());
        contextMenu.setOpenOnClick(true);
        contextMenu.setTarget(userName);
    }

    public void logout() {
        sessionService.logout();
        UI.getCurrent().navigate("login");
    }
}

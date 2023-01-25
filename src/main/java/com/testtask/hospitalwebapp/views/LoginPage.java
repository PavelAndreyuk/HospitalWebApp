package com.testtask.hospitalwebapp.views;

import com.testtask.hospitalwebapp.models.User;
import com.testtask.hospitalwebapp.models.UserRole;
import com.testtask.hospitalwebapp.services.SessionService;
import com.testtask.hospitalwebapp.services.UserService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Route("login")
public class LoginPage extends HorizontalLayout {

    private final UserService userService;
    private final SessionService sessionService;

    private final TextField loginField = new TextField();

    @Autowired
    public LoginPage(UserService userService,
                     SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
        displayLogin();
    }

    private void displayLogin() {
        setHeightFull();
        setAlignItems(Alignment.CENTER);

        loginField.setPrefixComponent(VaadinIcon.USER.create());
        loginField.focus();
        VerticalLayout loginLayout = new VerticalLayout();

        loginLayout.setAlignItems(Alignment.CENTER);
        loginLayout.add(new Span("Enter a login:"), loginField);

        Button loginButton = new Button("Login", event -> login(loginField.getValue()));
        loginButton.addClickShortcut(Key.ENTER);

        loginLayout.add(loginButton);

        add(loginLayout);
    }

    public void login(String login) {
        log.warn("User " + login + " is trying to enter");
        User user = userService.get(login);

        if (user == null) {
            if (login.equals("admin")) {
                user = new User("admin", UserRole.ADMINISTRATOR);
            } else {
                user = new User(login, UserRole.CLIENT);
            }

            userService.save(user);
        }

        try {
            sessionService.login(user);
            UI.getCurrent().navigate(HomePage.class);
        } catch (Exception e) {
            log.error("Failed to login", e);
        }
    }
}

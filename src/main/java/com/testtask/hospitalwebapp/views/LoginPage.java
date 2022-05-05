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
    private TextField loginTextField;

    @Autowired
    public LoginPage(UserService userService,
                     SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
        displayLogin();
    }

    private void displayLogin() {
        setHeight("100%");
        setAlignItems(Alignment.CENTER);

        loginTextField = new TextField();
        loginTextField.setPrefixComponent(VaadinIcon.USER.create());
        loginTextField.focus();
        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.setAlignItems(Alignment.CENTER);
        verticalLayout.add(new Span("Enter a login:"));
        verticalLayout.add(loginTextField);
        Button loginButton = new Button("Login", event -> login(loginTextField.getValue()));
        loginButton.addClickShortcut(Key.ENTER);
        verticalLayout.add(loginButton);

        add(verticalLayout);
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

package com.testtask.hospitalwebapp;

import com.testtask.hospitalwebapp.annotations.Permissions;
import com.testtask.hospitalwebapp.models.UserRole;
import com.testtask.hospitalwebapp.services.SessionService;
import com.testtask.hospitalwebapp.views.HomePage;
import com.testtask.hospitalwebapp.views.LoginPage;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.shared.communication.PushMode;
import com.vaadin.flow.shared.ui.Transport;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
public class ServiceInitListener implements VaadinServiceInitListener {
    private final SessionService sessionService;

    @Autowired
    public ServiceInitListener(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::beforeEnter);
            ui.getPushConfiguration().setPushMode(PushMode.AUTOMATIC);
            ui.getPushConfiguration().setTransport(Transport.WEBSOCKET_XHR);
        });
    }

    private void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        Class<?> target = beforeEnterEvent.getNavigationTarget();
        if (target == null) {
            return;
        }

        if (sessionService.getUser() == null) {
            if (LoginPage.class.isAssignableFrom(target)) {
                // Open some pages for unauthorized user
                return;
            }

            beforeEnterEvent.forwardTo(LoginPage.class);
            return;
        } else {
            if (LoginPage.class.isAssignableFrom(target)) {
                beforeEnterEvent.forwardTo(HomePage.class);
                return;
            }

            // Check authorized user
            Permissions annotation = target.getAnnotation(Permissions.class);
            String[] permissions = null;
            if (annotation != null) {
                permissions = annotation.value();
            }
            if (permissions != null) {
                if (!accessToPage(target)) {
                    Notification.show("Permission denied");
                    beforeEnterEvent.forwardTo(HomePage.class);
                }
            }
        }
    }

    private boolean accessToPage(Class<?> pageClass) {
        //handle situation when class have no @Permissions
        if (pageClass.getAnnotation(Permissions.class) == null) return true;

        String[] requiredPermissions = pageClass.getAnnotation(Permissions.class).value();
        UserRole role = sessionService.getUser().getRole();
        for (String requiredPermission : requiredPermissions) {
            if (!role.has(requiredPermission)) {
                return false;
            }
        }
        return true;
    }
}
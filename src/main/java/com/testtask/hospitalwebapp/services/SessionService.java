package com.testtask.hospitalwebapp.services;

import com.testtask.hospitalwebapp.models.User;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.VaadinSessionState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@Slf4j
public class SessionService {

    public void login(@NotNull User user) {
        if (user == null) {
            throw new NullPointerException("User must have a value");
        }
        VaadinSession.getCurrent().setAttribute("user", user);
    }

    public void logout() {
        VaadinSession session = VaadinSession.getCurrent();
        session.setAttribute("user", null);
        session.getSession().invalidate();

        VaadinSessionState sessionState = session.getState();
        if (sessionState == VaadinSessionState.OPEN) {
            try {
                if (session.getSession() != null) {
                    session.getSession().invalidate();
                }
            } catch (Throwable t) {
                log.warn("Error invalidating session.getSession(): " + t.getMessage(), t);
            }
            try {
                session.close();
            } catch (Throwable t) {
                log.warn("Error closing session: " + t.getMessage(), t);
            }
        }
    }

    @Nullable
    public User getUser() {
        return (User) VaadinSession.getCurrent().getAttribute("user");
    }
}
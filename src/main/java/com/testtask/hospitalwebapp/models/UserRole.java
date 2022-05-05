package com.testtask.hospitalwebapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.compress.utils.Sets;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum UserRole {
    CLIENT(Sets.newHashSet("patients")),
    ADMINISTRATOR(Sets.newHashSet("patients", "doctors", "recipes"));

    private final Set<String> permissions;

    public boolean has(String permission) {
        return permissions.contains(permission);
    }
}

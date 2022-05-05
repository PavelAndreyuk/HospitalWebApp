package com.testtask.hospitalwebapp.annotations;

import com.vaadin.flow.component.dependency.CssImport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@CssImport("/main.css")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SharedStyles {
}

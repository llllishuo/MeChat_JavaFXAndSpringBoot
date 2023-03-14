package com.example.UI.common;


import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class FXMLLoaderFactory implements Callback<Class<?>, Object> {
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Object call(Class<?> clazz) {
        Object controller = applicationContext.getBean(clazz);
        if (controller == null) {
            try {
                controller = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return controller;
    }
}
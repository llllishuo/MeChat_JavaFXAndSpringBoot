package com.example.UI.service;

import javafx.scene.Parent;
import org.springframework.context.ApplicationContext;

public interface FxmlService {
//    public void FxmlServiceImpl(ApplicationContext applicationContext);

    <T extends Parent> T getByPath(String path);
//    public <T extends Parent> T getByPath(String path);
}

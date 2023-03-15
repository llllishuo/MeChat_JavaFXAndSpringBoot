package com.example.UI.service.impl;

import com.example.UI.service.FxmlService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FxmlServiceImpl implements FxmlService {


    @Getter
    private final ApplicationContext applicationContext;

    public FxmlServiceImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }



    @Override
    public <T extends Parent> T getByPath(String path){
        try {
            FXMLLoader loader = new FXMLLoader(new ClassPathResource(path).getURL());
            //获取在fxml中的指定的controller（将controller纳入了spring）
            loader.setControllerFactory(clazz -> applicationContext.getBean(clazz));
            return loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

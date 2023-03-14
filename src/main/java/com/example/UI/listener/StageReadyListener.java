package com.example.UI.listener;

import com.example.UI.event.StageReadyEvent;
import com.example.UI.service.FxmlService;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class StageReadyListener implements ApplicationListener<StageReadyEvent> {

    private final FxmlService fxmlService;

    public StageReadyListener(FxmlService fxmlService) {
        this.fxmlService = fxmlService;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        Parent parent = fxmlService.getByPath("/view/login.fxml");
        Scene scene=new Scene(parent);
        stage.setScene(scene);
//        stage.setResizable(false);
        stage.setTitle("meChat");
        stage.sizeToScene();
        stage.show();
    }
}
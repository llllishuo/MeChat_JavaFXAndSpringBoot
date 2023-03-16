package com.example;

import com.example.UI.service.ViewDto;
import com.example.UI.view.DataDisplayView;
import com.example.UI.view.LoginView;
import com.example.UI.view.LogonView;
import com.example.UI.view.MainView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class MainApplication extends AbstractJavaFxApplicationSupport {


    //启动窗口
    public static void main(String[] args) {
        launch(MainApplication.class, LoginView.class, new CustomSplashScreen(), args);
    }
    @Override
    public void start(Stage stage) throws Exception {

        super.start(stage);
    }

    //图标
    @Override
    public Collection<Image> loadDefaultIcons() {

        return Collections.singletonList(new Image(this.getClass().getClassLoader().getResource("image/logo01.png").toExternalForm()));
    }

}




package com.example;

import com.example.UI.view.LoginView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class MainApplication extends AbstractJavaFxApplicationSupport {
    //    public static void main(String[] args) {
//        Application.launch(JavaFxApplication.class,args);
//    }
    public static void main(String[] args) {
        launch(MainApplication.class, LoginView.class, new CustomSplashScreen(), args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);
    }

    @Override
    public Collection<Image> loadDefaultIcons() {
        return Collections.singletonList(new Image(this.getClass().getClassLoader().getResource("image/logo01.png").toExternalForm()));
    }
//    @Override
//    public List<Image> loadDefaultIcons() {
//        return Collections.singletonList(new Image(Objects.requireNonNull(this.getClass().getClassLoader().getResource("/image/Dune.gif")).toExternalForm()));
//    }

}




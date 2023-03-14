package com.example;

import com.example.UI.controller.MainViewController;
import com.example.UI.event.StageReadyEvent;
import com.example.UI.view.MainView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.net.SocketException;
import java.net.URL;
import java.util.function.Consumer;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        Application.launch(JavaFxApplication.class,args);
    }

}




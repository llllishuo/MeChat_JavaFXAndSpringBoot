package com.example.UI.controller;

import com.example.MainApplication;
import com.example.UI.service.SendDataService;
import com.example.UI.view.DataDisplayView;
import com.example.UI.view.MainView;
import com.example.server.controller.UserController;
import com.example.server.entity.User;
import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

@FXMLController
@Slf4j
public class DataDisplayController implements Initializable {

    private Stage stage;
    @FXML
    private ImageView headImage;

    @FXML
    private Label name;

    @FXML
    private Label username;

    @FXML
    private TextArea sing;

    @FXML
    private Button resume;

    @FXML
    private Button update;

    @Autowired
    private DataDisplayView dataDisplayView;

    @Autowired
    private SendDataService<User> displayDataService;



    User data;


    @Autowired
    private UserController userController;

    @FXML
    private Button back;

    @FXML
    void backClicked(MouseEvent event) {
        log.info("back."+String.valueOf(displayDataService.getData()));
        MainApplication.showView(MainView.class);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        data = displayDataService.getData();
        log.info("initialize."+String.valueOf(displayDataService.getData()));
        headImage.setImage(new Image(data.getHeadImg()));
        name.setText(data.getName());
        if (data.getIsLogin() == 1){
            name.setTextFill(Paint.valueOf("#00FF00"));
        }
        username.setText(data.getUsername());
        sing.setText(data.getSign());

    }
    /**
     * 启动初始化组件
     */
    void init() {
        Platform.runLater(() -> {
            log.info("init."+String.valueOf(displayDataService.getData()));
            Parent parent = dataDisplayView.getView();
            DataDisplayController.this.stage = (Stage) parent.getScene().getWindow();
            stage.setResizable(false);

        });
    }


    public void resumeClicked(MouseEvent mouseEvent) {
        sing.setText(data.getSign());
    }

    public void updateClicked(MouseEvent mouseEvent) {
        String singText = sing.getText();
        if(Objects.equals(data.getSign(), singText)){
            return;
        }
        data.setSign(singText);
        userController.updateSing(data);

    }
}

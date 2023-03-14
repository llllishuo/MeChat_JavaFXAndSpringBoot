package com.example.UI.controller;

import com.example.UI.service.SendDataService;
import com.example.UI.view.MainView;
import com.example.server.controller.UserController;
import com.example.server.entity.User;
import com.example.server.service.UserService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

@FXMLController
@Slf4j
public class DataDisplayController implements Initializable {

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
    private MainView mainView;

    @Autowired
    private SendDataService<User> displayDataService;
    @Setter
    private User user;

    @Autowired
    private UserController userController;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = userController.getUserById(1);
        log.info(String.valueOf(user));
        headImage.setImage(new Image(user.getHeadImg()));
        name.setText(user.getName());
        username.setText(user.getUsername());
        sing.setText(user.getSign());


    }


    public void resumeClicked(MouseEvent mouseEvent) {
        sing.setText(user.getSign());
    }

    public void updateClicked(MouseEvent mouseEvent) {
        String singText = sing.getText();
        if(Objects.equals(user.getSign(), singText)){
            return;
        }
        user.setSign(singText);
        log.info(user.getSign());
        userController.updateSing(user);

    }
}

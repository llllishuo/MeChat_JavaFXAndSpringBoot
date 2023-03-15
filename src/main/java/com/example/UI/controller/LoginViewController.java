package com.example.UI.controller;


import com.example.UI.service.SendUser;
import com.example.UI.view.LogonView;
import com.example.UI.view.MainView;
import com.example.server.common.R;
import com.example.server.controller.LoginController;
import com.example.server.entity.User;
import com.example.server.utils.BeanUtils;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

@FXMLController
@Slf4j
@Scope("prototype")
public class LoginViewController implements Initializable {

    @Autowired
    private ApplicationContext logonContext;


    @Autowired
    private MainView mainView;


//    @Autowired
    private LogonView logonView = BeanUtils.getBean(LogonView.class);

    @FXML
    private AnchorPane loginMain;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField username;

    @FXML
    private Label usernameLabel;

    @FXML
    private PasswordField password;

    @FXML
    private Button logon;

    @FXML
    private Button login;

    @FXML
    private ImageView topImg;

    @FXML
    private Label tips;

    @Setter
    private String CPMMsg;

    @Autowired
    private LoginController loginController;

    @Autowired
    private MainViewController mainViewController;

    @Autowired
    private SendUser sendUser;


    @Getter
    private User user;

    private Scene scene;
    private Parent view;


    @FXML
    void loginClicked(MouseEvent event) throws IOException {
        R data = loginController.login(username.getText(), password.getText());
        if (data.getCode() == 1) {
            if (data.getData() == null) {
                return;
            }
            sendUser.setUserId((Integer) data.getData());
            log.info(String.valueOf(sendUser.getUserId()));
            submit((Integer) data.getData());
//            Parent parent = mainView.getView();
//            scene =  parent.getScene();
//            MainViewController.stage = (Stage) parent.getScene().getWindow();
//            sendUser.setUserId((Integer) data.getData());
//            mainViewController.setUserId((Integer) data.getData());
            return;
        }
        CPM(data.getMsg());
    }

    public void CPM(String msg) {
        Stage msgBox = new Stage();
        Group msgGroup = new Group();
        Scene scene1 = new Scene(msgGroup, 200, 60);
        Label label = new Label(msg);
        label.setLayoutX(200 / 2 - msg.length() * 5);
        label.setLayoutY(60 / 2);
        msgGroup.getChildren().add(label);
        msgBox.setScene(scene1);
        msgBox.show();
    }

    public void submit(int id) throws IOException {
        // 创建一个新的Stage
        // 创建一个新的Stage
        Stage newStage = (Stage) login.getScene().getWindow();
        // 使用FXMLLoader从FXML文件加载新窗口的场景
        Parent parent = mainView.getView();
        scene = parent.getScene();

        // 在新窗口中设置场景
        newStage.setScene(new Scene(parent));
        // 显示新窗口
        newStage.show();


    }


    /**
     * 初始化
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void logonClicked(MouseEvent mouseEvent) throws IOException {
//        Stage close = (Stage) logon.getScene().getWindow();
//        close.close();

        // 创建一个新的Stage
        Stage newStage = (Stage) login.getScene().getWindow();

        // 使用FXMLLoader从FXML文件加载新窗口的场景




        view = logonView.getView();

        scene = new Scene(view);
        log.info(String.valueOf(view.getStyle().getClass()));
        log.info(String.valueOf(newStage.getScene()));
        newStage.setScene(scene);

        // 在新窗口中设置场景
//        newStage.setScene(new Scene(view));
        // 显示新窗口
        newStage.show();

    }


}

package com.example.UI.controller;

import com.example.UI.view.LoginView;
import com.example.server.controller.FileUploadController;
import com.example.server.controller.LoginController;
import com.example.server.entity.Login;
import com.example.server.entity.User;
import com.example.server.entity.dto.LoginAndUser;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.UUID;

@FXMLController
@Slf4j
public class LogonViewController implements Initializable {
    @FXML
    private ImageView headImage;

    @FXML
    private TextField name;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField enderPassword;

    @FXML
    private Button submitHeadImage;

    @FXML
    private Button submit;

    @FXML
    private Button back;

    @Autowired
    private FileUploadController fileUploadController;

    @Autowired
    private LoginController loginController;

    private File selectedFile;

    @Autowired
    private LoginView loginView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    @FXML
    void backClicked(MouseEvent event) throws IOException {
//        Stage close = (Stage) back.getScene().getWindow();
//        close.close();
        // 创建一个新的Stage
        Stage newStage =(Stage) back.getScene().getWindow();;
        // 使用FXMLLoader从FXML文件加载新窗口的场景
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
        Parent root = loader.load();
        // 在新窗口中设置场景
        newStage.setScene(new Scene(root));
        // 显示新窗口
        newStage.show();
    }
    @FXML
    public void submitClicked(MouseEvent event) throws IOException {
        if(selectedFile==null){
            CPM("请添加头像");
            return;
        }
        uploadImage(selectedFile);
        LoginAndUser loginAndUser = new LoginAndUser();
        User user = new User();
        user.setName(name.getText());
//        user.setHeadImg(fileUploadController.getResourcesURL());
        user.setHeadImg("image/headImage.png");
        user.setUsername(username.getText());
        user.setSign("");


        Login login = new Login();
        login.setUsername(username.getText());
        login.setPassword(password.getText());
        loginAndUser.setUser(user);
        loginAndUser.setLogin(login);

        loginController.updateLoginAndUser(loginAndUser);

        Stage newStage = (Stage) back.getScene().getWindow();;
        // 使用FXMLLoader从FXML文件加载新窗口的场景
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
        Parent root = loader.load();
        // 在新窗口中设置场景
        newStage.setScene(new Scene(root));
        // 显示新窗口
        newStage.show();
    }

    public void CPM(String msg){
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
    @FXML
    public void handleSelectImage(ActionEvent actionEvent) {
        String uuid= UUID.randomUUID().toString();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
//        fileChooser.setInitialFileName(uuid);
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            headImage.setImage(image);
        }
    }

    private void uploadImage(File file) {
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            ByteArrayResource resource = new ByteArrayResource(bytes) {
                @Override
                public String getFilename() {
                    return file.getName();
                }
            };
            body.add("file", resource);
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            String serverUrl = "http://localhost:8848/upload";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, requestEntity, String.class);
//            log.info(fileUploadController.getResourcesURL());
//            log.info(response.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

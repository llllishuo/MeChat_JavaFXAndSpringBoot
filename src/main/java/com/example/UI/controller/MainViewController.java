package com.example.UI.controller;


import com.example.UI.service.SendDataService;
import com.example.UI.service.SendUser;
import com.example.UI.view.DataDisplayView;
import com.example.UI.view.MainView;
import com.example.server.controller.TalkController;
import com.example.server.controller.UserController;
import com.example.server.entity.Talk;
import com.example.server.entity.User;
import com.example.server.entity.dto.FriendListDto;
import de.felixroske.jfxsupport.FXMLController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


@FXMLController
@Slf4j
public class MainViewController implements Initializable {


    @Getter
    @Setter
    static Stage stage;

    @Getter
    Scene scene;

    @Autowired
    private MainView mainView;

    @Autowired
    private DataDisplayView dataDisplayView;

    //主要布局
    @FXML
    private FlowPane main;

    //左侧菜单栏
    @FXML
    private VBox menu;

    //头像
    @FXML
    private ImageView myHeadImage;

    //朋友圈容器
    @FXML
    private VBox blogBox;

    //朋友圈图标
    @FXML
    private ImageView blogImage;

    //好友列表滚动条容器
    @FXML
    private ScrollPane friendListScrollPane;

    //好友列表容器
    @FXML
    private VBox friendList;

    //好友单元
    @FXML
    private HBox friendItem;


    //好友头像
    @FXML
    private ImageView friendItemHeadImage;

    //好友数据容器
    @FXML
    private VBox friendData;

    @FXML
    private HBox friendNewMsgAndNewTime;

    //好友名字文本布局
    @FXML
    private TextFlow friendNameTextFlow;


    //好友名字
    @FXML
    private Label friendName;

    //消息时间布局
    @FXML
    private TextFlow newsTimeTextFlow;


    //消息时间
    @FXML
    private Label newsTime;


    //最新消息布局
    @FXML
    private TextFlow friendNewsTextFlow;

    //好友最新消息
    @FXML
    private Label friendNews;


    //会话布局
    @FXML
    private VBox dialogBox;

    //会话框滚动条布局
    @FXML
    private ScrollPane dialogScrollPane;

    //会话列表布局
    @FXML
    private VBox dialogList;


    //会话单位
    @FXML
    private HBox dialogItem;


    //好友头像
    @FXML
    private ImageView dialogHeadImage;

    //好友消息
    @FXML
    private VBox dialogMsgBox;


    //消息单元
    @FXML
    private TextFlow msgItem;


    //消息发送的内容
    @FXML
    private Label msgText;

    //消息发送的时间布局
    @FXML
    private TextFlow msgTime;

    //消息发送得到时间
    @FXML
    private Label msgTimeText;

    //会话菜单
    @FXML
    private FlowPane dialogMenu;

    //发送按钮
    @FXML
    private Button sendMsg;

    //文本输入框
    @FXML
    private TextArea msgInput;


    //提示信息
    @FXML
    private Label tips;


    //调用服务层接口
    @Autowired
    private UserController userController;

    @Autowired
    private TalkController talkController;

    @Autowired
    private SendUser sendUser;

    @Autowired
    private SendDataService<User> displayDataService;


    private FlowPane[] flowPane = {new FlowPane(), new FlowPane(), new FlowPane()};
    @FXML
    private TextFlow textFlow;
    @FXML
    private Text text;


    @Setter
    private User user;


    @Setter
    private int userId;

    /**
     * 初始化
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userId = sendUser.getUserId();
        log.info(String.valueOf(userId));
//        userId = 1;
        log.info(String.valueOf(userId));
        user = userController.getUserById(userId);
        log.info(String.valueOf(userId));
        tips = new Label();
        tips.setText("正在进行初始化...");
//        user = userController.getUserById(1);
        myHeadImage.setImage(new Image(user.getHeadImg()));
        myHeadImage.setFitHeight(45);
        myHeadImage.setFitWidth(45);
        myHeadImage.setOnMouseClicked((e) -> {
            try {
                dataDisplayClicked(user);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        //获取好友列表
        getFriendList(user);
        //初始化会话容器
        getDialogBox();

    }

    public void dataDisplayClicked(User data) throws IOException {
        displayDataService.setData(data);
        // 创建一个新的Stage
        Stage newStage = new Stage();
        // 使用FXMLLoader从FXML文件加载新窗口的场景
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dataDisplay.fxml"));
        Parent root = loader.load();

        // 在新窗口中设置场景
        newStage.setScene(new Scene(root));
        // 显示新窗口
        newStage.show();
/*        stage.setOnCloseRequest((e)->{

            Parent root = scene.getRoot();
            root.getChildrenUnmodifiable().remove(scene);
        });*/

    }

    //根据用户与好友id创建对话容器
    private void getDialogBox(User user, FriendListDto friend, List<Talk> talkList) {
        if (dialogBox.getChildren().size() == 3) {
            for (int i = 0; i < 3; i++) {
                dialogBox.getChildren().remove(flowPane[i]);
            }

        }
        if (dialogBox.getChildren().size() == 4) {
            dialogBox.getChildren().remove(dialogScrollPane);

            dialogBox.getChildren().remove(dialogMenu);
            dialogBox.getChildren().remove(msgInput);
            dialogBox.getChildren().remove(tips);

        }

        dialogBox.getChildren().removeAll();

//        log.info(String.valueOf(talkList));
        dialogBox.setMinWidth(479);
        dialogBox.setMinHeight(469);

        dialogScrollPane = new ScrollPane();
        dialogScrollPane.setMinWidth(479);
        dialogScrollPane.setMinHeight(340);
        dialogScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        slowScrollToBottom(dialogScrollPane);
        dialogList = new VBox();
        dialogList.setMinWidth(479);
        dialogList.setMinHeight(340);

        talkList.forEach((item) -> {
            dialogItem = new HBox();
            dialogItem.setMinSize(479, 69);
            dialogItem.setAlignment(Pos.CENTER);
            dialogItem.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);


            dialogHeadImage = new ImageView(friend.getHeadImg());
            dialogHeadImage.setFitHeight(47);
            dialogHeadImage.setFitWidth(47);
            if (Objects.equals(item.getFromId(), user.getId())) {
                dialogHeadImage.setImage(new Image(user.getHeadImg()));
                dialogItem.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            }
            dialogHeadImage.setOnMouseClicked((e) -> {
                try {
                    dataDisplayClicked((Objects.equals(item.getFromId(), user.getId())) ? user : userController.getUserById(friend.getId()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            dialogMsgBox = new VBox();
            dialogMsgBox.setMinHeight(69);
            dialogMsgBox.setMinWidth(394);

            msgItem = new TextFlow();
            msgItem.setMaxSize(394, 56);
            msgText = new Label();
            msgText.setText(item.getContent());
            msgText.setMaxSize(394, 56);
            msgText.setMinSize(394, 56);
            msgText.setWrapText(true);

            msgTime = new TextFlow();
            msgTime.setMaxSize(394, 13);

            msgTimeText = new Label();
            msgTimeText.setText(item.getSendTime());


            dialogItem.getChildren().add(dialogHeadImage);
            dialogItem.getChildren().add(dialogMsgBox);
            dialogMsgBox.getChildren().add(msgItem);
            msgItem.getChildren().add(msgText);
            dialogMsgBox.getChildren().add(msgTime);
            msgTime.getChildren().add(msgTimeText);
            dialogList.getChildren().add(dialogItem);
            dialogList.getChildren().add(new Separator());
        });

        dialogMenu = new FlowPane();
        dialogMenu.setMinWidth(479);
        dialogMenu.setMinHeight(25);

        msgInput = new TextArea();
        msgInput.setMinWidth(479);
        msgInput.setMinHeight(90);
        msgInput.setWrapText(true);

        //发送信息
        msgInput.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                sendMsg(user.getId(), friend.getId(), msgInput.getText(), 0);
                List<Talk> msgListById = talkController.getMsgListById(user.getId(), friend.getId());
                getDialogBox(user, friend, msgListById);
            }
        });


        tips.setMinWidth(479);
        tips.setMinHeight(15);


        dialogBox.getChildren().add(dialogScrollPane);
        dialogScrollPane.setContent(dialogList);
        dialogBox.getChildren().add(dialogMenu);
        dialogBox.getChildren().add(msgInput);
        dialogBox.getChildren().add(tips);

    }

    //滚动条自动滑倒底部
    static void slowScrollToBottom(ScrollPane scrollPane) {
        Animation animation = new Timeline(
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(scrollPane.vvalueProperty(), 20)));
        animation.play();
    }

    //发送信息
    private void sendMsg(int fromId, Integer toId, String text, int isGroup) {
        talkController.sendTalk(fromId, toId, text, isGroup);
    }

    //初始化会话容器
    private void getDialogBox() {

        dialogBox.setMinWidth(479);
        dialogBox.setMinHeight(469);

        for (int i = 0; i < 3; i++) {
            FlowPane fp = flowPane[i];
            fp.setMinSize(479, 469 / 3);
            fp.setAlignment(Pos.CENTER);
            textFlow = new TextFlow();
            text = new Text("请选择一名好友开启对话");
            textFlow.getChildren().add(text);
            fp.getChildren().add(textFlow);
            dialogBox.getChildren().add(fp);
        }


    }


    //获取好友列表
    public void getFriendList(User user) {
        //创建好友列表对象
        List<FriendListDto> friendList = null;
        try {
            //调用业务逻辑查询用户的好友
            friendList = userController.getFriendList(user.getId());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //遍历显示每个好友单元
        for (FriendListDto item : friendList) {
            tips.setText("正在载入...");
            loadFriendListData(item.getHeadImg(), item.getName(), item.getSendTime(), item.getContent());
            //添加鼠标单击事件
            friendItem.setOnMouseClicked(event -> {

                if (dialogBox.getChildren().size() > 0) {
                    dialogBox.getChildren().remove(dialogScrollPane);
                    dialogBox.getChildren().remove(dialogMenu);
                    dialogBox.getChildren().remove(msgInput);
                    dialogBox.getChildren().remove(tips);
                }

                List<Talk> talkList = talkController.getMsgListById(user.getId(), item.getId());
                getDialogBox(user, item, talkList);
            });

        }

        tips.setText("好友加载完成...");
    }

    //创建每个好友单元
    public void loadFriendListData(String headImageUrl, String Name, String time, String news) {
        //初始化好友单元容器
        friendItem = new HBox();
        friendItem.setMinHeight(44);
        friendItem.setMinWidth(149);

        //初始化好友头像组件
        friendItemHeadImage = new ImageView();
        friendItemHeadImage.setFitWidth(34);
        friendItemHeadImage.setFitHeight(34);
        friendItemHeadImage.setImage(new Image(headImageUrl));

        //初始化好友数据容器
        friendData = new VBox();
        friendData.setMinWidth(111);
        friendData.setMinHeight(40);


        //初始化好友名字容器
        friendName = new Label();
        friendName.setFont(Font.font(18));
        friendName.setText(Name);
        friendNameTextFlow = new TextFlow(friendName);
        friendNameTextFlow.setMinWidth(111);
        friendNameTextFlow.setMinSize(68, 15);
        friendNameTextFlow.setMinHeight(31);


        //初始化好友消息和时间容器
        friendNewMsgAndNewTime = new HBox();
        friendNewMsgAndNewTime.setMinWidth(111);
        friendNewMsgAndNewTime.setMinHeight(18);

        //初始化好友最新消息
        friendNews = new Label();
        friendNews.setText(news);
        friendNews.setFont(Font.font(10));
        friendNewsTextFlow = new TextFlow(friendNews);
        friendNewsTextFlow.setMinWidth(68);
        friendNewsTextFlow.setMinHeight(18);

        //初始化最新消息时间
        newsTime = new Label();
        newsTime.setText(time);
        newsTime.setFont(Font.font(9));
        newsTimeTextFlow = new TextFlow(newsTime);
        newsTimeTextFlow.setTextAlignment(TextAlignment.RIGHT);
        newsTimeTextFlow.setMaxWidth(43);
        newsTimeTextFlow.setMinHeight(15);


        //添加分隔符
        friendList.getChildren().add(new Separator());

        //将各组件依次添加到好友列表主容器种
        friendData.getChildren().add(friendNameTextFlow);
        friendData.getChildren().add(friendNewMsgAndNewTime);

        friendNewMsgAndNewTime.getChildren().add(friendNewsTextFlow);
        friendNewMsgAndNewTime.getChildren().add(newsTime);

        friendItem.getChildren().add(friendItemHeadImage);
        friendItem.getChildren().add(friendData);
        friendList.getChildren().add(friendItem);
//            friendList.getChildren().add(new Separator());

    }


}

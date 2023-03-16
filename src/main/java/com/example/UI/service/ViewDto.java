package com.example.UI.service;

import com.example.UI.view.DataDisplayView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ViewDto {
    private Scene dataDisplay;
    private Scene login;
    private Scene logon;
    private Scene main;
//    public ViewDto(Scene dataDisplay,Scene login,Scene logon,Scene main){
//        this.dataDisplay= dataDisplay
//    }
}

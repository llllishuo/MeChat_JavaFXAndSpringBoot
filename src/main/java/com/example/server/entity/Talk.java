package com.example.server.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Talk implements Serializable {
    private Integer Id;
    private Integer fromId;
    private Integer toId;
    private String content;
    private String sendTime;
    private Integer isGroup;
}

package com.example.server.entity.dto;


import lombok.Data;

@Data
public class FriendListDto {
    private Integer id;
    private String content;
    private String sendTime;
    private String name;
    private String headImg;

}

package com.example.server.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Friend implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer friendId;
    private String startTime;
    private String overTime;

    private Integer isDelete;
}

package com.example.server.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Blog implements Serializable {
    private Integer blogId;
    private Integer userId;
    private String blogContent;
    private String foundTime;
}

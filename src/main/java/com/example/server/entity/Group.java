package com.example.server.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class Group implements Serializable {
    private Integer id;
    private String groupName;
    private Integer memberId;
    private Integer groupId;

}

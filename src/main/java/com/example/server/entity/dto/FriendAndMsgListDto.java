package com.example.server.entity.dto;

import com.example.server.entity.Friend;
import com.example.server.entity.Talk;
import lombok.Data;

import java.util.List;

@Data
public class FriendAndMsgListDto {
    private Friend friend;
    private List<Talk> talks;
}
